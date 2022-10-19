package com.fannog.solicitudConstancia;

import com.fannog.accionSolicitudJustificacion.AccionSolicitudConstancia;
import com.fannog.adjuntosSolicitudConstancia.AdjuntosSolicitudConstancia;
import com.fannog.constancia.Constancia;
import com.fannog.estadosSolicitudConstancia.EstadosSolicitudConstancia;
import com.fannog.estudiante.Estudiante;
import com.fannog.tiposConstancia.TiposConstancia;
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
@Entity(name = "SolicitudConstancia")
@Table(name = "SOLICITUD_CONSTANCIA")
@NamedQueries({
    @NamedQuery(name = "SolicitudConstancia.findAll", query = "SELECT s FROM SolicitudConstancia s"),
    @NamedQuery(name = "SolicitudConstancia.findById", query = "SELECT s FROM SolicitudConstancia s WHERE s.idSolicitudConstancia = :idSolicitudConstancia"),
    @NamedQuery(name = "SolicitudConstancia.findByDetalle", query = "SELECT s FROM SolicitudConstancia s WHERE s.detalle = :detalle"),
    @NamedQuery(name = "SolicitudConstancia.findByEliminado", query = "SELECT s FROM SolicitudConstancia s WHERE s.eliminado = :eliminado")})
public class SolicitudConstancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SOLICITUD_CONSTANCIA_IDSOLICITUDCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_SOLICITUD_CONSTANCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLICITUD_CONSTANCIA_IDSOLICITUDCONSTANCIA_GENERATOR")
    @Column(name = "ID_SOLICITUD_CONSTANCIA", unique = true, nullable = false, precision = 38)
    private Long idSolicitudConstancia;

    @Column(nullable = false, length = 100)
    @NonNull
    private String detalle;

    @OneToMany(mappedBy = "solicitud")
    private List<AccionSolicitudConstancia> acciones;

    @OneToMany(mappedBy = "solicitud")
    @NonNull
    private List<AdjuntosSolicitudConstancia> adjuntos;

    @OneToMany(mappedBy = "solicitud")
    private List<Constancia> constancias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO", nullable = false)
    @NonNull
    private EstadosSolicitudConstancia estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTUDIANTE", nullable = false)
    @NonNull
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CONSTANCIA", nullable = false)
    @NonNull
    private TiposConstancia tipo;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public AccionSolicitudConstancia addAccion(AccionSolicitudConstancia accion) {
        getAcciones().add(accion);
        accion.setSolicitud(this);

        return accion;
    }

    public AccionSolicitudConstancia removeAccion(AccionSolicitudConstancia accion) {
        getAcciones().remove(accion);
        accion.setSolicitud(null);

        return accion;
    }

    public AdjuntosSolicitudConstancia addAdjunto(AdjuntosSolicitudConstancia adjunto) {
        getAdjuntos().add(adjunto);
        adjunto.setSolicitud(this);

        return adjunto;
    }

    public AdjuntosSolicitudConstancia removeAdjunto(AdjuntosSolicitudConstancia adjunto) {
        getAdjuntos().remove(adjunto);
        adjunto.setSolicitud(null);

        return adjunto;
    }

    public Constancia addConstancia(Constancia constancia) {
        getConstancias().add(constancia);
        constancia.setSolicitud(this);

        return constancia;
    }

    public Constancia removeConstancia(Constancia constancia) {
        getConstancias().remove(constancia);
        constancia.setSolicitud(null);

        return constancia;
    }

}
