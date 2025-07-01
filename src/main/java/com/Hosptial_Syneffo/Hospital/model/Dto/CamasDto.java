package com.Hosptial_Syneffo.Hospital.model.Dto;

import lombok.Data;

@Data
public class CamasDto {
    private Long id;
    private String numeroCama;
    private int cantidadCamas;
    private String estadoCama;
    private Long habitacionId;
}
