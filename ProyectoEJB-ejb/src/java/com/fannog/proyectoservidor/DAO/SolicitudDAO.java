package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.Solicitud;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.io.File;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface SolicitudDAO {

    Solicitud create(Solicitud solicitud, List<File> file) throws ServicioException;

    Solicitud edit(Solicitud tipoConstancia) throws ServicioException;

    void remove(Solicitud tipoConstancia) throws ServicioException;

    Solicitud findById(Long id);

    List<Solicitud> findAll();
}
