package com.taziya.software.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taziya.software.entity.Vessel;
import com.taziya.software.mapper.VesselMapper;
import com.taziya.software.service.VesselService;
import com.taziya.software.utils.Boat;
import com.taziya.software.utils.VesselName;
import com.taziya.software.vo.DataVO;
import com.taziya.software.vo.barVo;
import com.taziya.software.vo.chartsVo;
import com.taziya.software.vo.vesselNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VesselServiceImpl implements VesselService {

    @Autowired
    private VesselMapper vesselMapper;

    @Override
    public DataVO<Vessel> findData(Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.inSql("id","select id from vessel a inner join(select VesselID,max(BaseDateTime) as preTime from vessel where deleted=0 group by VesselID)bs on bs.VesselID=a.VesselID AND bs.preTime=a.BaseDateTime");
        IPage<Vessel> vesselIPage = new Page<>(page, limit);
        IPage<Vessel> result = vesselMapper.selectPage(vesselIPage,wrapper);
        dataVO.setCount(result.getTotal());
        //
        List<Vessel> vesselList = result.getRecords();
        dataVO.setData(vesselList);
//        dataVO.setData(vesselMapper.findNewest());

        return dataVO;
    }

    public DataVO<Vessel> findData(Integer page, Integer limit,String vesselId) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("VesselID", vesselId);
        IPage<Vessel> vesselIPage = new Page<>(page, limit);
        IPage<Vessel> result = vesselMapper.selectPage(vesselIPage,wrapper);
        dataVO.setCount(result.getTotal());
        //
        List<Vessel> vesselList = result.getRecords();
        dataVO.setData(vesselList);

        return dataVO;
    }

    public List<Vessel> Data2Json() {
        List<Vessel> result = vesselMapper.selectList(null);
        return result;

    }

    @Override
    public VesselName findAI(String id) {
        return vesselMapper.findAI(id);
    }

    @Override
    public List<Boat> findAll() {
        return vesselMapper.findAll();
    }

    public boolean ifNoValue(byte a0,byte a1){
        if ((a0==(byte) 0xff)&&(a1==(byte)0xff)){
            return true;
        }else {
            return false;
        }
    }

    public int judgePlusOrMinus(byte a0,byte a1){
        byte x= (byte) (a0&0x80);

        if (x==0x80){
            //为负数
            byte x1= (byte) (a0&0x7f);
            return -(x1<<8+a1);

        }else {
            //为正数
            return x<<8+a1;
        }
    }

    public void save(Vessel vessel){
        vesselMapper.insert(vessel);
    };

    @Override
    public barVo getBarVo(String id, String begin, String end) {
        List<chartsVo> list = vesselMapper.findById(id,begin,end);
        List<Float> Tcase = new ArrayList<>();
        List<String> BaseDateTime = new ArrayList<>();
        List<Float> SOG = new ArrayList<>();
        List<Float> AirTemperature = new ArrayList<>();
        List<Float> Humidity = new ArrayList<>();
        List<Float> Pressure = new ArrayList<>();
        List<Float> Visibility = new ArrayList<>();
        List<Float> Windspeed = new ArrayList<>();
        List<Float> Watertemperature = new ArrayList<>();
        List<Float> Salinity = new ArrayList<>();
        List<Float> Voltage = new ArrayList<>();
        for (chartsVo chartsVo : list) {
            Tcase.add(chartsVo.getTcase());
            BaseDateTime.add(chartsVo.getBaseDateTime());
            SOG.add(chartsVo.getSOG());
            AirTemperature.add(chartsVo.getAirTemperature());
            Humidity.add(chartsVo.getHumidity());
            Pressure.add(chartsVo.getPressure());
            Visibility.add(chartsVo.getVisibility());
            Windspeed.add(chartsVo.getWindspeed());
            Watertemperature.add(chartsVo.getWatertemperature());
            Salinity.add(chartsVo.getSalinity());
            Voltage.add(chartsVo.getVoltage());
        }
        barVo barVo = new barVo();
        barVo.setBaseDateTime(BaseDateTime);
        barVo.setTcase(Tcase);
        barVo.setSOG(SOG);
        barVo.setAirTemperature(AirTemperature);
        barVo.setHumidity(Humidity);
        barVo.setPressure(Pressure);
        barVo.setVisibility(Visibility);
        barVo.setWindspeed(Windspeed);
        barVo.setWatertemperature(Watertemperature);
        barVo.setSalinity(Salinity);
        barVo.setVoltage(Voltage);
        return barVo;
    }

    @Override
    public List<vesselNameVo> findAllVessel() {
        return vesselMapper.findAllVessel();
    }
}
