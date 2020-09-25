package sample;

public class DuplicateChecker extends Check {


    public DuplicateChecker(int[][] matrix){
        super(matrix);
    }

    public boolean check(){
        int result = 0;
        int[][] m = super.getCheckMatrix();
        for(int i = 0; i < m.length; i++){
            for(int j = 0 ; j < m.length; j++){
                if(i == m.length - 1 && j == m.length - 1){
                    break;
                }else{
                    if(i == m.length-1){
                        if(m[i][j] == 0 && m[i][j+1] == 0){
                            result = 1;
                        }
                    }else{
                        if(j == m.length-1){
                            if(m[i][j] == 0 && m[i+1][j] == 0){
                                result = 1;
                            }
                        }else{
                            if((m[i][j] == 0 && m[i][j+1] == 0) || (m[i][j] == 0 && m[i+1][j] == 0)){
                                result = 1;
                            }
                        }
                    }
                }

            }
        }

        if(result == 0){
            return true;
        }

        return false;
    }
}
