package com.taziya.software.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taziya.software.entity.Vessel;
import com.taziya.software.mapper.VesselMapper;
import com.taziya.software.service.VesselDelete;
import com.taziya.software.service.VesselService;
import com.taziya.software.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VesselDeleteImpl implements VesselDelete {

    @Autowired
    private VesselMapper mapper;
    @Autowired
    private VesselService vesselService;

    @Override
    public void deleteData(String vesselId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("VesselID", vesselId);
        mapper.delete(wrapper);
    }

    @Override
    public void deleteSingleData(int id){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id",id);
        mapper.delete(wrapper);
    }
}
