package com.taziya.software.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taziya.software.entity.vesselname;
import com.taziya.software.utils.VesselName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface VesselListMapper extends BaseMapper<vesselname> {
    @Update("update vesselname set mmsi=#{mmsi},imo=#{imo},type=#{type},name=#{name},owner=#{owner},length=#{length},width=#{width},draft=#{draft} where id=#{id}")
    public int updateVesselName(@Param("mmsi") String mmsi, @Param("imo") String imo, @Param("type") String type, @Param("name") String name, @Param("owner") String owner, @Param("length") Float length, @Param("width") Float width, @Param("draft") Float draft, @Param("id") String id);

    @Select("select * from vesselname where ID=#{id}")
    vesselname findAI(String id);
}
