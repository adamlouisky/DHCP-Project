package com.init;

import com.dhcp.DHCPConfig;
import com.dhcp.DHCPPUB;
import com.dhcp.DHCPServer;
import com.dhcp.DHCPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class InitServlet extends HttpServlet {

    private final Logger logger= (Logger) LoggerFactory.getLogger(InitServlet.class);



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        long bootTime=System.currentTimeMillis();

        logger.debug("Start at {}", formatTime(bootTime));
        // read config file

        DHCPConfig dhcpConfig=new DHCPConfig();
        dhcpConfig.readConfig();

        logger.debug("Read Config: Net={}, Router={}", DHCPPUB.net,DHCPPUB.router);
        // start thread
        DHCPServer dhcpServer=new DHCPServer();
        dhcpServer.start();

    }

    private String formatTime(long time){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return df.format(time);
    }
}
