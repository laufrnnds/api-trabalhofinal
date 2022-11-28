package com.growdev.trabalhofinal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String email;
    private String senha;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    Instant data_criacao;

    @OneToMany(mappedBy = "usuario")
    private List<Recado> recados;

    // Essa função vai ser executada assim que eu inserir uma informação devido a anotação prepersist;
    @PrePersist
    public void prepersist(){
        data_criacao = Instant.now();
    }
}
