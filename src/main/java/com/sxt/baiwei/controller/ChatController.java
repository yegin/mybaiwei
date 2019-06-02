package com.sxt.baiwei.controller;

import com.sxt.baiwei.bean.Hr;
import com.sxt.baiwei.bean.MsgContent;
import com.sxt.baiwei.bean.RespBean;
import com.sxt.baiwei.bean.SysMsg;
import com.sxt.baiwei.service.HrService;
import com.sxt.baiwei.service.SysMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理通知消息的controller
 * 登陆即可访问
 */
@RestController
@RequestMapping("/chat")
public class ChatController  {

    @Autowired
    HrService hrService;

    @Autowired
    SysMsgService sysMsgService;

//    获取所有的除了admin的hrs
    @RequestMapping(value = "/hrs", method = RequestMethod.GET)
    public List<Hr> getAllHr() {
        List<Hr> allHrExceptAdmin = hrService.getAllHrExceptAdmin();
//        for(Hr hr:allHrExceptAdmin){
//            System.out.println(hr);
//        }
        return allHrExceptAdmin;
    }

//    管理员群发消息
    @RequestMapping(value = "/nf", method = RequestMethod.POST)
    public RespBean sendNf(MsgContent msg) {
        if (sysMsgService.sendMsg(msg)) {
            return RespBean.ok("发送成功!");
        }
        return RespBean.error("发送失败!");
    }

//    查询所有群发消息
    @RequestMapping("/sysmsgs")
    public List<SysMsg> getSysMsg(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return sysMsgService.getSysMsgByPage(page, size);
    }
//标记是否已读
    @RequestMapping(value = "/markread", method = RequestMethod.PUT)
    public RespBean markRead(Integer flag) {
        System.out.println(flag);
        if (sysMsgService.markRead(flag)) {
            if (flag == -1) {
                return RespBean.ok("multiple");
            } else {
                return RespBean.ok("single");
            }
        } else {
            if (flag == -1) {
                return RespBean.error("multiple");
            } else {
                return RespBean.error("single");
            }
        }
    }


}
