package com.fannog.tiposConstancia;

import com.fannog.solicitudConstancia.SolicitudConstancia;
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
@Entity(name = "TiposConstancia")
@Table(name = "TIPOS_CONSTANCIA")
@NamedQueries({
    @NamedQuery(name = "TiposConstancia.findAll", query = "SELECT t FROM TiposConstancia t"),
    @NamedQuery(name = "TiposConstancia.findById", query = "SELECT t FROM TiposConstancia t WHERE t.idTiposConstancia = :idTiposConstancia"),
    @NamedQuery(name = "TiposConstancia.findByNombre", query = "SELECT t FROM TiposConstancia t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TiposConstancia.findByEliminado", query = "SELECT t FROM TiposConstancia t WHERE t.eliminado = :eliminado")})
public class TiposConstancia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TIPOS_CONSTANCIA_IDTIPOSCONSTANCIA_GENERATOR", sequenceName = "SEQ_ID_TIPOS_CONSTANCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOS_CONSTANCIA_IDTIPOSCONSTANCIA_GENERATOR")
    @Column(name = "ID_TIPOS_CONSTANCIA", unique = true, nullable = false, precision = 38)
    private Long idTiposConstancia;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @Lob
    @Column(nullable = false)
    @NonNull
    private byte[] plantilla;

    @OneToMany(mappedBy = "tipo")
    private List<SolicitudConstancia> solicitudes;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public SolicitudConstancia addSolicitud(SolicitudConstancia solicitud) {
        getSolicitudes().add(solicitud);
        solicitud.setTipo(this);

        return solicitud;
    }

    public SolicitudConstancia removeSolicitud(SolicitudConstancia solicitud) {
        getSolicitudes().remove(solicitud);
        solicitud.setTipo(null);

        return solicitud;
    }

}
