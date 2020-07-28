package com.hy.traffic.saftyEdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hy.traffic.saftyEdu.entity.PageJson;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.saftyEdu.mapper.SaftyeduMapper;
import com.hy.traffic.saftyEdu.service.ISaftyeduService;
import com.hy.traffic.studentInfo.mapper.StudentinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
@Service
public class SaftyeduServiceImpl extends ServiceImpl<SaftyeduMapper, Saftyedu> implements ISaftyeduService {

    @Autowired
    private SaftyeduMapper saftyeduMapper;

    @Autowired
    private StudentinfoMapper studentinfoMapper;

    //默认查询所有并且分页
    @Override
    public PageJson querySaftyedu(Integer currpage, Integer pagesize, PageJson pageJson) {
        Page page = PageHelper.startPage(currpage, pagesize, true);
        pageJson.setData(saftyeduMapper.querySaftyedu());
        pageJson.setCount(page.getTotal());

        return pageJson;
    }

    //根据日期进行条件查询并分页
    @Override
    public PageJson querySaftyeduMonth(String yuefen, Integer currpage, Integer pagesize, PageJson pageJson) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page page = PageHelper.startPage(currpage, pagesize, true);
        pageJson.setData(saftyeduMapper.querySaftyeduMonth(dateFormat.parse(yuefen)));
        pageJson.setCount(page.getTotal());

        return pageJson;
    }


    //根据id查询并分页
    @Override
    public PageJson querySaftyduStudentinfoByid(Integer said,Integer currpage, Integer pagesize,PageJson pageJson) {
        Page page = PageHelper.startPage(currpage, pagesize, true);
        pageJson.setData(studentinfoMapper.queryStudentinfoByid(said));
        pageJson.setCount(page.getTotal());
        return pageJson;
    }
}
