package m202005;


import cn.jiguang.common.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class StringTest {

    @Before
    public void init() {

    }

    @Test
    public void compressStringTest() {
        String s = "aaabbccc";
        String sres = "a3b2c3";
        String compressString = compressString(s);
        System.out.println(compressString + " 运行是否正确 ：" + compressString.equals(sres));

        String s1 = "aabbcc";
        String s1res = "aabbcc";
        String compressString1 = compressString(s1);
        System.out.println(compressString1 + " 运行是否正确 ：" + compressString1.equals(s1res));
    }

    public String compressString(String S) {
        if (null == S || S.length() == 0 || S.length() == 1) {
            return S;
        }

        // 如果第二个和第一个相同，移除第二个，第一个加一，直到不同为止
        StringBuilder str = new StringBuilder();
        int j = 1;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (i == S.length() - 1) {
                str.append(c).append(j);
                return str.toString().length() < S.length() ? str.toString() : S;
            }
            char afterC = S.charAt(i + 1);
            if (c == afterC) {
                j++;
            } else {
                str.append(c).append(j);
                j = 1;
            }
        }
        return S;
//        Map<Character, Integer> char2countMap = new HashMap<>();
//        for (int i = 0; i < S.length(); i++) {
//            char c = S.charAt(i);
//            int count = 1;
//            if (char2countMap.containsKey(c)) {
//                count += char2countMap.get(c);
//            }
//            char2countMap.put(c, count);
//        }
//        if (char2countMap.keySet().size() * 2 < S.length()) {
//            StringBuilder resultStrBuilder = new StringBuilder();
//            for (Map.Entry<Character, Integer> entry : char2countMap.entrySet()) {
//                Character key = entry.getKey();
//                Integer value = entry.getValue();
//                resultStrBuilder.append(key).append(value);
//            }
//            return resultStrBuilder.toString();
//        } else {
//            return S;
//        }

    }
}
