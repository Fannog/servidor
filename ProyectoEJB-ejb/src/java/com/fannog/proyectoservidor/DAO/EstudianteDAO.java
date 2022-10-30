package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.Estudiante;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface EstudianteDAO extends DAO<Estudiante> {

    Estudiante findByNombreUsuario(String nombreUsuario) throws ServicioException;

    public List<Estudiante> findAllWithAll();
}
