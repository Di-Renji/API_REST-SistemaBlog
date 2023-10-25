package com.org.SistemaBlog.controller;

import com.org.SistemaBlog.Service.ComentarioService;
import com.org.SistemaBlog.dto.ComentarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/registrar/{id}")
    public ResponseEntity<ComentarioDTO> registrar(@PathVariable(value = "id") Long id, @RequestBody ComentarioDTO comentarioDTO) {
        return new ResponseEntity<>(comentarioService.registrar(id, comentarioDTO), HttpStatus.CREATED);
    }

    @GetMapping("/listar/{id}")
    public List<ComentarioDTO> listar(@PathVariable(value = "id") Long id) {
        return comentarioService.obtenerComentarioPorPublicacion(id);
    }

    @GetMapping("/buscar/{publicacionId}/{comentarioId}")
    public ResponseEntity<ComentarioDTO> buscar(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "comentarioId") Long comentarioId) {
        ComentarioDTO comentarioDTO = comentarioService.buscar(publicacionId, comentarioId);
        return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{publicacionId}/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizar(@PathVariable("publicacionId") Long publicacionId, @PathVariable("comentarioId") Long comentarioId, @Valid @RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO comentarioActualizado = comentarioService.actualizar(publicacionId, comentarioId, comentarioDTO);
        return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{publicacionId}/{comentarioId}")
    public ResponseEntity<String> eliminar(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "comentarioId") Long comentarioId){
        comentarioService.eliminar(publicacionId, comentarioId);
        return new ResponseEntity<>("Comentario eliminado !!!", HttpStatus.OK);
    }

}
