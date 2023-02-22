package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.MENU)
@Setter
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MenuId", nullable = false)

    private Integer menuId;
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<MenuItem> menuItems;


    /**
     * To add menu to its child objects menu items
     */
    public void addMenuItems(MenuItem child) {
        child.setMenu(this);

        if (ObjectUtils.isEmpty(this.menuItems)) {
            this.menuItems = new ArrayList<>();
        }

        this.menuItems.add(child);
    }
}
