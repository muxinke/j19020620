package com.qf.j1902.service;

import com.qf.j1902.pojo.HuiBao;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/2.
 */
public interface HuiBaoService {
    void add(HuiBao huiBao);

    ArrayList<HuiBao> query(int pid);
}
