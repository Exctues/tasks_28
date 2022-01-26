package task10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PowerSetTest {

    @Test
    public void putTest() {
        PowerSet set = new PowerSet();
        Assertions.assertEquals(0, set.size());
        set.put("1");
        set.put("2");
        set.put("3");

        set.put("1");
        set.put("2");
        set.put("3");

        Assertions.assertEquals(3, set.size());
        Assertions.assertTrue(set.get("1"));
        Assertions.assertTrue(set.get("2"));
        Assertions.assertTrue(set.get("3"));
    }

    @Test
    public void unionTest() {
        PowerSet set = new PowerSet();
        set.put("1");
        set.put("2");
        set.put("3");
        Assertions.assertEquals(3, set.size());

        PowerSet set2 = new PowerSet();
        set2.put("1");
        set2.put("2");
        set2.put("3");
        set2.put("4");
        set2.put("5");
        set2.put("6");
        Assertions.assertEquals(6, set2.size());

        PowerSet united = set.union(set2);
        Assertions.assertTrue(united.get("1"));
        Assertions.assertTrue(united.get("2"));
        Assertions.assertTrue(united.get("3"));
        Assertions.assertTrue(united.get("4"));
        Assertions.assertTrue(united.get("5"));
        Assertions.assertTrue(united.get("6"));
        Assertions.assertEquals(6, united.size());
    }

    @Test
    public void differenceTest() {
        PowerSet set = new PowerSet();
        set.put("1");
        set.put("2");
        set.put("3");
        set.put("5");
        Assertions.assertEquals(4, set.size());

        PowerSet set0 = new PowerSet();

        PowerSet set2 = new PowerSet();
        set2.put("1");
        set2.put("2");
        set2.put("3");
        set2.put("4");
        set2.put("5");
        set2.put("6");
        Assertions.assertEquals(6, set2.size());

        PowerSet result = set2.difference(set);
        Assertions.assertFalse(result.get("1"));
        Assertions.assertFalse(result.get("2"));
        Assertions.assertFalse(result.get("3"));
        Assertions.assertTrue(result.get("4"));
        Assertions.assertFalse(result.get("5"));
        Assertions.assertTrue(result.get("6"));
        Assertions.assertEquals(2, result.size());

        Assertions.assertEquals(0, set0.difference(set).size());
        Assertions.assertEquals(0, set0.difference(set2).size());

        Assertions.assertEquals(4, set.difference(set0).size());
        Assertions.assertEquals(6, set2.difference(set0).size());
    }

    @Test
    public void differenceTestEmpty() {
        PowerSet set = new PowerSet();
        set.put("1");
        set.put("2");
        set.put("3");
        set.put("5");
        Assertions.assertEquals(4, set.size());

        PowerSet set2 = new PowerSet();
        Assertions.assertEquals(0, set2.size());

        PowerSet result = set2.difference(set);
        Assertions.assertFalse(result.get("1"));
        Assertions.assertFalse(result.get("2"));
        Assertions.assertFalse(result.get("3"));
        Assertions.assertFalse(result.get("4"));
        Assertions.assertFalse(result.get("5"));
        Assertions.assertFalse(result.get("6"));
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void isSubsetTest() {
        PowerSet set = new PowerSet();
        set.put("1");
        set.put("2");
        set.put("3");
        Assertions.assertEquals(3, set.size());

        PowerSet set2 = new PowerSet();
        set2.put("1");
        set2.put("2");
        set2.put("3");
        set2.put("4");
        set2.put("5");
        set2.put("6");
        Assertions.assertEquals(6, set2.size());

        Assertions.assertFalse(set.isSubset(set2));
        Assertions.assertTrue(set2.isSubset(set));
    }

    @Test
    public void intersectionBenchmark() {
        PowerSet set = new PowerSet();
        for (int i = 0; i < 20000; i++) {
            set.put(i + "");
        }
        PowerSet set2 = new PowerSet();
        for (int i = 0; i < 20000; i++) {
            set.put(19999 - i + "");
        }
        long startTime = System.nanoTime();
        PowerSet res = set.intersection(set);
        long endTime = System.nanoTime();


        long duration = (endTime - startTime);
        System.out.println( duration/1000000 + " ms");
    }
}
