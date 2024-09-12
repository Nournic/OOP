package ru.ssau.tk.nour.oop.functions;

public class LinkedListTabulatedFunction {
    private FunctionNode head;
    private FunctionNode last_search;
    private int last_search_index;
    private int pointsCount;

    private FunctionNode getNodeByIndex(int index){
        FunctionNode current_node = head.getNext();
        int cur_index = 0;

        if(last_search_index - index < index) {
            current_node = last_search;
            cur_index = last_search_index;
        }

        while(cur_index++ != index)
            current_node=current_node.getNext();

        last_search = current_node;
        last_search_index = cur_index;

        return current_node;
    }

    private FunctionNode addNodeToTail(){
        FunctionNode new_node = new FunctionNode(head.getPrev(),head);
        head.getPrev().setNext(new_node);
        head.setPrev(new_node);

        return new_node;
    }

    private FunctionNode addNodeByIndex(int index){
        FunctionNode current_node = getNodeByIndex(index);
        FunctionNode new_node = new FunctionNode(current_node.getPrev(), current_node.getNext());

        new_node.getPrev().setNext(new_node);
        new_node.getNext().setPrev(new_node);

        return new_node;
    }

    private FunctionNode deleteNodeByIndex(int index){
        FunctionNode current_node = getNodeByIndex(index);
        current_node.getPrev().setNext(current_node.getNext());
        current_node.getNext().setPrev(current_node.getPrev());

        return current_node;
    }
}
