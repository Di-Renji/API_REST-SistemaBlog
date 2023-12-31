package com.org.SistemaBlog.Service;

import com.org.SistemaBlog.dto.PublicacionDTO;
import com.org.SistemaBlog.dto.PublicacionRespuesta;
import com.org.SistemaBlog.entity.Publicacion;
import com.org.SistemaBlog.exception.ResourceNotFoundException;
import com.org.SistemaBlog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDTO registrar(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = mapearEntidad(publicacionDTO);
        Publicacion publicacionRegistrada = publicacionRepository.save(publicacion);
        PublicacionDTO publicacionRespuesta = mapearDTO(publicacionRegistrada);
        return publicacionRespuesta;
    }

    @Override
    public List<PublicacionDTO> listar() {
        List<Publicacion> publicaciones= publicacionRepository.findAll();
        return publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
    }

    @Override
    public PublicacionRespuesta listarConPaginacion(int numeroPagina, int medidaPagina) {
        Pageable pageable = PageRequest.of(numeroPagina, medidaPagina);
        Page<Publicacion> publicacions = publicacionRepository.findAll(pageable);
        List<Publicacion> listaDePublicaciones= publicacions.getContent();
        List<PublicacionDTO> contenido = listaDePublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());

        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setNumeroPagina(publicacions.getNumber());
        publicacionRespuesta.setMedidaPagina(publicacions.getSize());
        publicacionRespuesta.setTotalElementos(publicacions.getTotalElements());
        publicacionRespuesta.setTotalPaginas(publicacions.getTotalPages());
        publicacionRespuesta.setUltima(publicacions.isLast());

        return publicacionRespuesta;

    }

    @Override
    public PublicacionDTO obtenerPorID(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResolutionException("Recurso No Encontrado"));
        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizar(PublicacionDTO publicacionDTO, long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Publicacion","id", id));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return mapearDTO(publicacionActualizada);
    }

    @Override
    public void eliminar(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Publicacion","id", id));
        publicacionRepository.delete(publicacion);
    }



    // ***************** UTILIDADES START ***********************
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
    // ***************** UTILIDADES END ***********************

}
