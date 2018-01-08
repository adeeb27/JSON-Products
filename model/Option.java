
package com.adeeb.internify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Option {

    @SerializedName("id")
    @Expose
    private float id;
    @SerializedName("product_id")
    @Expose
    private float productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("values")
    @Expose
    private List<String> values = null;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

}
