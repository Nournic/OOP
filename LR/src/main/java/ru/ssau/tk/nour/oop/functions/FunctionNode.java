package ru.ssau.tk.nour.oop.functions;

import java.io.Serializable;

public class FunctionNode implements Serializable {
    private FunctionPoint data;
    private FunctionNode next;
    private FunctionNode prev;

    public FunctionPoint getData() {
        return data;
    }

    public FunctionNode(FunctionNode prev, FunctionNode next) {
        this.prev = prev;
        this.next = next;
    }

    public FunctionNode getPrev() {
        return prev;
    }

    public FunctionNode getNext() {
        return next;
    }

    public void setPrev(FunctionNode prev) {
        this.prev = prev;
    }

    public void setNext(FunctionNode next) {
        this.next = next;
    }

    public void setData(FunctionPoint data) {
        this.data = data;
    }

}
