package com.taziya.software.controller;

import com.taziya.software.service.VesselService;
import com.taziya.software.SerialPortUtils.SerialPortTool;
import com.taziya.software.entity.Vessel;
import gnu.io.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller("com")
@RequestMapping("/COM")
public class ComConller {
    @Autowired
    public  static VesselService vesselService;
    private static SerialPort port;
    //static String commName="COM3";
    private  static ArrayList<Byte> RecvBufList=new ArrayList<Byte>();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    //定义的数组偏移量
    private static int DConeframe;
    private static int DCdatanum;
    //定义的接受一帧完整数据的数组
    //byte[] DCfrmbf=new byte[512];
    private static ArrayList<Byte> DCfrmbf=new ArrayList<>();

    //定义的校验的数据
    private static int DCdataleft;

    //完整的接受一帧数据
//    private static void DCDeframer(byte data) {
//        System.out.println("收到！！！");
//        DConeframe = 0;
//        if (DCdatanum < 5) {
//            if (data == (byte) '$') {
//                byte x0 = (byte) '$';
//                DCfrmbf.add(0, x0);
//                DCdatanum = 1;
//            } else if (DCdatanum >= 1) {
//                DCfrmbf.add(DCdatanum, data);
//
//                DCdatanum++;
//                if (DCdatanum == 5) {
//                    if ((!(DCfrmbf.get(0) == (byte) '$' && DCfrmbf.get(1) == (byte) 'T' && DCfrmbf.get(2) == (byte) 'X' && DCfrmbf.get(3) == (byte) 'X' && DCfrmbf.get(4) == (byte) 'X'))) {
//                        DCdatanum = 0;
//                    }
//                }
//            }
//        } else if (DCdatanum < 7) {
//            DCfrmbf.add(DCdatanum, data);
//
//            DCdatanum++;
//            if (DCdatanum == 7) {
//                DCdataleft = (DCfrmbf.get(5) << 8) + DCfrmbf.get(6) - 7;
//                if (DCdataleft < 0) {
//                    DCdatanum = 0;
//                }
//            }
//        } else if (DCdataleft > 0) {
//            if (DCdatanum < 511) {
//                DCfrmbf.add(DCdatanum, data);
//
//                DCdatanum++;
//                DCdataleft--;
//                if (DCdataleft == 0) {
//                    DConeframe = DCdatanum;
//                    DCdatanum = 0;
//                }
//            } else {
//                DCdatanum = 0;
//                DConeframe = 0;
//                DCdataleft = 0;
//            }
//        }
//    }
    //电文解析
    //TXXX电文解析
//    private static void ViewInfo(ArrayList<Byte> buff, int len){
//        for (int i = 0; i < len; i++) {
//            DCDeframer(buff.get(i));
//            if (DConeframe!=0){
//                if (DCfrmbf.get(0) == '$' && DCfrmbf.get(1) == 'T' && DCfrmbf.get(2) == 'X' && DCfrmbf.get(3) == 'X' && DCfrmbf.get(4) == 'X')        //通信信息
//                {
//                    // txtreceived.Text = "";
//                    try {
//                        Vessel vessel = ViewPDA2PC(DCfrmbf, DConeframe);
//                        vesselService.save(vessel);
//
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        }
//    }


    //查看和解析帧数据
    // 未
    // 完
    // 成
    private static Vessel ViewPDA2PC(ArrayList<Byte> bf, int size) throws ParseException {
        String VesselId=String.valueOf((bf.get(22)<<128)+(bf.get(23)<<64)+(bf.get(24)<<32)+(bf.get(25)<<16)+(bf.get(26)<<8)+bf.get(27));
        int TimeFormat=bf.get(28);
        int Year=bf.get(29)+2000;
        int Month=bf.get(30);
        int Day=bf.get(31);
        int Hour=bf.get(32);
        int Minute=bf.get(33);
        String date=String.valueOf(Year)+"-"+String.valueOf(Month)+"-"+String.valueOf(Day)+" "+String.valueOf(Hour)+":"+String.valueOf(Minute);
        Date BaseDateTime=simpleDateFormat.parse(date);
        float sog=(bf.get(34)<<8)+(bf.get(35));

        if (vesselService.ifNoValue(bf.get(34),bf.get(35))){//vessel.ifNoValue(bf34,35)
            sog=0;
        }else {
            sog= (float) (sog*0.1);
        }
        float cog=(bf.get(36)<<8)+(bf.get(37));

        if (vesselService.ifNoValue(bf.get(36),bf.get(37))){
            cog=0;
        }else {
            cog= (float) (cog*0.1);
        }
        //纬度
        float lat=0;
        if (vesselService.ifNoValue(bf.get(38), bf.get(39))){
            lat=0;
        }else{
            int i1 = vesselService.judgePlusOrMinus(bf.get(38), bf.get(39));
            lat= (float) (i1*0.01);
        }

        //经度
        float lon=0;
        if (vesselService.ifNoValue(bf.get(40), bf.get(41))){
            lon=0;
        }else {
            int i2 = vesselService.judgePlusOrMinus(bf.get(38), bf.get(39));
            lon= (float) (i2*0.01);
        }

        //气温
        float AirTemperature=(bf.get(42)<<8)+bf.get(43);
        //(((bf.get(42)<<8)+bf.get(43))-500)/10;
        if (vesselService.ifNoValue(bf.get(42), bf.get(43))){
            AirTemperature=0;
        }else {
            AirTemperature=(AirTemperature-500)/10;
        }
        //湿度
        float Humidity=(bf.get(44)<<8)+bf.get(45);
        if (vesselService.ifNoValue(bf.get(44), bf.get(45))){
            Humidity=0;
        }
        //气压
        float Pressure=(bf.get(46)<<8)+bf.get(47);
        if (vesselService.ifNoValue(bf.get(46), bf.get(47))){
            Pressure=0;
        }else {
            Pressure=(Pressure+8500)/10;
        }
        //能见度
        float Visibility=(bf.get(48)<<8)+bf.get(49);
        if (vesselService.ifNoValue(bf.get(48), bf.get(49))){
            Visibility=0;
        }else{
            Visibility=Visibility/10;
        }
        //真风速
        float WindSpeed=(bf.get(50)<<8)+bf.get(51);
        if (vesselService.ifNoValue(bf.get(50), bf.get(51))){
            WindSpeed=0;
        }else {
            WindSpeed=WindSpeed/10;
        }
        //真风速对应的风向
        float WindDirection=(bf.get(52)<<8)+bf.get(53);
        if (vesselService.ifNoValue(bf.get(52), bf.get(53))){
            WindDirection=0;
        }
        //水温
        float WaterTemperature=(bf.get(54)<<8)+bf.get(55);
        if (vesselService.ifNoValue(bf.get(54), bf.get(55))){
            WaterTemperature=0;
        }else {
            WaterTemperature=(WaterTemperature-500)/10;
        }
        //盐度
        float Salinity=0;
        int Sal=(bf.get(56)<<16)+(bf.get(57)<<8)+bf.get(58);
        if (vesselService.ifNoValue(bf.get(56), bf.get(57))&&(bf.get(58)==0xff)){
            Salinity=0;
        }else {
            Salinity=Sal/1000;
        }
        //工作电压
        float Voltage=(bf.get(59));
        if (bf.get(59)==0xff){
            Voltage=0;
        }else {
            Voltage=Voltage/10;
        }
        //机箱温度
        float Tcase=(bf.get(60)<<8)+bf.get(61);
        if (vesselService.ifNoValue(bf.get(60), bf.get(61)))
        {
            Tcase=0;
        }else {
            Tcase=Tcase/10;
        }
        Vessel vessel = new Vessel();
        vessel.setVesselid(VesselId);
        vessel.setTimeformat(TimeFormat);
        vessel.setBasedatetime(BaseDateTime);
        vessel.setSog(sog);
        vessel.setCog(cog);
        vessel.setLat(lat);
        vessel.setLon(lon);
        vessel.setAirtemperature(AirTemperature);
        vessel.setHumidity(Humidity);
        vessel.setPressure(Pressure);
        vessel.setVisibility(Visibility);
        vessel.setWindspeed(WindSpeed);
        vessel.setWinddirection(WindDirection);
        vessel.setWatertemperature(WaterTemperature);
        vessel.setSalinity(Salinity);
        vessel.setVoltage(Voltage);
        vessel.setTcase(Tcase);
        return vessel;


    }




    @RequestMapping("closeCOMPort")
    public static void closeCOMPort(){
        SerialPortTool.closeComPort(port);
        System.out.println("串口已经关闭");
    }


    @RequestMapping("findAllCOM")
    @ResponseBody
    public static ArrayList<String> FindCOMPort(){
        ArrayList<String> systemAllComPort = SerialPortTool.findSystemAllComPort();
        System.out.println(systemAllComPort);
        return systemAllComPort;
    }



    @RequestMapping("openCOMPort/{com}/{baudrate}")
    public static void openCOMPort(@PathVariable("com") String commName,@PathVariable int baudrate){
        //int baudrate=9600;
        if (commName==null||commName.equals("")){
            System.out.println("未发现串口"+commName);
        }else{
            port= SerialPortTool.openComPort(commName,baudrate,8,1,0);
            if (port!=null){
                System.out.println(commName+"串口已打开！");
            }
        }
        SerialPortTool.addListener(port, new SerialPortTool.DataAvailableListener() {
            @Override
            public void dataAvailable() {
                if (port==null){
                    System.out.println(port.getName()+"串口对象为空，监听开启失败！");
                }else {
                    byte[] bytes = SerialPortTool.readFromPort(port);
//                    RecvBufList.clear();
//                    for (int i = 0; i < bytes.length; i++) {
//                        RecvBufList.add(bytes[i]);
//
//                    }
//                    for (int i = 0; i < RecvBufList.size(); i++) {
//                        DCDeframer(RecvBufList.get(i));
//                        if (DConeframe!=0){
//                            ViewInfo(DCfrmbf,DConeframe);
//                            //清空
//                            DConeframe=0;
//                            DCfrmbf.clear();
//                        }
//                    }







//                    if (bytes==null){
//                        System.out.println("没有接收到数据");
//                    }else {
//                        String s = new String(bytes);
//                        System.out.println(port.getName()+"监听到数据"+":"+s);
//
//                    }
                }
            }
        });
    }
}
