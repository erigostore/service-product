package id.web.saka.report.product.ginee.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GineeProductDTO {

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("images")
    private List<String> images;

    @JsonProperty("variationBriefs")
    private List<GineeProductVariantDTO> variants;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<GineeProductVariantDTO> getVariants() {
        return variants;
    }

    public void setVariants(List<GineeProductVariantDTO> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "GineeProductDTO{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", images=" + images +
                ", variants=" + variants +
                '}';
    }
}
