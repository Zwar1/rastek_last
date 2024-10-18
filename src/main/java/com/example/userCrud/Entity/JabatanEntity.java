package com.example.userCrud.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kode_jabatan")
public class JabatanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jabatan")
    private Long id;

    private String kode_jabatan;

    private String nama_struktural;

    private String nama_fungsional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_riwayat", referencedColumnName = "id_riwayat")
    private RiwayatJabatanEntity riwayatJabatan;

}
