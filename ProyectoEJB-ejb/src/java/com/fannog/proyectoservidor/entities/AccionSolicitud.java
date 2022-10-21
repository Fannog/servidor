package com.fannog.proyectoservidor.entities;

import com.fannog.proyectoservidor.entities.Analista;
import com.fannog.proyectoservidor.entities.Solicitud;
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
@Entity(name = "AccionSolicitud")
@Table(name = "ACCIONES_SOLICITUD")
@NamedQuery(name = "AccionSolicitud.findAll", query = "SELECT a FROM AccionSolicitud a")
public class AccionSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACCION_SOLICITUD_IDACCIONSOLICITUD_GENERATOR", sequenceName = "SEQ_ID_ACCION_SOLICITUD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCION_SOLICITUD_IDACCIONSOLICITUD_GENERATOR")
    @Column(name = "ID_ACCION_SOLICITUD", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(length = 1000)
    @NonNull
    private String detalle;

    @Column(name = "FEC_HORA", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANALISTA", nullable = false)
    @NonNull
    private Analista analista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SOLICITUD", nullable = false)
    @NonNull
    private Solicitud solicitud;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

}
