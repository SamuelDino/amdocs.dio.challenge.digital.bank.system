package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class HGBrasilQuotation {

    @SerializedName("by")
    @Expose
    public String by;
    @SerializedName("valid_key")
    @Expose
    public Boolean validKey;
    @SerializedName("results")
    @Expose
    public Results results;
    @SerializedName("execution_time")
    @Expose
    public Double executionTime;
    @SerializedName("from_cache")
    @Expose
    public Boolean fromCache;

}
