package com.qf.j1902.mapper;

import com.qf.j1902.pojo.TagType;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/31.
 */
public interface TagMapper {
    ArrayList<TagType> findAll();
}
