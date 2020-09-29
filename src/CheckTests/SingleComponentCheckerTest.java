package CheckTests;

import sample.Check;
import sample.SingleComponentChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleComponentCheckerTest {

    @Test
    void check() {
        int[][] matrix = {
                {5,4,0,1,4},
                {0,1,4,5,3},
                {1,0,2,0,4},
                {3,2,0,4,1},
                {0,3,1,2,0}
        };
        Check comp = new SingleComponentChecker(matrix);
        assertTrue(comp.check());
    }
}