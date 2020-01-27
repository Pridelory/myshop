package com.funtl.myshop.commons.service;

import com.funtl.myshop.commons.domain.TbUser;
import com.funtl.myshop.commons.dto.AbstractBaseDomain;

/**
 * 通用的业务逻辑
 */
public interface BaseCrudService<T extends AbstractBaseDomain> {

    /**
     * 查询属性值是否唯一
     * @param value
     * @param property
     * @return true/唯一 false/不唯一
     */
    default boolean unique(String property, String value) {
        return false;
    }
}
