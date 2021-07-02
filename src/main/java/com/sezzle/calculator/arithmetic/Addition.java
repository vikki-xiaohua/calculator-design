package com.sezzle.calculator.arithmetic;

public class Addition implements Arithmetic {
    @Override
    public double calculate(double leftOperand, double rightOperand) {
        return leftOperand + rightOperand;
    }

}