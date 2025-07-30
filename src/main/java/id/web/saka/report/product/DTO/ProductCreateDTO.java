package id.web.saka.report.product.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCreateDTO {

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("brandId")
    private int brandId;

    @JsonProperty("themeId")
    private int themeId;
    @JsonProperty("typeId")
    private int typeId;

    @JsonProperty("sizeId")
    private int sizeId;

    @JsonProperty("colourCode")
    private int colourCode;

    @JsonProperty("genderId")
    private int genderId;

    @JsonProperty("buyingPrice")
    private int buyingPrice;

    @JsonProperty("sellingPrice")
    private int sellingPrice;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getColourCode() {
        return colourCode;
    }

    public void setColourCode(int colourCode) {
        this.colourCode = colourCode;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public int getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(int buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }


    @Override
    public String toString() {
        return "ProductCreateDTO{" +
                "productName='" + productName + '\'' +
                ", brandId=" + brandId +
                ", themeId=" + themeId +
                ", typeId=" + typeId +
                ", sizeId=" + sizeId +
                ", colourCode=" + colourCode +
                ", genderId=" + genderId +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
