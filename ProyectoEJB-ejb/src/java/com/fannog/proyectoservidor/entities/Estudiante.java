package com.fannog.proyectoservidor.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Estudiante")
@Table(name = "ESTUDIANTES")
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByGeneracion", query = "SELECT e FROM Estudiante e WHERE e.generacion = :generacion")})
public class Estudiante extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, precision = 4)
    @Positive(message = "El campo generacion debe contener valores positivos")
    private Integer generacion;

    @OneToMany(mappedBy = "estudiante")
    private List<Solicitud> solicitudes;

    public Estudiante(int generacion, String apellidos, String documento, String email, String nombres, Integer telefono, String password, EstadoUsuario estado, Localidad localidad, Long rolUsuario) {
        super(apellidos, documento, email, nombres, telefono, password, estado, localidad, rolUsuario);
        this.generacion = generacion;
    }

    public Solicitud addSolicitud(Solicitud solicitud) {
        getSolicitudes().add(solicitud);
        solicitud.setEstudiante(this);

        return solicitud;
    }

    public Solicitud removeSolicitud(Solicitud solicitud) {
        getSolicitudes().remove(solicitud);
        solicitud.setEstudiante(null);

        return solicitud;
    }

}
