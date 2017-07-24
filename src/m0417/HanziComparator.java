package m0417;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;

/** 
 * °´ºº×ÖÊ××ÖÄ¸ÅÅÐò 
 */  
public class  HanziComparator implements Comparator<UserInfo> {  
	  
    private Collator cmp = Collator.getInstance(java.util.Locale.CHINA);  
  
    
    @Override  
    public int compare(UserInfo o1, UserInfo o2) {  
        String userName1 = o1.getTempUserName();  
        String userName2 = o2.getTempUserName();  
        if (null == userName1) {  
            if (null == userName2) {  
                return 0;  
            } else {  
                return 1;  
            }  
        } else if (null == userName2) {  
            return -1;  
        } else {  
            int result = cmp.compare(userName1, userName2);  
            return result;  
        }  
    }  
  
}  