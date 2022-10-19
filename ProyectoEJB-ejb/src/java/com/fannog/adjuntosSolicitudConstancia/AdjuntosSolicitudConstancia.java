package com.fannog.adjuntosSolicitudConstancia;

import com.fannog.solicitudConstancia.SolicitudConstancia;
import java.io.File;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "AdjuntosSolicitudConstancia")
@Table(name = "ADJUNTOS_SOLICITUD_CONSTANCIA")
@NamedQueries({
    @NamedQuery(name = "AdjuntosSolicitudConstancia.findAll", query = "SELECT a FROM AdjuntosSolicitudConstancia a"),
    @NamedQuery(name = "AdjuntosSolicitudConstancia.findById", query = "SELECT a FROM AdjuntosSolicitudConstancia a WHERE a.idAdjuntosSolicitudConstancia = :idAdjuntosSolicitudConstancia"),
    @NamedQuery(name = "AdjuntosSolicitudConstancia.findByNombArchivo", query = "SELECT a FROM AdjuntosSolicitudConstancia a WHERE a.nombArchivo = :nombArchivo"),
    @NamedQuery(name = "AdjuntosSolicitudConstancia.findByEliminado", query = "SELECT a FROM AdjuntosSolicitudConstancia a WHERE a.eliminado = :eliminado")})
public class AdjuntosSolicitudConstancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ADJUNTOS_SOLICITUD_CONSTANCIA_IDADJUNTOSSOLICITUDCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_ADJUNTOS_SOLICITUD_CONSTANCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADJUNTOS_SOLICITUD_CONSTANCIA_IDADJUNTOSSOLICITUDCONSTANCIA_GENERATOR")
    @Column(name = "ID_ADJUNTOS_SOLICITUD_CONSTANCIA", unique = true, nullable = false, precision = 38)
    private Long idAdjuntosSolicitudConstancia;

    @Lob
    @Column(nullable = false)
    @NonNull
    private File archivo;

    @Column(nullable = false, precision = 1)
    private BigDecimal eliminado;

    @Column(name = "NOMB_ARCHIVO", nullable = false, length = 100)
    @NonNull
    private String nombArchivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SOLICITUD_CONSTANCIA", nullable = false)
    @NonNull
    private SolicitudConstancia solicitudConstancia;

}
