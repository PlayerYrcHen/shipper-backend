package com.taziya.software.controller;

import com.taziya.software.entity.StaticData;
import com.taziya.software.entity.Vessel;
import com.taziya.software.entity.vesselname;
import com.taziya.software.mapper.StaticMapper;
import com.taziya.software.mapper.VesselListMapper;
import com.taziya.software.service.VesselDelete;
import com.taziya.software.service.VesselListService;
import com.taziya.software.service.VesselService;
import com.taziya.software.utils.Boat;
import com.taziya.software.utils.Dom4jXml;
import com.taziya.software.utils.VesselName;
import com.taziya.software.vo.DataVO;
import com.taziya.software.vo.barVo;
import com.taziya.software.vo.vesselNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class VesselController {

    @Autowired
    private VesselService vesselService;

    @Autowired
    private VesselDelete vesselDelete;

    @Autowired
    private VesselListService vesselListService;

    @Autowired
    private VesselListMapper vesselListMapper;

    @Autowired
    private StaticMapper staticMapper;

    @RequestMapping("/list")
    public DataVO<Vessel> list(Integer page, Integer limit, String vesselid) {
        if (vesselid == null || vesselid == "")
            return vesselService.findData(page, limit);
        else{
            System.out.println(vesselid);
            return vesselService.findData(page, limit, vesselid);}
    }

    @RequestMapping("/del")
    public void del(HttpServletResponse response, Integer id) throws IOException  {
        vesselDelete.deleteSingleData(id);
        response.sendRedirect("http://localhost:1234/table.html");
    }

    @RequestMapping("/json")
    public List<Boat> json() {
        List<Boat> list = vesselService.findAll();
        Dom4jXml dom4jXml = new Dom4jXml();
        for (Boat boat : list) {
            String fileLocation = "E:\\xml\\";
            int i = 1;
            String vesselID = boat.getVesselID();
            //这里换成你的按照id查询静态信息的方法
            VesselName attentionInformation = vesselService.findAI(vesselID);

            dom4jXml.createXml(fileLocation, boat, attentionInformation);
        }
        return vesselService.findAll();
    }

    @RequestMapping("/vessellist")
    public DataVO<vesselname> vessellist(Integer page, Integer limit, String id) {
        if(id == null || id == "")
            return vesselListService.findList(page, limit);
        else
            return vesselListService.findOne(id);
    }

    @RequestMapping("/staticDel")
    public void staticDel(HttpServletResponse response, String id) throws IOException{
        vesselListService.staticDel(id);
        response.sendRedirect("http://localhost:1234/vessellist.html");
    }

    @RequestMapping("/updatename")
    public void updateVessel(@RequestParam Map<String, Object> data) {
        StaticData data_input = new StaticData(data.get("id"), data.get("mmsi"), data.get("imo"), data.get("type"), data.get("name"), data.get("owner"), data.get("length"), data.get("width"), data.get("draft"));
//      删除指定的id的数据
        staticMapper.updateVesselName(data_input.getMmsi(), data_input.getImo(), data_input.getType(), data_input.getName(), data_input.getOwner(), data_input.getLength(), data_input.getWidth(), data_input.getDraft(), data_input.getId());
    }

    @RequestMapping("/insert")
    public void hello(@RequestParam Map<String, Object> data, HttpServletResponse response) throws IOException {
        System.out.println(data);

        StaticData data_input = new StaticData(data.get("id"), data.get("mmsi"), data.get("imo"), data.get("type"), data.get("name"), data.get("owner"), data.get("length"), data.get("width"), data.get("draft"));

        String flag = staticMapper.getData(data_input.getId());

        System.out.println(flag);

        if (flag == null) {
            staticMapper.insertVessleName(data_input.getMmsi(), data_input.getImo(), data_input.getType(), data_input.getName(), data_input.getOwner(), data_input.getLength(), data_input.getWidth(), data_input.getDraft(), data_input.getId());
            response.sendRedirect("http://localhost:1234/vessellist.html");
        } else {
            response.sendRedirect("http://localhost:1234/vessellist.html");
        }
    }

//    echarts图表展示
    @RequestMapping("/temp")
    public barVo barvo(HttpServletRequest request  ){

        String xzqh = request.getParameter("id");
        String begin = request.getParameter("begintime");
        String end = request.getParameter("endtime");
        if(begin=="" ){
            begin="2000-11-06";
        }
        if(end=="" ){
            end="2080-11-06";
        }
        System.out.println("=========================");
        System.out.println(xzqh);
        System.out.println(begin);
        System.out.println(end);
        System.out.println("=========================");
        return vesselService.getBarVo(xzqh,begin,end);
    }

    @RequestMapping("/show")
    public List<vesselNameVo> list(){
        return vesselService.findAllVessel();
    }

}
