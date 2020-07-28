package com.hy.traffic.document.entity;

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
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String num;

    private String title;

    private String texts;

    private String sendCounts;

    private String readCounts;

    private String readTime;

    private String createTime;

    private String updateTime;

    private Integer status;


}
