package com.example.tabledemo.service;

import com.example.tabledemo.pojo.PlatDapertment;
import com.example.tabledemo.pojo.PlatUserCounter;
import com.github.pagehelper.PageInfo;

import java.io.OutputStream;
import java.util.List;

public interface PlatUserCounterService {
//    PageInfo<PlatUserCounter> selectAll(List<String> deviceIds, List<String> userIds, List<String> deptIds, String start, String end, Integer pageNo, Integer pageSize,String prop,String order);
    PageInfo<PlatUserCounter> selectAll(String deviceName,String deviceIp, String userName, String deptName, String start, String end, Integer pageNo, Integer pageSize,String prop,String order);

    void exportTest(OutputStream out, String excelTitle,String start,String end, List<PlatUserCounter> list);
    List<PlatUserCounter> getAll(String deviceName,String deviceIp, String userName, String deptName, String start, String end,String prop,String order);

}
