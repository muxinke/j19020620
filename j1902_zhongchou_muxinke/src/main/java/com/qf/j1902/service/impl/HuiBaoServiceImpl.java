package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.HuiBaoMapper;
import com.qf.j1902.pojo.HuiBao;
import com.qf.j1902.service.HuiBaoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/2.
 */
@Service
public class HuiBaoServiceImpl implements HuiBaoService {
    @Resource
    private HuiBaoMapper huiBaoMapper;
    @Override
    public void add(HuiBao huiBao) {
        huiBaoMapper.add(huiBao);
    }

    @Override
    public ArrayList<HuiBao> query(int pid) {
        return huiBaoMapper.query(pid);
    }
}
