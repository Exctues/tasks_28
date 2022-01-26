package task10;

import java.util.*;

public class PowerSet {

    public ArrayList<String> a;

    public PowerSet() {
        a = new ArrayList<>();
    }

    public int size() {
        return a.size();
    }


    public void put(String value) {
        if (!a.contains(value))
            a.add(value);
    }

    public boolean get(String value) {
        return a.contains(value);
    }

    public boolean remove(String value) {
        return a.remove(value);
    }

    public PowerSet intersection(PowerSet set2) {
        PowerSet result = new PowerSet();
        PowerSet smallSet = size() < set2.size() ? this : set2;
        PowerSet bigSet = (smallSet == this) ? set2 : this;

        for (int i = 0; i < smallSet.size(); i++) {
            String val = smallSet.a.get(i);
            if (bigSet.a.contains(val))
                result.put(val);
        }

        return result;
    }

    public PowerSet union(PowerSet set2) {
        PowerSet result = new PowerSet();
        PowerSet smallSet = size() < set2.size() ? this : set2;
        PowerSet bigSet = (smallSet == this) ? set2 : this;

        for (int i = 0; i < smallSet.size(); i++) {
            String val = smallSet.a.get(i);
            if (!result.a.contains(val))
                result.put(val);
        }

        for (int i = 0; i < bigSet.size(); i++) {
            String val = bigSet.a.get(i);
            if (!result.a.contains(val))
                result.put(val);
        }
        return result;
    }

    public PowerSet difference(PowerSet set2) {
        PowerSet result = new PowerSet();
        for (int i = 0; i < a.size(); i++) {
            String val = a.get(i);
            if (!set2.a.contains(val))
                result.put(val);
        }
        return result;
    }

    public boolean isSubset(PowerSet set2) {
        for (int i = 0; i < set2.size(); i++) {
            String val = set2.a.get(i);
            if (!a.contains(val))
                return false;
        }
        return true;
    }
}