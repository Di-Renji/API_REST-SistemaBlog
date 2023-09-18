package com.org.SistemaBlog.Service;

import com.org.SistemaBlog.dto.PublicacionDTO;
import com.org.SistemaBlog.entity.Publicacion;
import com.org.SistemaBlog.exception.ResourceNotFoundException;
import com.org.SistemaBlog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = mapearEntidad(publicacionDTO);
        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
        PublicacionDTO publicacionRespuesta = mapearDTO(nuevaPublicacion);
        return publicacionRespuesta;

    }

    @Override
    public List<PublicacionDTO> obtenerTodasLasPublicaciones() {
        List<Publicacion> publicaciones= publicacionRepository.findAll();
        return publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO obtenerPublicacionPorID(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Publicacion","id", id));
        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Publicacion","id", id));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return mapearDTO(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Publicacion","id", id));
        publicacionRepository.delete(publicacion);
    }

    // Convierte Entidad a DTO
    private PublicacionDTO mapearDTO(Publicacion publicacion){
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setContenido(publicacion.getContenido());
        return publicacionDTO;
    }

    // Convierte DTO a Entidad
    private Publicacion mapearEntidad(PublicacionDTO publicacionDTO){
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        return publicacion;
    }

}
