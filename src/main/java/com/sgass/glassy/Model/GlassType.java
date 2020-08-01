package com.sgass.glassy.Model;

public enum GlassType {
    CR39("Cr - 39"),
    HMC("Hmc"),
    PHOTOMETRIC("Photometric"),
    BLUE("Blue Cut");

    private final String displayValue;

    private GlassType(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}
