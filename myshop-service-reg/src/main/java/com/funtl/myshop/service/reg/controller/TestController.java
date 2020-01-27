package com.funtl.myshop.service.reg.controller;

import com.funtl.myshop.commons.domain.TbUser;
import com.funtl.myshop.commons.dto.AbstractBaseResult;
import com.funtl.myshop.commons.dto.BaseResultFactory;
import com.funtl.myshop.commons.dto.SuccessResult;
import com.google.common.collect.Lists;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping(value = "records/{id}")
    public AbstractBaseResult getById(HttpServletRequest request, @PathVariable Long id) {

        TbUser tbUser = new TbUser();
        tbUser.setId(1l);
        tbUser.setUsername("王蒙");

        if (id == 1) {
            return BaseResultFactory.getInstance().build(request.getRequestURI(), tbUser);
        } else {
            return BaseResultFactory.build(HttpStatus.UNAUTHORIZED.value(), "参数类型错误","id只能为1", applicationContext.getEnvironment().getProperty("logging.level.com.funtl.myshop"));
        }
    }

    @GetMapping(value = "records")
    public AbstractBaseResult getList(HttpServletRequest request) {
        TbUser tbUser1 = new TbUser();
        tbUser1.setId(1l);
        tbUser1.setUsername("王雨柔");

        TbUser tbUser2 = new TbUser();
        tbUser2.setId(2l);
        tbUser2.setUsername("王蒙");

        ArrayList<Object> tbUsers = Lists.newArrayList();

        tbUsers.add(tbUser1);
        tbUsers.add(tbUser2);

        return BaseResultFactory.getInstance().build(request.getRequestURI(),2 ,10, tbUsers);
    }
}
