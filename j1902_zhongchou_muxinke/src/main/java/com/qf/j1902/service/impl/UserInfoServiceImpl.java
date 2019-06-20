package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.UserInfoMapper;
import com.qf.j1902.pojo.UserInfo;
import com.qf.j1902.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2019/5/27.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Override
    public ArrayList<UserInfo> findByjsType(String jstype) {
        return userInfoMapper.findByjsType(jstype);
    }

    @Override
    public boolean insert(String zhanghu, String password, String email, String jstype) {
        return userInfoMapper.insert(zhanghu,password,email,jstype);
    }

    @Override
    public ArrayList<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }

    @Override
    public UserInfo selectByid(int id) {
        return userInfoMapper.selectByid(id);
    }

    @Override
    public boolean update(String zhanghu, String username, String email, Integer id) {
        return userInfoMapper.update(zhanghu,username,email,id);
    }

    @Override
    public boolean addUser(String zhanghu, String password, String username, String email) {
        return userInfoMapper.addUser(zhanghu,password,username,email);
    }

    @Override
    public boolean delete(int id) {
        return userInfoMapper.delete(id);
    }

    @Override
    public ArrayList<UserInfo> query(String query) {
        return userInfoMapper.query(query);
    }

    @Override
    public UserInfo selectByZhanghu(String zhanghu) {
        return userInfoMapper.selectByZhanghu(zhanghu);
    }

    @Override
    public boolean updateUserByIndentify(UserInfo userInfo) {
        return userInfoMapper.updateUserByIndentify(userInfo);
    }

    @Override
    public ArrayList<UserInfo> selectByStatus(String status, int offsize, int pagesize) {
        return userInfoMapper.selectByStatus(status,offsize,pagesize);
    }

    @Override
    public boolean updateUserByzhanghu(String zhanghu,String status,String idea) {
        return userInfoMapper.updateUserByzhanghu(zhanghu,status,idea);
    }


}
