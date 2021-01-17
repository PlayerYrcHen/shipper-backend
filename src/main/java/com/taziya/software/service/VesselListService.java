package com.taziya.software.service;

import com.taziya.software.entity.vesselname;
import com.taziya.software.vo.DataVO;

public interface VesselListService {
    public DataVO<vesselname> findList(Integer page, Integer limit);
    public void staticDel(String id);
    public DataVO<vesselname> findOne(String id);
}
