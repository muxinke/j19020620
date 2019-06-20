package com.qf.j1902.mapper;

import com.qf.j1902.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface UserInfoMapper {
    public ArrayList<UserInfo> findByjsType(@Param("jstype") String jstype);

    boolean insert(@Param("zhanghu")String zhanghu,@Param("password") String password, @Param("email")String email,@Param("jstype") String jstype);

    ArrayList<UserInfo> findAll();

    UserInfo selectByid(int id);

    boolean update(@Param("zhanghu") String zhanghu, @Param("username") String username, @Param("email") String email,@Param("id") Integer id);

    boolean addUser(@Param("zhanghu")String zhanghu, @Param("password")String password, @Param("username")String username, @Param("email")String email);

    boolean delete(int id);

    ArrayList<UserInfo> query(@Param("query")String query);

    UserInfo selectByZhanghu(@Param("zhanghu") String zhanghu);

    boolean updateUserByIndentify(UserInfo userInfo);

    ArrayList<UserInfo> selectByStatus(@Param("status")String status,@Param("offset")int offsize,
                                       @Param("pagesize")int pagesize);
    //根据状态分组,查询对应状态总行数
    int  queryRows(@Param("status")String status);

    boolean updateUserByzhanghu(@Param("zhanghu") String zhanghu,@Param("status")String status,@Param("suggestion")String suggestion);
}
