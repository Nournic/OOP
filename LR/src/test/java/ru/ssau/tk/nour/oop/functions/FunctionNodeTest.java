package ru.ssau.tk.nour.oop.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FunctionNodeTest {
    FunctionNode functionNode;
    FunctionNode prevNode = new FunctionNode(null, functionNode);
    FunctionNode nextNode = new FunctionNode(functionNode, null);

    @BeforeEach
    void init() {
        functionNode = new FunctionNode(prevNode, nextNode);
    }

    @Test
    void getData() {
        FunctionPoint expect = new FunctionPoint(5, 10);
        functionNode.setData(expect);

        Assertions.assertEquals(expect, functionNode.getData());
    }

    @Test
    void getPrev() {
        Assertions.assertEquals(prevNode, functionNode.getPrev());
        Assertions.assertNotEquals(prevNode, functionNode.getNext());
    }

    @Test
    void getNext() {
        Assertions.assertEquals(nextNode, functionNode.getNext());
        Assertions.assertNotEquals(nextNode, functionNode.getPrev());
    }

    @Test
    void setData() {
        FunctionPoint expect = new FunctionPoint(5, 10);
        functionNode.setData(expect);

        Assertions.assertEquals(expect, functionNode.getData());
    }
}