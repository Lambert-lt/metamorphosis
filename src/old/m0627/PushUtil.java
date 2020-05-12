package old.m0627;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;


/**
 * 极光推�?�工具类
 * @version 1.0
 */
public class PushUtil {

	public static final String APPKEY = "2f3fce39bd8a146b74b0abac";
	public static final String APPSECRET = "2bd7fb44d2aa5f13a04fd6cf";

	// JPushCLient对象，负责进行消息推�??
	private static JPushClient pushClient = new JPushClient(APPSECRET, APPKEY);

	public PushUtil() {
		pushClient = new JPushClient(APPSECRET, APPKEY);
	}

	private static PushPayload.Builder getPayloadBuilder(Platform platform, Audience audience) {
		return PushPayload.newBuilder()
				// 限制推�?�平�??
				.setPlatform(platform)
				// 限制推�?�对象为�??有设�??
				.setAudience(audience);
	}

	// 推�?�给拥有Alias别名用户消息
	public static boolean sendPush4All(String title, String content) {
		return sendPush4All(title, content, new HashMap<String, String>());
	}

	// 推�?�广播消�??
	public static boolean sendPush4All(String title, String content, Map<String, String> extras) {
		try {
			// 创建发�?�给�??有人的PushPayload对象
			PushPayload.Builder payloadBuilder;
			// 设置推�?�消息参�??
			try {
				payloadBuilder = getPayloadBuilder(Platform.ios(), Audience.all());
				Options options = Options.sendno();
				Boolean flag = Boolean.FALSE;
//				if(SystemConfig.getSysProperty("sys.jpush.status").equals("Y")){
//					flag = true;
//				}
				options.setApnsProduction(flag);// true代表ios是生产环节，false是开发环境，具体根据ios配置的证书设�??
				payloadBuilder.setOptions(options);
				PushPayload payload = payloadBuilder.setNotification(Notification.ios(content, extras)).build();
				PushResult result = pushClient.sendPush(payload);
				System.out.println(result.getOriginalContent());
			} catch (Exception e) {
				System.out.println("ios推�?�消息失�??!");
			}

			payloadBuilder = getPayloadBuilder(Platform.android(), Audience.all());
			PushPayload payload = payloadBuilder.setNotification(Notification.android(content, title, extras)).build();
			PushResult result = pushClient.sendPush(payload);
			System.out.println(result.getOriginalContent());
			return true;
		} catch (Exception e) {
			System.out.println("android推�?�消息失�??!");
		}
		return false;
	}

	public static void main(String[] args) {
		// 推�?�所有�?��?��?��?�有链接
		/*
		 * PushUtil pu = new PushUtil(); Map<String,String> extras = new
		 * HashMap<String,String>(); extras.put("type","1");//推�?�类�?? 0=账单 1=公告
		 * extras.put("url","");//url链接 boolean send =
		 * pu.sendPush4All("秒账","买房可享中央空调主机0元购套餐�??3�??3送，详情戳这儿！",extras);
		 * System.out.println(send);
		 */

		// 推�?�所有�?��?��?��?�无链接
//		PushUtil pu = new PushUtil();
//		boolean send = pu.sendPush4All("秒账", "十面\"霾\"伏，请做好健康防护，注意行车安全");
//		System.out.println(send);

		// 推�?�个人�?��?��?��?�有链接
		
//		  String[] arr = new String[1]; arr[0] = "497"; PushUtil pu =
//		  new PushUtil(); Map<String,String> extras = new
//		  HashMap<String,String>(); extras.put("type","4");
//		  //推�?�类�?? 0=账单 1=公告
//		  extras.put("url","");//url链接 
//		  boolean send =
//		  pu.sendPushByAlias("秒账","买房可享中央空调主机0元购套餐�??3�??3送，详情戳这儿！",arr,extras);
//		  System.out.println(send);
		 

//		 推�?�个人�?��?��?��?�无链接
		 String[] arr = new String[1];
		 arr[0] = "497";
		 Map<String,String> extras = new HashMap<String,String>();
		 extras.put("type","4");
		 PushUtil pu = new PushUtil();
		 boolean send =
		 pu.sendPushByAlias("秒账","十面\"霾\"伏，请做好健康防护，注意行车安全",arr,extras);
		 System.out.println(send);
	}

	// 推�?�给拥有Alias别名用户消息
	public static boolean sendPushByAlias(String title, String content, String[] alias) {
		return sendPushByAlias(title, content, alias, new HashMap<String, String>());
	}

	// 推�?�给拥有Alias别名用户消息
	public static boolean sendPushByAlias(String title, String content, String[] alias, Map<String, String> extras) {

		try {
			// 创建发�?�给拥有Alias别名用户的PushPayload对象
			PushPayload.Builder payloadBuilder;
			PushPayload payload;
			try {
				payloadBuilder = getPayloadBuilder(Platform.ios(), Audience.alias(alias));
//				payloadBuilder = getPayloadBuilder(Platform.ios(),Audience.registrationId("191e35f7e07462f37b9"));
				// 设置推�?�消息参�??
				Options options = Options.sendno();
				Boolean flag = Boolean.FALSE;
//				if(SystemConfig.getSysProperty("sys.jpush.status").equals("Y")){
//					flag = true;
//				}
				options.setApnsProduction(flag);// true代表ios是生产环节，false是开发环境，具体根据ios配置的证书设�??
				payloadBuilder.setOptions(options);
				payload = payloadBuilder.setNotification(Notification.ios(content, extras)).build();
				pushClient.sendPush(payload);
				System.out.println("ios推�??"+alias[0]+"消息成功!");
			} catch (Exception e) {
				System.out.println("ios推�?�消息失�??!");
			}
			
			payloadBuilder = getPayloadBuilder(Platform.android(), Audience.alias(alias));
			payload = payloadBuilder.setNotification(Notification.android(content, title, extras)).build();
			pushClient.sendPush(payload);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("android推�?�消息失�??!");
		}
		return false;
	}

	public static boolean sendPushByTags(String title, String content, String[] tags) {
		return sendPushByTags(title, content, tags, new HashMap<String, String>());
	}

	// 推�?�消�??
	public static boolean sendPushByTags(String title, String content, String[] tags, Map<String, String> extras) {

		try {
			// 创建发�?�给拥有Tag标签用户的PushPayload对象
			PushPayload.Builder payloadBuilder = getPayloadBuilder(Platform.ios(), Audience.tag(tags));
			// 设置推�?�消息参�??
			PushPayload payload = payloadBuilder.setNotification(Notification.ios(content, extras)).build();
			pushClient.sendPush(payload);

			payloadBuilder = getPayloadBuilder(Platform.android(), Audience.tag(tags));
			payload = payloadBuilder.setNotification(Notification.android(content, title, extras)).build();
			pushClient.sendPush(payload);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("推�?�消息失�??!");
		}
		return false;
	}
}
