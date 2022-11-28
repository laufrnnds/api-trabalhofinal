package com.growdev.trabalhofinal.resources;

import com.growdev.trabalhofinal.dto.RecadoDTO;
import com.growdev.trabalhofinal.dto.UsuarioDTO;
import com.growdev.trabalhofinal.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> readU(){
        List<UsuarioDTO> listaUsuario = service.listarTodosU();
        return ResponseEntity.ok(listaUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findIdU(@PathVariable("id") Long id){
        UsuarioDTO usuario = service.listarIdU(id);
        return ResponseEntity.ok(usuario);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByIdU(@PathVariable("id") Long id){
        service.deletarU(id);
        return ResponseEntity.ok().body("Apagado com sucesso");
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insertU(@RequestBody UsuarioDTO usuario){
        UsuarioDTO dto = service.salvarU(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId_usuario()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateR(@RequestBody UsuarioDTO usuario, @PathVariable Long id){
        service.atualizarU(usuario, id);
        return ResponseEntity.ok().body(usuario);
    }
}
