package com.hy.traffic.saftyEdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.traffic.saftyEdu.entity.PageJson;
import com.hy.traffic.saftyEdu.entity.Saftyedu;

import java.text.ParseException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
public interface ISaftyeduService extends IService<Saftyedu> {

    //默认查询所有并且分页
    PageJson querySaftyedu(Integer firstIndex, Integer lastIndex, PageJson pageJson);
    //根据日期进行条件查询并分页
    PageJson querySaftyeduMonth(String yuefen,Integer currpage, Integer pagesize, PageJson pageJson) throws ParseException;

    //根据said查数据
    PageJson querySaftyduStudentinfoByid(Integer said,Integer currpage, Integer pagesize,PageJson pageJson);

}
