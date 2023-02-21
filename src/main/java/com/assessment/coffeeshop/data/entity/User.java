package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DatabaseConstants.USER)
@Setter
@Getter
public class User {

    @Id
    @Column(name="UserId", nullable = false, length = 10)
    private String userId;
    @Column(name="FullName", nullable = false, length = 250)
    private String fullName;

    @Column(name="Email", nullable = false, length = 250)
    private String email;

}
