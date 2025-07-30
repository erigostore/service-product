package id.web.saka.report.category.type.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class    TypeDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("brandCode")
    private String brandCode;

    @JsonProperty("theme")
    private String theme;

    @JsonProperty("typeCode")
    private String typeCode;

    @JsonProperty("typeLevel1")
    private String typeLevel1;

    @JsonProperty("typeLevel2")
    private String typeLevel2;

    @JsonProperty("typeLevel3")
    private String typeLevel3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeLevel1() {
        return typeLevel1;
    }

    public void setTypeLevel1(String typeLevel1) {
        this.typeLevel1 = typeLevel1;
    }

    public String getTypeLevel2() {
        return typeLevel2;
    }

    public void setTypeLevel2(String typeLevel2) {
        this.typeLevel2 = typeLevel2;
    }

    public String getTypeLevel3() {
        return typeLevel3;
    }

    public void setTypeLevel3(String typeLevel3) {
        this.typeLevel3 = typeLevel3;
    }

    @Override
    public String toString() {
        return "TypeDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", brandCode='" + brandCode + '\'' +
                ", theme='" + theme + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", typeLevel1='" + typeLevel1 + '\'' +
                ", typeLevel2='" + typeLevel2 + '\'' +
                ", typeLevel3='" + typeLevel3 + '\'' +
                '}';
    }
}
