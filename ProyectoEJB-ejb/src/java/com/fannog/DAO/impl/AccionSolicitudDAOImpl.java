package com.fannog.DAO.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.DAO.AccionSolicitudDAO;
import com.fannog.entities.AccionSolicitud;
import com.fannog.exceptions.ServicioException;
import java.util.List;

@Stateless
public class AccionSolicitudDAOImpl implements AccionSolicitudDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @Override
    public AccionSolicitud create(AccionSolicitud accionSolicitud) throws ServicioException {
    }

    @Override
    public AccionSolicitud edit(AccionSolicitud accionSolicitud) throws ServicioException {
    }

    @Override
    public void remove(AccionSolicitud accionSolicitud) throws ServicioException {
    }

    @Override
    public AccionSolicitud findById(Long id) {
    }

    @Override
    public List<AccionSolicitud> findAll() {
    }


}
