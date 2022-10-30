package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.AccionSolicitud;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface AccionSolicitudDAO extends DAO<AccionSolicitud> {

    List<AccionSolicitud> findAllBySolicitud(Long idSolicitud);
}
