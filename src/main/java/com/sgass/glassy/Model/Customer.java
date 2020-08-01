package com.sgass.glassy.Model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="customer", schema = "kosovasaat")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CUSTOMER_ID")
    private Long id;

    @NotBlank(message = "Please enter customer's name.")
    private String customerName;

    @NotBlank(message = "Please enter customer's surname")
    private String customerSurname;

    @NotBlank(message = "Please enter the Phone Number")
    private String phoneNumber;

    public Customer() {
    }

    public Customer( String name, String surname, String phoneNumber) {
        this.customerName = name;
        this.customerSurname = surname;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
