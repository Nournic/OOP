class MyFirstClass {
    public static void main(String[] s) {
        MySecondClass o = new MySecondClass(0,0);
        int i, j;
        for (i = 1; i <= 8; i++) {
            for (j = 1; j <= 8; j++) {
                o.setI(i);
                o.setJ(j);
                System.out.print(o.amplify());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

class MySecondClass {
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
