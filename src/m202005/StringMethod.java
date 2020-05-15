package m202005;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class StringMethod {

   /*public static void main(String[] args) {
        String astr = "abcdefg";
        String astr1 = "abcddefg";
        System.out.println("astr isUnique: " + isUnique(astr));
        System.out.println("astr1 isUnique: " + isUnique(astr1));

    }*/

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


    public static boolean CheckPermutation(String s1, String s2) {
        char[] chars = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        Arrays.sort(chars);
        Arrays.sort(chars2);

        return Arrays.toString(chars).equals(Arrays.toString(chars2));
    }

 /*   public static void main(String[] args) {
        String astr = "abcdefg";
        String astr1 = "abcddefg";
        System.out.println("astr CheckPermutation: " + CheckPermutation(astr,astr1));
        String astr2 = "abcdefg";
        String astr3 = "fgdebca";
        System.out.println("astr2 CheckPermutation: " + CheckPermutation(astr2,astr3));
    }*/


    public String replaceSpaces(String S, int length) {
        // 1.查询需要增加的长度
        int addLength = 0;
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == ' ') {
                addLength++;
            }
        }

        // 2.替换字符串



        return S;
    }

    public static void main(String[] args) {
        String astr = "abcdefg";
        String astr1 = "abcddefg";
        System.out.println("astr CheckPermutation: " + CheckPermutation(astr,astr1));
        String astr2 = "abcdefg";
        String astr3 = "fgdebca";
        System.out.println("astr2 CheckPermutation: " + CheckPermutation(astr2,astr3));
    }
}



