package com.hy.traffic.saftyEdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.traffic.saftyEdu.entity.SaftyduStudentinfo;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface SaftyeduMapper extends BaseMapper<Saftyedu> {

    //默认查询所有并且分页
    @Select("SELECT * FROM saftyedu")
    List<Saftyedu> querySaftyedu();
    //根据日期进行条件查询并分页
    @Select("SELECT * FROM saftyedu WHERE DATE_FORMAT(startTime,'%m')=DATE_FORMAT(#{value},'%m')")
    List<Saftyedu> querySaftyeduMonth(Date yuefen);

    @Select("Select * from saftydustudentinfo where said=#{value}")
    List<SaftyduStudentinfo> querySaftyduStudentinfoByid(Integer said);

    //默认查询所有并且分页
    @Select("SELECT * FROM saftyedu where id=#{value}")
    Saftyedu querySaftyeduByid(Integer id);

}
