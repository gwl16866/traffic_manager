package com.hy.traffic.saftyEdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.studentInfo.entity.Studentinfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SaftyeduMapper extends BaseMapper<Saftyedu> {

    @SelectProvider(type =SaftyEduProvider.class,method ="selectSaftyEdu" )
    public List<Saftyedu> selectSaftyEdu(Integer learnType);

    //查询参训人数
    @Select("select count(*) from studentacc where saftyid=#{id}")
    public Integer selectStudentCount(Integer id);

    //修改状态
    @Update("update saftyedu set status=#{status} where id=#{id}")
    public void updateStatus(Integer id,Integer status);


    //根据安全教育课程查询学生
    @Select("SELECT sa.*,si.*  FROM studentinfo si,studentacc sa,saftyedu se WHERE si.id=sa.studentid AND sa.saftyid=se.id AND se.id=#{id}")
    public List<Studentinfo> selectStudent(Integer id);

    //在安全教育中删除学生
    @Delete("delete from studentacc where studentid=${studentid} and saftyid=${saftyid}")
    public void deleteStudent(Integer studentid,Integer saftyid);

    //查询所有学员
    @Select("select * from studentinfo where status=1 and headImgStatus=5 and id not in(select studentid from studentacc where saftyid=#{saftyid})")
    public List<Studentinfo> selectAllStudent(Integer saftyid);

    //批量添加学员
    @Insert("insert into studentacc (studentid,saftyid,completion) values(#{studentid},#{saftyid},1)")
    public void batchAddStudent(Integer saftyid,Integer studentid);
}
