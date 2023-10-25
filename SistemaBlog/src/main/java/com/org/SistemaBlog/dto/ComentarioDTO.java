package com.org.SistemaBlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {

    private Long id;
    private String nombre;
    private String email;
    private String cuerpo;

}
