package checker;

public class CorrectMatrixChecker extends Check{

    public CorrectMatrixChecker(int[][] matrix){
        super(matrix);
    }

    @Override
    public boolean check() {
        int[][] m = super.getCheckMatrix();

        for(int i = 0 ; i < m.length; i++){
            for(int j = 0 ; j < m.length; j++){
                if(m[i][j] != 0) {
                    if (!rowCheck(m[i][j], m, i) || !colCheck(m[i][j], m, j)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean rowCheck(int x, int[][] matrix, int row){
        int entryRow = 0;
        for(int col = 0; col < matrix.length; col++){
            if(matrix[row][col] == x){
                entryRow++;
            }
        }

        if(entryRow == 1)
            return true;
        return false;
    }

    public boolean colCheck(int x, int[][] matrix, int col){
        int entryCol = 0;
        for(int row = 0; row < matrix.length; row++){
            if(matrix[row][col] == x){
                entryCol++;
            }
        }

        if(entryCol == 1)
            return true;
        return false;
    }
}
