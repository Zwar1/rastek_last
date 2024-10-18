package com.example.userCrud.Repository;


import com.example.userCrud.Entity.JabatanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JabatanRepository extends JpaRepository<JabatanEntity, Long> {
    Optional<JabatanEntity> findFirstById(Long id);
}
