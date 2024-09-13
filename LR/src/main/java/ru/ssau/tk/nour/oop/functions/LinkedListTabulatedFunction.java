package ru.ssau.tk.nour.oop.functions;

public class LinkedListTabulatedFunction implements TabulatedFunction{
    private FunctionNode head;
    private FunctionNode last_search;
    private int last_search_index;
    private int pointsCount;

    private void checkIndex(int index) throws FunctionPointIndexOutOfBoundsException {
        if (index > pointsCount - 1 || index < 0)
            throw new FunctionPointIndexOutOfBoundsException();
    }

    private FunctionNode getNodeByIndex(int index) {
        FunctionNode current_node = head.getNext();
        int cur_index = 0;

        if (Math.abs(last_search_index - index) < index) {
            current_node = last_search;
            cur_index = last_search_index;
        }

        while (cur_index != index){
            if(index<cur_index) {
                current_node = current_node.getPrev();
                cur_index--;
            }
            else{
                current_node = current_node.getNext();
                cur_index++;
            }
        }

        last_search = current_node;
        last_search_index = index;

        return current_node;
    }

    private FunctionNode addNodeToTail() {
        FunctionNode new_node = new FunctionNode(head.getPrev(), head);
        head.getPrev().setNext(new_node);
        head.setPrev(new_node);

        return new_node;
    }

    private FunctionNode addNodeByIndex(int index) {
        FunctionNode current_node = getNodeByIndex(index);
        last_search_index = 0;
        last_search = head.getNext();

        FunctionNode new_node = new FunctionNode(current_node.getPrev(), current_node);

        new_node.getPrev().setNext(new_node);
        new_node.getNext().setPrev(new_node);

        return new_node;
    }

    private FunctionNode deleteNodeByIndex(int index) {
        FunctionNode current_node = getNodeByIndex(index);
        current_node.getPrev().setNext(current_node.getNext());
        current_node.getNext().setPrev(current_node.getPrev());

        last_search_index = 0;
        last_search = head.getNext();

        return current_node;
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException {
        if (leftX >= rightX || pointsCount < 2)
            throw new IllegalArgumentException();

        head = new FunctionNode(null, null);
        head.setNext(head);
        head.setPrev(head);

        this.pointsCount = pointsCount;
        double step = (rightX - leftX) / (pointsCount - 1);

        int i;
        double current_step;
        for (i = 0, current_step = leftX; i < pointsCount; i++) {
            addNodeToTail().setData(new FunctionPoint(current_step, 0));
            current_step += step;
        }
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException {
        if (leftX >= rightX || values.length < 2)
            throw new IllegalArgumentException();

        head = new FunctionNode(null, null);
        head.setNext(head);
        head.setPrev(head);

        this.pointsCount = values.length;
        double step = (rightX - leftX) / (pointsCount - 1);

        int i;
        double current_step;
        for (i = 0, current_step = leftX; i < pointsCount; i++) {
            addNodeToTail().setData(new FunctionPoint(current_step, values[i]));
            current_step += step;
        }
    }

    public double getLeftDomainBorder() {
        return head.getNext().getData().getX();
    }

    public double getRightDomainBorder() {
        return head.getPrev().getData().getX();
    }

    public double getFunctionValue(double x) {
        double answer = Double.NaN;

        if (x >= getLeftDomainBorder() && x <= getRightDomainBorder()) {
            FunctionPoint next_point = getPoint(1);
            FunctionPoint prev_point = getPoint(0);
            for (int i = 0; i < pointsCount; i++) {
                if (getPoint(i).getX() >= x && x != getLeftDomainBorder()) {
                    next_point = getPoint(i);
                    prev_point = getPoint(i - 1);
                    break;
                }
            }
            answer = ((x - prev_point.getX()) / (next_point.getX() - prev_point.getX())
                    * (next_point.getY() - prev_point.getY()) + prev_point.getY());
        }
        return answer;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        return this.getNodeByIndex(index).getData();
    }

    public void setPoint(int index, FunctionPoint point) throws FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException {
        checkIndex(index);

        // Точка за левой границей
        if (point.getX() < head.getNext().getData().getX())
            throw new InappropriateFunctionPointException();

        // Точка за правой границей
        if (point.getX() > head.getPrev().getData().getX())
            throw new InappropriateFunctionPointException();

        getNodeByIndex(index).setData(point);
    }

    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        return getNodeByIndex(index).getData().getX();
    }

    public void setPointX(int index, double x) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        getNodeByIndex(index).getData().setX(x);
    }

    public double getPointY(int index) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        return getNodeByIndex(index).getData().getY();
    }

    public void setPointY(int index, double y) throws FunctionPointIndexOutOfBoundsException {
        checkIndex(index);
        getNodeByIndex(index).getData().setY(y);
    }

    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, IllegalStateException {
        checkIndex(index);

        if (pointsCount < 3)
            throw new IllegalStateException();

        deleteNodeByIndex(index);
        pointsCount--;
    }

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        int index_insert = getPointsCount();
        for (int i = 0; i < pointsCount; i++) {
            if (getNodeByIndex(i).getData().getX() >= point.getX()) {
                if (getNodeByIndex(i).getData().getX() == point.getX())
                    throw new InappropriateFunctionPointException();

                index_insert = i;
                break;
            }
        }

        addNodeByIndex(index_insert).setData(point);
        pointsCount++;
    }
}
