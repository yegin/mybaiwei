package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.Hr;
import com.sxt.baiwei.bean.MsgContent;
import com.sxt.baiwei.bean.SysMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMsg record);

    int insertSelective(SysMsg record);

    SysMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMsg record);

    int updateByPrimaryKey(SysMsg record);

    //发送消息
    int sendMsg(MsgContent msg);

    int addMsg2AllHr(@Param("hrs") List<Hr> hrs, @Param("mid") Integer mid);

    List<SysMsg> getSysMsg(@Param("start") int start, @Param("size") Integer size,@Param("hrid") Integer hrid);

    int markRead(@Param("flag") Integer flag, @Param("hrid") Integer hrid);
}