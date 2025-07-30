package id.web.saka.report.category.type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "code")
    private String code;

    @Column(name = "spu_counter")
    private int spuCounter;

    @Column(name = "msku_counter")
    private int mskuCounter;

    @Column(name = "theme_id")
    private int themeId;

    @Column(name = "type_level_1")
    private String typeLevel1;

    @Column(name = "type_level_2")
    private String typeLevel2;

    @Column(name = "type_level_3")
    private String typeLevel3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSpuCounter() {
        return spuCounter;
    }

    public void setSpuCounter(int spuCounter) {
        this.spuCounter = spuCounter;
    }

    public int getMskuCounter() {
        return mskuCounter;
    }

    public void setMskuCounter(int mskuCounter) {
        this.mskuCounter = mskuCounter;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
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
        return "Type{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", code='" + code + '\'' +
                ", spuCounter=" + spuCounter +
                ", mskuCounter=" + mskuCounter +
                ", themeId=" + themeId +
                ", typeLevel1='" + typeLevel1 + '\'' +
                ", typeLevel2='" + typeLevel2 + '\'' +
                ", typeLevel3='" + typeLevel3 + '\'' +
                '}';
    }
}
