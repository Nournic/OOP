package myfirstpackage;

public class MySecondClass {
    private int i, j;

    public MySecondClass(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int amplify() {
        return i * j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
