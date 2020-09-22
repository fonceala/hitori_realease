package CheckTests;

import checker.Check;
import checker.DuplicateChecker;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateCheckerTest {

    @org.junit.jupiter.api.Test
    void checkDuplicates() {
        int[][] matrix = {
                {1,1,3,5,5},
                {4,2,5,3,1},
                {4,3,3,5,4},
                {5,1,2,3,3},
                {3,3,4,1,1}
        };
        Check duplicate = new DuplicateChecker(matrix);
        boolean result = duplicate.checkDuplicates();
        assertFalse(result);

        int[][] matrix1 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        duplicate.setCheckMatrix(matrix1);
        assertTrue(duplicate.checkDuplicates());
    }
}