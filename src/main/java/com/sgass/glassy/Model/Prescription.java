package com.sgass.glassy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="prescription", schema = "kosovasaat")
public class Prescription extends Order {

    @Enumerated(EnumType.STRING)
    private GlassIndex glassIndex;
    @Enumerated(EnumType.STRING)
    private GlassType glassType;
    @Enumerated(EnumType.STRING)
    private Lens lens;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prescriptionData_id")
    @JsonIgnore
    private PrescriptionData prescriptionData;

    public Prescription() {
    }

    public Prescription(String name, String date, String price, GlassIndex glassIndex, GlassType glassType, Lens lens) {
        super(name, date, price);
        this.glassIndex = glassIndex;
        this.glassType = glassType;
        this.lens = lens;
    }

    public GlassIndex getGlassIndex() {
        return glassIndex;
    }

    public void setGlassIndex(GlassIndex glassIndex) {
        this.glassIndex = glassIndex;
    }

    public GlassType getGlassType() {
        return glassType;
    }

    public void setGlassType(GlassType glassType) {
        this.glassType = glassType;
    }

    public Lens getLens() {
        return lens;
    }

    public void setLens(Lens lens) {
        this.lens = lens;
    }

    public PrescriptionData getPrescriptionData() {
        return prescriptionData;
    }

    public void setPrescriptionData(PrescriptionData prescriptionData) {
        this.prescriptionData = prescriptionData;
    }
}
