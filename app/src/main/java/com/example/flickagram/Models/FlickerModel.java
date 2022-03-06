package com.example.flickagram.Models;

import com.google.gson.annotations.SerializedName;

public class FlickerModel {

    @SerializedName("photos")
    private Photos mPhotos;

    public Photos getPhotos() {
        return mPhotos;
    }

    public void setPhotos(Photos photos) {
        mPhotos = photos;
    }

//    @SerializedName("stat")
//    private String mStat;

/*

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }
*/

}
