package com.fannog.analista;

import com.fannog.accionJustificacion.AccionJustificacion;
import com.fannog.accionSolicitudJustificacion.AccionSolicitudConstancia;
import com.fannog.constancia.Constancia;
import com.fannog.usuario.Usuario;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Analista")
@Table(name = "ANALISTA")
@NamedQueries({
    @NamedQuery(name = "Analista.findAll", query = "SELECT a FROM Analista a"),
    @NamedQuery(name = "Analista.findById", query = "SELECT a FROM Analista a WHERE a.idAnalista = :idAnalista")})
public class Analista implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ANALISTA_IDANALISTA_GENERATOR", sequenceName = "SEQ_ID_ANALISTA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANALISTA_IDANALISTA_GENERATOR")
    @Column(name = "ID_ANALISTA", unique = true, nullable = false, precision = 38)
    private Long idAnalista;

    @OneToMany(mappedBy = "analista")
    private List<AccionJustificacion> accionJustificacions;

    @OneToMany(mappedBy = "analista")
    private List<AccionSolicitudConstancia> accionSolicitudConstancias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @NonNull
    private Usuario usuario;

    @OneToMany(mappedBy = "analista")
    private List<Constancia> constancias;

    public AccionJustificacion addAccionJustificacion(AccionJustificacion accionJustificacion) {
        getAccionJustificacions().add(accionJustificacion);
        accionJustificacion.setAnalista(this);

        return accionJustificacion;
    }

    public AccionJustificacion removeAccionJustificacion(AccionJustificacion accionJustificacion) {
        getAccionJustificacions().remove(accionJustificacion);
        accionJustificacion.setAnalista(null);

        return accionJustificacion;
    }

    public AccionSolicitudConstancia addAccion(AccionSolicitudConstancia accion) {
        getAccionSolicitudConstancias().add(accion);
        accion.setAnalista(this);

        return accion;
    }

    public AccionSolicitudConstancia removeAccion(AccionSolicitudConstancia accion) {
        getAccionSolicitudConstancias().remove(accion);
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
