package com.growdev.trabalhofinal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growdev.trabalhofinal.entities.Recado;
import com.growdev.trabalhofinal.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecadoDTO implements Serializable {

    private Long id_recado;
    private String descricao;
    private String detalhamento;
    private Instant data_criacao;
    private Instant data_atualizacao;
    private String status;

    private UsuarioEmailDTO user;

    public RecadoDTO(Recado entity){
        this.id_recado = entity.getId_recado();
        this.descricao = entity.getDescricao();
        this.detalhamento = entity.getDetalhamento();
        this.status = entity.getStatus();
        this.data_criacao = entity.getData_criacao();
        this.data_atualizacao = entity.getData_atualizacao();
    }

    public RecadoDTO (Recado entity, UsuarioEmailDTO usuarioEmailDTO){
        this(entity);
        this.user = usuarioEmailDTO;
    }

}
