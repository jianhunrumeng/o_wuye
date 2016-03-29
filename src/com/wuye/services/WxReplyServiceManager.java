package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.entity.WxReply;

public interface WxReplyServiceManager extends BaseManager {
    WxReply getWxReplyServiceById(int id);
}
