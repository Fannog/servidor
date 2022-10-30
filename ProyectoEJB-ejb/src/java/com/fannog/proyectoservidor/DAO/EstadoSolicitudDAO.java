package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.EstadoSolicitud;
import javax.ejb.Remote;

@Remote
public interface EstadoSolicitudDAO extends DAO<EstadoSolicitud> {

    EstadoSolicitud findByNombre(String nombre);
}
