package com.fannog.servidor.DAO;

import com.fannog.servidor.entities.AdjuntoSolicitud;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface AdjuntoSolicitudDAO extends DAO<AdjuntoSolicitud> {

    List<AdjuntoSolicitud> findAllBySolicitud(Long idSolicitud);
}
