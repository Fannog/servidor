package com.fannog.entities;

import com.fannog.entities.EstadoUsuario;
import com.fannog.entities.Localidad;
import com.fannog.entities.Solicitud;
import com.fannog.entities.Usuario;
import java.io.Serializable;
import javax.persistence.*;
import java.time.Year;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity(name = "Estudiante")
@Table(name = "ESTUDIANTE")
@NamedNativeQuery(name = "Estudiante.findById", query = "SELECT * FROM ESTUDIANTE WHERE ID_ESTUDIANTE = ?1")
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByGeneracion", query = "SELECT e FROM Estudiante e WHERE e.generacion = :generacion")})
public class Estudiante extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, precision = 4)
    @NonNull
    private Year generacion;

    @OneToMany(mappedBy = "estudiante")
    private List<Solicitud> solicitudConstancias;

    public Estudiante(Year generacion, String apellidos, Long documento, String emailInstitucional, String emailPersonal, String nombres, Long telefono, String password, EstadoUsuario estado, Localidad localidad) {
        super(apellidos, documento, emailInstitucional, emailPersonal, nombres, telefono, password, estado, localidad);
        this.generacion = generacion;
    }

    public Solicitud addSolicitud(Solicitud solicitud) {
        getSolicitudConstancias().add(solicitud);
        solicitud.setEstudiante(this);

        return solicitud;
    }

    public Solicitud removeSolicitud(Solicitud solicitud) {
        getSolicitudConstancias().remove(solicitud);
        solicitud.setEstudiante(null);

        return solicitud;
    }

}
