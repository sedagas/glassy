package com.sgass.glassy.Model;


import javax.persistence.*;

@Entity
@Table(name="product", schema = "kosovasaat")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PRODUCT_ID")
    private Long productId;
    private String productName;
    private ProductType productType;
    private String fileName;
    private String fileType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length=10000000)
    private byte[] picBytes;

    public Product() {
    }

    public Product(Long productId, String productName, ProductType productType, String fileName, String fileType, byte[] picBytes) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.fileName = fileName;
        this.fileType = fileType;
        this.picBytes = picBytes;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getPicBytes() {
        return picBytes;
    }

    public void setPicBytes(byte[] picBytes) {
        this.picBytes = picBytes;
    }

    public byte[] getProductPicPath() {
        return picBytes;
    }

    public void setProductPicPath(byte[] picBytes) {
        this.picBytes = picBytes;
    }

}
