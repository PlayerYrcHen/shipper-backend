package com.taziya.software.mapper;


import org.apache.ibatis.annotations.*;



//指定这是一个操作数据库的mapper
@Mapper
public interface StaticMapper {

    @Update("update vesselname set mmsi=#{mmsi},imo=#{imo},type=#{type},name=#{name},owner=#{owner},length=#{length},width=#{width},draft=#{draft} where id=#{id}")
    public int updateVesselName(@Param("mmsi") String mmsi, @Param("imo") String imo, @Param("type") String type, @Param("name") String name, @Param("owner") String owner, @Param("length") Float length, @Param("width") Float width, @Param("draft") Float draft, @Param("id") String id);

    @Insert("insert into vesselname(id,mmsi,imo,type,name,owner,length,width,draft) values(#{id},#{mmsi},#{imo},#{type},#{name},#{owner},#{length},#{width},#{draft})")
    public int insertVessleName(@Param("mmsi") String mmsi, @Param("imo") String imo, @Param("type") String type, @Param("name") String name, @Param("owner") String owner, @Param("length") Float length, @Param("width") Float width, @Param("draft") Float draft, @Param("id") String id);

    @Select("select 1 from vesselname where id=#{id} limit 1")
    public String getData(String id);
}
