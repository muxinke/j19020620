package com.qf.j1902.mapper;

import com.qf.j1902.pojo.TagType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */
public class TagMapperxxx {
    @Test
    public void findAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        TagMapper bean = ac.getBean(TagMapper.class);
        ArrayList<TagType> all = bean.findAll();
        System.out.println(all);
    }
}
