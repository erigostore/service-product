package id.web.saka.report.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @JsonProperty("masterProductId")
    @Column(name = "id")
    private String id;
    @JsonProperty("brand")
    @Column(name = "brand")
    private String brand;
    @JsonProperty("msku")
    @Column(name = "msku")
    private String msku;

    @JsonProperty("spu")
    @Column(name = "spu")
    private String spu;
    @JsonProperty("name")
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @Column(name = "theme_id")
    private Integer themeId;
    @JsonIgnore
    @Transient
    private String theme;
    @JsonProperty("type")
    @Column(name = "type")
    private String type;
    @JsonProperty("category")
    @Transient
    private String category;
    @JsonIgnore
    @Column(name = "status")
    private String status;
    @JsonProperty("variant")
    @Column(name = "variant")
    private String variant;
    @JsonProperty("colour")
    @Column(name = "colour")
    private String colour;
    @JsonProperty("sex")
    @Column(name = "sex")
    private String sex;
    @JsonIgnore
    @Column(name = "colour_id")
    private int colourId;
    @JsonProperty("selling_price")
    @Column(name = "selling_price")
    private Long sellingPrice;
    @JsonProperty("purchasePrice")
    @Column(name = "purchase_price")
    private Long purchasePrice;
    @JsonProperty("createDatetime")
    @Column(name = "create_datetime")
    private String createDatetime;
    @JsonProperty("updateDatetime")
    @Column(name = "update_datetime")
    private String updateDatetime;

    @JsonProperty("quantity")
    @Transient
    private int quantity;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spuId) {
        this.spu = spuId;
    }

    public String getMsku() {
        return msku;
    }

    public void setMsku(String id) {
        this.msku = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusEnum(ProductStatus status) {
        this.status = status.toString();
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public int getColourId() {
        return colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Long sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("sellingPrice")
    private void unpackSellingPriceAmount(Map<String, Long> sellingPriceMap) {
        sellingPrice = sellingPriceMap.get("amount");
    }


    @JsonProperty("extraInfoS")
    private void unpackTheme(Map<String, Object> extraInfo) {
        if(extraInfo.containsKey("additionInfo")) {
            Map<String, String> additionsInfo = (Map<String,String>) extraInfo.get("additionInfo");

            if(additionsInfo != null && additionsInfo.containsKey("remark1")) {
                theme = (String) additionsInfo.get("remark1");
            }
        }
    }

    @JsonProperty("extraInfo")
    private void unpackColour(Map<String, Object> extraInfo) {
        if(extraInfo.containsKey("additionInfo")) {
            Map<String, String> additionsInfo = (Map<String,String>) extraInfo.get("additionInfo");

            if(additionsInfo != null && additionsInfo.containsKey("remark2")) {
                colour = (String) additionsInfo.get("remark2");
            }
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", msku='" + msku + '\'' +
                ", spu='" + spu + '\'' +
                ", name='" + name + '\'' +
                ", themeId=" + themeId +
                ", theme='" + theme + '\'' +
                ", status='" + status + '\'' +
                ", variant='" + variant + '\'' +
                ", colour='" + colour + '\'' +
                ", colourId=" + colourId +
                ", sellingPrice=" + sellingPrice +
                ", purchasePrice=" + purchasePrice +
                ", createDatetime='" + createDatetime + '\'' +
                ", updateDatetime='" + updateDatetime + '\'' +
                '}';
    }
}
