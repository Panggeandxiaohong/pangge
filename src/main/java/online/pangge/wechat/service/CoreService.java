package online.pangge.wechat.service;

import online.pangge.exam.domain.Subject;
import online.pangge.exam.service.IStudentService;
import online.pangge.exam.service.ISubjectService;
import online.pangge.exam.util.ExamConst;
import online.pangge.exam.util.OSSUtil;
import online.pangge.exam.util.RedisUtil;
import online.pangge.wechat.damain.XmlMessageEntity;
import online.pangge.wechat.damain.message.resp.Article;
import online.pangge.wechat.damain.message.resp.NewsMessage;
import online.pangge.wechat.damain.message.resp.TextMessage;
import online.pangge.wechat.util.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 核心服务类,由此类去调用其它类
 *
 * @author Jimmy
 * @date 2017-04-04
 */
@Service
public class CoreService {
    /**
     * 处理微信发来的请求
     *
     * @return xml
     */
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OSSUtil ossUtil;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ISubjectService subjectService;

    public String processRequest(XmlMessageEntity entity) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try {
            // 调用parseXml方法解析请求消息
            //Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = entity.getFromUserName();
            // 开发者微信号
            String toUserName = entity.getToUserName();//requestMap.get("ToUserName");
            // 消息类型
            String msgType = entity.getMsgType();//requestMap.get("MsgType");
            //消息内容
            String msg = entity.getContent();//requestMap.get("Content");
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            String key = null;
            String responseStr = null;
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                String redisKey = (String) redisUtil.get("key");
                if (StringUtils.isEmpty(redisKey)) {
                    if (msg.contains("自测")) {
                        redisUtil.set("key", "exam", 3600L);
                        responseStr = "开始自测。。。";
                    } else if (msg.contains("统计")) {
                        redisUtil.set("key", "count", 3600L);
                        responseStr = "真正的开始统计。。。";
                    } else if (msg.contains("练习")) {
                        redisUtil.remove(fromUserName);
                        redisUtil.set("key", "exercise", 3600L);
                        List<Subject> allSubject = subjectService.selectAll();
                        for (Subject s : allSubject) {
                            redisUtil.setSubject(fromUserName + ExamConst.exam_type_exercise, s);
                        }
                        responseStr = "开始练习。。。";
                    } else {
                        responseStr = "请按套路出牌";
                    }
                } else {
                    if ("退出".equals(msg)) {
                        redisUtil.remove("key");
                        redisUtil.remove(fromUserName + ExamConst.exam_type_exercise);
                        responseStr = "退出成功。。。";
                    } else if ("count".equals(redisKey)) {
                        responseStr = "统计中。。。";
                    } else if ("exercise".equals(redisKey)) {
                        if (!redisUtil.exists(fromUserName + ExamConst.exam_type_exercise)) {
                            redisUtil.remove("key");
                            respContent = "你的分数不及格！";
                            // 设置文本消息的内容
                            textMessage.setContent(respContent);
                            // 将文本消息对象转换成xml
                            respXml = MessageUtil.messageToXml(textMessage);
                            return respXml;
                        }
                        int subjectNumber = 1;
                        if (!redisUtil.exists(fromUserName + "subjectNumber")) {
                            redisUtil.set(fromUserName + "subjectNumber", 1);
                        } else {
                            subjectNumber = Integer.valueOf(redisUtil.get("subjectNumber").toString());
                        }
                        Subject beforeSubject = redisUtil.getSubject(fromUserName + ExamConst.exam_type_temp);
                        beforeSubject.setUserAnswer(msg);
                        redisUtil.setSubject(fromUserName + ExamConst.exam_type_answer, beforeSubject);
                        return getNewsMessageXML(fromUserName, toUserName, subjectNumber);
                    } else if ("exam".equals(redisKey)) {
                        responseStr = "考试中。。。";
                    }
                }
                respContent = responseStr;
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                Article article = new Article();
                article.setTitle("开源中国");
                article.setDescription("开源中国社区成立于2008年8月，是目前中国最大的开源技术社区。\n\n开源中国的目的是为中国的IT技术人员提供一个全面的、快捷更新的用来检索开源软件以及交流开源经验的平台。\n\n经过不断的改进,目前开源中国社区已经形成了由开源软件库、代码分享、资讯、讨论区和博客等几大频道内容。");
                article.setPicUrl("");
                article.setUrl("http://m.oschina.net");
                List<Article> articleList = new ArrayList<Article>();
                articleList.add(article);
                // 创建图文消息
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                newsMessage.setArticleCount(articleList.size());
                newsMessage.setArticles(articleList);
                respXml = MessageUtil.messageToXml(newsMessage);
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！消息id是" + entity.getRecognition();
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = entity.getEvent();//requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件

                    String eventKey = entity.getEvent();//requestMap.get("EventKey");
                    // 根据key值判断用户点击的按钮
                    if (eventKey.equals("V1001_HELLO_WORLD")) {
                        Article article = new Article();
                        article.setTitle("开源中国");
                        article.setDescription("开源中国社区成立于2008年8月，是目前中国最大的开源技术社区。\n\n开源中国的目的是为中国的IT技术人员提供一个全面的、快捷更新的用来检索开源软件以及交流开源经验的平台。\n\n经过不断的改进,目前开源中国社区已经形成了由开源软件库、代码分享、资讯、讨论区和博客等几大频道内容。");
                        article.setPicUrl("");
                        article.setUrl("http://m.oschina.net");
                        List<Article> articleList = new ArrayList<Article>();
                        articleList.add(article);
                        // 创建图文消息
                        NewsMessage newsMessage = new NewsMessage();
                        newsMessage.setToUserName(fromUserName);
                        newsMessage.setFromUserName(toUserName);
                        newsMessage.setCreateTime(new Date().getTime());
                        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                        newsMessage.setArticleCount(articleList.size());
                        newsMessage.setArticles(articleList);
                        respXml = MessageUtil.messageToXml(newsMessage);
                    } else if (eventKey.equals("iteye")) {
                        textMessage.setContent("ITeye即创办于2003年9月的JavaEye,从最初的以讨论Java技术为主的技术论坛，已经逐渐发展成为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com");
                        respXml = MessageUtil.messageToXml(textMessage);
                    }
                }
            }
            // 设置文本消息的内容
            textMessage.setContent(respContent);
            // 将文本消息对象转换成xml
            respXml = MessageUtil.messageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }

    private String getNewsMessageXML(String fromUserName, String toUserName, int s) {
        Article article = new Article();
        article.setTitle("第"+s+"题：");
        article.setDescription("");
        article.setPicUrl("");
        article.setUrl("http://39.108.2.41/exam.do");
        List<Article> articleList = new ArrayList<Article>();
        articleList.add(article);
        // 创建图文消息
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }
}
