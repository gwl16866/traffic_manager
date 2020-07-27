package com.hy.traffic.saftyEdu.mapper;

public class SaftyEduProvider {

    public String selectSaftyEdu(Integer learnType){
        StringBuffer sf=new StringBuffer();
        if(learnType == 9){
            sf.append("select * from saftyedu");
        }else{
            sf.append("select * from saftyedu where learnType="+learnType);
        }
        return  sf.toString();
    }
}
