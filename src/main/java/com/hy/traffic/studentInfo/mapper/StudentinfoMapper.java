package com.hy.traffic.studentInfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.studentInfo.entity.Studentinfo;
import com.hy.traffic.studentInfo.provider.StudentinfoProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
@Mapper
public interface StudentinfoMapper extends BaseMapper<Studentinfo> {

    //根据said查数据
    @Select("select * from studentinfo where id in(select stuid from saftydustudentinfo where said= #{value})")
    List<Studentinfo> queryStudentinfoByid(Integer said);

    //根据said查数据
    @Select("select * from saftyedu where id in(select said from saftydustudentinfo where stuid= #{value})")
    List<Saftyedu> queryStudentinfoBystuid(Integer stuid);

    @SelectProvider(type = StudentinfoProvider.class , method = "queryByid")
     List<Studentinfo> queryByid(Integer said, String select , String neirong);

    @Select("select * from studentinfo where id=#{value}")
    Studentinfo querystuByid(Integer id);

}
