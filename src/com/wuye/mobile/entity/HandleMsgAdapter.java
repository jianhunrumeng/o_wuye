package com.wuye.mobile.entity;

import com.wuye.common.util.SpringUtil;
import com.wuye.entity.WxReply;
import com.wuye.services.WxReplyServiceManager;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.marker.weixin.HandleMessageListener;
import org.marker.weixin.msg.*;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

public class HandleMsgAdapter implements HandleMessageListener {

    private WxReplyServiceManager wxReplyService = SpringUtil.getBean("wxReplyService");

    private WuYeSession session = null;

    public HandleMsgAdapter(WuYeSession session) {
        this.session = session;
    }

    public void onTextMsg(Msg4Text msg, String url) {

        System.out.println("收到的信息：" + msg.getContent());
        Msg reply = null;
        String infoType = "text";
        String info = msg.getContent();
        reply = createReplyNewsMsg(msg, url);
        reply.setFromUserName(msg.getToUserName());
        reply.setToUserName(msg.getFromUserName());

        session.callback(reply, null, null);
        return;
    }

    /**
     * 构建newsMsg
     *
     * @param replyinfo
     * @return
     */
    private Msg createReplyNewsMsg(Msg msg, String url) {
        WxReply wxReply = wxReplyService.getWxReplyServiceById(5);
        if (wxReply == null) {
            wxReply = new WxReply();
        }
        Msg4ImageText imageText = new Msg4ImageText();
        String content = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Articles>";
        StringReader read = null;
        String cont = "<item><Title>" + wxReply.getTitle()
                + "</Title><Description>" + wxReply.getDescription()
                + "</Description><PicUrl>" + url + wxReply.getPicUrl()
                + "</PicUrl><Url>" + url + wxReply.getUrl()
                + "&amp;wxid=" + msg.getFromUserName()
                + "</Url></item>";
        cont = cont + "</Articles>";
        read = new StringReader(content + cont);
        System.out.println("拼装图文信息报文" + content + cont);
        InputSource source = new InputSource(read);
        SAXBuilder sb = new SAXBuilder();
        try {
            Document doc = sb.build(source);
            List articles = doc.getRootElement().getChildren();
            Element item = null;
            for (int i = 0; i < articles.size(); i++) {
                Data4Item dataItem = new Data4Item();
                item = (Element) articles.get(i);
                dataItem.setTitle(item.getChild("Title").getText());
                dataItem.setDescription(item.getChild("Description").getText());
                dataItem.setPicUrl(item.getChild("PicUrl").getText());
                dataItem.setUrl(item.getChild("Url").getText());
                imageText.addItem(dataItem);
            }
            imageText.setCreateTime(String.valueOf(new Date().getTime()));
            // imageText.setFuncFlag("0");
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageText;
    }

    public void onErrorMsg(int arg0) {
        // TODO Auto-generated method stub

    }

    public void onEventMsg(Msg4Event arg0) {
        // TODO Auto-generated method stub

    }

    public void onImageMsg(Msg4Image arg0) {
        // TODO Auto-generated method stub

    }

    public void onLinkMsg(Msg4Link arg0) {
        // TODO Auto-generated method stub

    }

    public void onLocationMsg(Msg4Location arg0) {
        // TODO Auto-generated method stub

    }

    public void onTextMsg(Msg4Text arg0) {
        // TODO Auto-generated method stub

    }

    public void onVideoMsg(Msg4Video arg0) {
        // TODO Auto-generated method stub

    }

    public void onVoiceMsg(Msg4Voice arg0) {
        // TODO Auto-generated method stub

    }


}
