package com.taziya.software.service;

import com.taziya.software.entity.Vessel;
import com.taziya.software.vo.DataVO;

public interface VesselDelete {
    public void deleteData(String vesselId);
    public void deleteSingleData(int id);
}
