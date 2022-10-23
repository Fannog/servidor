package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.TipoConstancia;
import com.fannog.proyectoservidor.exceptions.ServicioException;
import java.io.File;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface TipoConstanciaDAO {

    TipoConstancia create(TipoConstancia tipoConstancia, File file) throws ServicioException;

    TipoConstancia edit(TipoConstancia tipoConstancia) throws ServicioException;

    void remove(TipoConstancia tipoConstancia) throws ServicioException;

    TipoConstancia findById(Long id);

    List<TipoConstancia> findAll();
}
