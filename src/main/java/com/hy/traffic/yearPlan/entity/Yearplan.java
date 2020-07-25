package com.hy.traffic.yearPlan.entity;

import lombok.Data;

import java.time.LocalDateTime;
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
public class Yearplan implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String bodys;

    private LocalDateTime createTime;


}
