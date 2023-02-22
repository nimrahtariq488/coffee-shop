package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = DatabaseConstants.CUSTOMER)
@Setter
@Getter
public class Customer {

    @Id
    @Column(name = "CustomerId", nullable = false, length = 10)
    private String customerId;

    @Column(name = "FullName", nullable = false, length = 250)
    private String fullName;

    @Column(name = "Email", length = 250)
    private String email;

    @Column(name = "PhoneNumber", length = 13)
    private String phoneNumber;

}
