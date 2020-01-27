package com.funtl.myshop.commons.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用的领域模型
 */
@Data
public abstract class AbstractBaseDomain implements Serializable{
    private Long id;
}
