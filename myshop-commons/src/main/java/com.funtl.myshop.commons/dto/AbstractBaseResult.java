package com.funtl.myshop.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 通用的响应结果
 */
@Data
public abstract class AbstractBaseResult {

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class Links {

        private String self;

        private String next;

        private String last;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected static class DataBean<T extends AbstractBaseDomain> {

        private String type;

        private Long id;

        private T attributes;

        private T relationships;

        private Links links;
    }
}
