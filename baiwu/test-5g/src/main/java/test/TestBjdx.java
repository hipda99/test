package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.CloseUtil;
import util.DateUtil;

public class TestBjdx{
	protected String pre = "beijingDxChatbot-";
    private OkHttpClient client = new OkHttpClient.Builder()
    		.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
    		.hostnameVerifier(new TrustAllHostnameVerifier())
    		.retryOnConnectionFailure(false)
    		.connectionPool(new ConnectionPool(200, 10, TimeUnit.MINUTES))
    		.connectTimeout(30, TimeUnit.SECONDS)
    		.readTimeout(300, TimeUnit.SECONDS)
    		.writeTimeout(300, TimeUnit.SECONDS)
    		.build();
	protected String serverRoot;
	protected String getAccessTokenUrl;
	protected String sendMessageUrl;
	protected String revokeUrl;
	protected String uploadFileUrl;
	protected String downloadUrl;
	protected String deleteUrl;
	
	protected String findChatbotUrl;
	protected String chatbotOptionUrl;
	protected String menuUrl;
	
	protected String accessToken;
	
	protected String getchatbotSip() {
		return "sip:" + this.chatbotId + "@botplatform.rcs.vnet.cn";
	}
	
	protected String headerDate = DateUtil.getHttpHeaderDate();// Tue, 15 Nov 2019 08:12:31 GMT
	
	protected String chatbotId;
	protected String appId;
	protected String appkey;
	
	public void init() {
		this.getAccessTokenUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/accessToken";
		this.sendMessageUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/messages";
		this.revokeUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/revoke";
		this.uploadFileUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/upload";
		this.downloadUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/download";
		this.deleteUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/delete";
		this.findChatbotUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/find/chatBotInfo";
		this.chatbotOptionUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/update/chatBotInfo/optionals";
		this.menuUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/update/chatBotInfo/menu";
	}
	
	public TestBjdx(String chatbotId,String appid,String appkey) {
		this.chatbotId = chatbotId;
		this.appId = appid;
		this.appkey = appkey;
	}
	
	protected String phone = "tel:+8615301356385";
	public static void main(String[] args) {
		//????????????
		String chatbotId = "106598858810000006";
		String appId = "xSnuH12M";
		String appkey = "aa6420f8dd9a812665b0e06dc36e0ebcd3614b24";

		TestBjdx t = new TestBjdx(chatbotId,appId,appkey);
		t.serverRoot = "https://maaptest.189.cn/maap_message";
		t.init();
//		t.accessToken = "a7d256eab9cf45a8be92972f6d78cf96";
        t.getAccessToken();
        //????????????
//        t.uploadFile("file/image2.png");
        //?????????
//        t.uploadFile("file/thumbnail2.jpg");
        //????????????
//        t.downloadFile("https://maaptest.189.cn/maap_message/bot/chanageUrl/temp/20210222155750/171008/22,49b11ff12ed3.png");
        //????????????
//        t.deleteFile("https://maaptest.189.cn/maap_message/bot/chanageUrl/temp/20210222155750/171008/22,49b11ff12ed3.png");
        //??????????????????
//        t.requestText();
        //????????????
//        t.revokeMsg();
        
        //chatbot????????????
//        t.findChatbot();
        //????????????
//        t.chatbotOption();
        //????????????
//        t.chatbotMenu();
        
        //3??????????????? 
//		t.sendFile();
        //5???????????????????????????(??? CSS ??????) + Suggestions 
        t.sendSigleCard();
        //6???????????????????????????(??? CSS ??????) + Suggestions 
//		t.sendMultCard();
	}
	
	String getDuokp() {
		String s = "{\r\n" + 
				"  \"contributionId\": \"134dbb7a-32c7-4813-80a3-b9597e3f9a27\",\r\n" + 
				"  \"conversationId\": \"7c000ce6-4206-4111-b186-7c0756fb80c9\",\r\n" + 
				"  \"destinationAddress\": [\r\n" + 
				"    \"tel:+8615330759941\"\r\n" + 
				"  ],\r\n" + 
				"  \"messageId\": \"426123a32-6391-4395-a5ef-d325506155ed\",\r\n" + 
				"  \"messageList\": [\r\n" + 
				"    {\r\n" + 
				"      \"contentText\": {\r\n" + 
				"        \"message\": {\r\n" + 
				"          \"generalPurposeCardCarousel\": {\r\n" + 
				"            \"content\": [\r\n" + 
				"              {\r\n" + 
				"                \"description\": \"??????5G???????????????!\",\r\n" + 
				"                \"media\": {\r\n" + 
				"                  \"height\": \"SHORT_HEIGHT\",\r\n" + 
				"                  \"mediaContentType\": \"image/png\",\r\n" + 
				"                  \"mediaFileSize\": 178887,\r\n" + 
				"                  \"mediaUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\",\r\n" + 
				"                  \"thumbnailContentType\": \"image/png\",\r\n" + 
				"                  \"thumbnailFileSize\": 178887,\r\n" + 
				"                  \"thumbnailUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\"\r\n" + 
				"                },\r\n" + 
				"                \"suggestions\": [\r\n" + 
				"                  {\r\n" + 
				"                    \"reply\": {\r\n" + 
				"                      \"displayText\": \"????????????\",\r\n" + 
				"                      \"postback\": {\r\n" + 
				"                        \"data\": \"????????????\"\r\n" + 
				"                      }\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                ],\r\n" + 
				"                \"title\": \"5G???????????? \"\r\n" + 
				"              },\r\n" + 
				"              {\r\n" + 
				"                \"description\": \"??????5G??????????????????\",\r\n" + 
				"                \"media\": {\r\n" + 
				"                  \"height\": \"SHORT_HEIGHT\",\r\n" + 
				"                  \"mediaContentType\": \"image/png\",\r\n" + 
				"                  \"mediaFileSize\": 178887,\r\n" + 
				"                  \"mediaUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\",\r\n" + 
				"                  \"thumbnailContentType\": \"image/png\",\r\n" + 
				"                  \"thumbnailFileSize\": 178887,\r\n" + 
				"                  \"thumbnailUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\"\r\n" + 
				"                },\r\n" + 
				"                \"suggestions\": [\r\n" + 
				"                  {\r\n" + 
				"                    \"reply\": {\r\n" + 
				"                      \"displayText\": \"????????????\",\r\n" + 
				"                      \"postback\": {\r\n" + 
				"                        \"data\": \"????????????\"\r\n" + 
				"                      }\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                ],\r\n" + 
				"                \"title\": \"??????5G???????????????!\"\r\n" + 
				"              }\r\n" + 
				"            ],\r\n" + 
				"            \"layout\": {\r\n" + 
				"              \"cardWidth\": \"MEDIUM_WIDTH\"\r\n" + 
				"            }\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"contentType\": \"application/vnd.gsma.botmessage.v1.0+json\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"reportRequest\": [\r\n" + 
				"    \"sent\",\r\n" + 
				"    \"failed\",\r\n" + 
				"    \"delivered\",\r\n" + 
				"    \"displayed\",\r\n" + 
				"    \"deliveredToNetwork\"\r\n" + 
				"  ],\r\n" + 
				"  \"senderAddress\": \"sip:106598858810000006@botplatform.rcs.vnet.cn\",\r\n" + 
				"  \"serviceCapability\": [\r\n" + 
				"    {\r\n" + 
				"      \"capabilityId\": \"ChatbotSA\",\r\n" + 
				"      \"version\": \"+g.gsma.rcs.botversion=\\\"#=1\\\"\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"smsSupported\": false,\r\n" + 
				"  \"storeSupported\": false\r\n" + 
				"}";
		return s;
	}
	
	String chatbotMenu() {
		String json = "{\r\n" + 
				"  \"menu\": {\r\n" + 
				"    \"entries\": [\r\n" + 
				"      {\r\n" + 
				"        \"reply\": {\r\n" + 
				"          \"displayText\": \"??????\",\r\n" + 
				"          \"postback\": {\r\n" + 
				"            \"data\": \"set_by_chatbot_reply1\"\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"menu\": {\r\n" + 
				"          \"displayText\": \"????????????\",\r\n" + 
				"          \"entries\": [\r\n" + 
				"            {\r\n" + 
				"              \"reply\": {\r\n" + 
				"                \"displayText\": \"????????????\",\r\n" + 
				"                \"postback\": {\r\n" + 
				"                  \"data\": \"set_by_chatbot_reply2\"\r\n" + 
				"                }\r\n" + 
				"              }\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"              \"action\": {\r\n" + 
				"                \"dialerAction\": {\r\n" + 
				"                  \"dialPhoneNumber\": {\r\n" + 
				"                    \"phoneNumber\": \"+8615330759941\"\r\n" + 
				"                  }\r\n" + 
				"                },\r\n" + 
				"                \"displayText\": \"????????????\",\r\n" + 
				"                \"postback\": {\r\n" + 
				"                  \"data\": \"set_by_chatbot_dial_menu_phone_number\"\r\n" + 
				"                }\r\n" + 
				"              }\r\n" + 
				"            }\r\n" + 
				"          ]\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  }\r\n" + 
				"}";
		
		String url = menuUrl;
		
		return request(new Request.Builder()
				.addHeader("authorization", this.accessToken)
				.url(url)
				.post(RequestBody.create(json.getBytes(),MediaType.parse("application/json")))
				.build(),"chatbotMenu");
	}
	
	String chatbotOption() {
		System.out.println("chatbot???????????????");
		String url = chatbotOptionUrl;
		
		JSONObject json = new JSONObject();
		json.put("longitude", "118.1");
		
		return request(new Request.Builder()
				.addHeader("authorization", this.accessToken)
				.url(url)
				.post(RequestBody.create(JSON.toJSONString(json).getBytes(),MediaType.parse("application/json")))
				.build(),"chatbotOption");
	}
	
	String findChatbot() {
		System.out.println("??????chatbot?????????");
		String url = findChatbotUrl;
		
		return request(new Request.Builder()
				.addHeader("authorization", this.accessToken)
				.url(url)
				.get()
				.build(),"findChatbot");
	}
	
	String revokeMsg() {
		System.out.println("?????????????????????");
		String url = revokeUrl;
        
		JSONObject json = new JSONObject();
		json.put("messageId", "cb1188a3-37ec-1037-9254-2ec66e44305b2");
		json.put("status", "RevokeRequested");
		json.put("destinationAddress", Arrays.asList("tel:+8615330759941"));

		
		return request(new Request.Builder()
				.addHeader("authorization", this.accessToken)
				.addHeader("accept", "application/json")
				.addHeader("date", headerDate)
				.url(url)
				.post(RequestBody.create(JSON.toJSONString(json).getBytes(),MediaType.parse("application/json")))
				.build(),"deleteFile");
	}
	
	String deleteFile(String fileUrl) {
		System.out.println("?????????????????????");
		String url = deleteUrl;
        
		return request(new Request.Builder()
				.addHeader("authorization", this.accessToken)
				.addHeader("url", fileUrl)
				.url(url)
				.delete()
				.build(),"deleteFile");
	}
	
	String downloadFile(String fileUrl) {
		System.out.println("?????????????????????");
		String url = downloadUrl;
        
		return request(new Request.Builder()
				.addHeader("authorization", this.accessToken)
				.addHeader("url", fileUrl)
				.url(url)
				.get()
				.build(),"downloadFile");
	}
	
	String uploadFile(String filePath) {
		System.out.println("?????????????????????");
		String url = uploadFileUrl;
		
		File file = new File(filePath);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file", file.getName(), RequestBody.create(file,MediaType.parse("*/*")));
        
		return request(new Request.Builder()
				.addHeader("authorization", this.accessToken)
				.addHeader("uploadMode", "temp") //perm:???????????? temp:????????????
//				.addHeader("uploadMode", "perm") //perm:???????????? temp:????????????
				.addHeader("content-type", "multipart/form-data")
				.url(url)
				.post(builder.build())
				.build(),"uploadFile");
	}
	
	String getAccessToken() {
		if(null == this.accessToken || this.accessToken.length() == 0) {
			String accessTokenFile = pre + "accessToken";
			String accessToken = readFile(accessTokenFile);
			if(null != accessToken && accessToken.trim().length() > 0) {
				this.accessToken = accessToken.trim();
			}else {
				System.out.println("??????accessToken???");
				String url = getAccessTokenUrl;
				
				Map<String,String> requestBodyMap = new HashMap<>();
				requestBodyMap.put("appId", appId);
				requestBodyMap.put("appKey", appkey);
				String bodyStr = JSONObject.toJSONString(requestBodyMap);
				
				String response = request(new Request.Builder()
						.addHeader("content-type", "application/json")
						.addHeader("accept", "application/json")
						.addHeader("date", headerDate)
						.addHeader("host", "maaptest.189.cn")
						.addHeader("content-length", bodyStr.getBytes().length + "")
						.url(url)
						.post(RequestBody.create(bodyStr.getBytes(),MediaType.parse("application/json")))
						.build(),"beijingDxChatbot-getAccessToken");
				JSONObject jo = JSON.parseObject(response);
				accessToken = jo.getString("accessToken");
				writeAccessTokenFile(accessTokenFile,accessToken);
				this.accessToken = accessToken;
			}
		}
		return this.accessToken;
	}
	
	String request(Request request,String filePath) {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(request);
		System.out.println();
		System.out.println("======================================================================");
		
		try {
			Response response = client.newCall(request).execute();
			System.out.println("response ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			System.out.println(response);
			System.out.println();
			
			System.out.println("response header  ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			Headers headers = response.headers();
			for(int i = 0; i < headers.size(); i++) {
				String name = headers.name(i);
				String value = headers.get(name);
				
				System.out.println(name + " : " + value);
			}
			System.out.println();
			
			System.out.println("response body ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			String message = response.body().string();
			appendFile(filePath, message);
			System.out.println(message);
			System.out.println();
			System.out.println("=====================================");
			return message;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private SSLSocketFactory createSSLSocketFactory() {
		SSLSocketFactory ssfFactory = null;

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

			ssfFactory = sc.getSocketFactory();
		} catch (Exception e) {
		}

		return ssfFactory;
	}

	private class TrustAllCerts implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}
	private class TrustAllHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	
	void writeAccessTokenFile(String filename,String text) {
		if(null == text) {
			text = "";
		}
		File file = new File("accessToken/" + filename);
		wirteFile(file, text,false);
	}
	void appendFile(String filename,String text) {
		File file = new File("file/" + pre + filename);
		wirteFile(file, DateUtil.getDateTime() + " : " + text,true);
	}
	void wirteFile(File file,String text,boolean append) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
        	fos = new FileOutputStream(file,append);
        	osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(text); //????????????
            osw.write("\r\n");  //??????
        }catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(osw);
			CloseUtil.close(fos);
		}
	}
	
	String readFile(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			return null;
		}
		
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			 int iAvail = is.available();
			 byte[] bytes = new byte[iAvail];
			 is.read(bytes);
			 return new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	String sendRevoke(String messageId) {
		System.out.println("???????????? ");
		String url = revokeUrl;
		
		Map<String,Object> map = new HashMap<>();
		map.put("messageId", messageId);
		map.put("status", "RevokeRequested");
		map.put("destinationAddress", Arrays.asList("tel:+86"+phone));
        
		String body = JSON.toJSONString(map);
		System.out.println("????????????" + body);
		
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendRevoke");
	}
	
	String sendMultCard() {
		System.out.println("????????????????????????(??? CSS ??????) + Suggestions ");
		String url = sendMessageUrl;
		
		Map<String,Object> card = getMutlCard();
		Map map = getJSON(card);
        String body = JSON.toJSONString(map);
        
        //????????????
//		String body = getDuokp();
        
		System.out.println("????????????" + body);
		
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	Map<String,Object> getMutlCardContent3(){
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> media = new HashMap<String,Object>();
		
		media.put("mediaUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/523006452186980352");
		media.put("mediaContentType", "image/mp4");
		media.put("mediaFileSize", "660976");
		media.put("thumbnailUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522991713738268672");
		media.put("thumbnailContentType", "image/jpg");
		media.put("thumbnailFileSize", "9216");
		media.put("height", "SHORT_HEIGHT");
		
		content.put("media", media);
		content.put("title", "????????????????????????");
		content.put("description", "??????????????????????????????????????????????????????????????????????????????????????????????????????");
		
		content.put("suggestions", Arrays.asList(getReply("??????"),getReply("??????"),getUrlAction(),getDialerAction()));
		return content;
	}
	
	Map<String,Object> getMutlCardContent2(){
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> media = new HashMap<String,Object>();
		
		media.put("mediaUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522995847191306240");
		media.put("mediaContentType", "audio/amr");
		media.put("mediaFileSize", "2758");
		media.put("height", "SHORT_HEIGHT");
		
		content.put("media", media);
		content.put("title", "????????????????????????");
		content.put("description", "?????????????????????????????????????????????????????????????????????????????????????????????????????????");
		
		content.put("suggestions", Arrays.asList(getReply("??????"),getReply("??????"),getUrlAction(),getDialerAction()));
		return content;
	}
	
	Map<String,Object> getMutlCardContent1(){
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> media = new HashMap<String,Object>();
		
		media.put("mediaUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522989500022366208");
		media.put("mediaContentType", "image/png");
		media.put("mediaFileSize", "171008");
		media.put("thumbnailUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522991713738268672");
		media.put("thumbnailContentType", "image/jpg");
		media.put("thumbnailFileSize", "9216");
		media.put("height", "SHORT_HEIGHT");
		
		content.put("media", media);
		content.put("title", "????????????????????????");
		content.put("description", "?????????????????????????????????????????????????????????????????????????????????????????????????????????");
		
		content.put("suggestions", Arrays.asList(getReply("??????"),getReply("??????"),getUrlAction(),getDialerAction()));
		return content;
	}
	
	Map<String,Object> getMutlCard(){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType","application/vnd.gsma.botmessage.v1.0+json");
		Map<String,Object> contentText = new HashMap<String,Object>();
		Map<String,Object> message = new HashMap<String,Object>();
		Map<String,Object> generalPurposeCard = new HashMap<String,Object>();
		
		Map<String,Object> layout = new HashMap<String,Object>();
		layout.put("cardWidth", "MEDIUM_WIDTH");
//		layout.put("style", "http://example.com/default.css");
		generalPurposeCard.put("layout", layout);

		generalPurposeCard.put("content", Arrays.asList(getMutlCardContent1(),getMutlCardContent2(),getMutlCardContent3()));
		message.put("generalPurposeCardCarousel", generalPurposeCard);
		contentText.put("message", message);
		result.put("contentText", contentText);
		return result;
	}
	
	String sendSigleCard() {
		System.out.println("????????????????????????(??? CSS ??????) + Suggestions ???");
		String url = sendMessageUrl;
		
		Map<String,Object> card = getSignleCard();
		
		Map map = getJSON(card);
//        String body = JSON.toJSONString(map);
		
		//????????????
		String body = getDkp();
        
		System.out.println(body);
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("accessToken", this.accessToken)
				.addHeader("accessToken", "accessToken " +this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendText");
	} 
	
	
	String getDkp() {
		String s = "{\r\n" + 
				"  \"messageId\": \"cb1188a3-37ec-1037-9254-2ec66e44305b2\",\r\n" + 
				"  \"messageList\": [\r\n" + 
				"    {\r\n" + 
				"      \"contentType\": \"application/vnd.gsma.botmessage.v1.0+json\",\r\n" + 
				"      \"contentText\": {\r\n" + 
				"        \"message\": {\r\n" + 
				"          \"generalPurposeCard\": {\r\n" + 
				"            \"layout\": {\r\n" + 
				"              \"cardOrientation\": \"VERTICAL\",\r\n" + 
				"              \"titleFontStyle\": [\r\n" + 
				"                \"underline\",\r\n" + 
				"                \"bold\"\r\n" + 
				"              ],\r\n" + 
				"              \"descriptionFontStyle\": [\r\n" + 
				"                \"bold\"\r\n" + 
				"              ],\r\n" + 
				"              \"style\": \"http://example.com/default.css\"\r\n" + 
				"            },\r\n" + 
				"            \"content\": {\r\n" + 
				"              \"media\": {\r\n" + 
				"                \"mediaUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\",\r\n" + 
				"                \"mediaContentType\": \"image/jpg\",\r\n" + 
				"                \"mediaFileSize\": 178887,\r\n" + 
				"                \"thumbnailUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\",\r\n" + 
				"                \"thumbnailContentType\": \"image/png\",\r\n" + 
				"                \"thumbnailFileSize\": 178887,\r\n" + 
				"                \"height\": \"MEDIUM_HEIGHT\",\r\n" + 
				"                \"contentDescription\": \"Textual description of media content,  e.g. for use with screen readers.\"\r\n" + 
				"              },\r\n" + 
				"              \"title\": \"This is a single rich card.\",\r\n" + 
				"              \"description\": \"This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card.\",\r\n" + 
				"              \"suggestions\": [\r\n" + 
				"                {\r\n" + 
				"                  \"reply\": {\r\n" + 
				"                    \"displayText\": \"????????????\",\r\n" + 
				"                    \"postback\": {\r\n" + 
				"                      \"data\": \"set_by_chatbot_reply_no\"\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                  \"action\": {\r\n" + 
				"                    \"urlAction\": {\r\n" + 
				"                      \"openUrl\": {\r\n" + 
				"                        \"url\": \"www.baidu.com\",\r\n" + 
				"                        \"application\": \"webview\",\r\n" + 
				"                        \"viewMode\": \"half\"\r\n" + 
				"                      }\r\n" + 
				"                    },\r\n" + 
				"                    \"displayText\": \"????????????\",\r\n" + 
				"                    \"postback\": {\r\n" + 
				"                      \"data\": \"set_by_chatbot_open_url\"\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                  \"action\": {\r\n" + 
				"                    \"dialerAction\": {\r\n" + 
				"                      \"dialPhoneNumber\": {\r\n" + 
				"                        \"phoneNumber\": \"+8615301356385\"\r\n" + 
				"                      }\r\n" + 
				"                    },\r\n" + 
				"                    \"displayText\": \"????????????\",\r\n" + 
				"                    \"postback\": {\r\n" + 
				"                      \"data\": \"set_by_chatbot_open_dialer\"\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                }\r\n" + 
				"              ]\r\n" + 
				"            }\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"trafficType\": \"advertisement\",\r\n" + 
				"  \"destinationAddress\": [\r\n" + 
				"    \"tel:+8615301356385\"\r\n" + 
				"  ],\r\n" + 
				"  \"senderAddress\": \"sip:106598858810000006@botplatform.rcs.vnet.cn\",\r\n" + 
				"  \"smsSupported\": false,\r\n" + 
				"  \"storeSupported\": false,\r\n" + 
				"  \"serviceCapability\": [\r\n" + 
				"    {\r\n" + 
				"      \"capabilityId\": \"ChatbotSA\",\r\n" + 
				"      \"version\": \"+g.gsma.rcs.botversion=\\\"#=1\\\"\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"conversationId\": \"XSFDSFDFSAFDSAS^%\",\r\n" + 
				"  \"contributionId\": \"SFF$#REGFY7&^%THT\"\r\n" + 
				"}";
		return s;
	}
	
	String sendGeo() {
		System.out.println("???????????????????????? ???");
		String url = sendMessageUrl;
		
		Map<String,Object> text = getText("geo:50.7311865,7.0914591;crs=gcj02;u=10;rcsl=Qingfeng%20Steamed%20Dumpling%20Shop%20%F0%9F%8D%9A");
		
		Map map = getJSON(text);
        
		String body = JSON.toJSONString(map);
		System.out.println("????????????"+body);
		
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	String getTestFileJson() {
		return "{\r\n" + 
				"  \"contributionId\": \"c16b6f43-21dc-4e77-ab2d-19af51be466a\",\r\n" + 
				"  \"senderAddress\": \"sip:106598858810000006@botplatform.rcs.vnet.cn\",\r\n" + 
				"  \"destinationAddress\": [\r\n" + 
				"    \"tel:+8615330759941\"\r\n" + 
				"  ],\r\n" + 
				"  \"messageList\": [\r\n" + 
				"    {\r\n" + 
				"      \"contentText\": [\r\n" + 
				"        {\r\n" + 
				"          \"fileSize\": \"178887\",\r\n" + 
				"          \"until\": \"2021-11-25T12:17:07Z\",\r\n" + 
				"          \"type\": \"thumbnail\",\r\n" + 
				"          \"contentType\": \"image/jpeg\",\r\n" + 
				"          \"url\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"          \"fileName\": \"DSC_379395051.JPG\",\r\n" + 
				"          \"fileSize\": \"178887\",\r\n" + 
				"          \"until\": \"2021-11-25T12:17:07Z\",\r\n" + 
				"          \"type\": \"file\",\r\n" + 
				"          \"contentType\": \"image/jpeg\",\r\n" + 
				"          \"url\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\"\r\n" + 
				"        }\r\n" + 
				"      ],\r\n" + 
				"      \"contentEncoding\": \"utf8\",\r\n" + 
				"      \"contentType\": \"application/vnd.gsma.rcs-ft-http\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"reportRequest\": [\r\n" + 
				"    \"sent\",\r\n" + 
				"    \"failed\",\r\n" + 
				"    \"delivered\",\r\n" + 
				"    \"displayed\",\r\n" + 
				"    \"deliveredToNetwork\"\r\n" + 
				"  ],\r\n" + 
				"  \"conversationId\": \"7f77c667-0395-499b-a70a-c0d0f10a443f\",\r\n" + 
				"  \"messageId\": \"a2581c7f-2092-4d81-12a6-bd8dcf072561\",\r\n" + 
				"  \"smsSupported\": false,\r\n" + 
				"  \"storeSupported\": false,\r\n" + 
				"  \"serviceCapability\": [\r\n" + 
				"    {\r\n" + 
				"      \"capabilityId\": \"ChatbotSA\",\r\n" + 
				"      \"version\": \"+g.gsma.rcs.botversion=\\\"#=1\\\"\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
	}
	
	String sendFile() {
		System.out.println("?????????????????? ???");
		String url = sendMessageUrl;
		
		Map<String,Object> file = getFile();
		
		Map map = getJSON(file);
		String body = JSON.toJSONString(map);
		
		//??????
//		String body = getTestFileJson();
		
        System.out.println("????????????" + body);
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	String sendSugText() {
		System.out.println("??????????????????+ ???????????? ???");
		String url = sendMessageUrl;
		
		Map<String,Object> text = getText("??????chatbot,??????????????????+ ???????????? ");
		Map<String,Object> reply1 = getReply("?????? ");
		Map<String,Object> reply2 = getReply("?????? ");
		Map<String,Object> urlAction = getUrlAction();
		Map<String,Object> dialerAction = getDialerAction();
		Map<String,Object> sugs = getSug(reply1,reply2,urlAction,dialerAction);
		
		Map map = getJSON(text,sugs);
        
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	String sendText() {
		System.out.println("?????????????????????");
		String url = sendMessageUrl;
		
		Map<String,Object> map = getJSON(getText("??????chatbot,??????????????????"));
        
		String body = JSON.toJSONString(map);
		System.out.println("request body:");
		System.out.println(body);
		
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
//				.addHeader("Content-Type", "application/json")
//				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	Map<String,Object> getSignleCard(){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType","application/vnd.gsma.botmessage.v1.0+json");
		Map<String,Object> contentText = new HashMap<String,Object>();
		Map<String,Object> message = new HashMap<String,Object>();
		Map<String,Object> generalPurposeCard = new HashMap<String,Object>();
		
		Map<String,Object> layout = new HashMap<String,Object>();
		layout.put("cardOrientation", "HORIZONTAL");
		layout.put("imageAlignment", "LEFT");
		layout.put("titleFontStyle", Arrays.asList("underline","bold"));
		layout.put("descriptionFontStyle", Arrays.asList("calibri"));
//		layout.put("style", "http://example.com/default.css");
		generalPurposeCard.put("layout", layout);
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> media = new HashMap<String,Object>();
//		media.put("mediaUrl", "https://maaptest.189.cn/maap_message/bot/chanageUrl/temp/20210212111037/171008/12,489e21a8151d.png");
//		media.put("mediaContentType", "image/png");
//		media.put("mediaFileSize", "171008");
//		media.put("thumbnailUrl", "https://maaptest.189.cn/maap_message/bot/chanageUrl/temp/20210212111228/9216/12,489f69eede8a.jpg");
//		media.put("thumbnailContentType", "image/jpg");
//		media.put("thumbnailFileSize", "9216");
		
		media.put("mediaUrl", "https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg");
		media.put("mediaContentType", "image/jpg");
		media.put("mediaFileSize", "178887");
		media.put("thumbnailUrl", "https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg");
		media.put("thumbnailContentType", "image/png");
		media.put("thumbnailFileSize", "178887");
		
		media.put("height", "MEDIUM_HEIGHT");
		media.put("contentDescription", "???????????????????????????");
		content.put("media", media);
		content.put("title", "???????????????????????????");
		content.put("description", "?????????????????????????????????????????????????????????????????????????????????????????????????????????");
		
		content.put("suggestions", Arrays.asList(getReply("??????"),getReply("??????"),getUrlAction(),getDialerAction()));
		generalPurposeCard.put("content", content);
		message.put("generalPurposeCard", generalPurposeCard);
		contentText.put("message", message);
		result.put("contentText", contentText);
		return result;
	}
	
	Map<String,Object> getFile(){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType","application/vnd.gsma.rcs-ft-http");
		result.put("contentEncoding","utf8");
		List<Map<String,Object>> contentText = new ArrayList<>();
		
		Map<String,Object> file = new HashMap<>();
		file.put("type","file");
		file.put("fileSize","178887");
		file.put("fileName","image2.jpg");
		file.put("contentType","image/jpg");
		file.put("url","https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg");
//		file.put("until","2021-02-12T11:10:37Z");
		contentText.add(file);
		
		Map<String,Object> thumbnail = new HashMap<>();
		thumbnail.put("type","thumbnail");
		thumbnail.put("fileSize","178887");
		thumbnail.put("fileName","imaget2.png");
		thumbnail.put("contentType","image/png");
		thumbnail.put("url","https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg");
//		thumbnail.put("until","2021-02-12T11:12:28Z");
		contentText.add(thumbnail);
		result.put("contentText", contentText);
		
		return result;
	}
	
	Map<String,Object> getSug(Map<String,Object>... sugs){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType", "application/vnd.gsma.botsuggestion.v1.0+json");
		Map<String,Object> contentText = new HashMap<>();
		contentText.put("suggestions", sugs);
		result.put("contentText",contentText);
		return result;
	}
	
	Map<String,Object> getDialerAction(){
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> action = new HashMap<>();
		action.put("displayText", "????????????");
		Map<String,String> data = new HashMap<>();
		data.put("data", "????????????data");
		action.put("postback", data);
		Map<String,Object> dialerAction = new HashMap<>();
		Map<String,Object> dialPhoneNumber = new HashMap<>();
		dialPhoneNumber.put("phoneNumber","+8613211112222");
		dialerAction.put("openUrl", dialPhoneNumber);
		result.put("action", action);
		return result;
	}
	
	Map<String,Object> getUrlAction(){
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> action = new HashMap<>();
		action.put("displayText", "????????????");
		Map<String,String> data = new HashMap<>();
		data.put("data", "????????????data");
		action.put("postback", data);
		Map<String,Object> urlAction = new HashMap<>();
		Map<String,Object> openUrl = new HashMap<>();
		openUrl.put("openUrl","http://www.baidu.com");
		urlAction.put("openUrl", openUrl);
		result.put("action", action);
		return result;
	}
	
	Map<String,Object> getReply(String text){
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> replay = new HashMap<>();
		replay.put("displayText", text);
		Map<String,String> data = new HashMap<>();
		data.put("data", text + "data");
		replay.put("postback", data);
		result.put("reply", replay);
		return result;
	}
	
	protected Map<String,Object> getText(String text){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType", "text/plain");
		result.put("contentEncoding", "utf8");
		result.put("contentText", text);
		return result;
	}
	
	protected Map<String,Object> getJSON(Map<String,Object>... message){
		Map<String,Object> map = new HashMap<>();
		map.put("messageId", UUID.randomUUID().toString());//??????ID
		
		map.put("messageList", message);//?????????????????????????????????????????????contentType, contentEncoding???contentText????????????????????????????????????????????????
		
		map.put("destinationAddress", new String[] {phone});//??????URI list??? tel??????
		map.put("senderAddress", getchatbotSip());//???????????????From??????????????????Chatbot???URI??????????????????Chatbot???URI??????????????????
		map.put("conversationId", UUID.randomUUID().toString());//???????????????????????????????????????????????????
		map.put("contributionId", UUID.randomUUID().toString());//??????????????????????????????
		
		Map<String,String> serviceCapability = new HashMap<>();
		serviceCapability.put("capabilityId", "ChatbotSA");
		serviceCapability.put("version", "+g.gsma.rcs.botversion=\"#=1\"");
		map.put("serviceCapability", new Map[] {serviceCapability});//???????????????Chatbot??????????????????????????????ServiceCapability????????????
//		???????????????????????????
//		advertisement
//		payment
//		premium
//		subscription
//		token???token????????????????????????
//		map.put("trafficType", "");
		map.put("smsSupported", false);//??????????????????false:?????????true:????????????false
//		map.put("imFormat", "");//IM ?????????????????????????????? IM LargerMode???PagerMode???????????????IM
//		map.put("inReplyTo", "");//?????????????????????????????????????????????????????????????????????contributionId???
//		????????????????????????????????????????????????????????????:
//		?????????????????????????????????????????????
//		sent??????????????????
//		failed?????????????????????
//		delivered??????????????????
//		displayed??????????????????
//		deliveredToNetwork???????????????????????????
		map.put("reportRequest", new String[] {"sent","delivered","displayed","failed"});
		map.put("storeSupported", true);//?????????????????????false:?????????????????????true:????????????true
//		map.put("smsContent", "");//smsContent????????????????????????????????????smsSupported???true???????????????????????????????????????
		return map;
	}
}
