package com.qf.j1902.service;

import com.qf.j1902.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import javax.management.Query;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/5/27.
 */
public interface UserInfoService {
    //查找所有user用户信息
    public ArrayList<UserInfo> findByjsType(String jstype);

    boolean insert(String zhanghu, String password, String email, String jstype);

    public  ArrayList<UserInfo> findAll();

    UserInfo selectByid(int id);

    boolean update(String zhanghu, String username, String email, Integer id);

    boolean addUser(String zhanghu, String password, String username, String email);

    boolean delete(int id);

    ArrayList<UserInfo> query(String  query);

    UserInfo selectByZhanghu(String zhanghu);


    boolean updateUserByIndentify(UserInfo userInfo);
    //根据状态查询实名认证审批信息,分页
    ArrayList<UserInfo> selectByStatus(String status,int offsize,int pagesize);
    //根据账户修改 审批状态
    boolean updateUserByzhanghu(String zhanghu,String status,String idea);

}
