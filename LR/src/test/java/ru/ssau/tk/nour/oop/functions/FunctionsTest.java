package ru.ssau.tk.nour.oop.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.nour.oop.functions.basic.Exp;

class FunctionsTest {
    @Test
    void integrateTest(){
        Function function = new Exp();
        System.out.println(Functions.integrate(function,0,1,0.001));

    }
}
