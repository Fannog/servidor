package com.fannog.proyectoservidor.entities;

import com.fannog.proyectoservidor.entities.Departamento;
import com.fannog.proyectoservidor.entities.Usuario;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Localidad")
@Table(name = "LOCALIDADES")
@NamedQueries({
    @NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l"),
    @NamedQuery(name = "Localidad.findByNombre", query = "SELECT l FROM Localidad l WHERE l.nombre = :nombre")})
public class Localidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "LOCALIDAD_IDLOCALIDAD_GENERATOR", sequenceName = "SEQ_ID_LOCALIDAD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCALIDAD_IDLOCALIDAD_GENERATOR")
    @Column(name = "ID_LOCALIDAD", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(nullable = false, length = 50)
    @Size(max = 50, min = 2, message = "El campo nombre debe contener entre 2 a 50 caracteres")
    @NotNull(message = "El campo nombre no puede estar vacío")
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "localidad")
    @ToString.Exclude
    private List<Itr> itrs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
    @NotNull(message = "Debes seleccionar un departamento")
    @NonNull
    @ToString.Exclude
    private Departamento departamento;

    @OneToMany(mappedBy = "localidad")
    @ToString.Exclude
    private List<Usuario> usuarios;

    public Itr addItr(Itr itr) {
        getItrs().add(itr);
        itr.setLocalidad(this);

        return itr;
    }

    public Itr removeItr(Itr itr) {
        getItrs().remove(itr);
        itr.setLocalidad(null);

        return itr;
    }

    public Usuario addUsuario(Usuario usuario) {
        getUsuarios().add(usuario);
        usuario.setLocalidad(this);

        return usuario;
    }

    public Usuario removeUsuario(Usuario usuario) {
        getUsuarios().remove(usuario);
        usuario.setLocalidad(null);

        return usuario;
    }

}
