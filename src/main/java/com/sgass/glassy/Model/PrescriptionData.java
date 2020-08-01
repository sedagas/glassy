package com.sgass.glassy.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="PrescriptionData", schema = "kosovasaat")
public class PrescriptionData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    //OD-Right eye
    private String odSphere;
    private String odCylinder;
    private String odAxis;
    private String odAdd;

    //OS-Left eye
    private String osSphere;
    private String osCylinder;
    private String osAxis;
    private String osAdd;

    private String Pd;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id")
    @JsonIgnore
    private Prescription prescription;




    public PrescriptionData() {
    }

    public PrescriptionData(String odSphere, String odCylinder, String odAxis, String odAdd, String osSphere, String osCylinder, String osAxis, String osAdd, String pd) {
        this.odSphere = odSphere;
        this.odCylinder = odCylinder;
        this.odAxis = odAxis;
        this.odAdd = odAdd;
        this.osSphere = osSphere;
        this.osCylinder = osCylinder;
        this.osAxis = osAxis;
        this.osAdd = osAdd;
        Pd = pd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOdSphere() {
        return odSphere;
    }

    public void setOdSphere(String odSphere) {
        this.odSphere = odSphere;
    }

    public String getOdCylinder() {
        return odCylinder;
    }

    public void setOdCylinder(String odCylinder) {
        this.odCylinder = odCylinder;
    }

    public String getOdAxis() {
        return odAxis;
    }

    public void setOdAxis(String odAxis) {
        this.odAxis = odAxis;
    }

    public String getOdAdd() {
        return odAdd;
    }

    public void setOdAdd(String odAdd) {
        this.odAdd = odAdd;
    }

    public String getOsSphere() {
        return osSphere;
    }

    public void setOsSphere(String osSphere) {
        this.osSphere = osSphere;
    }

    public String getOsCylinder() {
        return osCylinder;
    }

    public void setOsCylinder(String osCylinder) {
        this.osCylinder = osCylinder;
    }

    public String getOsAxis() {
        return osAxis;
    }

    public void setOsAxis(String osAxis) {
        this.osAxis = osAxis;
    }

    public String getOsAdd() {
        return osAdd;
    }

    public void setOsAdd(String osAdd) {
        this.osAdd = osAdd;
    }

    public String getPd() {
        return Pd;
    }

    public void setPd(String pd) {
        Pd = pd;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
