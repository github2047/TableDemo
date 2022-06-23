package com.example.tabledemo.controller;

import com.example.tabledemo.mapper.PlatUserCounterMapper;
import com.example.tabledemo.pojo.PlatUserCounter;
import com.example.tabledemo.service.PlatUserCounterService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@Slf4j
public class PlatUserCounterController {
    @Autowired
    private PlatUserCounterService platUserCounterService;


@RequestMapping("/")
public String index() {
        return "index";
    }
@RequestMapping(value = "/platUserCounter/selectAll",method = RequestMethod.POST)
@ResponseBody
public Map<Object, Object> selectAll(@RequestBody Map maps) {
    Map<Object,Object> map=new HashMap<>();
    if(maps.get("deviceName")==null || maps.get("deviceName").equals("null")){
        maps.put("deviceName","");
    }
    if(maps.get("deviceIp")==null){
        maps.put("deviceIp","");
    }
    if(maps.get("userName")==null){
        maps.put("userName","");
    }
    if(maps.get("deptName")==null){
        maps.put("deptName","");
    }
    if(!maps.get("start").equals("null")&&!maps.get("start").equals("")&&!maps.get("start").equals("undefined")&&maps.get("start")!=null){
        maps.put("start",maps.get("start")+" 00:00:00");
        maps.put("end",maps.get("end")+" 23:59:59");
    }
    PageInfo<PlatUserCounter> platUserCounterPageInfo=null;
    try {
        platUserCounterPageInfo = platUserCounterService.selectAll((String) maps.get("deviceName"), (String)maps.get("deviceIp"), (String)maps.get("userName"),(String) maps.get("deptName"), (String)maps.get("start"), (String)maps.get("end"), (Integer) maps.get("pageNo"), (Integer) maps.get("pageSize"), (String) maps.get("prop"), (String) maps.get("order"));
        map.put("code",200);
        map.put("data",platUserCounterPageInfo);
    }catch (Exception e) {
        e.printStackTrace();
        log.error("查询失败");
    }
    return map;
}

    //导出excl
    @RequestMapping("/platUserCounter/exportExcl")
    @ResponseBody
    public void prodTest(HttpServletResponse response,@RequestBody Map map) throws IOException {
        // 创建导出文件名称 当前日期+前台传递过来的标题名（excelTitle）
        String fileName =map.get("excelTitle")+"("+DateUtils.format(new Date(),"yyyy-MM-dd")+")"+".xls";
        // 设置返回的消息头和返回值类型 并设置编码 不设置编码文件名为中文的话 不会显示
        response.setCharacterEncoding("utf-8");
        // 当设置成如下返回值时，浏览器才会执行下载文件动作
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
        // 创建输出流，调用service中exportTest方法，参数：输出流 标题名
        List<PlatUserCounter> list= platUserCounterService.getAll((String) map.get("deviceName"), (String) map.get("deviceIp"), (String) map.get("userName"), (String) map.get("deptName"), (String) map.get("start"), (String) map.get("end"),"id","asc");
        platUserCounterService.exportTest(response.getOutputStream(), (String) map.get("excelTitle"), (String) map.get("start"), (String) map.get("end"),list);

    }
}
