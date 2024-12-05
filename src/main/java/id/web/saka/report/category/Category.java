package id.web.saka.report.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @PrimaryKey
    @JsonProperty("spu")
    @Column(name = "spu")
    private String spu;

    @JsonProperty("category")
    @Column(name = "category")
    private String category;

    @JsonIgnore
    @Column(name = "category_level_1_id")
    private Long categoryLevel1Id;

    @JsonProperty("category_level_1_name")
    @Column(name = "category_level_1_name")
    private String categoryLevel1Name;

    @JsonIgnore
    @Column(name = "category_level_2_id")
    private Long categoryLevel2Id;
    @JsonProperty("category_level_2_name")
    @Column(name = "category_level_2_name")
    private String categoryLevel2Name;

    @JsonIgnore
    @Column(name = "category_level_3_id")
    private Long categoryLevel3Id;
    @JsonProperty("category_level_3_name")
    @Column(name = "category_level_3_name")
    private String categoryLevel3Name;

    /*public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }*/

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCategoryLevel1Id() {
        return categoryLevel1Id;
    }

    public void setCategoryLevel1Id(Long categoryLevel1Id) {
        this.categoryLevel1Id = categoryLevel1Id;
    }

    public String getCategoryLevel1Name() {
        return categoryLevel1Name;
    }

    public void setCategoryLevel1Name(String categoryLevel1Name) {
        this.categoryLevel1Name = categoryLevel1Name;
    }

    public Long getCategoryLevel2Id() {
        return categoryLevel2Id;
    }

    public void setCategoryLevel2Id(Long categoryLevel2Id) {
        this.categoryLevel2Id = categoryLevel2Id;
    }

    public String getCategoryLevel2Name() {
        return categoryLevel2Name;
    }

    public void setCategoryLevel2Name(String categoryLevel2Name) {
        this.categoryLevel2Name = categoryLevel2Name;
    }

    public Long getCategoryLevel3Id() {
        return categoryLevel3Id;
    }

    public void setCategoryLevel3Id(Long categoryLevel3Id) {
        this.categoryLevel3Id = categoryLevel3Id;
    }

    public String getCategoryLevel3Name() {
        return categoryLevel3Name;
    }

    public void setCategoryLevel3Name(String categoryLevel3Name) {
        this.categoryLevel3Name = categoryLevel3Name;
    }
}
