package com.example.userCrud.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class JabatanReq {

    //Struktural Fields
    private String kode_jabatan;

    private String nama_struktural;

    //Fungsional Fields
    private String nama_fungsional;

}
