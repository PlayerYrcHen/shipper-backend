package com.taziya.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taziya.software.entity.Vessel;
import com.taziya.software.utils.Boat;
import com.taziya.software.utils.VesselName;
import com.taziya.software.vo.chartsVo;
import com.taziya.software.vo.vesselNameVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import sun.security.pkcs11.wrapper.Constants;

import java.sql.Wrapper;
import java.util.List;

@Repository
public interface VesselMapper extends BaseMapper<Vessel> {
    @Select("select * from vesselname where ID=#{id}")
    VesselName findAI(String id);

    @Select("select * from vessel")
    public List<Boat> findAll();

    @Select(" SELECT * FROM vessel where VesselID=#{id} and BaseDateTime between #{begin} and #{end} order by BaseDateTime")
    public List<chartsVo> findById(String id, String begin, String end);

    @Select("SELECT DISTINCT VesselID FROM vessel")
    List<vesselNameVo> findAllVessel();

}
