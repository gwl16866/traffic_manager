package com.hy.traffic.saftyEdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.saftyEdu.entity.Tree;
import com.hy.traffic.studentInfo.entity.Studentinfo;
import com.hy.traffic.teachInfo.entity.ClassDetail;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
public interface ISaftyeduService extends IService<Saftyedu> {
    public List<Saftyedu> selectSaftyEdu(Integer learnType);
    public Integer selectStudentCount(Integer id);
    public void updateStatus(Integer id,Integer status);
    public List<Studentinfo> selectStudent(Integer id);
    public void deleteStudent(Integer studentid,Integer saftyid);
    public List<Studentinfo> selectAllStudent(Integer saftyid);
    public void batchAddStudent(Integer saftyid,Integer studentid);
    public List<Studentinfo> selectAllStu();
    public List<Tree> queryclassDetail();
    public void addSaftyEdu(String theme, String startTime, String endTime, String lession, String manager,String testPeople, Integer learnType , String learnTime);
    public Integer selectMaxId();
    public void deleteSaftyedu(Integer id);
    public Saftyedu selectlession(Integer id);
    public List<ClassDetail> classDetailList(String id);
}
