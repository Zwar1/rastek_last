package com.example.userCrud.Repository;

import com.example.userCrud.Entity.Liquid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidRepository extends JpaRepository<Liquid, Long> {
    boolean existsByName(String name);
}
