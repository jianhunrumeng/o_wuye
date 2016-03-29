package com.wuye.entity;

/**
 * Opinion entity. @author MyEclipse Persistence Tools
 */

public class WxReply extends BaseEntity implements java.io.Serializable {

    // Fields

    private Integer replyId;
    private String title;
    private String author;
    private String description;
    private String picUrl;
    private String url;

    // Constructors

    /**
     * default constructor
     */
    public WxReply() {
    }


    public Integer getReplyId() {
        return replyId;
    }


    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}