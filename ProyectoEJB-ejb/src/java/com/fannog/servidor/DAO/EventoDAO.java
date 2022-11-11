package com.fannog.servidor.DAO;

import com.fannog.servidor.entities.Evento;
import javax.ejb.Remote;

@Remote
public interface EventoDAO extends DAO<Evento> {

}
