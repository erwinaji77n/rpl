package com.justfriend.suratuin.Model.Profile;

import com.google.gson.annotations.SerializedName;

public class ResponseProfile {

    @SerializedName("name")
    private String name;

    @SerializedName("updated_at")
    private Object updatedAt;

    @SerializedName("nim")
    private String nim;

    @SerializedName("prodi")
    private int prodi;
    @SerializedName("fakultas")
    private int fakultas;

    @SerializedName("created_at")
    private Object createdAt;

}
