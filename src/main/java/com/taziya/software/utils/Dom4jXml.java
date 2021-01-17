package com.taziya.software.utils;

import com.taziya.software.utils.Boat;
import com.taziya.software.utils.VesselName;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class Dom4jXml {

//    @Test
//    public void test(){
//        Long start = System.currentTimeMillis();
//        Boat boat = new Boat();
//        //当前时间
//        boat.setBaseDateTime(new Date());
//        boat.setLON(33.4444f);
//        boat.setLAT(44.4567f);
//        boat.setWindSpeed(33.54745856F);
//        boat.setPressure(10000.456f);
//        boat.setAirTemperature(-4343.333f);
//        boat.setVesselID("LZU2020111");
//        createXml("E:\\boat\\xml\\",boat);
//        System.out.println("运行时间："+ (System.currentTimeMillis() - start));
//    }

    /**
     * 生成xml方法
     */
    public  void createXml(String absolutePath, Boat boat, VesselName vesselName){
        try {
            if(boat==null){
                System.out.println("无可用信息用来生成！！！！！！");
            }else {
                //先处理一下时间,得到文档需要的格式,需要将日期和时间分开到两个标签里面
                DateUtil dateUtil = new DateUtil();
                ContainFloat containFloat = new ContainFloat();

                String[] dateAndTime = dateUtil.getDateAndTime(boat.getBaseDateTime());
                //日期
                String Dates = dateAndTime[0];
                //精确到分钟的用来写入文件名的时间
                String TimeM = dateAndTime[1];
                //精确到秒的用来写入xml文档的时间
                String TimeS = dateAndTime[2];
                // 1、创建document对象
                Document document = DocumentHelper.createDocument();
                // 2、创建根节点DataSet
                Element dataSet = document.addElement("DataSet");
                // 3、向DataSet节点添加version属性
//            dataSet.addAttribute("version", "2.0");
                // 4、生成子节点及子节点内容
                Element Info = dataSet.addElement("Info");
                Element title = Info.addElement("BaseInfo");
                title.addAttribute("ID",boat.getVesselID());
                //判断整个用户信息为空时的处理
                if (vesselName==null){
                    title.addAttribute("Type","/");
                    title.addAttribute(" Name","/");
                    title.addAttribute("Owner","/");
                }else{
                    if (vesselName.getType()==null){
                        title.addAttribute("Type","/");
                    }else {
                        String type = vesselName.getType();
                        title.addAttribute("Type",type);
                    }

                    if (vesselName.getName()==null){
                        title.addAttribute(" Name","/");
                    }else {
                        String name = vesselName.getName();
                        title.addAttribute("Type",name);
                    }

                    if (vesselName.getOwner()==null){
                        title.addAttribute("Owner","/");
                    }else {
                        String owner = vesselName.getOwner();
                        title.addAttribute("Owner",owner);
                    }

                }


                //生成datetime节点
                Element DateTime = Info.addElement("DateTime");
                Element Date = DateTime.addElement("Date");
                Date.setText(Dates);
                Element Time = DateTime.addElement("Time");
                Time.setText(TimeS);

                //生成Loc节点
                Element Loc = Info.addElement("Loc");
                Element Lon = Loc.addElement("Lon");
                /**
                 *需要写入的东西是字符串类型的数据，需要对boat中分装的浮点数全部转换为字符串类型
                 * 这里调用函数转换
                 */
                FloatToString floatToString = new FloatToString();

                if(boat.getLON()==null){
                    Lon.setText("/");
                }else {
                    float lon = containFloat.containTwoFloat(boat.getLON());
                    String sLon = floatToString.change(lon);
                    Lon.setText(sLon);
                }


                Element Lat = Loc.addElement("Lat");
                if(boat.getLAT()==null){
                    Lat.setText("/");
                }else {
                    float lat = containFloat.containTwoFloat(boat.getLAT());
                    String sLat = floatToString.change(lat);
                    Lat.setText(sLat);
                }


                //生成Status节点
                Element Status = Info.addElement("Status");
                Element Vol = Status.addElement("Vol");
                if(boat.getVoltage()==null){
                    Vol.setText("/");
                }else {
                    float voltage = containFloat.containOnoFloat(boat.getVoltage());
                    String sVol = floatToString.change(voltage);
                    Vol.setText(sVol);
                }


                Element SS = Status.addElement("SS");
                if(boat.getSOG()==null){
                    SS.setText("/");
                }else {
                    float sog = containFloat.containOnoFloat(boat.getSOG());
                    String sSS = floatToString.change(sog);
                    SS.setText(sSS);
                }

                Element SD = Status.addElement("SD");
                if(boat.getCOG()==null){
                    SD.setText("/");
                }else {
                    float cog = containFloat.containOnoFloat(boat.getCOG());
                    String sSD = floatToString.change(cog);
                    SD.setText(sSD);
                }


                //生成DataSet下的Data子标签
                Element Data = dataSet.addElement("Data");
                Element Met = Data.addElement("Met");
                Element WS = Met.addElement("WS");
                /**
                 * 相对风速与相对风速的风向数据缺少，直接填充'/',暂时不考虑
                 */
                // String sWS = floatToString.change(boat.getWindSpeed());
                WS.setText("/");

                Element WD = Met.addElement("WD");
                // String sWD = floatToString.change(boat.getWindDirection());
                WD.setText("/");


                Element CWS = Met.addElement("CWS");
                if(boat.getWindSpeed()==null){
                    CWS.setText("/");
                }else {
                    float windspeed = containFloat.containOnoFloat(boat.getWindSpeed());
                    String sCWS = floatToString.change(windspeed);
                    CWS.setText(sCWS);
                }


                Element CWD = Met.addElement("CWD");
                if(boat.getWindDirection()==null){
                    CWD.setText("/");
                }else {
                    String sCWD = floatToString.change(boat.getWindDirection());
                    CWD.setText(sCWD);
                }


                Element BP = Met.addElement("BP");
                if(boat.getPressure()==null){
                    BP.setText("/");
                }else {
                    float pressure = containFloat.containOnoFloat(boat.getPressure());
                    String sBP = floatToString.change(pressure);
                    BP.setText(sBP);
                }


                Element AT = Met.addElement("AT");
                if(boat.getAirTemperature()==null){
                    AT.setText("/");
                }else {
                    float airtemperature = containFloat.containOnoFloat(boat.getAirTemperature());
                    String sAT = floatToString.change(airtemperature);
                    AT.setText(sAT);
                }


                Element HU = Met.addElement("HU");
                if(boat.getHumidity()==null){
                    HU.setText("/");
                }else {
                    String sHU = floatToString.change(boat.getHumidity());
                    HU.setText(sHU);
                }


                Element Hydrology = Data.addElement("Hydrology");
                Element WT = Hydrology.addElement("WT");
                if(boat.getWaterTemperature()==null){
                    WT.setText("/");
                }else {
                    float WaterTemperature = containFloat.containOnoFloat(boat.getWaterTemperature());
                    String sWT = floatToString.change(WaterTemperature );
                    WT.setText(sWT);
                }


                // 5、设置生成xml的格式
                OutputFormat format = OutputFormat.createPrettyPrint();
                // 设置编码格式
                format.setEncoding("GB2312");
                // 6、生成xml文件
                File file = new File(absolutePath+Dates+TimeM+boat.getVesselID()+".dat.xml");
                XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
                // 设置是否转义，默认使用转义字符
                writer.setEscapeText(false);
                writer.write(document);
                writer.close();
                System.out.println("生成xml成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成xml失败");
        }
    }
}


