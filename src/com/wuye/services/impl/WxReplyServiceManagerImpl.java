package com.wuye.services.impl;


import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.entity.WxReply;
import com.wuye.services.WxReplyServiceManager;
import org.springframework.stereotype.Service;

@Service("wxReplyService")
public class WxReplyServiceManagerImpl extends BaseManagerImpl implements WxReplyServiceManager {


    public WxReply getWxReplyServiceById(int id) {
        WxReply wxReply = (WxReply) this.dao.getObject(WxReply.class, id);
        return wxReply;
    }

}
