package com.hy.traffic.saftyEdu.entity;

import lombok.Data;

import java.io.Serializable;

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

    private int id;

    private String theme;


    private String  startTime;

    private String endTime;

    private String project;

    private String lession;

    private String student;

    private String manager;

    private String testPeople;

    private String learnType;

    private Integer status;

    private String address;

    private String trainTeacher;


}
