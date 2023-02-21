package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = DatabaseConstants.ORDER_ITEM)
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="OrderItemId", nullable = false)
    private Integer orderItemId;

    @Column(name="Quantity", nullable = false)
    private Integer quantity;
    @Column(name="Price", nullable = false)
    private BigDecimal price;

    @JoinColumn(name = "OrderId", nullable = false)
    @ManyToOne
    private SalesOrder order;

    @JoinColumn(name = "MenuItemId", nullable = false)
    @OneToOne
    private MenuItem menuItem;

}
