package m907;

import java.security.MessageDigest;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.RandomStringUtils;

public class ApiTest {
	private String MD5(String value) throws Exception {
		StringBuffer md5StrBuff = new StringBuffer();

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(value.getBytes("UTF-8"));

		byte[] result = md5.digest();

		for (int i = 0; i < result.length; i++) {
			if (Integer.toHexString(0xFF & result[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & result[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & result[i]));
			}
		}
		return md5StrBuff.toString();
	}

	private void sendsms() throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://api.1cloudsp.com/api/send");
        postMethod.getParams().setContentCharset("UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler());

        String accesskey = "pA70tZOm1JEQLb7j"; //用户开发key
        String accessScrect = "mRLtv1M7WxhhUHTijrcJPgCENzItWFZd"; //用户开发秘钥
        String random = RandomStringUtils.random(10); //随机字符串
        String timestamp = ""+System.currentTimeMillis(); //当前时间戳
        String token = this.MD5(accessScrect+random+timestamp); //计算token
        NameValuePair[] data = {
                new NameValuePair("token", token),
                new NameValuePair("accesskey", accesskey),
                new NameValuePair("timestamp", timestamp),
                new NameValuePair("random", random),
                new NameValuePair("mobile", "13035320857"),
                new NameValuePair("content", "短信验证码为8564请勿将验证码提供给他人。"),
                new NameValuePair("sign", "【秒账】"),
                new NameValuePair("scheduleSendTime",""),
        };
        postMethod.setRequestBody(data);

        int statusCode = httpClient.executeMethod(postMethod);
        System.out.println("statusCode: " + statusCode + ", body: "
                    + postMethod.getResponseBodyAsString());
    }

	
	private void sms() throws Exception {
		   HttpClient httpClient = new HttpClient();
	        PostMethod postMethod = new PostMethod("http://api.1cloudsp.com/intl/api/send");
	        postMethod.getParams().setContentCharset("UTF-8");
	        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
	                    new DefaultHttpMethodRetryHandler());

	        String accesskey = "pA70tZOm1JEQLb7j"; //用户开发key
	        String accessScrect = "mRLtv1M7WxhhUHTijrcJPgCENzItWFZd"; //用户开发秘钥
	        String random = RandomStringUtils.random(10); //随机字符串
	        String timestamp = ""+System.currentTimeMillis(); //当前时间戳
	        String token = this.MD5(accessScrect+random+timestamp); //计算token
	        NameValuePair[] data = {
	                new NameValuePair("token", token),
	                new NameValuePair("accesskey", accesskey),
	                new NameValuePair("timestamp", timestamp),
	                new NameValuePair("random", random),
	                new NameValuePair("mobile", "65-96998318"),
	                new NameValuePair("content", "短信验证码为8564请勿将验证码提供给他人。"),
	                new NameValuePair("sign", "【秒账】"),
	                new NameValuePair("scheduleSendTime",""),
	        };
	        postMethod.setRequestBody(data);

	        int statusCode = httpClient.executeMethod(postMethod);
	        System.out.println("statusCode: " + statusCode + ", body: "
	                    + postMethod.getResponseBodyAsString());
	}
	public static void main(String[] args) throws Exception {
		ApiTest t = new ApiTest();
//		t.sendsms();
		t.sms();
	}
}