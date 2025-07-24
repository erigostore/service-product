package id.web.saka.report.product.ginee;

import java.io.Serializable;

public class GineeProductPrimaryKey implements Serializable {
    private String variantId;
    private String msku;

    public GineeProductPrimaryKey() {
    }

    public GineeProductPrimaryKey(String variantId, String msku) {
        this.variantId = variantId;
        this.msku = msku;
    }

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
}
