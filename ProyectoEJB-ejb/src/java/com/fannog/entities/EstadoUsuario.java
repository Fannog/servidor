package com.fannog.entities;

import com.fannog.entities.Usuario;
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
@Entity(name = "EstadoUsuario")
@Table(name = "ESTADO_USUARIO")
@NamedQueries({
    @NamedQuery(name = "EstadoUsuario.findAll", query = "SELECT e FROM EstadoUsuario e"),
    @NamedQuery(name = "EstadoUsuario.findByNombre", query = "SELECT e FROM EstadoUsuario e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoUsuario.findByEliminado", query = "SELECT e FROM EstadoUsuario e WHERE e.eliminado = :eliminado")})
public class EstadoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTADO_USUARIO_IDESTADOUSUARIO_GENERATOR", sequenceName = "SEQ_ID_ESTADO_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_USUARIO_IDESTADOUSUARIO_GENERATOR")
    @Column(name = "ID_ESTADO_USUARIO", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "estado")
    private List<Usuario> usuarios;

    public Usuario addUsuario(Usuario usuario) {
        getUsuarios().add(usuario);
        usuario.setEstado(this);

        return usuario;
    }

    public Usuario removeUsuario(Usuario usuario) {
        getUsuarios().remove(usuario);
        usuario.setEstado(null);

        return usuario;
    }

}
