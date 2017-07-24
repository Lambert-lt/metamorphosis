package m0417;

public class UserInfo {  
    private String userName;  
  
    private String tempUserName;  
  
    public String getTempUserName() {  
        return tempUserName;  
    }  
  
    public void setTempUserName(String tempUserName) {  
        this.tempUserName = tempUserName;  
    }  
  
    public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
  
    @Override  
    public String toString() {  
        return "UserInfo [userName=" + userName + ", tempUserName="  
                + tempUserName + "]";  
    }  
  
}  
