package id.web.saka.report.sap;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sap_status")
public class SapStatus {

    @Id
    @JsonProperty("docnum")
    @Column(name = "msku")
    private String msku;
    @JsonProperty("Id")
    @Column(name = "spu")
    private String spu;
    @JsonProperty("status")
    @Column(name = "status")
    private String status;
    @JsonProperty("message")
    @Column(name = "message")
    private String message;

    @JsonProperty("series")
    @Column(name = "series")
    private String series;

    public String getMsku() {
        return msku;
    }

    public void setMsku(String msku) {
        this.msku = msku;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
}
