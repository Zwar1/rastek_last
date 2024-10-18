package com.example.userCrud.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subdivision")

public class SubDivisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sub")
    private Long id;

    private String subDivision_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_division", referencedColumnName = "id_division")
    private DivisionEntity divisionEntity;

    @OneToMany(mappedBy = "subDivisionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RiwayatJabatanEntity> riwayatJabatanEntities = new HashSet<>();

}
