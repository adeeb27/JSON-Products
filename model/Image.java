
package com.adeeb.internify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Image {

    @SerializedName("id")
    @Expose
    private float id;
    @SerializedName("product_id")
    @Expose
    private float productId;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("variant_ids")
    @Expose
    private List<Object> variantIds = null;

    public float getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<Object> getVariantIds() {
        return variantIds;
    }

    public void setVariantIds(List<Object> variantIds) {
        this.variantIds = variantIds;
    }

}
