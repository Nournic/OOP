package ru.ssau.tk.nour.oop.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TabulatedFunctionTest {
    TabulatedFunction tabulatedFunctionArray;
    TabulatedFunction tabulatedFunctionPointsCount;

    @BeforeEach
    void init(){
        double[] values = {1,2,4,8,16,32,64,128,256};
        tabulatedFunctionArray = new TabulatedFunction(-5.0, 5.0, values);
        tabulatedFunctionPointsCount = new TabulatedFunction(-5.0, 5.0, 9);
    }
    @Test
    void getLeftDomainBorder() {
        double expected = -5.0;

        Assertions.assertEquals(expected, tabulatedFunctionArray.getLeftDomainBorder());
        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getLeftDomainBorder());
    }

    @Test
    void getRightDomainBorder() {
        double expected = 5.0;

        Assertions.assertEquals(expected, tabulatedFunctionArray.getRightDomainBorder());
        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getRightDomainBorder());
    }

    @Test
    void getFunctionValueArray() {
        double[] expected = {1,2,4,8,16,32,64,128,256};

        double step = 10.0 / 8.0;
        double current_step = -5.0;
        for (double v : expected) {
            Assertions.assertEquals(v, tabulatedFunctionArray.getFunctionValue(current_step));
            current_step += step;
        }

        Assertions.assertEquals(Double.NaN, tabulatedFunctionArray.getFunctionValue(-999));
        Assertions.assertEquals(Double.NaN, tabulatedFunctionArray.getFunctionValue(999));
    }

    @Test
    void getFunctionValuePointsCount() {
        double expected = 0;

        Assertions.assertEquals(expected, tabulatedFunctionPointsCount.getFunctionValue(0));
        Assertions.assertEquals(Double.NaN, tabulatedFunctionPointsCount.getFunctionValue(-999));
        Assertions.assertEquals(Double.NaN, tabulatedFunctionPointsCount.getFunctionValue(999));
    }
}