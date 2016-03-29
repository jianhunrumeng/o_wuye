package com.wuye.services.vo;

/**
 * @版权：福富软件 版权所有 (c) 2007
 * @文件：com.domain.login.vo.LoginVO.java
 * @所含类：LoginVO
 * @author: panchh
 * @version: V1.0
 * @see:
 * @创建日期：Sep 20, 2007
 * @功能说明：
 * @修改记录： =============================================================<br>
 * 日期:Sep 20, 2007 panchh 创建
 * <p/>
 * =============================================================<br>
 */
public class LoginVO {

    private String macAddr;

    private String ipAddr;

    private String loginId;

    private String password;

    private String username;

    private Long teamMemberId;

    private Long teamId;

    private Long areaId;

    private String teamName;

    private String areaName;

    private String areaCode;

    private String areaNumber;

    private Long cbbm; // 承办部门,合同系统用

    private Long bmTeId;

    private Long localAreaId;

    private String oaStaff;

    private Long partnerFxId;


    /**
     * @return the areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode the areaCode to set
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return the areaId
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * @param areaId the areaId to set
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * @return the areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName the areaName to set
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return the areaNumber
     */
    public String getAreaNumber() {
        return areaNumber;
    }

    /**
     * @param areaNumber the areaNumber to set
     */
    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    /**
     * @return the ipAddr
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * @param ipAddr the ipAddr to set
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the macAddr
     */
    public String getMacAddr() {
        return macAddr;
    }

    /**
     * @param macAddr the macAddr to set
     */
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the teamId
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * @param teamId the teamId to set
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    /**
     * @return the teamMemberId
     */
    public Long getTeamMemberId() {
        return teamMemberId;
    }

    /**
     * @param teamMemberId the teamMemberId to set
     */
    public void setTeamMemberId(Long teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    /**
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCbbm() {
        return cbbm;
    }

    public void setCbbm(Long cbbm) {
        this.cbbm = cbbm;
    }

    public Long getBmTeId() {
        return bmTeId;
    }

    public void setBmTeId(Long bmTeId) {
        this.bmTeId = bmTeId;
    }

    public Long getLocalAreaId() {
        return localAreaId;
    }

    public void setLocalAreaId(Long localAreaId) {
        this.localAreaId = localAreaId;
    }

    public String getOaStaff() {
        return oaStaff;
    }

    public void setOaStaff(String oaStaff) {
        this.oaStaff = oaStaff;
    }

    public Long getPartnerFxId() {
        return partnerFxId;
    }

    public void setPartnerFxId(Long partnerFxId) {
        this.partnerFxId = partnerFxId;
    }

}
