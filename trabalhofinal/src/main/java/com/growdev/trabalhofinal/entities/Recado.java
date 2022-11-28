package com.growdev.trabalhofinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "recado")
public class Recado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_recado;
    private String descricao;
    private String detalhamento;
    private String status;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant data_criacao;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant data_atualizacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk")
    private Usuario usuario;

    @PrePersist
    public void prepersist(){
        data_criacao = Instant.now();
    }

    @PreUpdate
    public void preupdate(){
        data_atualizacao = Instant.now();
    }



}
