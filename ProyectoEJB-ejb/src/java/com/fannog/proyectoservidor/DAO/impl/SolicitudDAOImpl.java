package com.fannog.proyectoservidor.DAO.impl;

import com.fannog.proyectoservidor.DAO.EstadoSolicitudDAO;
import com.fannog.proyectoservidor.entities.Solicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fannog.proyectoservidor.DAO.SolicitudDAO;
import com.fannog.proyectoservidor.entities.AdjuntoSolicitud;
import com.fannog.proyectoservidor.entities.EstadoSolicitud;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.persistence.PersistenceException;

import javax.validation.ConstraintViolationException;

@Stateless
public class SolicitudDAOImpl implements SolicitudDAO {

    @PersistenceContext(unitName = "ProyectoEJB-ejbPU")
    private EntityManager em;

    @EJB
    private EstadoSolicitudDAO estadoDAO;

    @Override
    public Solicitud create(Solicitud solicitud, List<File> files) throws ServicioException {
        try {
            em.persist(solicitud);

            files.forEach(file -> {

                byte[] bytesFromFile = null;

                try {
                    bytesFromFile = Files.readAllBytes(file.toPath());
                } catch (IOException ex) {
                }

                AdjuntoSolicitud adjunto = new AdjuntoSolicitud(bytesFromFile, file.getName(), solicitud);

                em.persist(adjunto);
            });

            em.flush();
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
        } catch (Exception e) {
            throw new ServicioException("Ha ocurrido un error al intentar crear la solicitud");
        }

        return solicitud;
    }

    @Override
    public Solicitud edit(Solicitud solicitud) throws ServicioException {
        try {

            boolean emitida = solicitud.getEmision() != null;
            Long idEstado = solicitud.getEstado().getId();
            
            if (emitida && idEstado != 3L) {

                EstadoSolicitud newEstado = estadoDAO.findById(3L);
                
                if (newEstado != null) {
                    solicitud.setEstado(newEstado);
                }

            }

            em.merge(solicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar actualizar la solicitud");
        } catch (ConstraintViolationException e) {
            String errorMessages = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getMessage().concat(",").replace(",", " "))
                    .collect(Collectors.joining("\n"));

            throw new ServicioException(errorMessages);
        }

        return solicitud;
    }

    @Override
    public void remove(Solicitud solicitud) throws ServicioException {
        try {
            em.remove(solicitud);
            em.flush();
        } catch (PersistenceException e) {
            throw new ServicioException("Ha ocurrido un error al intentar dar de baja la solicitud");
        }
    }

    @Override
    public Solicitud findById(Long id) {
        Solicitud solicitud = em.find(Solicitud.class, id);

        return solicitud;
    }

    @Override
    public List<Solicitud> findAll() {
        List<Solicitud> solicitudes = em.createNamedQuery("Solicitud.findAll").getResultList();

        return solicitudes;
    }

    @Override
    public List<Solicitud> findAllWithRelations() {
        List<Solicitud> solicitudes = em.createNamedQuery("Solicitud.findAll").setHint("javax.persistence.loadgraph",
                em.getEntityGraph("findAllWithRelations")).getResultList();

        return solicitudes;
    }
}
