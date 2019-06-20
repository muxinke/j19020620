package com.qf.j1902.mapper;

import com.qf.j1902.pojo.UserInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/28.
 */
public class TestUserInfo {
    @Test
    public void query(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserInfoMapper bean = ac.getBean(UserInfoMapper.class);
        ArrayList<UserInfo> mu = bean.query("12");
        System.out.println(mu);
    }
    @Test
    public void queryRows(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserInfoMapper bean = ac.getBean(UserInfoMapper.class);
        int i = bean.queryRows("0");
        System.out.println(i);
    }
}
