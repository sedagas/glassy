package com.sgass.glassy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="orders", schema = "kosovasaat")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE
    )
    @Column(name="ORDER_ID")
    private Long id;

    private String name;
    private String date;
    private String price;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;


//
//    @Enumerated(EnumType.STRING)
//    private GlassType glassType;
//
//    @Enumerated(EnumType.STRING)
//    private GlassIndex glassIndex;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.ALL)
//    private List<Oculus> sight;


//    @Enumerated(EnumType.STRING)
//    private Lens lens;


    public Order() {
    }

    public Order(String name, String date, String price) {
//        this.glassType = glassType;
//        this.glassIndex = glassIndex;
        this.date = date;
        this.name = name;
//        this.lens = lens;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
//
//    public GlassType getGlassType() {
//        return glassType;
//    }
//
//    public void setGlassType(GlassType glassType) {
//        this.glassType = glassType;
//    }
//
//    public GlassIndex getGlassIndex() {
//        return glassIndex;
//    }
//
//    public void setGlassIndex(GlassIndex glassIndex) {
//        this.glassIndex = glassIndex;
//    }
//
//    public List<Oculus> getSight() {
//        return sight;
//    }
//
//    public void setSight(List<Oculus> sight) {
//        this.sight = sight;
//    }

//    public Lens getLens() {
//        return lens;
//    }
//
//    public void setLens(Lens lens) {
//        this.lens = lens;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
