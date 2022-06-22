package com.example.tabledemo.mapper;

import com.example.tabledemo.pojo.PlatDapertment;
import com.example.tabledemo.pojo.PlatUserCounter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlatUserCounterMapper {
    List<PlatUserCounter> selectAll(@Param("deviceName") String deviceName,@Param("deviceIp") String deviceIp,@Param("userName")String userName,@Param("deptName") String deptName,@Param("start") String start,@Param("end") String end,@Param("prop") String prop,@Param("order") String order);

    //    List<PlatUserCounter> selectAll(@Param("deviceIds") List<String> deviceIds,@Param("userIds") List<String> userIds,@Param("deptIds") List<String> deptIds,@Param("start") String start,@Param("end") String end,@Param("prop") String prop,@Param("order") String order);
    @Select("select DeviceID from plat_user_counter group by DeviceID")
    List<String> getDevices();


    @Select("select * from plat_dapertment")
    List<PlatDapertment> getAll();
}
