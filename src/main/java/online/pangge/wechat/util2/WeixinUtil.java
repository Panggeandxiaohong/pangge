package online.pangge.wechat.util2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class WeixinUtil {

	public static final String APPID = "wx35a234c06d4fb604";
	public static final String APPSECRET = "a2df696156bf5cb5bb670b1b3cc15cd7";
	public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	public static final String WEB_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static String accessToken;
	public static long expiresTime;

	public static String getAccessToken() {
		//如果accessToken为空或者失效时间小于当前时间,则需要重新更新accessToken
		if (accessToken == null || expiresTime < new Date().getTime()) {
			// 获取access_token
			String str = HttpUtil.get(ACCESS_TOKEN_URL.replace("APPID", APPID)
					.replace("APPSECRET", APPSECRET));
			System.out.println(str);
			JSONObject json = JSON.parseObject(str);
			accessToken = json.getString("access_token");
			Long temp = json.getLong("expires_in");// 7200
			// 使用当前时间毫秒值 + 有效期的毫秒值 = 失效时间
			expiresTime = new Date().getTime() + (temp - 60) * 1000;
		}
		return accessToken;
	}
	
	public static void createMenu() {
		String result = HttpUtil.post(CREATE_MENU_URL.replace("ACCESS_TOKEN",getAccessToken()),
				"{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{	\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}");
		System.out.println(result);
	}
	
	public static void sendTemplate(){
		String result = HttpUtil.post(TEMPLATE_URL.replace("ACCESS_TOKEN", getAccessToken()), 
				"{\"touser\":\"oCQfft6jLxU4M_xP5wJln5wb1VQM\",\"template_id\":\"W6YqCyQmNwo2x-zRCZQN4nS3lbpG7v01aPbBVScr-GY\",\"url\":\"http://weixin.qq.com/download\",\"data\":{\"first\":{\"value\":\"恭喜你购买成功！\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"巧克力\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"39.8元\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"2014年9月22日\",\"color\":\"#173177\"},\"remark\":{\"value\":\"欢迎再次购买！\",\"color\":\"#173177\"}}}");
		System.out.println(result);
	}
	

	public static void main(String[] args) {
		//删除菜单
		/*String result = HttpUtil.get(DELETE_MENU_URL.replace("ACCESS_TOKEN",getAccessToken()));
		System.out.println(result);*/
		//创建菜单
		//createMenu();
		//发送模版消息
		//sendTemplate();
		//System.out.println(getAccessToken());
		
		//获取ticket
		String result = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi".replace("ACCESS_TOKEN", getAccessToken()));
		System.out.println(result);
	}
}
