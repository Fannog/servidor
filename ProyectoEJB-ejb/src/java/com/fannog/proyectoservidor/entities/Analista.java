package com.fannog.proyectoservidor.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data()
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "Analista")
@Table(name = "ANALISTAS")
@NamedNativeQuery(name = "Analista.findById", query = "SELECT * FROM ANALISTAS WHERE ID_ANALISTA = ?1")
@NamedQueries({
    @NamedQuery(name = "Analista.findAll", query = "SELECT a FROM Analista a")})
public class Analista extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "analista")
    private List<AccionJustificacion> accionesJustificacion;

    @OneToMany(mappedBy = "analista")
    private List<AccionSolicitud> accionesSolicitud;

    @OneToMany(mappedBy = "analista")
    private List<Constancia> constancias;

    public Analista(String apellidos, Long documento, String emailInstitucional, String emailPersonal, String nombres, Long telefono, String password, EstadoUsuario estado, Localidad localidad) {
        super(apellidos, documento, emailInstitucional, emailPersonal, nombres, telefono, password, estado, localidad);
    }

    public AccionJustificacion addAccionJustificacion(AccionJustificacion accionJustificacion) {
        getAccionesJustificacion().add(accionJustificacion);
        accionJustificacion.setAnalista(this);

        return accionJustificacion;
    }

    public AccionJustificacion removeAccionJustificacion(AccionJustificacion accionJustificacion) {
        getAccionesJustificacion().remove(accionJustificacion);
        accionJustificacion.setAnalista(null);

        return accionJustificacion;
    }

    public AccionSolicitud addAccion(AccionSolicitud accion) {
        getAccionesSolicitud().add(accion);
        accion.setAnalista(this);

        return accion;
    }

    public AccionSolicitud removeAccion(AccionSolicitud accion) {
        getAccionesSolicitud().remove(accion);
        accion.setAnalista(null);

        return accion;
    }

    public Constancia addConstancia(Constancia constancia) {
        getConstancias().add(constancia);
        constancia.setAnalista(this);

        return constancia;
    }

    public Constancia removeConstancia(Constancia constancia) {
        getConstancias().remove(constancia);
        constancia.setAnalista(null);

        return constancia;
    }

}
