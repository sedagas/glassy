package com.sgass.glassy.Model;

public enum GlassIndex {
    FIRST("1.56"),
    SECOND("1.61"),
    THIRD("1.67"),
    FOURTH("1.74");

    private final String displayValue;

    private GlassIndex(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }


}
