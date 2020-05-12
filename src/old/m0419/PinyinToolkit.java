package m0419;

import net.sourceforge.pinyin4j.PinyinHelper; 
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType; 
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat; 
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType; 
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.UnsupportedEncodingException;

/** 
* Æ´Òô¹¤¾ß 
* 
* @author zhouhang 2010-01-25

*/ 
public class PinyinToolkit {

        /** 
         * »ñÈ¡ºº×Ö´®Æ´ÒôÊ××ÖÄ¸£¬Ó¢ÎÄ×Ö·û²»±ä 
         * 
         * @param chinese ºº×Ö´® 
         * @return ººÓïÆ´ÒôÊ××ÖÄ¸ 
         */ 
        public static String cn2FirstSpell(String chinese) { 
                StringBuffer pybf = new StringBuffer(); 
                char[] arr = chinese.toCharArray(); 
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
                defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
                defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
                defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
                for (int i = 0; i < arr.length; i++) { 
                    if (arr[i] > 128) 
                    { 
                        try { 
                                String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat); 
                                if (_t != null) { 
                                      pybf.append(_t[0].charAt(0)); 
                                } 
                        } catch (BadHanyuPinyinOutputFormatCombination e) { 
                                e.printStackTrace(); 
                        } 
                    } else { 
                            pybf.append(arr[i]); 
                    } 
                } 
                return pybf.toString().replaceAll("//W", "").trim(); 
        }

        /** 
         * »ñÈ¡ºº×Ö´®Æ´Òô£¬Ó¢ÎÄ×Ö·û²»±ä 
         * 
         * @param chinese ºº×Ö´® 
         * @return ººÓïÆ´Òô 
         */ 
        public static String cn2Spell(String chinese) { 
                StringBuffer pybf = new StringBuffer(); 
                char[] arr = chinese.toCharArray(); 
                HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
                defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
                defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
                defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
                for (int i = 0; i < arr.length; i++) { 
                    if (arr[i] > 128) { 
                        try { 
                                pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]); 
                        } catch (BadHanyuPinyinOutputFormatCombination e) { 
                                e.printStackTrace(); 
                        } 
                    } else { 
                            pybf.append(arr[i]); 
                    } 
                } 
                return pybf.toString(); 
        }

        public static void main(String[] args) throws UnsupportedEncodingException { 
                String x = "1xj½ðÍ¯Äê"; 
                System.out.println(cn2FirstSpell(x)); 
                System.out.println(cn2Spell(x)); 
        } 
}

 