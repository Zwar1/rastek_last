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

public class UpdateDepartementReq {

    @JsonIgnore
    @NotBlank
    private Long id;

    private String departement_name;

    private String departement_head;

}
