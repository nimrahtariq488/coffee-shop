package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.SALES_ORDER)
@Setter
@Getter
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId", nullable = false)
    private Integer orderId;

    @Column(name = "OrderDate", nullable = false)
    private LocalDate date;

    @JoinColumn(name = "CustomerId", nullable = false)
    @ManyToOne()
    private Customer customer;

    @Column(name = "TotalAmount", nullable = false)
    private BigDecimal totalAmount;

    @JoinColumn(name = "OrderStatusId")
    @OneToOne(fetch = FetchType.LAZY)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderItem> orderItems;


    /**
     * To add order to its child objects order items
     */
    public void addOrderItems(OrderItem child) {
        child.setOrder(this);

        if (ObjectUtils.isEmpty(this.orderItems)) {
            this.orderItems = new ArrayList<>();
        }

        this.orderItems.add(child);
    }

}
