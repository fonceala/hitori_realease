package sample;

import sample.Check;

public class SingleComponentChecker extends Check {


    public SingleComponentChecker(int[][] matrix){
        super(matrix);
    }

    @Override
    public boolean check() {
        int[][] m = super.getCheckMatrix();
        if(numberOfComponents(m) == 1){
            return true;
        }
       return false;
    }

    public boolean safe(int[][] matrix,int row, int col, boolean[][] visited){
        return (row >= 0) && (row < matrix.length) && (col >= 0) && (col < matrix.length) && (matrix[row][col] != 0 && !visited[row][col]);
    }

    public void search(int[][] matrix, int row, int col, boolean[][] visited){
        int rowN[] = new int[] {-1,-1,-1,0,0,1,1,1};
        int colN[] = new int[] {-1,0,1,-1,1,-1,0,1};

        visited[row][col] = true;

        for(int k = 0; k < 8; ++k){
            if(safe(matrix,row + rowN[k], col + colN[k], visited)){
                search(matrix,row + rowN[k], col + colN[k], visited);
            }
        }
    }

    public int numberOfComponents(int[][] matrix){
        boolean[][] visited = new boolean[matrix.length][matrix.length];

        int number = 0;
        for(int i = 0; i < matrix.length; ++i){
            for(int j = 0; j < matrix.length; ++j){
                if(matrix[i][j] != 0 && !visited[i][j]) {
                    search(matrix, i, j, visited);
                    ++number;
                }
            }
        }

        return number;
    }

}
