package com.wuye.common.vo;


public class FtpServerParam {
    private String hostname;
    private int port;
    private String username;
    private String password;
    private long lPerTime;
    private String LocalDir;
    private String path;


    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
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
     * @return the lPerTime
     */
    public long getLPerTime() {
        return lPerTime;
    }

    /**
     * @param perTime the lPerTime to set
     */
    public void setLPerTime(long perTime) {
        lPerTime = perTime;
    }

    /**
     * @return the localDir
     */
    public String getLocalDir() {
        return LocalDir;
    }

    /**
     * @param localDir the localDir to set
     */
    public void setLocalDir(String localDir) {
        LocalDir = localDir;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
}
