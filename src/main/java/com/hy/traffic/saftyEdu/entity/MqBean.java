package com.hy.traffic.saftyEdu.entity;

import org.apache.ibatis.annotations.Param;

public class MqBean {

    public String year(@Param("i")Integer i){
        StringBuffer str=new StringBuffer(" select  * from saftyedu where 1=1 ");

        if(i!=null){
            str.append(" and month(startTime)= "+i+" ");
        }
        return str.toString();
    }

    public String num(@Param("time")String time,@Param("learnType")String learnType){
        StringBuffer str=new StringBuffer(" select  * from saftyedu where 1=1 ");
        System.out.println(time);
        System.out.println(learnType);
            if(time!=null && time!=""){
                str.append(" and date_format(startTime,'%Y')= "+time+"  ");
            }
        if(learnType!=null && learnType!=""){
            str.append(" and learnType= '"+learnType+"' ");
        }

        return str.toString();
    }

}
