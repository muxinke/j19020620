package com.qf.j1902.service;

import com.qf.j1902.pojo.ProjectType;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/30.
 */
public interface ProjectTypeService {
    ArrayList<ProjectType> findall();

    boolean insertType(String projectType, String jianjie);

    ProjectType queryById(int id);

    boolean update_projectType(ProjectType projectType);
}
