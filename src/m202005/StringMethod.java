package m202005;

import java.util.HashSet;
import java.util.Set;

public class StringMethod {

    public static void main(String[] args) {
        String astr = "abcdefg";
        String astr1 = "abcddefg";
        System.out.println("astr isUnique: " + isUnique(astr));
        System.out.println("astr1 isUnique: " + isUnique(astr1));

    }

    public static boolean isUnique(String astr) {
        Set<Character> strSet = new HashSet();
        for (char c : astr.toCharArray()) {
            strSet.add(c);
        }
        return astr.length() <= strSet.size();
    }


    public boolean isUnique2(String astr) {
        if (astr.length() < 2)
            return true;
        int p = 0;
        for (int i = 0; i < astr.length(); ++i) {
            if (((1 << (astr.charAt(i) - 'a')) & p) != 0) {
                return false;
            }
            p |= (1 << (astr.charAt(i) - 'a'));
        }
        return true;
    }
}



