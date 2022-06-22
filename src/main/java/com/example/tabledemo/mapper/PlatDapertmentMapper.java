//package com.example.tabledemo.mapper;
//
//import com.example.tabledemo.pojo.PlatDapertment;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//
//@Mapper
//public interface PlatDapertmentMapper {
//    @Select("select id,departName from plat_dapertment order by id limit 30")
//    List<PlatDapertment> getIdAndName();
//    PlatDapertment dept(Integer id);
//    @Select("select * from plat_dapertment")
//    List<PlatDapertment> getAll();
//}
