package com.qf.j1902.service;

import com.qf.j1902.pojo.ProjectInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/1.
 */
public interface ProjectInfoService {
    boolean add(ProjectInfo projectInfo);

    ProjectInfo select_jilu(int id, String zhuangtai);

    void update_jilu(ProjectInfo projectInfo);

    void add_caogao(String yifubaozhanghu, String farenidcard, int pid, String zhuangtai);

    void update_zhuangtai_2(String zhuangtai,int pid);

    ArrayList<ProjectInfo> queryByzhuangtai(String zhuangtai);

    ProjectInfo selectBypid(int pid);

    void update_shenpiBypid(int pid, String zhuangtai);

    void add_shenpi(ProjectInfo projectInfo);
}
