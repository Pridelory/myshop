package com.funtl.myshop.service.reg.controller;

import com.funtl.myshop.commons.domain.TbUser;
import com.funtl.myshop.commons.dto.AbstractBaseResult;
import com.funtl.myshop.commons.dto.BaseResultFactory;
import com.funtl.myshop.commons.mapper.TbUserMapper;
import com.funtl.myshop.commons.service.TbUserService;
import com.funtl.myshop.commons.validator.BeanValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping(value = "reg")
public class RegController {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private TbUserService tbUserService;

    /**
     * 根据 ID 测试查询用户信息
     * @param tbUser
     * @return
     */
    @GetMapping(value = "")
    public AbstractBaseResult reg(TbUser tbUser) {
        String message = BeanValidator.validator(tbUser);
        if (StringUtils.isNotEmpty(message)) {
            return BaseResultFactory.getInstance().build(HttpStatus.UNAUTHORIZED.value(), message, null, applicationContext.getEnvironment().getProperty("logging.level.com.funtl.myshop"));
        }

        // 验证用户名是否重复
        if (!tbUserService.unique("username", tbUser.getUsername())) {
            return BaseResultFactory.getInstance().build(HttpStatus.UNAUTHORIZED.value(), message, "用户名重复，请重试", applicationContext.getEnvironment().getProperty("logging.level.com.funtl.myshop"));
        }

        // 验证邮箱是否重复
        if (!tbUserService.unique("email", tbUser.getEmail())) {
            return BaseResultFactory.getInstance().build(HttpStatus.UNAUTHORIZED.value(), message, "邮箱重复，请重试", applicationContext.getEnvironment().getProperty("logging.level.com.funtl.myshop"));
        }

        return null;
    }
}
