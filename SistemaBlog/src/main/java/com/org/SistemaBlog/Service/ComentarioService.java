package com.org.SistemaBlog.Service;

import com.org.SistemaBlog.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioService {

    ComentarioDTO registrar(Long publicacionId, ComentarioDTO comentarioDTO);
    List<ComentarioDTO> obtenerComentarioPorPublicacion(Long publicacionId);
    ComentarioDTO buscar(Long publicacionId, Long comentarioId);
    ComentarioDTO actualizar(Long publicacionId, Long comentarioId, ComentarioDTO comentarioDTO);
    void eliminar(Long publicacionId,Long comentarioId);

}
