package com.fannog.proyectoservidor.entities;

import java.io.File;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "AdjuntoSolicitud")
@Table(name = "ADJUNTOS_SOLICITUD")
@NamedQueries({
    @NamedQuery(name = "AdjuntoSolicitud.findAll", query = "SELECT a FROM AdjuntoSolicitud a"),
    @NamedQuery(name = "AdjuntoSolicitud.findByNombArchivo", query = "SELECT a FROM AdjuntoSolicitud a WHERE a.nombArchivo = :nombArchivo"),
    @NamedQuery(name = "AdjuntoSolicitud.findByEliminado", query = "SELECT a FROM AdjuntoSolicitud a WHERE a.eliminado = :eliminado")})
public class AdjuntoSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ADJUNTO_SOLICITUD_IDADJUNTOSOLICITUD_GENERATOR", sequenceName = "SEQ_ID_ADJUNTO_SOLICITUD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADJUNTO_SOLICITUD_IDADJUNTOSOLICITUD_GENERATOR")
    @Column(name = "ID_ADJUNTO_SOLICITUD", unique = true, nullable = false, precision = 38)
    private Long id;

    @Lob
    @Column(nullable = false)
    @NotNull(message = "Debes adjuntar al menos un archivo")
    @NonNull
    private File archivo;

    @Column(name = "NOMB_ARCHIVO", nullable = false, length = 100)
    @NotNull(message = "El nombre del archivo no puede estar vacío")
    @Max(value = 100, message = "El nombre del archivo debe contener menos de 100 caracteres")
    @NonNull
    private String nombArchivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SOLICITUD", nullable = true)
    @NonNull
    private Solicitud solicitud;

    @Column(nullable = false, precision = 1)
    private BigDecimal eliminado;
}
