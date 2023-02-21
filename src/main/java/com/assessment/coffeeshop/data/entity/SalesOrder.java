package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="OrderId", nullable = false)
    private Integer orderId;
    @Column(name="OrderDate", nullable = false)
    private LocalDate date;
    @JoinColumn(name="UserId", nullable = false)
    @ManyToOne()
    private User user;
    @Column(name="TotalAmount", nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderItem> orderItems;


    /**
     * To add order to its child objects order items
     */
    public void addMenuItems(OrderItem child) {
        child.setOrder(this);

        if (ObjectUtils.isEmpty(this.orderItems)) {
            this.orderItems = new ArrayList<>();
        }

        this.orderItems.add(child);
    }

}
