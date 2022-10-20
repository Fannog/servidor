package com.fannog.entities;

import com.fannog.entities.Solicitud;
import java.io.File;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "TipoConstancia")
@Table(name = "TIPOS_CONSTANCIA")
@NamedQueries({
    @NamedQuery(name = "TipoConstancia.findAll", query = "SELECT t FROM TipoConstancia t"),
    @NamedQuery(name = "TipoConstancia.findByNombre", query = "SELECT t FROM TipoConstancia t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoConstancia.findByEliminado", query = "SELECT t FROM TipoConstancia t WHERE t.eliminado = :eliminado")})
public class TipoConstancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TIPO_CONSTANCIA_IDTIPOCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_TIPO_CONSTANCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_CONSTANCIA_IDTIPOCONSTANCIA_GENERATOR")
    @Column(name = "ID_TIPO_CONSTANCIA", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @Lob
    @Column(nullable = false)
    @NonNull
    private File plantilla;

    @OneToMany(mappedBy = "tipo")
    private List<Solicitud> solicitudes;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public Solicitud addSolicitud(Solicitud solicitud) {
        getSolicitudes().add(solicitud);
        solicitud.setTipo(this);

        return solicitud;
    }

    public Solicitud removeSolicitud(Solicitud solicitud) {
        getSolicitudes().remove(solicitud);
        solicitud.setTipo(null);

        return solicitud;
    }

}
