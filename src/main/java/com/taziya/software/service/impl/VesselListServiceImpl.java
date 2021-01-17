package com.taziya.software.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taziya.software.entity.vesselname;
import com.taziya.software.mapper.VesselListMapper;
import com.taziya.software.service.VesselListService;
import com.taziya.software.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VesselListServiceImpl implements VesselListService {

    @Autowired
    private VesselListMapper vesselListMapper;

    @Autowired
    private VesselListService vesselListService;

    @Autowired
    private VesselDeleteImpl vesselDeleteImpl;

    @Override
    public DataVO<vesselname> findList(Integer page, Integer limit){
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<vesselname> vesselIPage = new Page<>(page, limit);
        IPage<vesselname> result = vesselListMapper.selectPage(vesselIPage,null);
        dataVO.setCount(result.getTotal());
        List<vesselname> vesselList = result.getRecords();
        dataVO.setData(vesselList);

        return dataVO;
    }

    @Override
    public void staticDel(String id){
        vesselDeleteImpl.deleteData(id);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        vesselListMapper.delete(wrapper);
    }

    public DataVO<vesselname> findOne(String id){
        DataVO<vesselname> dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("ID",id);
        List<vesselname> vesselnames = vesselListMapper.selectList(wrapper);
        dataVO.setCount((long) 1);
        dataVO.setData(vesselnames);
        return dataVO;
    }
}
