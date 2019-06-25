package com.dhcp;


import java.util.Iterator;
import java.util.Map;


public class AssignUtil {
	
	public static boolean checkMacRecord(String mac){
		if (DHCPPUB.hmMacRecord.get(mac.toUpperCase())!=null){
			return true;
		}
		return false;		
	}
	
	public static void updateDiscover(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		long time=System.currentTimeMillis();
		if (ar==null){
			ar=new AssignedRecord();
			ar.setMac(mac);
			ar.setDiscoverCount(1);
			ar.setDiscoverTime(time);
			ar.setStatus(DHCPOptions.DHCPDISCOVER);
			DHCPPUB.hmMacRecord.put(mac, ar);
		}else{
			ar.setDiscoverTime(time);
			ar.setDiscoverCount(ar.getDiscoverCount()+1);
		}
		
	}
	
	public static void updateRequest(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		long time=System.currentTimeMillis();
		if (ar!=null){			
			ar.setRequestTime(time);
			ar.setRequestCount(ar.getRequestCount()+1);
			ar.setStatus(DHCPOptions.DHCPREQUEST);			
		}		
	}
	
	public static void updateOffer(String mac,String ip, String server, String mask,String router,String dns,long lease){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		long time=System.currentTimeMillis();
		if (ar!=null){			
			ar.setIp(ip);
			ar.setServer(server);
			ar.setSubnetMask(mask);
			ar.setRouter(router);
			ar.setDns(dns);
			ar.setLeaseTime(lease);
			
			ar.setOfferTime(time);
			ar.setOfferCount(ar.getOfferCount()+1);
			ar.setStatus(DHCPOptions.DHCPOFFER);
		}		
	}
	
	public static void updateAck(String mac,String ip, String server, String mask,String router,String dns,long lease){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		long time=System.currentTimeMillis();
		if (ar!=null){			
			ar.setIp(ip);
			ar.setServer(server);
			ar.setSubnetMask(mask);
			ar.setRouter(router);
			ar.setDns(dns);
			ar.setLeaseTime(lease);
			
			ar.setReplyTime(time);
			ar.setReplyCount(ar.getReplyCount()+1);
			ar.setStatus(DHCPOptions.DHCPACK);
			new DHCPConfig().writeAssignedIP();
		}		
	}
	
	public static void updateNak(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		long time=System.currentTimeMillis();
		if (ar!=null){						
			ar.setReplyTime(time);
			ar.setReplyCount(ar.getReplyCount()+1);
			ar.setStatus(DHCPOptions.DHCPNAK);
		}		
	}
	
	public static void updateInform(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		long time= System.currentTimeMillis();
		if (ar!=null){						
			ar.setInformTime(time);
			ar.setInformCount(ar.getInformCount()+1);
			ar.setStatus(DHCPOptions.DHCPINFORM);
		}		
	}
	
	public static void updateDecline(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		long time=System.currentTimeMillis();
		if (ar!=null){						
			ar.setDeclineTime(time);
			ar.setDeclineCount(ar.getDeclineCount()+1);
			ar.setStatus(DHCPOptions.DHCPDECLINE);
		}		
	}
	
	public static void updateRelease(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		long time=System.currentTimeMillis();
		if (ar!=null){						
			ar.setReleaseTime(time);
			ar.setReleaseCount(ar.getReleaseCount()+1);
			ar.setStatus(DHCPOptions.DHCPRELEASE);
		}		
	}
	
	
	
	
	
	public static AssignedRecord getMacRecord(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		return ar;
	}
	
	public static void declineIPAssign(String mac,String ip){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		if (ar==null) return;
		if (ar.getStatus()!=DHCPOptions.DHCPDECLINE){
			ar.setStatus(DHCPOptions.DHCPDECLINE);
			ar.setDeclineCount(ar.getDeclineCount()+1);
			ar.setDeclineTime(System.currentTimeMillis());
		}
		ar.setIp("0.0.0.0");
		
		if (DHCPPUB.hmIPRecord.get(ip)!=null){
			DHCPPUB.hmIPRecord.remove(ip);
		}		
	}
	
	public static void removeIPAssign(String ip){
		Iterator it = DHCPPUB.hmMacRecord.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			AssignedRecord ar = (AssignedRecord) entry.getValue();
			if (ip.equals(ar.getIp())) {
				DHCPPUB.hmMacRecord.remove(entry.getKey());
				break;
			}
		}
		if (DHCPPUB.hmIPRecord.get(ip)!=null){
			DHCPPUB.hmIPRecord.remove(ip);
		}		
	}
	
	public static void addIPAssign(String ip,int status){
		AssignedRecord ar= new AssignedRecord();
        ar.setIp(ip);
        ar.setStatus(status);
        DHCPPUB.hmIPRecord.put(ip,ar);
	}

//	private static String getClientIPbyMac(String mac){
//		String temp=mac.replaceAll(":","-");
//		Iterator it = HOSTPUB.hmConfigClients.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String ip=(String)entry.getKey();
//			String[] clientInfo = (String[]) entry.getValue();
//			if (clientInfo.length > 0) {
//				if (clientInfo[0].equals(temp)) return ip;
//			}
//		}
//
//		return null;
//	}
	
	public static String getAvailableIP(String mac){

	    String temp=mac.replaceAll(":","-");
	    String ip=null;
//	    getClientIPbyMac(temp.toUpperCase());
//
//	    if (ip!=null){
//	        addAssignedIP(ip,mac.toUpperCase());
//            return ip;
//        }

		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		if (ar!=null &&  ar.getIp()!=null && !ar.getIp().equals("0.0.0.0")){
			return ar.getIp();
		}else{
			String prefix = DHCPPUB.net.substring(0, DHCPPUB.net.lastIndexOf("."));
			ip=prefix+"."+ DHCPPUB.startNo;
			boolean bGot=false;
			while (DHCPPUB.startNo<= DHCPPUB.endNo){
                ip=prefix+"."+ DHCPPUB.startNo;
			    if (DHCPPUB.alExclusion.contains(ip)){
                    DHCPPUB.startNo++;
                    continue;
                }
                if (DHCPPUB.hmIPRecord.get(ip)!=null){
                    DHCPPUB.startNo++;
                    continue;
                }
                if (DHCPPUB.alReserved.contains(ip)){
                    DHCPPUB.startNo++;
                    continue;
                }
    			bGot=true;
				break;
			}
			if (bGot){
				addAssignedIP(ip,mac.toUpperCase());
				DHCPPUB.startNo++;
				return ip;
			}
			return "";
		}		
	}
	
	public static String getDiscoverIP(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		if (ar!=null){
			return ar.getIp();
		}else{			
			return "";
		}		
	}
	
	public static String getAssignedIP(String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		if (ar!=null){
			return ar.getIp();
		}else{			
			return "";
		}		
	}
	
	private static AssignedRecord addAssignedIP(String ip ,String mac){
		AssignedRecord ar= DHCPPUB.hmMacRecord.get(mac.toUpperCase());
		
		if (ar==null){
			ar= new AssignedRecord();
			ar.setIp(ip);
			ar.setMac(mac);
			ar.setDiscoverTime(System.currentTimeMillis());
			ar.setDiscoverCount(1);
			DHCPPUB.hmMacRecord.put(mac, ar);
			DHCPPUB.hmIPRecord.put(ip, ar);
		}else{
			ar.setIp(ip);
			DHCPPUB.hmIPRecord.put(ip, ar);
		}
		return ar;
	}
	
	
	
	 public static String Bytes2MACStr(byte[] b) {
	        String ret = "";
	        for (int i = 0; i < b.length; i++) {
	            String hex = Integer.toHexString(b[i] & 0xFF);
	            if (hex.length() == 1) {
	                hex = '0' + hex;
	            }

	            ret += hex.toUpperCase();
	            if (i != b.length - 1) {
	                ret += ":";
	            }
	        }
	        return ret;
	    }

	    public static String Bytes2IPStr(byte[] b) {
	        String ret = "";
	        for (int i = 0; i < b.length; i++) {
	            ret += b[i] & 255;
	            if (i != b.length - 1) {
	                ret += ".";
	            }
	        }
	        return ret;
	    }

	    public static byte[] MacStr2Bytes(String mac) {
	        byte[] b = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
	        String[] m = mac.split(":");
	        if (m.length != 6) {
	            return b;
	        }
	        for (int i = 0; i < m.length; i++) {
	            char[] hexChars = m[i].toUpperCase().toCharArray();
	            b[i] = (byte) (char2Byte(hexChars[0]) << 4 | char2Byte(hexChars[1]));
	        }
	        return b;
	    }
	    
	    private static byte char2Byte(char c) {
	        return (byte) "0123456789ABCDEF".indexOf(c);
	    }
	

}
