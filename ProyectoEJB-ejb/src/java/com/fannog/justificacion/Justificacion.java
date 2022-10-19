package com.fannog.justificacion;

import com.fannog.accionJustificacion.AccionJustificacion;
import com.fannog.adjuntosJustificacion.AdjuntosJustificacion;
import com.fannog.estadosJustificacion.EstadosJustificacion;
import com.fannog.evento.Evento;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Justificacion")
@Table(name = "JUSTIFICACION")
@NamedQueries({
    @NamedQuery(name = "Justificacion.findAll", query = "SELECT j FROM Justificacion j"),
    @NamedQuery(name = "Justificacion.findById", query = "SELECT j FROM Justificacion j WHERE j.idJustificacion = :idJustificacion"),
    @NamedQuery(name = "Justificacion.findByDetalle", query = "SELECT j FROM Justificacion j WHERE j.detalle = :detalle"),
    @NamedQuery(name = "Justificacion.findByFecHora", query = "SELECT j FROM Justificacion j WHERE j.fecHora = :fecHora"),
    @NamedQuery(name = "Justificacion.findByEliminado", query = "SELECT j FROM Justificacion j WHERE j.eliminado = :eliminado")})
public class Justificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "JUSTIFICACION_IDJUSTIFICACION_GENERATOR", sequenceName = "SEQ_ID_JUSTIFICACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JUSTIFICACION_IDJUSTIFICACION_GENERATOR")
    @Column(name = "ID_JUSTIFICACION", unique = true, nullable = false, precision = 38)
    private Long idJustificacion;

    @Column(nullable = false, length = 1000)
    @NonNull
    private String detalle;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(name = "FEC_HORA", nullable = false)
    @NonNull
    private LocalDateTime fecHora;

    @OneToMany(mappedBy = "justificacion")
    private List<AccionJustificacion> accionJustificacions;

    @OneToMany(mappedBy = "justificacion")
    @NonNull
    private List<AdjuntosJustificacion> adjuntosJustificacions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO", nullable = false)
    private EstadosJustificacion estadosJustificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EVENTO", nullable = false)
    @NonNull
    private Evento evento;

    public AccionJustificacion addAccion(AccionJustificacion accion) {
        getAccionJustificacions().add(accion);
        accion.setJustificacion(this);

        return accion;
    }

    public AccionJustificacion removeAccion(AccionJustificacion accion) {
        getAccionJustificacions().remove(accion);
        accion.setJustificacion(null);

        return accion;
    }

    public AdjuntosJustificacion addAdjunto(AdjuntosJustificacion adjunto) {
        getAdjuntosJustificacions().add(adjunto);
        adjunto.setJustificacion(this);

        return adjunto;
    }

    public AdjuntosJustificacion removeAdjunto(AdjuntosJustificacion adjunto) {
        getAdjuntosJustificacions().remove(adjunto);
        adjunto.setJustificacion(null);

        return adjunto;
    }

}
