package com.fannog.estudiante;

import com.fannog.solicitudConstancia.SolicitudConstancia;
import com.fannog.usuario.Usuario;
import java.io.Serializable;
import javax.persistence.*;
import java.time.Year;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Estudiante")
@Table(name = "ESTUDIANTE")
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findById", query = "SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante"),
    @NamedQuery(name = "Estudiante.findByGeneracion", query = "SELECT e FROM Estudiante e WHERE e.generacion = :generacion")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTUDIANTE_IDESTUDIANTE_GENERATOR", sequenceName = "SEQ_ID_ESTUDIANTE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTUDIANTE_IDESTUDIANTE_GENERATOR")
    @Column(name = "ID_ESTUDIANTE", unique = true, nullable = false, precision = 38)
    private Long idEstudiante;

    @Column(nullable = false, precision = 4)
    @NonNull
    private Year generacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @NonNull
    private Usuario usuario;

    @OneToMany(mappedBy = "estudiante")
    private List<SolicitudConstancia> solicitudConstancias;

    public SolicitudConstancia addSolicitud(SolicitudConstancia solicitud) {
        getSolicitudConstancias().add(solicitud);
        solicitud.setEstudiante(this);

        return solicitud;
    }

    public SolicitudConstancia removeSolicitud(SolicitudConstancia solicitud) {
        getSolicitudConstancias().remove(solicitud);
        solicitud.setEstudiante(null);

        return solicitud;
    }

}
