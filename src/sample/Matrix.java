package sample;

public class Matrix {


    private int[][] matrix5 = {
            {1,1,3,5,5},
            {4,2,5,3,1},
            {4,3,3,5,4},
            {5,1,2,3,3},
            {3,3,4,1,1}
    };
    private int[][] matrix6 = {
            {3,4,3,1,5,5},
            {1,4,3,2,1,4},
            {4,1,2,6,3,5},
            {1,2,2,3,3,1},
            {2,3,4,6,1,6},
            {4,6,2,4,2,6}
    };
    private int[][] matrix7 = {
            {5,6,6,2,2,5,3},
            {3,7,6,5,4,4,2},
            {1,5,3,7,3,2,1},
            {1,2,3,4,3,6,6},
            {3,4,1,7,7,3,5},
            {5,3,7,1,2,6,1},
            {3,1,7,3,4,4,7}
    };


    public int[][] getMatrix5() {
        return matrix5;
    }

    public void setMatrix5(int[][] matrix5) {
        this.matrix5 = matrix5;
    }

    public int[][] getMatrix6() {
        return matrix6;
    }

    public void setMatrix6(int[][] matrix6) {
        this.matrix6 = matrix6;
    }

    public int[][] getMatrix7() {
        return matrix7;
    }

    public void setMatrix7(int[][] matrix7) {
        this.matrix7 = matrix7;
    }
}
