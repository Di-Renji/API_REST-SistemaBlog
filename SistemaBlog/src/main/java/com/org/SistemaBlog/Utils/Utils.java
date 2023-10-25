package com.org.SistemaBlog.Utils;

import com.org.SistemaBlog.dto.ComentarioDTO;
import com.org.SistemaBlog.dto.PublicacionDTO;
import com.org.SistemaBlog.entity.Comentario;
import com.org.SistemaBlog.entity.Publicacion;

public class Utils {

    // Convierte DTO a Entidad ------> para guardar el registro en la BD
    public static Publicacion mapearPublicacion(PublicacionDTO publicacionDTO){
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        return publicacion;
    }
    // Convierte Entidad a DTO ------> Para la respuesta en el Postman
    public static PublicacionDTO mapearPublicacionDTO(Publicacion publicacion){
        PublicacionDTO publicacionDTO = new PublicacionDTO();
        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setContenido(publicacion.getContenido());
        return publicacionDTO;
    }


    /************* COMENTARIO **************/
    public static Comentario mapearComentario(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        return comentario;
    }
    public static ComentarioDTO mapearComentarioDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setNombre(comentario.getNombre());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setCuerpo(comentario.getCuerpo());
        return comentarioDTO;
    }




}
