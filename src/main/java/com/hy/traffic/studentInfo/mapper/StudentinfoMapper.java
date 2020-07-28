package com.hy.traffic.studentInfo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hy.traffic.studentInfo.entity.Studentinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.traffic.studentInfo.utils.Provider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

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

    @SelectProvider(type = Provider.class,method = "queryAllStudentInFo")
    IPage<Studentinfo> queryAllStudentInFo(@Param("ipage")IPage<Studentinfo> page , @Param("studentinfo")Studentinfo studentinfo);

    @UpdateProvider(type = Provider.class , method = "updateOneStudent")
    public int updateOneStudent(Integer id,Integer status);

}
