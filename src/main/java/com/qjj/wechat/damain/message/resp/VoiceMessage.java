package com.qjj.wechat.damain.message.resp;

/**
 * 语音消息
 *
 * @author Jimmy
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
