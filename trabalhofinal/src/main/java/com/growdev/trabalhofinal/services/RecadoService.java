package com.growdev.trabalhofinal.services;

import com.growdev.trabalhofinal.dto.RecadoDTO;
import com.growdev.trabalhofinal.dto.RecadoReadDTO;
import com.growdev.trabalhofinal.dto.UsuarioEmailDTO;
import com.growdev.trabalhofinal.entities.Recado;
import com.growdev.trabalhofinal.entities.Usuario;
import com.growdev.trabalhofinal.repositories.RecadoRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecadoService {

    @Autowired
    private RecadoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<RecadoReadDTO> listarTodosR(){
        try{
            List<Recado> lista = repository.findAll();
            UsuarioEmailDTO dto = new UsuarioEmailDTO();
            return lista.stream().map(recado -> new RecadoReadDTO(recado, recado.getUsuario())).collect(Collectors.toList());
        }catch (InternalServerErrorException e){
            throw new InternalServerErrorException("Erro no servidor");
        }
    }

    @Transactional(readOnly = true)
    public RecadoDTO listarIdR(Long id){
        try{
            Recado entity = repository.findById(id).get();

            return new RecadoDTO(entity, new UsuarioEmailDTO(entity.getUsuario()));
        }catch(EntityNotFoundIdException e){
            throw new EntityNotFoundIdException("Recado não encontrado");
        }catch(BadRequestException e){
            throw new BadRequestException("Requisição inválida");
        }
    }

    public List<RecadoReadDTO> BuscarRecado(String busca, String operacao){
        List<Recado> lista = repository.findAll();
        List<RecadoReadDTO> listaDTO =  lista.stream().map(rcd -> new RecadoReadDTO(rcd, rcd.getUsuario())).collect(Collectors.toList());
        List<RecadoReadDTO> recadosAchadosDTO = new ArrayList<>();
        if(operacao.equals("descricao")){
            for (RecadoReadDTO recado: listaDTO) {

                if(recado.getDescricao().contains(busca)){
                    recadosAchadosDTO.add(recado);
                }

            }
        }else if(operacao.equals("detalhamento")){
            for (RecadoReadDTO recado: listaDTO) {
                if(recado.getDetalhamento().contains(busca)){
                    recadosAchadosDTO.add(recado);
                }
            }

        }else if(operacao.equals("status")){
            for (RecadoReadDTO recado: listaDTO) {
                String statusRecado = recado.getStatus().toLowerCase();

                if( statusRecado.contains(busca) && busca.equals("concluido")){
                    recadosAchadosDTO.add(recado);
                } else if(statusRecado.contains(busca) && busca.equals("pendente")){
                    recadosAchadosDTO.add(recado);
                }
                else if(statusRecado.contains(busca) && busca.equals("cancelado")){
                    recadosAchadosDTO.add(recado);
                }
            }
        }
//        else if(operacao.equals("usuario")){
//            Long num = Long.parseLong(busca);
//            Recado rcd = repository.findById(num).get();
//            for (RecadoDTO recado: listaDTO) {
//
//                if( recado.getUser().getId_usuario() == rcd.getUsuario().getId_usuario()){
//                    recadosAchadosDTO.add(recado);
//                }
//                else {
//                    recadosAchadosDTO.add(recado);
//                }
//
//            }
//        }
        return recadosAchadosDTO;
    }

    public RecadoDTO salvarR(RecadoDTO dto){
        try{
            Recado entity = new Recado();
            entity.setDescricao((dto.getDescricao()));
            entity.setDetalhamento((dto.getDetalhamento()));
            entity.setStatus(dto.getStatus());

            // busca o usuario apartir do id_usuario do dto;
            Usuario user = usuarioRepository.findById(dto.getUser().getId_usuario()).get();
            // adicionando o usuario achado no usuario do recado
            entity.setUsuario(user);

            entity = repository.save(entity);

            return new RecadoDTO(entity, new UsuarioEmailDTO(entity.getUsuario()));
        }catch(BadRequestException e){
            throw new BadRequestException("Requisição inválida");
        }

    }

    public RecadoDTO atualizarR( RecadoDTO dto, Long id){
        try{
            Recado entity = repository.findById(id).get();
            entity.setDescricao((dto.getDescricao()));
            entity.setDetalhamento((dto.getDetalhamento()));
            entity.setStatus(dto.getStatus());
            entity.preupdate();
            // busca o usuario apartir do id_usuario do dto;
            Usuario user = usuarioRepository.findById(dto.getUser().getId_usuario()).get();
            // adicionando o usuario achado no usuario do recado
            entity.setUsuario(user);

            entity = repository.save(entity);

            return new RecadoDTO(entity, new UsuarioEmailDTO(entity.getUsuario()));
        }
        catch(EntityNotFoundIdException e){
            throw new EntityNotFoundIdException("Recado não encontrado");
        }catch(BadRequestException e){
            throw new BadRequestException("Requisição inválida");
        }
    }

    public void deletarR( Long id){
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new EntityNotFoundIdException("Recado não encontrado");
        }catch(DataIntegrityViolationException e) {
            throw new DataBaseException("Violação de integridade");
        }
    }
}
