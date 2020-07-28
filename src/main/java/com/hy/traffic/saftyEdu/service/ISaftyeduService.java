package com.hy.traffic.saftyEdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.studentInfo.entity.Studentinfo;

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
}
