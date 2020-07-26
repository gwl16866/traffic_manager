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
}
