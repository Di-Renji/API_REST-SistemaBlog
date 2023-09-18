package com.org.SistemaBlog.controller;

import com.org.SistemaBlog.Service.PublicacionService;
import com.org.SistemaBlog.dto.PublicacionDTO;
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

    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PublicacionDTO> listarPublicaciones(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int numeroPagina,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int medidaPagina){
        return publicacionService.obtenerTodasLasPublicaciones(numeroPagina, medidaPagina);
    }

    // Para Obtener todas las publicaciones sin Paginacion
    /*
    * @GetMapping
    public List<PublicacionDTO> listarPublicaciones(){
        return publicacionService.obtenerTodasLasPublicaciones();
    }
    * */

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@RequestBody PublicacionDTO publicacionDTO, @PathVariable(name = "id") long id){
        PublicacionDTO publicacionRespuesta = publicacionService.actualizarPublicacion(publicacionDTO, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id){
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
    }

}
