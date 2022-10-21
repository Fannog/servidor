package com.fannog.proyectoservidor.entities;

import com.fannog.proyectoservidor.entities.Solicitud;
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
@Entity(name = "EstadosSolicitud")
@Table(name = "ESTADOS_SOLICITUD")
@NamedQueries({
    @NamedQuery(name = "EstadosSolicitud.findAll", query = "SELECT e FROM EstadosSolicitud e"),
    @NamedQuery(name = "EstadosSolicitud.findByNombre", query = "SELECT e FROM EstadosSolicitud e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadosSolicitud.findByEliminado", query = "SELECT e FROM EstadosSolicitud e WHERE e.eliminado = :eliminado")})
public class EstadoSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTADOS_SOLICITUD_IDESTADOSOLICITUD_GENERATOR", sequenceName = "SEQ_ID_ESTADO_SOLICITUD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADOS_SOLICITUD_IDESTADOSOLICITUD_GENERATOR")
    @Column(name = "ID_ESTADO_SOLICITUD", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "estado")
    private List<Solicitud> solicitudes;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public Solicitud addSolicitud(Solicitud solicitud) {
        getSolicitudes().add(solicitud);
        solicitud.setEstado(this);

        return solicitud;
    }

    public Solicitud removeSolicitud(Solicitud solicitud) {
        getSolicitudes().remove(solicitud);
        solicitud.setEstado(null);

        return solicitud;
    }

}
