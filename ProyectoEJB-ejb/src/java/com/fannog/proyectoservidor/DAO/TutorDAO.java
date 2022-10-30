package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.Tutor;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface TutorDAO extends DAO<Tutor> {

    public Tutor findByNombreUsuario(String nombreUsuario) throws ServicioException;

    public List<Tutor> findAllWithAll();

}
