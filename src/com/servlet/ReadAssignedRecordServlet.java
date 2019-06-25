package com.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhcp.AssignedRecord;
import com.dhcp.DHCPPUB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@WebServlet("/ReadAssignedRecordServlet")
public class ReadAssignedRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Iterator it = DHCPPUB.hmMacRecord.entrySet().iterator();
        JSONArray ja=new JSONArray();
        while(it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            AssignedRecord ar=(AssignedRecord)entry.getValue();
            JSONObject jo=new JSONObject();
            jo.put("mac",ar.getMac());
            jo.put("ip",ar.getIp());
            jo.put("mask",ar.getSubnetMask());
            jo.put("router",ar.getRouter());
            jo.put("dns",ar.getDns());
            jo.put("server",ar.getServer());
            jo.put("lease",ar.getLeaseTime());
            jo.put("offerTime",ar.getOfferTime());
            jo.put("offerCount",ar.getOfferCount());
            jo.put("replyTime",ar.getReplyTime());jo.put("replyCount",ar.getReplyCount());
            jo.put("informCount",ar.getInformCount());
            jo.put("declineCount",ar.getDeclineCount());
            jo.put("releaseCount",ar.getReleaseCount());
            ja.add(jo);
        }
        String ret=ja.toJSONString();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("appliction/json;charset=utf-8");
        response.getWriter().write(ret);
    }

}
