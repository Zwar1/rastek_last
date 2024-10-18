package com.example.userCrud.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class UpdateJabatanReq {

    @JsonIgnore
    @NotBlank
    private Long id;

    //Struktural Fields
    private String kode_jabatan;

    private String nama_struktural;

    //Fungsional Fields

    private String nama_fungsional;

}