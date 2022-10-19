package com.fannog.constancia;

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
@Entity(name = "Constancia")
@Table(name = "CONSTANCIA")
@NamedQueries({
    @NamedQuery(name = "Constancia.findAll", query = "SELECT c FROM Constancia c"),
    @NamedQuery(name = "Constancia.findById", query = "SELECT c FROM Constancia c WHERE c.idConstancia = :idConstancia"),
    @NamedQuery(name = "Constancia.findByFecha", query = "SELECT c FROM Constancia c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Constancia.findByEliminado", query = "SELECT c FROM Constancia c WHERE c.eliminado = :eliminado")})
public class Constancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CONSTANCIA_IDCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_CONSTANCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSTANCIA_IDCONSTANCIA_GENERATOR")
    @Column(name = "ID_CONSTANCIA", unique = true, nullable = false, precision = 38)
    private Long idConstancia;

    @Lob
    @Column(nullable = false)
    @NonNull
    private byte[] archivo;

    @Column(nullable = false)
    @NonNull
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANALISTA", nullable = false)
    @NonNull
    private Analista analista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SOLICITUD_CONSTANCIA", nullable = false)
    @NonNull
    private SolicitudConstancia solicitud;

    @Column(nullable = false, precision = 1)
    private boolean eliminado;

}
