//package com.example.tabledemo.mapper;
//
//import com.example.tabledemo.pojo.PlatUser;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//
//@Mapper
//public interface PlatUserMapper {
//    @Select("select id,userName from plat_user where userName like '%${name}%'")
//    List<PlatUser> getIdAndName(String name);
//}
