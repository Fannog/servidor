package com.fannog.estadosSolicitudConstancia;

import com.fannog.solicitudConstancia.SolicitudConstancia;
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
@Entity(name = "EstadosSolicitudConstancia")
@Table(name = "ESTADOS_SOLICITUD_CONSTANCIA")
@NamedQueries({
    @NamedQuery(name = "EstadosSolicitudConstancia.findAll", query = "SELECT e FROM EstadosSolicitudConstancia e"),
    @NamedQuery(name = "EstadosSolicitudConstancia.findById", query = "SELECT e FROM EstadosSolicitudConstancia e WHERE e.idEstadosSolicitudConstancia = :idEstadosSolicitudConstancia"),
    @NamedQuery(name = "EstadosSolicitudConstancia.findByNombre", query = "SELECT e FROM EstadosSolicitudConstancia e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadosSolicitudConstancia.findByEliminado", query = "SELECT e FROM EstadosSolicitudConstancia e WHERE e.eliminado = :eliminado")})
public class EstadosSolicitudConstancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTADOS_SOLICITUD_CONSTANCIA_IDESTADOSSOLICITUDCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_ESTADOS_SOLICITUD_CONSTANCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADOS_SOLICITUD_CONSTANCIA_IDESTADOSSOLICITUDCONSTANCIA_GENERATOR")
    @Column(name = "ID_ESTADOS_SOLICITUD_CONSTANCIA", unique = true, nullable = false, precision = 38)
    private Long idEstadosSolicitudConstancia;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "estado")
    private List<SolicitudConstancia> solicitudes;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public SolicitudConstancia addSolicitud(SolicitudConstancia solicitud) {
        getSolicitudes().add(solicitud);
        solicitud.setEstado(this);

        return solicitud;
    }

    public SolicitudConstancia removeSolicitud(SolicitudConstancia solicitud) {
        getSolicitudes().remove(solicitud);
        solicitud.setEstado(null);

        return solicitud;
    }

}
