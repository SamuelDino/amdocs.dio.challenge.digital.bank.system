package me.dio.web.challenge.digital.banking.system.amdocs.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Generated("jsonschema2pojo")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Currencies {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("buy")
    @Expose
    public Double buy;
    @SerializedName("sell")
    @Expose
    public Double sell;
    @SerializedName("variation")
    @Expose
    public Double variation;

}
