package sample;

public abstract class Check {

    private int[][] checkMatrix;

    public Check(int[][] checkMatrix){
        this.checkMatrix = checkMatrix;
    }

   public int[][] getCheckMatrix(){
        return checkMatrix;
   }

   public void setCheckMatrix(int[][] matrix){
        this.checkMatrix = matrix;
   }
   public abstract boolean check();
}

