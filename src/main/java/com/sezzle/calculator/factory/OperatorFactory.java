package com.sezzle.calculator.factory;

import com.sezzle.calculator.arithmetic.*;
import com.sezzle.calculator.util.Constants;

import java.util.Map;
import java.util.function.Supplier;

public class OperatorFactory {
    public static final Map<Character, Supplier<Arithmetic>> operatorFactoryMap =
            Map.of(Constants.OPERATOR_ADDITION, Addition::new, Constants.OPERATOR_SUBTRACTION, Subtraction::new,
                    Constants.OPERATOR_MULTIPLICATION, Multiplication::new, Constants.OPERATOR_DIVISION, Division::new);

    public static Arithmetic createOperator(char action) {
        return operatorFactoryMap.get(action).get();
    }

}
