package id.web.saka.report.product.ginee.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GineeProductVariantDTO {

    @JsonProperty("id")
    private String variantId;

    @JsonProperty("sku")
    private String msku;

    @JsonProperty("optionValues")
    private List<String> variantNames;

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getMsku() {
        return msku;
    }

    public void setMsku(String msku) {
        this.msku = msku;
    }

    public List<String> getVariantNames() {
        return variantNames;
    }

    public void setVariantNames(List<String> variantNames) {
        this.variantNames = variantNames;
    }

    @Override
    public String toString() {
        return "GineeProductVariantDTO{" +
                "variantId='" + variantId + '\'' +
                ", msku='" + msku + '\'' +
                ", variantNames=" + variantNames +
                '}';
    }
}
