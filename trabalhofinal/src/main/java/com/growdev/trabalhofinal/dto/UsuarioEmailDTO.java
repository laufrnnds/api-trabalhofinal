package com.growdev.trabalhofinal.dto;

import com.growdev.trabalhofinal.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEmailDTO implements Serializable {

    private Long id_usuario;
    private String email;

    public UsuarioEmailDTO (Usuario entity){
        this.id_usuario = entity.getId_usuario();
        this.email = entity.getEmail();
    }
}
