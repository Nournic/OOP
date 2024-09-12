package ru.ssau.tk.nour.oop.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FunctionPointTest {
    FunctionPoint functionPoint;

    @BeforeEach
    void functionPointConstructorTest(){
        functionPoint = new FunctionPoint(5,4);
    }


    @Test
    void getX() {
        double expected = 5;
        Assertions.assertEquals(expected, functionPoint.getX());
    }

    @Test
    void getY() {
        double expected = 4;
        Assertions.assertEquals(expected, functionPoint.getY());
    }

}