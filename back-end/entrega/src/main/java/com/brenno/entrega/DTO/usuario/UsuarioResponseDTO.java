package com.brenno.entrega.DTO.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Integer idUsuario;
    private String nome;
    private String email;

}
