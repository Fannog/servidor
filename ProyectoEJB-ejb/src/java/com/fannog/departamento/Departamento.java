package com.fannog.departamento;

import com.fannog.localidad.Localidad;
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
@Entity(name = "Departamento")
@Table(name = "DEPARTAMENTO")
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findById", query = "SELECT d FROM Departamento d WHERE d.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "Departamento.findByNombre", query = "SELECT d FROM Departamento d WHERE d.nombre = :nombre")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "DEPARTAMENTO_IDDEPARTAMENTO_GENERATOR", sequenceName = "SEQ_ID_DEPARTAMENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTAMENTO_IDDEPARTAMENTO_GENERATOR")
    @Column(name = "ID_DEPARTAMENTO", unique = true, nullable = false, precision = 38)
    private Long idDepartamento;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "departamento")
    private List<Localidad> localidads;

    public Localidad addLocalidad(Localidad localidad) {
        getLocalidads().add(localidad);
        localidad.setDepartamento(this);

        return localidad;
    }

    public Localidad removeLocalidad(Localidad localidad) {
        getLocalidads().remove(localidad);
        localidad.setDepartamento(null);

        return localidad;
    }

}
