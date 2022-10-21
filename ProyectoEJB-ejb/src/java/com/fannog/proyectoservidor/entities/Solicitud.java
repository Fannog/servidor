package com.fannog.proyectoservidor.entities;

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
@Entity(name = "Solicitud")
@Table(name = "SOLICITUDES")
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findByDetalle", query = "SELECT s FROM Solicitud s WHERE s.detalle = :detalle"),
    @NamedQuery(name = "Solicitud.findByEliminado", query = "SELECT s FROM Solicitud s WHERE s.eliminado = :eliminado")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SOLICITUD_IDSOLICITUD_GENERATOR", sequenceName = "SEQ_ID_SOLICITUD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLICITUD_IDSOLICITUD_GENERATOR")
    @Column(name = "ID_SOLICITUD", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(nullable = false, length = 100)
    @NonNull
    private String detalle;

    @OneToMany(mappedBy = "solicitud")
    private List<AccionSolicitud> acciones;

    @OneToMany(mappedBy = "solicitud")
    @NonNull
    private List<AdjuntoSolicitud> adjuntos;

    @OneToMany(mappedBy = "solicitud")
    private List<Constancia> constancias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO", nullable = false)
    @NonNull
    private EstadoSolicitud estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTUDIANTE", nullable = false)
    @NonNull
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CONSTANCIA", nullable = false)
    @NonNull
    private TipoConstancia tipo;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public AccionSolicitud addAccion(AccionSolicitud accion) {
        getAcciones().add(accion);
        accion.setSolicitud(this);

        return accion;
    }

    public AccionSolicitud removeAccion(AccionSolicitud accion) {
        getAcciones().remove(accion);
        accion.setSolicitud(null);

        return accion;
    }

    public AdjuntoSolicitud addAdjunto(AdjuntoSolicitud adjunto) {
        getAdjuntos().add(adjunto);
        adjunto.setSolicitud(this);

        return adjunto;
    }

    public AdjuntoSolicitud removeAdjunto(AdjuntoSolicitud adjunto) {
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
