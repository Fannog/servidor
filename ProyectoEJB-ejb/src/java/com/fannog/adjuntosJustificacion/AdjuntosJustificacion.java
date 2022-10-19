package com.fannog.adjuntosJustificacion;

import com.fannog.justificacion.Justificacion;
import java.io.File;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "AdjuntosJustificacion")
@Table(name = "ADJUNTOS_JUSTIFICACION")
@NamedQueries({
    @NamedQuery(name = "AdjuntosJustificacion.findAll", query = "SELECT a FROM AdjuntosJustificacion a"),
    @NamedQuery(name = "AdjuntosJustificacion.findById", query = "SELECT a FROM AdjuntosJustificacion a WHERE a.idAdjuntosJustificacion = :idAdjuntosJustificacion"),
    @NamedQuery(name = "AdjuntosJustificacion.findByNombArchivo", query = "SELECT a FROM AdjuntosJustificacion a WHERE a.nombArchivo = :nombArchivo"),
    @NamedQuery(name = "AdjuntosJustificacion.findByEliminado", query = "SELECT a FROM AdjuntosJustificacion a WHERE a.eliminado = :eliminado")})
public class AdjuntosJustificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ADJUNTOS_JUSTIFICACION_IDADJUNTOSJUSTIFICACION_GENERATOR", sequenceName = "SEQ_ID_ADJUNTOS_JUSTIFICACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADJUNTOS_JUSTIFICACION_IDADJUNTOSJUSTIFICACION_GENERATOR")
    @Column(name = "ID_ADJUNTOS_JUSTIFICACION", unique = true, nullable = false, precision = 38)
    private Long idAdjuntosJustificacion;

    @Lob
    @Column(nullable = false)
    @NonNull
    private File archivo;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(name = "NOMB_ARCHIVO", nullable = false, length = 100)
    @NonNull
    private String nombArchivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_JUSTIFICACION", nullable = false)
    @NonNull
    private Justificacion justificacion;

}
