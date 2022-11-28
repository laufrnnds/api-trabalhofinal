package com.growdev.trabalhofinal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growdev.trabalhofinal.entities.Recado;
import com.growdev.trabalhofinal.entities.Usuario;
import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private Long id_usuario;
    private String email;
    private String senha;
    private Instant data_criacao;


    public UsuarioDTO (Usuario entity){
        this.id_usuario = entity.getId_usuario();
        this.email = entity.getEmail();
        this.senha = entity.getSenha();
        this.data_criacao = entity.getData_criacao();
    }

}
