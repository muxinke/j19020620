package com.qf.j1902.mapper;

import com.qf.j1902.pojo.HuiBao;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/2.
 */
public interface HuiBaoMapper {
     void add(HuiBao huiBao);

    ArrayList<HuiBao> query(@Param("pid") int pid);
}
