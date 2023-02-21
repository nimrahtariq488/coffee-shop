package com.assessment.coffeeshop.data.repository;

import com.assessment.coffeeshop.data.entity.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @EntityGraph(attributePaths = "menuItems", type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> findAll();

}
