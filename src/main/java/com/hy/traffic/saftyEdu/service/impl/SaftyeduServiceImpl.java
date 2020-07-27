package com.hy.traffic.saftyEdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.traffic.saftyEdu.entity.Bchar;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.saftyEdu.mapper.SaftyeduMapper;
import com.hy.traffic.saftyEdu.service.ISaftyeduService;
import com.hy.traffic.studentaccmq.mapper.StudentaccmqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    StudentaccmqMapper studentaccmqMapper;

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

    public Integer[] thenumber(){
        Integer [] in=new Integer [12];
        for (int i = 0; i < in.length; i++) {
            List<Saftyedu> list=saftyeduMapper.year(i+1);
            int cc=0;
            for (Saftyedu saftyedu : list) {
                String str=saftyedu.getStudent(); //培训人
                Integer sid=saftyedu.getId();
                cc+=studentaccmqMapper.thenumber(str,sid,1);

            }
            in[i]=cc;
        }

        return in;
    }

    public  List<Bchar> num(String learnType){
        System.out.println("测=="+learnType);
        Integer[] in=new Integer[2];
        int nb=0;
        int nb2=0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date=new Date();
        String cc=sdf.format(date);
        List<Saftyedu> list=saftyeduMapper.num(cc,learnType);
        if(list.size()>0){
            System.out.println("集合的长度："+list.size());
            for (Saftyedu saftyedu : list) {
                System.out.println("大哥啊-----------------"+saftyedu.getId());
                nb+=studentaccmqMapper.thenumber(saftyedu.getStudent(),saftyedu.getId(),1);
                nb2+=studentaccmqMapper.thenumber(saftyedu.getStudent(),saftyedu.getId(),2);
            }
        }

        in[0]=nb;
        in[1]=nb2;

        Bchar bchar=new Bchar();
        bchar.setValue(in[0]);
        bchar.setName("完成人数");
        Bchar bchar1=new Bchar();
        bchar1.setValue(in[1]);
        bchar1.setName("未完成人数");
        List<Bchar> mq=new ArrayList<>();
        mq.add(bchar);
        mq.add(bchar1);
        return mq;
    }

    public List<Saftyedu> jhs(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date=new Date();
        String cc=sdf.format(date);
        List<Saftyedu> list=saftyeduMapper.num(cc,null);

        return list;
    }



}
