package com.fannog.accionJustificacion;

import com.fannog.analista.Analista;
import com.fannog.justificacion.Justificacion;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "AccionJustificacion")
@Table(name = "ACCION_JUSTIFICACION")
@NamedQueries({
    @NamedQuery(name = "AccionJustificacion.findAll", query = "SELECT a FROM AccionJustificacion a"),
    @NamedQuery(name = "AccionJustificacion.findById", query = "SELECT a FROM AccionJustificacion a WHERE a.idAccionJustificacion = :idAccionJustificacion"),
    @NamedQuery(name = "AccionJustificacion.findByFecHora", query = "SELECT a FROM AccionJustificacion a WHERE a.fecHora = :fecHora"),
    @NamedQuery(name = "AccionJustificacion.findByDetalle", query = "SELECT a FROM AccionJustificacion a WHERE a.detalle = :detalle"),
    @NamedQuery(name = "AccionJustificacion.findByEliminado", query = "SELECT a FROM AccionJustificacion a WHERE a.eliminado = :eliminado")})
public class AccionJustificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACCION_JUSTIFICACION_IDACCIONJUSTIFICACION_GENERATOR", sequenceName = "SEQ_ID_ACCION_JUSTIFICACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCION_JUSTIFICACION_IDACCIONJUSTIFICACION_GENERATOR")
    @Column(name = "ID_ACCION_JUSTIFICACION", unique = true, nullable = false, precision = 38)
    private Long idAccionJustificacion;

    @Column(length = 1000)
    @NonNull
    private String detalle;

    @Column(nullable = false, precision = 1)
    private boolean eliminado;

    @Column(name = "FEC_HORA", nullable = false)
    @NonNull
    private LocalDateTime fecHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANALISTA", nullable = false)
    @NonNull
    private Analista analista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_JUSTIFICACION", nullable = false)
    @NonNull
    private Justificacion justificacion;

}
