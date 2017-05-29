package online.pangge.wechat.damain.main;

import online.pangge.wechat.damain.menu.ClickButton;
import online.pangge.wechat.damain.menu.ComplexButton;
import online.pangge.wechat.damain.menu.Menu;
import online.pangge.wechat.damain.menu.ViewButton;
import online.pangge.wechat.damain.menu.Button;
import online.pangge.wechat.pojo.Token;
import online.pangge.wechat.util.CommonUtil;
import online.pangge.wechat.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 菜单管理器类
 *
 * @author Jimmy
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 *
	 * @return
	 */
	private static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("查看错题");
		btn11.setType("click");
		btn11.setKey("ERROR");

		ClickButton btn12 = new ClickButton();
		btn12.setName("查看IP");
		btn12.setType("click");
		btn12.setKey("IP");

		ClickButton btn13 = new ClickButton();
		btn13.setName("查看物品使用");
		btn13.setType("click");
		btn13.setKey("THINGS");

		ViewButton btn21 = new ViewButton();
		btn21.setName("大学主页");
		btn21.setType("view");
		btn21.setUrl("http://www.gxtcmu.edu.cn");

		ViewButton btn22 = new ViewButton();
		btn22.setName("护理主页");
		btn22.setType("view");
		btn22.setUrl("http://www.gxzyxx.com");

		ClickButton btn23 = new ClickButton();
		btn23.setName("开发者主页");
		btn23.setType("click");
		btn23.setKey("SEE_ME");

		ClickButton btn24 = new ClickButton();
		btn24.setName("好评");
		btn24.setType("click");
		btn24.setKey("GOOD");

		ClickButton btn25 = new ClickButton();
		btn25.setName("中评");
		btn25.setType("click");
		btn25.setKey("MIDD");

		ClickButton btn31 = new ClickButton();
		btn31.setName("差评");
		btn31.setType("click");
		btn31.setKey("BAD");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("信息查询");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("友情连接");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("评价");
		mainBtn3.setSub_button(new Button[] { btn24, btn25,  btn31 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn3, mainBtn2 });

		return menu;
	}

	public String main() {
		// 第三方用户唯一凭证
		String appId = "wx68adac9fc5456e79";
		// 第三方用户唯一凭证密钥
		String appSecret = "68abd2f3968074997d6ddb5d9e6e3938";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		String isok="";
		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result){
				log.info("菜单创建成功！");
				isok="菜单创建成功！";
			}
			else{
				log.info("菜单创建失败！");
				isok="菜单创建失败！";
			}
		}
		return isok;
	}
}
