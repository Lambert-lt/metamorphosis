package old.m0419;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Comparator;

public class PinyinComparator implements Comparator<m0419.Search> {

    public int compare(m0419.Search s1, m0419.Search s2) {


        String o1 = s1.getSearchName();


        String o2 = s2.getSearchName();


        for (int i = 0; i < o1.length() && i < o2.length(); i++) {


            int codePoint1 = o1.charAt(i);


            int codePoint2 = o2.charAt(i);


            if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {
                i++;
            }


            if (codePoint1 != codePoint2) {
                if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {
                    return codePoint1 - codePoint2;
                }

                String pinyin1 = pinyin((char) codePoint1);
                String pinyin2 = pinyin((char) codePoint2);

                if (pinyin1 != null && pinyin2 != null) {
                    // �����ַ����Ǻ���
                    if (!pinyin1.equals(pinyin2)) {
                        return pinyin1.compareTo(pinyin2);
                    }
                } else {
                    return codePoint1 - codePoint2;
                }
            }
        }

        return o1.length() - o2.length();


    }

    /**
     * ����Ӣ������
     **/
    private String pinyin(char c) {

        if (String.valueOf(c) == null || String.valueOf(c).length() == 0) {
            return "";
        }

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String output = "";
        try {
            if (java.lang.Character.toString(c).matches("[\\u4E00-\\u9FA5]")) {
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (temp != null && temp.length > 0) {
                    output += temp[0];
                }
            } else {
                output += java.lang.Character.toString(c);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return output;
    }

}