package task16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ADS2Test {
    @Test
    //       3
    //    1     5
    //  0   2  4  6
    public void bstBuildTest() {
        int[] a = {0, 1, 2, 3, 4, 5, 6};
        int[] b = AlgorithmsDataStructures2.GenerateBBSTArray(a);
        Assertions.assertEquals(3, b[0]);
        Assertions.assertEquals(1, b[1]);
        Assertions.assertEquals(5, b[2]);
        Assertions.assertEquals(0, b[3]);
        Assertions.assertEquals(2, b[4]);
        Assertions.assertEquals(4, b[5]);
        Assertions.assertEquals(6, b[6]);
    }
}
