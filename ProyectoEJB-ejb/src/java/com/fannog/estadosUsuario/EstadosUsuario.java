package com.fannog.estadosUsuario;

import com.fannog.usuario.Usuario;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name = "EstadosUsuario")
@Table(name = "ESTADOS_USUARIO")
@NamedQueries({
    @NamedQuery(name = "EstadosUsuario.findAll", query = "SELECT e FROM EstadosUsuario e"),
    @NamedQuery(name = "EstadosUsuario.findById", query = "SELECT e FROM EstadosUsuario e WHERE e.idEstadosUsuario = :idEstadosEvento"),
    @NamedQuery(name = "EstadosUsuario.findByNombre", query = "SELECT e FROM EstadosUsuario e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadosUsuario.findByEliminado", query = "SELECT e FROM EstadosUsuario e WHERE e.eliminado = :eliminado")})
public class EstadosUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTADOS_USUARIO_IDESTADOSUSUARIO_GENERATOR", sequenceName = "SEQ_ID_ESTADOS_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADOS_USUARIO_IDESTADOSUSUARIO_GENERATOR")
    @Column(name = "ID_ESTADOS_USUARIO", unique = true, nullable = false, precision = 38)
    private Long idEstadosUsuario;

    @Column(precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "estadosUsuario")
    private List<Usuario> usuarios;

    public Usuario addUsuario(Usuario usuario) {
        getUsuarios().add(usuario);
        usuario.setEstadosUsuario(this);

        return usuario;
    }

    public Usuario removeUsuario(Usuario usuario) {
        getUsuarios().remove(usuario);
        usuario.setEstadosUsuario(null);

        return usuario;
    }

}
