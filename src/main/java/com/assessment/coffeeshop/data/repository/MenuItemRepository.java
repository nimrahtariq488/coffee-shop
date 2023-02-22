package com.assessment.coffeeshop.data.repository;

import com.assessment.coffeeshop.data.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByMenuItemIdIn(List<Integer> ids);

}
