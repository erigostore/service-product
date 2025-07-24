package id.web.saka.report.product.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductLabelDTO {

    @JsonProperty("msku")
    private String msku;

    @JsonProperty("totalPrint")
    private int quantity;


    public String getMsku() {
        return msku;
    }

    public void setMsku(String msku) {
        this.msku = msku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
