package com.example.userCrud.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class UpdateEmployeeReq {

    @JsonIgnore
    @NotBlank
    private Long NIK;

    // Personal Information fields
    private String name;
    private String no_ktp;
    private String NPWP;
    private String kartuKeluarga;
    private String jenisKelamin;
    private String tempatLahir;
    private String agama;
    private String alamatLengkap;
    private String alamatDomisili;
    private String noTelp;
    private String kontakDarurat;
    private String noKontakDarurat;
    private String emailPribadi;
    private String pendidikanTerakhir;
    private String jurusan;
    private String namaUniversitas;
    private String namaIbuKandung;
    private String statusPernikahan;
    private String jumlahAnak;
    private String nomorRekening;
    private String bank;
    private LocalDate joinDate;


    // Basic Information fields
    private String statusKontrak;
    private String tmt_awal;
    private String tmt_akhir;
    private String kontrakKedua;
    private BigDecimal salary;
    private String attachment;

}
