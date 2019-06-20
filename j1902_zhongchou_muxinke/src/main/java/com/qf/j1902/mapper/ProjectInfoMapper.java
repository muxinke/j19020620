package com.qf.j1902.mapper;

import com.qf.j1902.pojo.ProjectInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/1.
 */
public interface ProjectInfoMapper {
    //添加数据库
    boolean add(ProjectInfo projectInfo);
    //根据用户id 和状态查找当前记录
    ProjectInfo select_jilu(@Param("id") int id,@Param("zhuangtai") String zhuangtai);

    void update_jilu(ProjectInfo projectInfo);
    void add_caogao(@Param("yifubaozhanghu") String yifubaozhanghu,
                    @Param("farenidcard") String farenidcard,
                    @Param("pid") int pid,
                    @Param("zhuangtai") String zhuangtai);

    void updata_zhuangtai_2(@Param("zhuangtai") String zhuangtai,@Param("pid")int pid);

    ArrayList<ProjectInfo> queryByzhuangtai(@Param("zhuangtai") String zhuangtai);

    ProjectInfo selectBypid(@Param("pid") int pid);

    void update_shenpiBypid(@Param("pid") int pid, @Param("zhuangtai") String zhuangtai);

    void add_shenpi(ProjectInfo projectInfo);
}
