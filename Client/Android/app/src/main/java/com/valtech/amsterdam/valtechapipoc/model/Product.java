package com.valtech.amsterdam.valtechapipoc.model;

import com.google.gson.annotations.SerializedName;
import com.valtech.amsterdam.valtechapipoc.model.annotation.ApiInfo;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by jasper.van.zijp on 7-4-2017.
 */

@ApiInfo(methodName = "content")
public class Product extends BaseModel<UUID> implements Serializable {
    @SerializedName("Title") private String mTitle;
    @SerializedName("Description") private String mDescription;
    @SerializedName("ImageUrl") private String mImageUrl;

    protected Product(UUID key) {
        super(key);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }
}
