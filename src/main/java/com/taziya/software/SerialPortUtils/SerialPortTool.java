package com.taziya.software.SerialPortUtils;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

public class SerialPortTool {





    //内部类，来进行监听
    public static class SerialPortListener implements SerialPortEventListener {

        private DataAvailableListener mDataAvailableListener;

        public SerialPortListener(DataAvailableListener mDataAvailableListener) {
            this.mDataAvailableListener = mDataAvailableListener;
        }

        public void serialEvent(SerialPortEvent serialPortEvent) {
            switch (serialPortEvent.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE: // 1.串口存在有效数据
                    if (mDataAvailableListener != null) {
                        mDataAvailableListener.dataAvailable();
                    }
                    break;

                case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2.输出缓冲区已清空
                    break;

                case SerialPortEvent.CTS: // 3.清除待发送数据
                    break;

                case SerialPortEvent.DSR: // 4.待发送数据准备好了
                    break;

                case SerialPortEvent.RI: // 5.振铃指示
                    break;

                case SerialPortEvent.CD: // 6.载波检测
                    break;

                case SerialPortEvent.OE: // 7.溢位（溢出）错误
                    break;

                case SerialPortEvent.PE: // 8.奇偶校验错误
                    break;

                case SerialPortEvent.FE: // 9.帧错误
                    break;

                case SerialPortEvent.BI: // 10.通讯中断
                    System.out.println("与串口设备通讯中断");
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 串口存在有效数据监听
     */
    public interface DataAvailableListener {
        /**
         * 串口存在有效数据
         */
        void dataAvailable();
    }



    /**
     * @Description:列出所有可用串口
     */
    public static  ArrayList<String> findSystemAllComPort() {



        //getPortIdentifiers：获得电脑主板当前所有可用串口

        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<>();

        /**
         *  将可用串口名添加到 List 列表
         */
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//名称如 COM1、COM2....
            portNameList.add(portName);
        }
        return portNameList;
    }


    //打开电脑上面指定的串口
    public static SerialPort openComPort(String portName, int b, int d, int s, int p)  {

        CommPort commPort=null;
        try {
            //当没有传入可用的串口时候，就默认使用电脑中可用的com口的第一个
            if (portName==null||"".equals(portName)){
                List<String> comPortList = findSystemAllComPort();
                if (comPortList!=null&&comPortList.size()>0){
                    portName=comPortList.get(0);
                }
            }
            System.out.println("开始打开串口：portName=" + portName + ",baudrate=" + b + ",datebits=" + d + ",stopbits=" + s + ",parity=" + p);
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            commPort = portIdentifier.open(portName, 5000);
            if (commPort instanceof SerialPort){
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(b,d,s,p);
                System.out.println("打开串口 " + portName + " 成功...");
                return serialPort;
            }else {
                System.out.println("当前端口 " + commPort.getName() + " 不是串口...");
            }

        } catch (PortInUseException e) {
            System.out.println("串口 " + portName + " 已经被占用，请先解除占用...");
        } catch (UnsupportedCommOperationException e) {
            System.out.println("串口参数设置错误，关闭串口，数据位[5-8]、停止位[1-3]、验证位[0-4]...");
            if (commPort != null) {//此时必须关闭串口，否则下次 portIdentifier.open 时会打不开串口，因为已经被占用
                commPort.close();
            }

        } catch (NoSuchPortException e) {
            System.out.println("没有这个串口");
        }
        System.out.println("打开串口 " + portName + " 失败...");
        return null;
    }



    //从串口读数据
    public static byte[] readFromPort(SerialPort serialPort) {

        InputStream in = null;
        byte[] bytes = null;

        try {
            in = serialPort.getInputStream();
            int bufflenth = in.available();        //获取buffer里的数据长度

            while (bufflenth != 0) {
                bytes = new byte[bufflenth];    //初始化byte数组为buffer中数据的长度
                in.read(bytes);
                bufflenth = in.available();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;

    }




    //添加事件监听器
    public static void addListener(SerialPort port, DataAvailableListener listener){

        try {
            //给串口添加监听器
            port.addEventListener(new SerialPortListener(listener));
            //设置当有数据到达时唤醒监听接收线程
            port.notifyOnDataAvailable(true);
            //设置当通信中断时唤醒中断线程
            port.notifyOnBreakInterrupt(true);
        } catch (TooManyListenersException e) {

        }
    }


    //关闭串口
    public static void closeComPort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            System.out.println("关闭串口 " + serialPort.getName());
        }
    }

    public static byte[] toByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        final byte[] byteArray = new byte[hexString.length() / 2];

        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

}
