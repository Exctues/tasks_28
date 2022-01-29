package task11;

public class BloomFilter {
    public int filter_len;
    public int num;
    public final int m = 32;
    public BloomFilter(int f_len) {
        filter_len = f_len;
        num = 0;
    }

    // хэш-функции
    public int hash1(String str1) {
        final int const1 = 17;
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = str1.charAt(i);
            result = (result * const1 + code) % m;
        }
        return result;
    }

    public int hash2(String str1) {
        final int const2 = 223;
        int result = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = str1.charAt(i);
            result = (result * const2 + code) % m;
        }
        return result;
    }

    public void add(String str1) {
        int idx1 = hash1(str1);
        int idx2 = hash2(str1);
        setBit(idx1);
        setBit(idx2);
    }

    public boolean isValue(String str1) {
        int idx1 = hash1(str1);
        int idx2 = hash2(str1);
        return isBitSet(idx1) && isBitSet(idx2);
    }

    public boolean isBitSet(int i){
        return (num & (1 << i)) != 0;
    }

    public void setBit(int i) {
        // xxx0xxx
        // or
        // 0001000
        num |= (1 << i);
    }

    public void unsetBit(int i) {
        // xxx1xxx
        // and
        // 1110111
        num &= ~0 - (1 << i);
    }
}