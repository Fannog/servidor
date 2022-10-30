package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.Analista;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface AnalistaDAO extends DAO<Analista> {

    public List<Analista> findAllWithAll();
}
