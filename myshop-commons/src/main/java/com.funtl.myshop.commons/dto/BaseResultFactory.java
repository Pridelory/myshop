package com.funtl.myshop.commons.dto;

import java.util.List;

/**
 * 通用响应机构工厂
 */
public class BaseResultFactory<T extends AbstractBaseDomain> {

    private static final String LOGGER_LEVEL_DEBUG = "DEBUG";

    private static BaseResultFactory baseResultFactory;

    private BaseResultFactory() {

    }

    /**
     * 单例
     * @return
     */
    public static BaseResultFactory getInstance() {
        if (baseResultFactory == null) {
            synchronized (BaseResultFactory.class) {
                if (baseResultFactory == null) {
                    baseResultFactory = new BaseResultFactory();
                }
            }
        }
        return baseResultFactory;
    }

    /**
     * 构建单笔数据结果集
     * @param self
     * @return
     */
    public AbstractBaseResult build(String self, T attributes) {
        return new SuccessResult(self, attributes);
    }

    /**
     * 构建多笔数据结构集
     * @param self
     * @param next
     * @param last
     * @return
     */
    public AbstractBaseResult build(String self, int next, int last, List<T> attributes) {
        return new SuccessResult(self, next, last, attributes);
    }

    /**
     * 构建请求错误的响应结构
     * @param code
     * @param title
     * @param detail
     * @param level
     * @return
     */
    public static AbstractBaseResult build(int code, String title, String detail, String level) {
        if (LOGGER_LEVEL_DEBUG.equals(level)) {
            return new ErrorResult(code, title, detail);
        } else {
            return new ErrorResult(code, title, null);
        }
    }
}
