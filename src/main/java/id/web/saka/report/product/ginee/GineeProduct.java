package id.web.saka.report.product.ginee;

import jakarta.persistence.*;

@Entity
@IdClass(GineeProductPrimaryKey.class)
@Table(name = "ginee_product")
public class GineeProduct {

    @Column(name = "product_id")
    private String productId;

    @Id
    @Column(name = "variant_id")
    private String variantId;

    @Id
    @Column(name = "msku")
    private String msku;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_variant")
    private String productVariant;

    @Column(name = "product_images")
    private String productImages;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(String productVariant) {
        this.productVariant = productVariant;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }
}
