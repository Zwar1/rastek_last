package com.example.userCrud.Repository;

import com.example.userCrud.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findFirstByNIK(Long NIK);

    // Method untuk menghitung jumlah karyawan yang mendaftar pada bulan dan tahun tertentu
    long countByJoinDateBetween(LocalDate startDate, LocalDate endDate);
}