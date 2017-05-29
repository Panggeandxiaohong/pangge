package online.pangge.wechat.damain;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信发送的消息封装
 * @author Jimmy
 * @date 2017-04-04
 */
@Getter
@Setter
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlMessageEntity {
	//接收方账号，即微信公众平台账号
	private String ToUserName;
	//发送方账号
	private String FromUserName;
	//创建消息时间
	private Long CreateTime;
	//消息类型
	private String MsgType;
	//消息内容，文本消息特有
	private String Content;
	//事件类型
	private String Event;
	//消息id
	private Long MsgId;
	// 图片链接
	private String PicUrl;
	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
	private String Url;
	// 地理位置维度
	private String Location_X;
	// 地理位置经度
	private String Location_Y;
	// 地图缩放大小
	private String Scale;
	// 地理位置信息
	private String Label;
	// 视频消息媒体id
	private String MediaId;
	// 视频消息缩略图的媒体id
	private String ThumbMediaId;
	// 语音格式
	private String Format;
	// 语音识别结果，UTF8编码
	private String Recognition;
	@Override
	public String toString() {
		return "XmlMessageEntity [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", Event=" + Event + ", MsgType="
				+ MsgType + ", Content=" + Content + ", MsgId=" + MsgId + "]";
	}

}
