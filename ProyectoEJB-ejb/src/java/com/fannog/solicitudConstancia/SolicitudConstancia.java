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

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @OneToMany(mappedBy = "solicitudConstancia")
    private List<AccionSolicitudConstancia> accionSolicitudConstancias;

    @OneToMany(mappedBy = "solicitudConstancia")
    @NonNull
    private List<AdjuntosSolicitudConstancia> adjuntosSolicitudConstancias;

    @OneToMany(mappedBy = "solicitudConstancia")
    private List<Constancia> constancias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO", nullable = false)
    @NonNull
    private EstadosSolicitudConstancia estadosSolicitudConstancia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTUDIANTE", nullable = false)
    @NonNull
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CONSTANCIA", nullable = false)
    @NonNull
    private TiposConstancia tiposConstancia;

    public AccionSolicitudConstancia addAccion(AccionSolicitudConstancia accion) {
        getAccionSolicitudConstancias().add(accion);
        accion.setSolicitudConstancia(this);

        return accion;
    }

    public AccionSolicitudConstancia removeAccion(AccionSolicitudConstancia accion) {
        getAccionSolicitudConstancias().remove(accion);
        accion.setSolicitudConstancia(null);

        return accion;
    }

    public AdjuntosSolicitudConstancia addAdjunto(AdjuntosSolicitudConstancia adjunto) {
        getAdjuntosSolicitudConstancias().add(adjunto);
        adjunto.setSolicitudConstancia(this);

        return adjunto;
    }

    public AdjuntosSolicitudConstancia removeAdjunto(AdjuntosSolicitudConstancia adjunto) {
        getAdjuntosSolicitudConstancias().remove(adjunto);
        adjunto.setSolicitudConstancia(null);

        return adjunto;
    }

    public Constancia addConstancia(Constancia constancia) {
        getConstancias().add(constancia);
        constancia.setSolicitudConstancia(this);

        return constancia;
    }

    public Constancia removeConstancia(Constancia constancia) {
        getConstancias().remove(constancia);
        constancia.setSolicitudConstancia(null);

        return constancia;
    }

}
