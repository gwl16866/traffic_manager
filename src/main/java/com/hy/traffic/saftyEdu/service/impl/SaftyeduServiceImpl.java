package com.hy.traffic.saftyEdu.service.impl;

import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.saftyEdu.mapper.SaftyeduMapper;
import com.hy.traffic.saftyEdu.service.ISaftyeduService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
@Service
public class SaftyeduServiceImpl extends ServiceImpl<SaftyeduMapper, Saftyedu> implements ISaftyeduService {

    @Autowired
    SaftyeduMapper saftyeduMapper;

    public Integer[] year(){
        Integer [] in=new Integer [12];
        for (int i = 0; i < in.length; i++) {
            List<Saftyedu> list=saftyeduMapper.year(i+1);
            int cc=0;
            for (Saftyedu saftyedu : list) {
                String str=saftyedu.getStudent();
                String [] le=str.split(",");
                cc+=le.length;
            }
            in[i]=cc;
        }
        return in;
    }




}
