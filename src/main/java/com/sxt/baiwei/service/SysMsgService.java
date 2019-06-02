package com.sxt.baiwei.service;

import com.sxt.baiwei.bean.Hr;
import com.sxt.baiwei.bean.MsgContent;
import com.sxt.baiwei.bean.SysMsg;
import com.sxt.baiwei.Utils.HrUtils;
import com.sxt.baiwei.mapper.SysMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理消息服务的service
 */
@Service
@Transactional
public class SysMsgService {
    @Autowired
    SysMsgMapper sysMsgMapper;

    @Autowired
    HrService hrService;

    //只有管理员可以发送系统消息
    @PreAuthorize("hasRole('ROLE_admin')")
    public boolean sendMsg(MsgContent msg) {
        int result = sysMsgMapper.sendMsg(msg);
        List<Hr> allHr = hrService.getAllHr();
//        将消息给查找到用户
        int result2 = sysMsgMapper.addMsg2AllHr(allHr, msg.getId());
        return result2==allHr.size();
    }

//    将查询到的系统消息分页
    public List<SysMsg> getSysMsgByPage(Integer page, Integer size) {
        int start = (page - 1) * size;
        return sysMsgMapper.getSysMsg(start,size, HrUtils.getCurrentHr().getId());
    }

    public boolean markRead(Integer flag) {
        if (flag != -1) {
            return sysMsgMapper.markRead(flag,HrUtils.getCurrentHr().getId())==1;
        }
        sysMsgMapper.markRead(flag,HrUtils.getCurrentHr().getId());
        return true;
    }


}
