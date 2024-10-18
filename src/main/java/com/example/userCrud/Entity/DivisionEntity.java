package com.example.userCrud.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Division")

public class DivisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_division")
    private Long id;

    private String division_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departement", referencedColumnName = "id_departement", nullable=true)
    private DepartementEntity departementEntity;

    @OneToMany(mappedBy = "divisionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RiwayatJabatanEntity> riwayatJabatanEntities = new ArrayList<>();;

}