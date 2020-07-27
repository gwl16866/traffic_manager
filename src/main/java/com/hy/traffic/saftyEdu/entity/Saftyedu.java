package com.hy.traffic.saftyEdu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
@Data
public class Saftyedu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String theme;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String project;

    private String lession;

    private String student;

    private String manager;

    private String testPeople;

    private String learnType;

    private Integer status;

    private String address;

    private String trainTeacher;

    @TableField(exist = false)
    private Integer jd;
    @TableField(exist = false)
    private Integer a;
    @TableField(exist = false)
    private Integer b;
}
