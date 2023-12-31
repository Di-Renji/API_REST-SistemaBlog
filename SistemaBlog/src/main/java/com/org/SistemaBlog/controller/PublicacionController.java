package com.org.SistemaBlog.controller;

import com.org.SistemaBlog.Service.PublicacionService;
import com.org.SistemaBlog.dto.PublicacionDTO;
import com.org.SistemaBlog.dto.PublicacionRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicacion")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @PostMapping("/registrar")
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(publicacionService.registrar(publicacionDTO), HttpStatus.CREATED);
    }

    @GetMapping("/page")
    public PublicacionRespuesta listarPublicacionesConPaginacion(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int numeroPagina,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int medidaPagina){
        return publicacionService.listarConPaginacion(numeroPagina, medidaPagina);
    }

    @GetMapping("/listar")
    public List<PublicacionDTO> listarPublicaciones(){
        return publicacionService.listar();
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(publicacionService.obtenerPorID(id));
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") long id){
        PublicacionDTO publicacionRespuesta = publicacionService.actualizar(publicacionDTO, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id){
        publicacionService.eliminar(id);
        return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
    }

}
