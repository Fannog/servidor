package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.Localidad;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface LocalidadDAO extends DAO<Localidad> {

    public List<Localidad> findByNombre(String nombre) throws ServicioException;
}
