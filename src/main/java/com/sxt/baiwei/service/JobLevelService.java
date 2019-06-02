package com.sxt.baiwei.service;

import com.sxt.baiwei.bean.JObLevel;
import com.sxt.baiwei.mapper.JObLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobLevelService {

    @Autowired
    JObLevelMapper jObLevelMapper;

    public List<JObLevel> getAllJoblevel() {
        return jObLevelMapper.getAllJoblevel();
    }

    public Integer addJobLevel(JObLevel jObLevel) {
        jObLevel.setCreatedate(new Date());
        return jObLevelMapper.insertSelective(jObLevel);
    }

    public Integer deleJobLevel(Integer id) {
        return jObLevelMapper.deleteByPrimaryKey(id);
    }

    public Integer updatebLevel(JObLevel jObLevel) {
        return  jObLevelMapper.updateByPrimaryKeySelective(jObLevel);
    }

    public Integer addJobLevelList(List<JObLevel> list) {

        return jObLevelMapper.addJobLevelList(list);
    }
}
