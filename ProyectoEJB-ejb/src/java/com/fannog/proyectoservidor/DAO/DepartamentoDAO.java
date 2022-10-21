package com.fannog.proyectoservidor.DAO;

import com.fannog.proyectoservidor.entities.Departamento;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface DepartamentoDAO extends DAO<Departamento> {
    List<Departamento> findAllWithLocalidades();
}
