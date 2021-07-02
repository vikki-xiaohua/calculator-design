package com.sezzle.calculator;

import com.sezzle.calculator.arithmetic.Arithmetic;
import com.sezzle.calculator.exception.UnsupportedOperatorInputException;
import com.sezzle.calculator.factory.OperatorFactory;
import com.sezzle.calculator.util.Constants;
import lombok.extern.java.Log;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Stack;

@Log
public class EvaluateService {
    private static ThreadLocal<DecimalFormat> numberFormatter = ThreadLocal.withInitial(() -> new DecimalFormat(Constants.DECIMAL_DOUBLE_FORMAT));

    public double evaluate(String expression) {
        log.info("Expression: " + expression);
        if (expression == null || expression.isBlank()) return Double.MIN_VALUE;

        try {
            double evaluateResult = doEvaluate(expression);
            return Double.parseDouble(numberFormatter.get().format(evaluateResult));
        } catch (Exception e) {
            log.severe("Evaluate expression exception: " + Arrays.toString(e.getStackTrace()));
            return Double.MIN_VALUE;
        }
    }

    private double doEvaluate(String expression) {
        Stack<Double> evaluateStack = new Stack<>();
        int expressionLength = expression.length();
        double newNumber;
        char operator = '+';

        for (int i = 0; i < expressionLength; i++) {
            StringBuilder sb = new StringBuilder();

            while (i < expressionLength - 1 && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == Constants.PERIOD_SEPARATOR)) {
                sb.append(expression.charAt(i));
                i++;
            }

            char currentChar = expression.charAt(i);
            if (i == expressionLength - 1) sb.append(currentChar);

            double previousNumber = Double.parseDouble(sb.toString());
            log.info("PreviousNumber: " + previousNumber);

            if ((!Character.isDigit(currentChar)) || i == expressionLength - 1) {
                newNumber = doCalculate(evaluateStack, operator, previousNumber);
                evaluateStack.push(newNumber);
                operator = currentChar;
            }
        }

        double evaluateResult = 0;
        while (!evaluateStack.isEmpty()) {
            evaluateResult += evaluateStack.pop();
        }

        log.info("EvaluateResult: " + evaluateResult);

        return evaluateResult;
    }

    private double doCalculate(Stack<Double> evaluateStack, char operator, double previousNumber) {
        double newNumber;
        Arithmetic arithmetic = OperatorFactory.createOperator(operator);

        switch (operator) {
            case Constants.OPERATOR_ADDITION:
            case Constants.OPERATOR_SUBTRACTION:
                newNumber = arithmetic.calculate(0, previousNumber);
                break;
            case Constants.OPERATOR_MULTIPLICATION:
            case Constants.OPERATOR_DIVISION:
                newNumber = arithmetic.calculate(evaluateStack.pop(), previousNumber);
                break;
            default:
                throw new UnsupportedOperatorInputException("Unsupported operator: " + operator);
        }

        log.info("newNumber: " + newNumber);

        return newNumber;
    }
}

