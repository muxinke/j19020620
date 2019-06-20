package com.qf.j1902.mapper;

import com.qf.j1902.pojo.ProjectType;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/30.
 */
public interface ProjectTypeMapper {

    ArrayList<ProjectType> findAll();

    boolean insertType(@Param("projectType") String projectType,@Param("jianjie")String jianjie);

    ProjectType queryById(int id);

    boolean update_projectType(ProjectType projectType);
}
