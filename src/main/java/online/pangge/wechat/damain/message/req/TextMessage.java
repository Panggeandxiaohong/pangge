package online.pangge.wechat.damain.message.req;

/**
 * 文本消息
 *
 * @author Jimmy
 */
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
