package com.taziya.software.service;

import com.taziya.software.entity.Vessel;
import com.taziya.software.utils.Boat;
import com.taziya.software.utils.VesselName;
import com.taziya.software.vo.DataVO;
import com.taziya.software.vo.barVo;
import com.taziya.software.vo.vesselNameVo;

import java.util.List;

public interface VesselService {
    public DataVO<Vessel> findData(Integer page, Integer limit);
    public DataVO<Vessel> findData(Integer page, Integer limit,String vesselId);
    public VesselName findAI(String id);
    public List<Boat> findAll();
    public boolean ifNoValue(byte a0, byte a1);
    public int judgePlusOrMinus(byte a0, byte a1);
    public barVo getBarVo(String id, String begin, String end);
    List<vesselNameVo> findAllVessel();

    void save(Vessel vessel);
}
