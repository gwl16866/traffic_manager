package com.hy.traffic.saftyEdu.controller;

import com.hy.traffic.saftyEdu.ResultData;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.saftyEdu.entity.Saftyedutwo;
import com.hy.traffic.saftyEdu.entity.Tree;
import com.hy.traffic.saftyEdu.service.impl.SaftyeduServiceImpl;
import com.hy.traffic.studentInfo.entity.Studentinfo;
import com.hy.traffic.teachInfo.entity.ClassDetail;
import com.hy.traffic.teachInfo.service.impl.TeachinfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    TeachinfoServiceImpl teachinfoService;

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

            } else {
                a.setStatus(2);
                saftyeduService.updateStatus(a.getId(), a.getStatus());
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
        List<Studentinfo> studentinfoList = saftyeduService.selectStudent(id);

        ResultData resultData = new ResultData();
        resultData.setData(studentinfoList);
        return resultData;
    }

    //在安全教育中删除学员
    @CrossOrigin
    @RequestMapping("/deleteStudent")
    public Integer deleteStudent(Integer studentid, Integer saftyid) {
        System.out.println(saftyid + studentid);
        try {
            saftyeduService.deleteStudent(studentid, saftyid);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    //查询所有学员  未被当前活动添加过的学员
    @CrossOrigin
    @RequestMapping("/selectAllStudent")
    public ResultData selectAllStudent(Integer saftyid) {
        List<Studentinfo> saftyeduList = saftyeduService.selectAllStudent(saftyid);
        ResultData resultData = new ResultData();
        resultData.setData(saftyeduList);
        return resultData;
    }

    //查询所有学员
    @CrossOrigin
    @RequestMapping("/selectAllStu")
    public ResultData selectAllStu() {
        List<Studentinfo> saftyeduList = saftyeduService.selectAllStu();
        ResultData resultData = new ResultData();
        resultData.setData(saftyeduList);
        return resultData;
    }

    //批量添加学员
    @CrossOrigin
    @RequestMapping("/batchAddStudent")
    public Integer batchAddStudent(Integer saftyid, Integer[] batchList) {
        try {
            for (int i = 0; i < batchList.length; i++) {
                saftyeduService.batchAddStudent(saftyid, batchList[i]);
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    //查询所有课程
    @CrossOrigin
    @RequestMapping("/treeList")
    public List<Tree> treeList() {

        List<Tree> treeList = saftyeduService.queryclassDetail();

        return treeList;
    }

    //添加培训
    @CrossOrigin
    @RequestMapping("/addSaftyEdu")
    public Integer addSaftyEdu(@RequestBody Saftyedutwo addEdu) {
        try {
            StringBuilder sr = new StringBuilder();
            for (int i = 0; i < addEdu.getLession().length; i++) {
                sr.append(addEdu.getLession()[i]);
                if (i < addEdu.getLession().length - 1) {
                    sr.append(",");
                }
            }
            //添加培训
            saftyeduService.addSaftyEdu(addEdu.getTheme(), addEdu.getStartTime(), addEdu.getEndTime(), sr.toString(), addEdu.getManager(), addEdu.getTestPeople(), addEdu.getLearnType(), addEdu.getLearnTime());
            //查询最大Id
            Integer maxId = saftyeduService.selectMaxId();
            //添加参训人员
            for (int i = 0; i < addEdu.getStudent().length; i++) {
                saftyeduService.batchAddStudent(maxId, addEdu.getStudent()[i]);
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    //删除培训
    @CrossOrigin
    @RequestMapping("/deleteSaftyedu")
    public Integer deleteSaftyedu(Integer id) {
        try {
            saftyeduService.deleteSaftyedu(id);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    //查看课程
    @CrossOrigin
    @RequestMapping("/classDetailList")
    public List<ClassDetail> classDetailList(Integer id) {
        Saftyedu saftyedu = saftyeduService.selectlession(id);
        List<ClassDetail> classDetailList = saftyeduService.classDetailList(saftyedu.getLession());
        return classDetailList;
    }
}
