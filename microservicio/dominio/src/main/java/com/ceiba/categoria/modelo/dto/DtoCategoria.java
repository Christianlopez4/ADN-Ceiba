package com.ceiba.categoria.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCategoria {

    private Integer id;
    private String titulo;
    private Double cuotaModeradora;
    private Double multa;
}
