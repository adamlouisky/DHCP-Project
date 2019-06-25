package com.dhcp;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class DHCPPUB {

    public static ConcurrentHashMap<String, AssignedRecord> hmMacRecord=new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, AssignedRecord> hmIPRecord=new ConcurrentHashMap<>();
    public static String net="58.193.244.1";
    public static int startNo=10;
    public static int endNo=160;
    public static String subnetMask="255.255.255.0";
    public static String router="58.193.244.254";
    public static String dns="58.193.244.111";
    public static String broadcastIP="255.255.255.255";
    public static ArrayList<String> alExclusion=new ArrayList<>();
    public static ArrayList<String> alReserved=new ArrayList<>();
}
