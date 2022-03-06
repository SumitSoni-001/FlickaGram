package com.example.flickagram.Models;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("datetaken")
    private String mDatetaken;
    @SerializedName("datetakengranularity")
    private Long mDatetakengranularity;
    @SerializedName("datetakenunknown")
    private String mDatetakenunknown;
    @SerializedName("farm")
    private Long mFarm;
    @SerializedName("height_h")
    private Long mHeightH;
    @SerializedName("id")
    private String mId;
    @SerializedName("isfamily")
    private Long mIsfamily;
    @SerializedName("isfriend")
    private Long mIsfriend;
    @SerializedName("ispublic")
    private Long mIspublic;
    @SerializedName("owner")
    private String mOwner;
    @SerializedName("secret")
    private String mSecret;
    @SerializedName("server")
    private String mServer;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("url_h")
    private String mUrlH;
    @SerializedName("width_h")
    private Long mWidthH;

    public String getDatetaken() {
        return mDatetaken;
    }

    public void setDatetaken(String datetaken) {
        mDatetaken = datetaken;
    }

    public Long getDatetakengranularity() {
        return mDatetakengranularity;
    }

    public void setDatetakengranularity(Long datetakengranularity) {
        mDatetakengranularity = datetakengranularity;
    }

    public String getDatetakenunknown() {
        return mDatetakenunknown;
    }

    public void setDatetakenunknown(String datetakenunknown) {
        mDatetakenunknown = datetakenunknown;
    }

    public Long getFarm() {
        return mFarm;
    }

    public void setFarm(Long farm) {
        mFarm = farm;
    }

    public Long getHeightH() {
        return mHeightH;
    }

    public void setHeightH(Long heightH) {
        mHeightH = heightH;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Long getIsfamily() {
        return mIsfamily;
    }

    public void setIsfamily(Long isfamily) {
        mIsfamily = isfamily;
    }

    public Long getIsfriend() {
        return mIsfriend;
    }

    public void setIsfriend(Long isfriend) {
        mIsfriend = isfriend;
    }

    public Long getIspublic() {
        return mIspublic;
    }

    public void setIspublic(Long ispublic) {
        mIspublic = ispublic;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public String getSecret() {
        return mSecret;
    }

    public void setSecret(String secret) {
        mSecret = secret;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        mServer = server;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrlH() {
        return mUrlH;
    }

    public void setUrlH(String urlH) {
        mUrlH = urlH;
    }

    public Long getWidthH() {
        return mWidthH;
    }

    public void setWidthH(Long widthH) {
        mWidthH = widthH;
    }

}
