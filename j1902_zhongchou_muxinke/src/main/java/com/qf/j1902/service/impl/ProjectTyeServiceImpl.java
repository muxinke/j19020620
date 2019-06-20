package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.ProjectTypeMapper;
import com.qf.j1902.pojo.ProjectType;
import com.qf.j1902.service.ProjectTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/30.
 */
@Service
public class ProjectTyeServiceImpl implements ProjectTypeService {
    @Resource
    private  ProjectTypeMapper projectTypeMapper;
    @Override
    public ArrayList<ProjectType> findall() {
        return projectTypeMapper.findAll();
    }

    @Override
    public boolean insertType(String projectType, String jianjie) {
        return projectTypeMapper.insertType(projectType,jianjie);
    }

    @Override
    public ProjectType queryById(int id) {
        return projectTypeMapper.queryById(id);
    }

    @Override
    public boolean update_projectType(ProjectType projectType) {
        return projectTypeMapper.update_projectType(projectType);
    }
}
