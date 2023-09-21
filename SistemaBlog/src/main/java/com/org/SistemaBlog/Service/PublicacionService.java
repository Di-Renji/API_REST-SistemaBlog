package com.org.SistemaBlog.Service;

import com.org.SistemaBlog.dto.PublicacionDTO;
import com.org.SistemaBlog.dto.PublicacionRespuesta;

import java.util.List;

public interface PublicacionService {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
    public List<PublicacionDTO> obtenerTodasLasPublicaciones();
    public PublicacionRespuesta obtenerPublicacionesConPaginacion(int numeroPagina, int medidaPagina);
    public PublicacionDTO obtenerPublicacionPorID(long id);
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);
    public void eliminarPublicacion(long id);

    // public List<PublicacionDTO> obtenerPublicacionesConPaginacion(int numeroPagina, int medidaPagina);

}

