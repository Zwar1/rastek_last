package com.example.userCrud.Dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubDivisionReq {

    private String subDivision_name;

    private Long division_id;
}
