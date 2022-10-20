package com.fannog.entities;

import com.fannog.entities.Analista;
import com.fannog.entities.Justificacion;
import java.io.Serializable;
import java.util.Date;
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
    @NamedQuery(name = "AccionJustificacion.findByFecHora", query = "SELECT a FROM AccionJustificacion a WHERE a.fecHora = :fecHora"),
    @NamedQuery(name = "AccionJustificacion.findByDetalle", query = "SELECT a FROM AccionJustificacion a WHERE a.detalle = :detalle"),
    @NamedQuery(name = "AccionJustificacion.findByEliminado", query = "SELECT a FROM AccionJustificacion a WHERE a.eliminado = :eliminado")})
public class AccionJustificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACCION_JUSTIFICACION_IDACCIONJUSTIFICACION_GENERATOR", sequenceName = "SEQ_ID_ACCION_JUSTIFICACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCION_JUSTIFICACION_IDACCIONJUSTIFICACION_GENERATOR")
    @Column(name = "ID_ACCION_JUSTIFICACION", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(length = 1000)
    @NonNull
    private String detalle;

    @Column(nullable = false, precision = 1)
    private boolean eliminado;

    @Column(name = "FEC_HORA", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANALISTA", nullable = false)
    @NonNull
    private Analista analista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_JUSTIFICACION", nullable = false)
    @NonNull
    private Justificacion justificacion;

}
