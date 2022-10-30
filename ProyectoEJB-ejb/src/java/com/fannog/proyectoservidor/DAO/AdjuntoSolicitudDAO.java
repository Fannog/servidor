package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.AdjuntoSolicitud;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AdjuntoSolicitudDAO extends DAO<AdjuntoSolicitud> {

    List<AdjuntoSolicitud> findAllBySolicitud(Long idSolicitud);
}
