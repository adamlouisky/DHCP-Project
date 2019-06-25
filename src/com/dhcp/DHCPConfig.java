package com.dhcp;

import com.alibaba.fastjson.JSONArray;
//import com.common.UTIL;
//import com.dhcp.common.AssignedRecord;
//import com.dhcp.common.DHCPPUB;
//import com.host.common.HOSTPUB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class DHCPConfig {

    private final static Logger logger = LoggerFactory.getLogger(DHCPConfig.class);

    public int readAssignedIP() {
     
		String configFile="assignedips.properties";
      	Properties props=new Properties();

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFile);
        try {
            if (is == null) {
                throw new Exception("配置文件 " + configFile + " 未找到.");
            }
            Properties p = new Properties();
            p.load(is);
            Iterator it = p.keySet().iterator();
            while (it.hasNext()) {
                String mac = it.next().toString();
                String body=p.getProperty(mac);
                mac=mac.replaceAll("-", ":");
                JSONArray ja=JSONArray.parseArray(body);
                String ip=ja.getString(0);
                AssignedRecord ar =new AssignedRecord();
                ar.setMac(mac);
                ar.setIp(ip);
                DHCPPUB.hmMacRecord.put(mac, ar);
            }
            is.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("分配记录读取完毕。记录数：{}", DHCPPUB.hmMacRecord.size());
        return DHCPPUB.hmMacRecord.size();

    }

    public void writeAssignedIP(){

        String configFile="assignedips.properties";
        Properties p = new Properties();

        try {
            FileOutputStream oFile=new FileOutputStream(configFile);

            Iterator it = DHCPPUB.hmMacRecord.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String mac=(String)entry.getKey();
                AssignedRecord ar = (AssignedRecord) entry.getValue();
                if ("0.0.0.0".equals(ar.getIp())) continue;
                if (ar.getReplyTime()==0) continue;
                JSONArray ja=new JSONArray();
                ja.add(ar.getIp());
                ja.add(ar.getLeaseTime());
                ja.add(ar.getReplyTime());
                mac=mac.replaceAll(":", "-");
                p.setProperty(mac, ja.toJSONString());
            }
            p.store(oFile, System.currentTimeMillis()+"");
            oFile.close();
            logger.debug("Update AssignedIPs Record File: {}",p.size());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void readConfig(){

        String configFile="dhcpconfig.properties";

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFile);
        try {
            if (is == null) {
                throw new Exception("配置文件 " + configFile + " 未找到.");
            }
            Properties p = new Properties();
            p.load(is);
            Iterator it = p.keySet().iterator();
            DHCPPUB.net = p.getProperty("net").trim();
            DHCPPUB.startNo=Integer.parseInt(p.getProperty("startNo").trim());
            DHCPPUB.endNo=Integer.parseInt(p.getProperty("endNo").trim());
            DHCPPUB.subnetMask=p.getProperty("subnetMask").trim();
            DHCPPUB.router=p.getProperty("router").trim();
            DHCPPUB.dns=p.getProperty("dns").trim();
            DHCPPUB.broadcastIP=p.getProperty("broadcastip").trim();
            String[] exclusions=p.getProperty("exclusion").trim().split(",");

            for(int i=0;i<exclusions.length;i++){
                if (exclusions[i].trim().length()>7) DHCPPUB.alExclusion.add(exclusions[i].trim());
            }

            is.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("分配记录读取完毕。记录数：{}", DHCPPUB.hmMacRecord.size());

    }

    public void readReservedIP(){
//        Iterator it = HOSTPUB.hmConfigClients.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry entry = (Map.Entry) it.next();
//            String ip=(String)entry.getKey();
//            DHCPPUB.alReserved.add(ip);
//
//        }
//        logger.debug("读取到保留地址数：{}", DHCPPUB.alReserved.size());
//        return DHCPPUB.alReserved.size();
    }
}
