package com.org.SistemaBlog.Service;

import com.org.SistemaBlog.dto.PublicacionDTO;
import com.org.SistemaBlog.dto.PublicacionRespuesta;

import java.util.List;

public interface PublicacionService {

    PublicacionDTO registrar(PublicacionDTO publicacionDTO);
    List<PublicacionDTO> listar();
    PublicacionRespuesta listarConPaginacion(int numeroPagina, int medidaPagina);
    PublicacionDTO obtenerPorID(long id);
    PublicacionDTO actualizar(PublicacionDTO publicacionDTO, long id);
    void eliminar(long id);

}

