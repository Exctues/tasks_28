package task11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BloomFilterTest {
    @Test
    public void testBitSet() {
        BloomFilter f = new BloomFilter(10);
        Assertions.assertFalse(f.isBitSet(3));

        f.setBit(3);
        Assertions.assertTrue(f.isBitSet(3));
        Assertions.assertEquals(8, f.num);

        f.unsetBit(3);
        Assertions.assertFalse(f.isBitSet(3));
        Assertions.assertEquals(0, f.num);
    }

    @Test
    public void testStrings() {
        String orig = "0123456789";
        for (int i = 0; i < 10; i++) {
            BloomFilter f = new BloomFilter(10);

            String str = orig.substring(i, 10) + orig.substring(0, i);
            System.out.println(str);

            f.add(str);
            Assertions.assertTrue(f.isValue(str));
        }
    }
}
