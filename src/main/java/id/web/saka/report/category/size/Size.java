package id.web.saka.report.category.size;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "size")
public class Size {

    @Id
    @JsonProperty("id")
    @Column(name = "id")
    private int id;

    @JsonProperty("size1")
    @Column(name = "size1")
    private String size1;

    @JsonProperty("size2")
    @Column(name = "size2")
    private String size2;

    @JsonProperty("size3")
    @Column(name = "size3")
    private String size3;

    @JsonProperty("size4")
    @Column(name = "size4")
    private String size4;

    @JsonProperty("size5")
    @Column(name = "size5")
    private String size5;

    @JsonProperty("size6")
    @Column(name = "size6")
    private String size6;

    @JsonProperty("size7")
    @Column(name = "size7")
    private String size7;

    @JsonProperty("size8")
    @Column(name = "size8")
    private String size8;

    @JsonProperty("size9")
    @Column(name = "size9")
    private String size9;

    @JsonProperty("size10")
    @Column(name = "size10")
    private String size10;

    @JsonProperty("size11")
    @Column(name = "size11")
    private String size11;

    @JsonProperty("size12")
    @Column(name = "size12")
    private String size12;

    @JsonProperty("size13")
    @Column(name = "size13")
    private String size13;

    @JsonProperty("size14")
    @Column(name = "size14")
    private String size14;

    @JsonProperty("size15")
    @Column(name = "size15")
    private String size15;

    @JsonProperty("size16")
    @Column(name = "size16")
    private String size16;

    @JsonProperty("size17")
    @Column(name = "size17")
    private String size17;

    @JsonProperty("size18")
    @Column(name = "size18")
    private String size18;

    @JsonProperty("size19")
    @Column(name = "size19")
    private String size19;

    @JsonProperty("size20")
    @Column(name = "size20")
    private String size20;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize1() {
        return size1;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }

    public String getSize2() {
        return size2;
    }

    public void setSize2(String size2) {
        this.size2 = size2;
    }

    public String getSize3() {
        return size3;
    }

    public void setSize3(String size3) {
        this.size3 = size3;
    }

    public String getSize4() {
        return size4;
    }

    public void setSize4(String size4) {
        this.size4 = size4;
    }

    public String getSize5() {
        return size5;
    }

    public void setSize5(String size5) {
        this.size5 = size5;
    }

    public String getSize6() {
        return size6;
    }

    public void setSize6(String size6) {
        this.size6 = size6;
    }

    public String getSize7() {
        return size7;
    }

    public void setSize7(String size7) {
        this.size7 = size7;
    }

    public String getSize8() {
        return size8;
    }

    public void setSize8(String size8) {
        this.size8 = size8;
    }

    public String getSize9() {
        return size9;
    }

    public void setSize9(String size9) {
        this.size9 = size9;
    }

    public String getSize10() {
        return size10;
    }

    public void setSize10(String size10) {
        this.size10 = size10;
    }

    public String getSize11() {
        return size11;
    }

    public void setSize11(String size11) {
        this.size11 = size11;
    }

    public String getSize12() {
        return size12;
    }

    public void setSize12(String size12) {
        this.size12 = size12;
    }

    public String getSize13() {
        return size13;
    }

    public void setSize13(String size13) {
        this.size13 = size13;
    }

    public String getSize14() {
        return size14;
    }

    public void setSize14(String size14) {
        this.size14 = size14;
    }

    public String getSize15() {
        return size15;
    }

    public void setSize15(String size15) {
        this.size15 = size15;
    }

    public String getSize16() {
        return size16;
    }

    public void setSize16(String size16) {
        this.size16 = size16;
    }

    public String getSize17() {
        return size17;
    }

    public void setSize17(String size17) {
        this.size17 = size17;
    }

    public String getSize18() {
        return size18;
    }

    public void setSize18(String size18) {
        this.size18 = size18;
    }

    public String getSize19() {
        return size19;
    }

    public void setSize19(String size19) {
        this.size19 = size19;
    }

    public String getSize20() {
        return size20;
    }

    public void setSize20(String size20) {
        this.size20 = size20;
    }

    @Override
    public String toString() {
        return "Size{" +
                "id=" + id +
                ", size1='" + size1 + '\'' +
                ", size2='" + size2 + '\'' +
                ", size3='" + size3 + '\'' +
                ", size4='" + size4 + '\'' +
                ", size5='" + size5 + '\'' +
                ", size6='" + size6 + '\'' +
                ", size7='" + size7 + '\'' +
                ", size8='" + size8 + '\'' +
                ", size9='" + size9 + '\'' +
                ", size10='" + size10 + '\'' +
                ", size11='" + size11 + '\'' +
                ", size12='" + size12 + '\'' +
                ", size13='" + size13 + '\'' +
                ", size14='" + size14 + '\'' +
                ", size15='" + size15 + '\'' +
                ", size16='" + size16 + '\'' +
                ", size17='" + size17 + '\'' +
                ", size18='" + size18 + '\'' +
                ", size19='" + size19 + '\'' +
                ", size20='" + size20 + '\'' +
                '}';
    }
}
