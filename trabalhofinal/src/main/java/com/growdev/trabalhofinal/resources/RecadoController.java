package com.growdev.trabalhofinal.resources;

import com.growdev.trabalhofinal.dto.RecadoDTO;
import com.growdev.trabalhofinal.dto.RecadoReadDTO;
import com.growdev.trabalhofinal.services.RecadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recado")
@Api(value = "API REST")
@CrossOrigin("*")
public class RecadoController {

    @Autowired
    private RecadoService service;

    @GetMapping("/all")
    @ApiOperation("retorna todos os papeis")
    public ResponseEntity<List<RecadoReadDTO>> readR(){
        List<RecadoReadDTO> listaRecados = service.listarTodosR();
        return ResponseEntity.ok(listaRecados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecadoDTO> findIdR(@PathVariable("id") Long id){
        RecadoDTO recado = service.listarIdR(id);
        return ResponseEntity.ok(recado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<RecadoReadDTO>> readUserR(@PathVariable("id") Long id){
        List<RecadoReadDTO> listaRecados = service.listarTodosR();
        return ResponseEntity.ok(listaRecados);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<RecadoReadDTO>> filter(@RequestParam(value = "busca")  String busca, @RequestParam(value = "operacao") String operacao ){
        List<RecadoReadDTO> listaRecadosBusca = service.BuscarRecado(busca, operacao);
        return ResponseEntity.ok(listaRecadosBusca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByIdR(@PathVariable("id") Long id){
        service.deletarR(id);
        return ResponseEntity.ok().body("Apagado com sucesso");
    }

    @PostMapping
    public ResponseEntity<RecadoDTO> insertR(@RequestBody RecadoDTO recado){
        RecadoDTO dto = service.salvarR(recado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId_recado()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecadoDTO> updateR(@RequestBody RecadoDTO recado, @PathVariable Long id){
        RecadoDTO dto = service.atualizarR(recado, id);
        return ResponseEntity.ok().body(dto);
    }

}