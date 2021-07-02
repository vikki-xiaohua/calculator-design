package com.sezzle.calculator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EvaluateServiceTest {
    private static EvaluateService service;

    @BeforeAll
    public static void setUp() {
        service = new EvaluateService();
    }

    @AfterAll
    public static void tearDown() {
        service = null;
    }

    @Test
    public void test_normal_integer_expression_work_case() {
        Assertions.assertEquals(-27.00, service.evaluate("2-6-7*8/2+5"));
    }

    @Test
    public void test_normal_double_expression_work_case() {
        Assertions.assertEquals(3.4, service.evaluate("13.6/2-3.4"));
    }


    @Test
    public void test_special_expression_work_case() {
        Assertions.assertEquals(0, service.evaluate("0"));
        Assertions.assertEquals(0.8, service.evaluate(".8"));
        Assertions.assertEquals(5, service.evaluate("5."));
    }

    @Test
    public void test_single_number_work_case() {
        Assertions.assertEquals(0.8, service.evaluate("0.8"));
        Assertions.assertEquals(5, service.evaluate("5"));
    }


    @Test
    public void test_null_or_blank_expression_case() {
        Assertions.assertEquals(Double.MIN_VALUE, service.evaluate(null));
        Assertions.assertEquals(Double.MIN_VALUE, service.evaluate(" "));
    }

    @Test
    public void test_unsupported_operator_case() {
        Assertions.assertEquals(Double.MIN_VALUE, service.evaluate("1^2"));
    }

    @Test
    public void test_invalid_number_format_case() {
        Assertions.assertEquals(Double.MIN_VALUE, service.evaluate("."));
    }

    @Test
    public void test_formatted_double_result_case() {
        Assertions.assertEquals(3.33333, service.evaluate("10/3"));
    }

}
