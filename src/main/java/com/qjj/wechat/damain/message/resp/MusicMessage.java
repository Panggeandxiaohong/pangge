package com.qjj.wechat.damain.message.resp;

/**
 * 音乐消息
 *
 * @author Jimmy
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
