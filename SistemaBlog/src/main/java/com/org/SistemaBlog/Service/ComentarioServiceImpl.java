package com.org.SistemaBlog.Service;

import com.org.SistemaBlog.Utils.Utils;
import com.org.SistemaBlog.dto.ComentarioDTO;
import com.org.SistemaBlog.entity.Comentario;
import com.org.SistemaBlog.entity.Publicacion;
import com.org.SistemaBlog.exception.BlogAppException;
import com.org.SistemaBlog.repository.ComentarioRepository;
import com.org.SistemaBlog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentarioDTO registrar(Long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = Utils.mapearComentario(comentarioDTO);
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()->new ResolutionException("Recurso No Encontrado"));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);
        return Utils.mapearComentarioDTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDTO> obtenerComentarioPorPublicacion(Long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> Utils.mapearComentarioDTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO buscar(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()->new ResolutionException("Recurso no encontrado"));
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(()->new ResolutionException("Recurso no encontrado"));
        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
        }
        return Utils.mapearComentarioDTO(comentario);
    }

    @Override
    public ComentarioDTO actualizar(Long publicacionId, Long comentarioId, ComentarioDTO comentarioDTO) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()->new ResolutionException("Recurso no encontrado"));
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(()->new ResolutionException("Recurso no encontrado"));
        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
        }
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        Comentario comentarioActualizado = comentarioRepository.save(comentario);
        return Utils.mapearComentarioDTO(comentarioActualizado);
    }

    @Override
    public void eliminar(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()->new ResolutionException("Recurso no encontrado"));
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(()->new ResolutionException("Recurso no encontrado"));
        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
        }
        comentarioRepository.delete(comentario);
    }
}
