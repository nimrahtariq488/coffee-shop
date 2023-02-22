package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = DatabaseConstants.MENU_ITEM_CATEGORY)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemCategory {
    @Id
    @Column(name = "CategoryId", length = 50, nullable = false)
    private String categoryId;

    @Column(name = "Description", nullable = false, length = 250)
    private String description;

}
