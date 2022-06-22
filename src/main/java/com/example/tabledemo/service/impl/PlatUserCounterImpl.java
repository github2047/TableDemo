package com.example.tabledemo.service.impl;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.example.tabledemo.mapper.PlatUserCounterMapper;
import com.example.tabledemo.pojo.PlatDapertment;
import com.example.tabledemo.pojo.PlatUserCounter;
import com.example.tabledemo.service.PlatUserCounterService;
import com.example.tabledemo.utils.ExportExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlatUserCounterImpl implements PlatUserCounterService {
    @Autowired
    private PlatUserCounterMapper platUserCounterMapper;
    @Override
    public PageInfo<PlatUserCounter> selectAll(String deviceName, String deviceIp, String userName, String deptName, String start, String end, Integer pageNo, Integer pageSize, String prop, String order) {
        if(pageNo==null ||pageSize==null){
            pageNo=1;
            pageSize=15;
        }
        if(prop==null ||prop.equals("undefined")){
            prop="c.id";
        }else if(prop.equals("userName")){
            prop="u.userName";
        }else if(prop.equals("deptName")){
            prop="d.deptName";
        }else{
            prop="c."+prop;
        }
        if(null==order||order.equals("undefined")||order.equals("")){
            order="asc";
        }
        if(order.equals("ascending")) {
            order = "asc";
        }
        if(order.equals("descending")) {
            order = "desc";
        }

        if(order.equals("null") ){
            prop="c.id";
            order = "asc";
        }
        PageHelper.startPage(pageNo, pageSize);
        List<PlatUserCounter> list = platUserCounterMapper.selectAll(deviceName,deviceIp, userName, deptName, start, end,prop,order);
        if(list.size()>0) {
            List<PlatDapertment> deptAll = platUserCounterMapper.getAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getParentId() != 0) {
                    String name = getDept(deptAll, list.get(i).getParentId());
                    list.get(i).setDeptName(name + list.get(i).getDeptName());
                }
            }
        }
        PageInfo<PlatUserCounter> platUserCounterPageInfo=new PageInfo<>(list);
        return platUserCounterPageInfo;
    }
    public String getDept(List<PlatDapertment> list, Integer parentId){
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

    @Override
    public void exportTest(OutputStream out, String excelTitle,String start,String end,List<PlatUserCounter> list) {
        // 定义列标
        String[] rowsName = new String[]{"id", "设备Mac地址","设备名称","设备IP", "用户名", "部门名", "总数", "彩色总数", "扫描总数", "单色总数", "以全彩复制的总页数", "已发送传真中的总页数", "已接收传真中的总页数", "双工总数", "显示原始页面的总数", "以黑色复制的总页数", "已打印的纸张总数", "所有颜色打印的总页数", "以全彩打印的总页数", "完成时间", "是否需要上传至中央服务器"};
        // 创建导出数据集合 后续会将dataList中的数据写到Excel
        List<Object[]> dataList = new ArrayList<Object[]>();
        List<PlatUserCounter> platUserCounterList = list;
        PlatUserCounter platUserCounter = null;
        // 将用户列表信息封装到一个Object数组
        // 我这里封装Object数组 是为了方便后续代码复用,不会将对象类型写死
        // 当然也可以在下一层使用反射来做,太麻烦了 还是这样转一下吧
        for (int i=0;i<list.size();i++){
            //将数据库查到的每条数据 循环封装到Object[]
            platUserCounter=list.get(i);
            switch (platUserCounter.getIsReport()){
                case "1": platUserCounter.setIsReport("已上传");break;
                case "2": platUserCounter.setIsReport("已更新需要上传");break;
            }
            Object[] objs = new Object[]{platUserCounter.getId(),platUserCounter.getDeviceId(),platUserCounter.getDeviceName(),platUserCounter.getDeviceIp(),platUserCounter.getUserName(),platUserCounter.getDeptName(),platUserCounter.getTotal(),platUserCounter.getColorTotal(),platUserCounter.getScanFaxScans(),
            platUserCounter.getMonochromeTotal(),platUserCounter.getCopyFullColor(),platUserCounter.getFaxTx(),platUserCounter.getFaxRx(),platUserCounter.getDuplexTotal(),platUserCounter.getNumberOfOriginals(),platUserCounter.getCopyBlackLarge(),platUserCounter.getSheetsOfPrints(),
            platUserCounter.getPrintTotalLarge(),platUserCounter.getPrintFullColorLarge(),platUserCounter.getRectime(),platUserCounter.getIsReport()};
            //将转换好的数据 存入dataList
            dataList.add(objs);
        }
        // 创建ExportExcel工具类对象 通过构造方法赋值
        ExportExcel ex = new ExportExcel(excelTitle, rowsName,start,end, dataList);
        try {
            // 调用生成Excel的方法,将数据通过输出流写出
            ex.export(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlatUserCounter> getAll(String deviceName, String deviceIp, String userName, String deptName, String start, String end, String prop, String order) {
        if(prop==null ||prop.equals("undefined")){
            prop="c.id";
        }else if(prop.equals("userName")){
            prop="u.userName";
        }else if(prop.equals("deptName")){
            prop="d.deptName";
        }else{
            prop="c."+prop;
        }
        if(null==order||order.equals("undefined")||order.equals("")){
            order="asc";
        }
        if(order.equals("ascending")) {
            order = "asc";
        }
        if(order.equals("descending")) {
            order = "desc";
        }

        if(order.equals("null") ){
            prop="c.id";
            order = "asc";
        }
        List<PlatUserCounter> list = platUserCounterMapper.selectAll(deviceName,deviceIp, userName, deptName, start, end,prop,order);
        if(list.size()>0) {
            List<PlatDapertment> deptAll = platUserCounterMapper.getAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getParentId() != 0) {
                    String name = getDept(deptAll, list.get(i).getParentId());
                    list.get(i).setDeptName(name + list.get(i).getDeptName());
                }
            }
        }
        return list;
    }
}

