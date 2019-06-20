package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.TagMapper;
import com.qf.j1902.pojo.TagType;
import com.qf.j1902.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;
    @Override
    public ArrayList<TagType> findAll() {
        return tagMapper.findAll();
    }
}
