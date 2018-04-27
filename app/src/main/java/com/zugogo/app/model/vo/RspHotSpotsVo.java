package com.zugogo.app.model.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by evan on 2018/1/15.
 */

public class RspHotSpotsVo implements Serializable, Parcelable {
    private static final long serialVersionUID = -1344662300700807990L;

    private int id;
    private String name;
    private double lat;
    private double lng;

    public RspHotSpotsVo() {
    }

    protected RspHotSpotsVo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
    }

    public static final Creator<RspHotSpotsVo> CREATOR = new Creator<RspHotSpotsVo>() {
        @Override
        public RspHotSpotsVo createFromParcel(Parcel in) {
            return new RspHotSpotsVo(in);
        }

        @Override
        public RspHotSpotsVo[] newArray(int size) {
            return new RspHotSpotsVo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeDouble(lat);
        parcel.writeDouble(lng);
    }
}
