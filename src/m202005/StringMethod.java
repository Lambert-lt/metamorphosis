package m202005;

import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.lang.BooleanUtils;

import java.util.*;

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


    public static String replaceSpaces(String s, int length) {
        // 1.查询需要增加的长度
        int addLength = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == ' ') {
                addLength++;
            }
        }
        int totalLength = length + addLength * 2;
        char[] chars = new char[totalLength];
        int j = 0;
        // 2.替换字符串
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == ' ') {
                chars[j++] = '%';
                chars[j++] = '2';
                chars[j++] = '0';
            } else {
                chars[j++] = s.charAt(i);
            }
        }
        return String.valueOf(chars);
    }

   /* public static void main(String[] args) {

//        boolean b = BooleanUtils.isTrue(true) && BooleanUtils.isFalse(null);
//        System.out.println(b);
        String astr = "Mr John Smith    ";
        System.out.println(astr);
        System.out.println(replaceSpaces(astr, 13));
    }*/

    public static boolean canPermutePalindrome(String s) {
        // 某个字符是否是偶数
        Map<Character, Integer> char2CountsMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            int counts = 1;
            if (char2CountsMap.containsKey(c)) {
                counts += char2CountsMap.get(c);
            }
            char2CountsMap.put(c, counts);
        }

        int jishuCount = 0;
        for (Map.Entry<Character, Integer> entry : char2CountsMap.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                continue;
            }
            jishuCount++;
            if (jishuCount == 2) {
                return false;
            }
        }

        return true;
    }

    /*public static void main(String[] args) {
        String astr = "tactcoa";
        System.out.println(canPermutePalindrome(astr));
    }*/

//    判断出现奇数次的字符最多只有一个（该字符将会出现在中点位置）。
//    全体字符有128个，可以用高低两个 long 类型的位图表示128位。
//    通过将 1L 左移，例如 'A' 的 ASCII 值为65，就向左移动65位，所以位图的从第0位向左数第65位表示 'A'，
//    以此类推。0 ~ 63 放在低位，64 ~ 127 放在高位
//    。如果是0与1异或是1：0 ^ 1 = 1，1再与1异或又变回0：1 ^ 1 = 0，
//    所以0代表该位上得字符出现偶数次。最后利用Long.bitCount()统计一下1的数量，是否小于等于1，即是否最多只有1个字符出现奇数次。


    public boolean canPermutePalindromeNew(String s) {
        long highBmp = 0, lowBmp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 64) {
                highBmp ^= 1L << s.charAt(i) - 64;
            } else {
                lowBmp ^= 1L << s.charAt(i);
            }
        }
        return Long.bitCount(highBmp) + Long.bitCount(lowBmp) <= 1;
    }

    public static boolean oneEditAway(String first, String second) {
        if (first == null || second == null) return false;
        int len1 = first.length();
        int len2 = second.length();
        if (Math.abs(len1 - len2) > 1) return false;
        if (len2 > len1) return oneEditAway(second, first);

        // 保持第一个比第二个长
        for (int i = 0; i < len2; i++){
            if (first.charAt(i) != second.charAt(i)){
                // 如果是长度相同字符串，那就比较下一个，如果长度不一样，那就从该字符开始进行比较。
                return first.substring(i + 1).equals(second.substring(len1 == len2 ? i + 1 : i));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String astr1 = "bba";
        String astr2 = "abc";
        System.out.println(oneEditAway(astr1,astr2));
    }
}



