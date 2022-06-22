package com.example.tabledemo;

import com.example.tabledemo.mapper.PlatUserCounterMapper;
import com.example.tabledemo.pojo.PlatDapertment;
import com.example.tabledemo.pojo.PlatUserCounter;
import com.example.tabledemo.service.PlatUserCounterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class TableDemoApplicationTests {
    @Autowired
    private PlatUserCounterMapper platUserCounterMapper;

    @Autowired
    private PlatUserCounterService platUserCounterService;

    @Test
    void contextLoads() {
        List<PlatDapertment> deptAll = platUserCounterMapper.getAll();
        List<PlatUserCounter> list= platUserCounterMapper.selectAll("","","","","","","c.id","asc");
        for (int i = 0; i < 15; i++) {
            if(list.get(i).getParentId()!=0){
                String name = getDept(deptAll, list.get(i).getParentId());
                list.get(i).setDeptName(name+list.get(i).getDeptName());
            }
        }
    }

    public String getDept(List<PlatDapertment> list,Integer parentId){
        String name="";
        for (PlatDapertment platDapertment : list) {
            if(platDapertment.getId()==parentId){
                name=platDapertment.getDepartName()+"/";
                if(platDapertment.getParentId()!=0){
                    name=getDept(list, (int) platDapertment.getParentId())+name;
                }
            }
        }
        return name;
    }
}
