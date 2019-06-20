package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.ProjectInfoMapper;
import com.qf.j1902.pojo.ProjectInfo;
import com.qf.j1902.service.ProjectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/1.
 */
@Service
public class ProjectInfoServiceimpl  implements ProjectInfoService{
    @Resource
    private ProjectInfoMapper projectInfoMapper;
    @Override
    public boolean add(ProjectInfo projectInfo) {
        return projectInfoMapper.add(projectInfo);
    }

    @Override
    public ProjectInfo select_jilu(int id, String zhuangtai) {
        return projectInfoMapper.select_jilu(id,zhuangtai);
    }

    @Override
    public void update_jilu(ProjectInfo projectInfo) {
        projectInfoMapper.update_jilu(projectInfo);
    }

    @Override
    public void add_caogao(String yifubaozhanghu, String farenidcard, int pid, String zhuangtai) {
        projectInfoMapper.add_caogao(yifubaozhanghu,farenidcard,pid,zhuangtai);
    }

    @Override
    public void update_zhuangtai_2(String zhuangtai,int pid) {
        projectInfoMapper.updata_zhuangtai_2(zhuangtai,pid);
    }

    @Override
    public ArrayList<ProjectInfo> queryByzhuangtai(String zhuangtai) {
        return projectInfoMapper.queryByzhuangtai(zhuangtai);
    }

    @Override
    public ProjectInfo selectBypid(int pid) {
        return projectInfoMapper.selectBypid(pid);
    }

    @Override
    public void update_shenpiBypid(int pid, String zhuangtai) {
        projectInfoMapper.update_shenpiBypid(pid,zhuangtai);
    }

    @Override
    public void add_shenpi(ProjectInfo projectInfo) {
        projectInfoMapper.add_shenpi(projectInfo);
    }
}
