package com.growdev.trabalhofinal.dto;

import com.growdev.trabalhofinal.entities.Recado;
import com.growdev.trabalhofinal.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RecadoReadDTO extends RecadoDTO{
    private Usuario usuario;

    public RecadoReadDTO(Recado recado, Usuario usuario){
        super(recado, new UsuarioEmailDTO(usuario));
    }

}
