package com.qf.j1902.controller;

import com.qf.j1902.mapper.ProjectTypeMapper;
import com.qf.j1902.pojo.ProjectType;
import com.qf.j1902.service.ProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/30.
 */
@Controller
public class ProjectTypeController {
    @Autowired
    private ProjectTypeService projectTypeService;
    //项目分类
    @GetMapping("project_type")
    public String project_type(Model model){
        ArrayList<ProjectType> findall = projectTypeService.findall();
        model.addAttribute("projectTypeAll",findall);
        return "project_type";
    }
    @GetMapping("form")
    public String form(){
        return "form";
    }
  @PostMapping("project_type_submit")
    public String project_type_submit(@RequestParam(value = "projectType",defaultValue = "")String projectType,
                                      @RequestParam(value = "jianjie",defaultValue = "")String jianjie){
        if("".equals(projectType)||"".equals(jianjie)){
            return "redirect:form";
        }
       boolean b= projectTypeService.insertType(projectType,jianjie);
       return "redirect:project_type";
  }
  @GetMapping("updateProjectType")
    public String updateProjectType(@RequestParam("id") int id,Model model ){
       ProjectType projectType= projectTypeService.queryById(id);
       model.addAttribute("singleProject",projectType);
        return "updateProjectType";
  }
  @PostMapping("update_project_type_submit")
    public String update_project_type_submit(ProjectType projectType){
        if("".equals(projectType.getProjectType())||"".equals(projectType.getJianjie())){
            int id=projectType.getId();
            return "redirect:updateProjectType?id="+id;
        }
       boolean b= projectTypeService.update_projectType(projectType);
        return  "redirect:project_type";
    }
    @PostMapping("delProjectType")
    @ResponseBody
    public int delProjectType(@RequestParam("id")int id){
        return  1;
    }
}
