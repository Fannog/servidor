package com.fannog.localidad;

import com.fannog.departamento.Departamento;
import com.fannog.itr.Itr;
import com.fannog.usuario.Usuario;
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
@Entity(name = "Localidad")
@Table(name = "LOCALIDAD")
@NamedQueries({
    @NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l"),
    @NamedQuery(name = "Localidad.findById", query = "SELECT l FROM Localidad l WHERE l.idLocalidad = :idLocalidad"),
    @NamedQuery(name = "Localidad.findByNombre", query = "SELECT l FROM Localidad l WHERE l.nombre = :nombre")})
public class Localidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "LOCALIDAD_IDLOCALIDAD_GENERATOR", sequenceName = "SEQ_ID_LOCALIDAD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCALIDAD_IDLOCALIDAD_GENERATOR")
    @Column(name = "ID_LOCALIDAD", unique = true, nullable = false, precision = 38)
    private Long idLocalidad;

    @Column(nullable = false, length = 50)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "localidad")
    private List<Itr> itrs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
    @NonNull
    private Departamento departamento;

    @OneToMany(mappedBy = "localidad")
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
