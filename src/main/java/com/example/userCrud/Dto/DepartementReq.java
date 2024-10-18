package com.example.userCrud.Dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class DepartementReq {

    private String departement_name;

    private String departement_head;
}
