package com.dhcp;

public class AssignedRecord {
	
	private String mac;
	private String ip;
	private String subnetMask;
	private String router;
	private String dns;
	private String userName;
	private String userID;
	private long discoverTime;
	private int discoverCount;
	private long offerTime;
	private int offerCount;
	private long requestTime;
	private int requestCount;
	private long replyTime;
	private int replyCount;
	private long declineTime;
	private int declineCount;
	private long informTime;
	private int informCount;
	private long releaseTime;
	private int releaseCount;
	private long leaseTime;
	private long expireTime;
	private int status;	
	private String server;
	
	
	/**
	 * @return the informTime
	 */
	public long getInformTime() {
		return informTime;
	}
	/**
	 * @param informTime the informTime to set
	 */
	public void setInformTime(long informTime) {
		this.informTime = informTime;
	}
	/**
	 * @return the informCount
	 */
	public int getInformCount() {
		return informCount;
	}
	/**
	 * @param informCount the informCount to set
	 */
	public void setInformCount(int informCount) {
		this.informCount = informCount;
	}
	/**
	 * @return the releaseTime
	 */
	public long getReleaseTime() {
		return releaseTime;
	}
	/**
	 * @param releaseTime the releaseTime to set
	 */
	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}
	/**
	 * @return the releaseCount
	 */
	public int getReleaseCount() {
		return releaseCount;
	}
	/**
	 * @param releaseCount the releaseCount to set
	 */
	public void setReleaseCount(int releaseCount) {
		this.releaseCount = releaseCount;
	}
	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}
	/**
	 * @param mac the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the subnetMask
	 */
	public String getSubnetMask() {
		return subnetMask;
	}
	/**
	 * @param subnetMask the subnetMask to set
	 */
	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}
	/**
	 * @return the router
	 */
	public String getRouter() {
		return router;
	}
	/**
	 * @param router the router to set
	 */
	public void setRouter(String router) {
		this.router = router;
	}
	/**
	 * @return the dns
	 */
	public String getDns() {
		return dns;
	}
	/**
	 * @param dns the dns to set
	 */
	public void setDns(String dns) {
		this.dns = dns;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the discoverTime
	 */
	public long getDiscoverTime() {
		return discoverTime;
	}
	/**
	 * @param discoverTime the discoverTime to set
	 */
	public void setDiscoverTime(long discoverTime) {
		this.discoverTime = discoverTime;
	}
	/**
	 * @return the offerTime
	 */
	public long getOfferTime() {
		return offerTime;
	}
	/**
	 * @param offerTime the offerTime to set
	 */
	public void setOfferTime(long offerTime) {
		this.offerTime = offerTime;
	}
	/**
	 * @return the requestTime
	 */
	public long getRequestTime() {
		return requestTime;
	}
	/**
	 * @param requestTime the requestTime to set
	 */
	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}
	/**
	 * @return the replyTime
	 */
	public long getReplyTime() {
		return replyTime;
	}
	/**
	 * @param replyTime the replyTime to set
	 */
	public void setReplyTime(long replyTime) {
		this.replyTime = replyTime;
	}
	/**
	 * @return the leaseTime
	 */
	public long getLeaseTime() {
		return leaseTime;
	}
	/**
	 * @param leaseTime the leaseTime to set
	 */
	public void setLeaseTime(long leaseTime) {
		this.leaseTime = leaseTime;
	}
	/**
	 * @return the expireTime
	 */
	public long getExpireTime() {
		return expireTime;
	}
	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the discoverCount
	 */
	public int getDiscoverCount() {
		return discoverCount;
	}
	/**
	 * @param discoverCount the discoverCount to set
	 */
	public void setDiscoverCount(int discoverCount) {
		this.discoverCount = discoverCount;
	}
	/**
	 * @return the requestCount
	 */
	public int getRequestCount() {
		return requestCount;
	}
	/**
	 * @param requestCount the requestCount to set
	 */
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	/**
	 * @return the offerCount
	 */
	public int getOfferCount() {
		return offerCount;
	}
	/**
	 * @param offerCount the offerCount to set
	 */
	public void setOfferCount(int offerCount) {
		this.offerCount = offerCount;
	}
	/**
	 * @return the replyCount
	 */
	public int getReplyCount() {
		return replyCount;
	}
	/**
	 * @param replyCount the replyCount to set
	 */
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	/**
	 * @return the declineTime
	 */
	public long getDeclineTime() {
		return declineTime;
	}
	/**
	 * @param declineTime the declineTime to set
	 */
	public void setDeclineTime(long declineTime) {
		this.declineTime = declineTime;
	}
	/**
	 * @return the declineCount
	 */
	public int getDeclineCount() {
		return declineCount;
	}
	/**
	 * @param declineCount the declineCount to set
	 */
	public void setDeclineCount(int declineCount) {
		this.declineCount = declineCount;
	}
	
	
	
	

}
