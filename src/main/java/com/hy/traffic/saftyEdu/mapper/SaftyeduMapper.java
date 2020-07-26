package com.hy.traffic.saftyEdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.traffic.saftyEdu.entity.MqBean;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface SaftyeduMapper extends BaseMapper<Saftyedu> {

    @SelectProvider(type = MqBean.class,method = "year")
    public List<Saftyedu> year(Integer i);

}
