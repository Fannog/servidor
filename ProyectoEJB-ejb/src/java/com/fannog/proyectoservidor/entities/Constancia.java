package com.fannog.proyectoservidor.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Constancia")
@Table(name = "CONSTANCIAS")
@NamedQueries({
    @NamedQuery(name = "Constancia.findAll", query = "SELECT c FROM Constancia c"),
    @NamedQuery(name = "Constancia.findByFecha", query = "SELECT c FROM Constancia c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Constancia.findByEliminado", query = "SELECT c FROM Constancia c WHERE c.eliminado = :eliminado")})
public class Constancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CONSTANCIA_IDCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_CONSTANCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSTANCIA_IDCONSTANCIA_GENERATOR")
    @Column(name = "ID_CONSTANCIA", unique = true, nullable = false, precision = 38)
    private Long id;

    @Lob
    @Column(nullable = false)
    @NotNull(message = "Debes adjuntar un archivo para emitir la constancia")
    @NonNull
    private byte[] archivo;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANALISTA", nullable = false)
    @NotNull(message = "La emisión de constancia debe tener un analista asociado")
    @NonNull
    private Analista analista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SOLICITUD_CONSTANCIA", nullable = false)
    @NotNull(message = "La emisión de constancia debe tener una solicitud asociada")
    @NonNull
    private Solicitud solicitud;

    @Column(nullable = false, precision = 1)
    private boolean eliminado;

}
