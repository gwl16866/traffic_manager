package com.hy.traffic.trainProgress.entity;

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
public class Teachinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String classTitle;

    private Integer classDetails;


}
