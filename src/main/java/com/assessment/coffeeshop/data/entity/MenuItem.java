package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = DatabaseConstants.MENU_ITEM)
@Setter
@Getter
public class MenuItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="MenuItemId", nullable = false)
    private Integer menuItemId;
    @Column(name="Name", nullable = false, length = 50)
    private String name;
    @Column(name="Quantity", nullable = false)
    private Integer quantity;
    @Column(name="Price", nullable = false)
    private BigDecimal price;

    @JoinColumn(name="CategoryId")
    @OneToOne(fetch = FetchType.LAZY)
    private MenuItemCategory category;

    @JoinColumn(name = "MenuId", nullable = false)
    @ManyToOne
    private Menu menu;

}
