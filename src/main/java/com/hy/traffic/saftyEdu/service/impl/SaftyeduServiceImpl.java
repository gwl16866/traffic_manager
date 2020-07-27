package com.hy.traffic.saftyEdu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.traffic.saftyEdu.entity.Saftyedu;
import com.hy.traffic.saftyEdu.mapper.SaftyeduMapper;
import com.hy.traffic.saftyEdu.service.ISaftyeduService;
import com.hy.traffic.studentInfo.entity.Studentinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    SaftyeduMapper saftyeduMapper;

    public List<Saftyedu> selectSaftyEdu(Integer learnType) {
        return saftyeduMapper.selectSaftyEdu(learnType);
    }


    public Integer selectStudentCount(Integer id){
        return saftyeduMapper.selectStudentCount(id);
    };

    public void updateStatus(Integer id,Integer status){
         saftyeduMapper.updateStatus(id,status);
    }

    public List<Studentinfo> selectStudent(Integer id){
        return saftyeduMapper.selectStudent(id);
    }
    public void deleteStudent(Integer studentid,Integer saftyid) {

             saftyeduMapper.deleteStudent(studentid,saftyid);
    }

    public List<Studentinfo> selectAllStudent(Integer saftyid){
      return   saftyeduMapper.selectAllStudent(saftyid);
    }

    public void batchAddStudent(Integer saftyid,Integer studentid){
        saftyeduMapper.batchAddStudent(saftyid,studentid);
    }


}
