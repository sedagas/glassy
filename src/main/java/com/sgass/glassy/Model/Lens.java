package com.sgass.glassy.Model;

public enum Lens {
    MONO("Monofokal"),
    BIO("Biofokal"),
    PROGRESSIVE("Progressive");

    private final String displayValue;

    private Lens(String displayValue){
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}
