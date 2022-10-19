package com.fannog.accionSolicitudJustificacion;

import com.fannog.analista.Analista;
import com.fannog.solicitudConstancia.SolicitudConstancia;
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
@Entity(name = "AccionSolicitudConstancia")
@Table(name = "ACCION_SOLICITUD_CONSTANCIA")
@NamedQuery(name = "AccionSolicitudConstancia.findAll", query = "SELECT a FROM AccionSolicitudConstancia a")
public class AccionSolicitudConstancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACCION_SOLICITUD_CONSTANCIA_IDACCIONSOLICITUDCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_ACCION_SOLICITUD_CONSTANCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCION_SOLICITUD_CONSTANCIA_IDACCIONSOLICITUDCONSTANCIA_GENERATOR")
    @Column(name = "ID_ACCION_SOLICITUD_CONSTANCIA", unique = true, nullable = false, precision = 38)
    private Long idAccionSolicitudConstancia;

    @Column(length = 1000)
    @NonNull
    private String detalle;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(name = "FEC_HORA", nullable = false)
    @NonNull
    private LocalDateTime fecHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANALISTA", nullable = false)
    @NonNull
    private Analista analista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SOLICITUD_CONSTANCIA", nullable = false)
    @NonNull
    private SolicitudConstancia solicitudConstancia;

}
