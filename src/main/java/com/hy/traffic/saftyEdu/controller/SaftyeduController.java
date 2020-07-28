package com.hy.traffic.saftyEdu.controller;

import com.hy.traffic.saftyEdu.ResultData;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.saftyEdu.service.impl.SaftyeduServiceImpl;
import com.hy.traffic.studentInfo.entity.Studentinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
@RestController
@RequestMapping("/saftyEdu")
public class SaftyeduController {
    @Autowired
    SaftyeduServiceImpl saftyeduService;

    //查询
    @CrossOrigin
    @RequestMapping("/selectSaftyEdu")
    public ResultData selectSaftyEdu(Saftyedu saftyedu, Integer learnType) {
        System.out.println(learnType + "=====================" + learnType);

        List<Saftyedu> saftyeduList = saftyeduService.selectSaftyEdu(learnType);

        for (Saftyedu a : saftyeduList) {
            //查询参训学员人数
            a.setCount(saftyeduService.selectStudentCount(a.getId()));
            //修改培训状态
            Date date = new Date();
            if (date.compareTo(a.getStartTime()) > 0 && date.compareTo(a.getEndTime()) < 0) {

            }else{
                a.setStatus(2);
                saftyeduService.updateStatus(a.getId(),a.getStatus());
            }
        }

        ResultData resultData = new ResultData();
        resultData.setData(saftyeduList);
        return resultData;
    }

//查询参训学员
    @CrossOrigin
    @RequestMapping("/selectStudent")
    public ResultData selectStudent(Integer id) {
        List<Studentinfo> studentinfoList=saftyeduService.selectStudent(id);

        ResultData resultData = new ResultData();
        resultData.setData(studentinfoList);
        return resultData;
    }

    //在安全教育中删除学员
    @CrossOrigin
    @RequestMapping("/deleteStudent")
    public Integer deleteStudent(Integer studentid,Integer saftyid) {
        System.out.println(saftyid+studentid);
        try {
           saftyeduService.deleteStudent(studentid,saftyid);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    //查询所有学员
    @CrossOrigin
    @RequestMapping("/selectAllStudent")
    public ResultData  selectAllStudent(Integer saftyid){
        List<Studentinfo> saftyeduList=saftyeduService.selectAllStudent(saftyid);
        ResultData resultData=new ResultData();
        resultData.setData(saftyeduList);
        return resultData;
    }


    //批量添加学员
    @CrossOrigin
    @RequestMapping("/batchAddStudent")
    public Integer batchAddStudent(Integer saftyid,Integer[] batchList) {
        try {
            for (int i = 0; i < batchList.length; i++) {
                saftyeduService.batchAddStudent(saftyid,batchList[i]);
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
