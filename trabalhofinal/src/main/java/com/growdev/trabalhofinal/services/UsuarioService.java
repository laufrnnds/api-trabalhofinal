package com.growdev.trabalhofinal.services;

import com.growdev.trabalhofinal.dto.UsuarioDTO;
import com.growdev.trabalhofinal.entities.Usuario;
import com.growdev.trabalhofinal.repositories.UsuarioRepository;
import com.growdev.trabalhofinal.services.exceptions.BadRequestException;
import com.growdev.trabalhofinal.services.exceptions.DataBaseException;
import com.growdev.trabalhofinal.services.exceptions.EntityNotFoundIdException;
import com.growdev.trabalhofinal.services.exceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarTodosU(){
        try{
            List<Usuario> lista = repository.findAll();
            return lista.stream().map(user -> new UsuarioDTO(user)).collect(Collectors.toList());
        }catch (InternalServerErrorException e){
            throw new InternalServerErrorException("Erro no servidor");
        }
    }

    @Transactional(readOnly = true)
    public UsuarioDTO listarIdU(Long id){
        try{
            Usuario entity = repository.findById(id).get();
            return new UsuarioDTO(entity);
        }catch(EntityNotFoundIdException e){
            throw new EntityNotFoundIdException("Usuário não encontrado");
        }catch(BadRequestException e){
            throw new BadRequestException("Requisição inválida");
        }
    }

    public UsuarioDTO salvarU(UsuarioDTO dto){
        try{
            Usuario entity = new Usuario();
            entity.setEmail(dto.getEmail());
            entity.setSenha(dto.getSenha());
            entity = repository.save(entity);
            return new UsuarioDTO(entity);
        }catch(BadRequestException e){
            throw new BadRequestException("Requisição inválida");
        }

    }


    public UsuarioDTO atualizarU( UsuarioDTO dto, Long id){
        try{
            Usuario entity = repository.findById(id).get();
            entity.setEmail(dto.getEmail());
            entity.setSenha(dto.getSenha());
            entity = repository.save(entity);
            return new UsuarioDTO(entity);
        }
        catch(EntityNotFoundIdException e){
            throw new EntityNotFoundIdException("Usuário não encontrado");
        }catch(BadRequestException e){
            throw new BadRequestException("Requisição inválida");
        }
    }

    public void deletarU( Long id){
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundIdException("Usuário não encontrado");
        }catch(DataIntegrityViolationException e) {
            throw new DataBaseException("Violação de integridade");
        }
    }
}
