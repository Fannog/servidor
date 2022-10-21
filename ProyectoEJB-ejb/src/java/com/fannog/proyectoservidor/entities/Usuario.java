package com.fannog.proyectoservidor.entities;

import com.fannog.proyectoservidor.listeners.UsuarioListener;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "USUARIOS")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(UsuarioListener.class)
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByDocumento", query = "SELECT u FROM Usuario u WHERE u.documento = :documento"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByEmailPersonal", query = "SELECT u FROM Usuario u WHERE u.emailPersonal = :emailPersonal"),
    @NamedQuery(name = "Usuario.findByEmailInstitucional", query = "SELECT u FROM Usuario u WHERE u.emailInstitucional = :emailInstitucional"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuario.findByEliminado", query = "SELECT u FROM Usuario u WHERE u.eliminado = :eliminado")})
public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "USUARIO_IDUSUARIO_GENERATOR", sequenceName = "SEQ_ID_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_IDUSUARIO_GENERATOR")
    @Column(name = "ID_USUARIO", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(nullable = false, length = 50)
    @NonNull
    private String apellidos;

    @Column(name = "NOMBRE_USUARIO", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(nullable = false, precision = 9)
    @NonNull
    private Long documento;

    @Column(name = "EMAIL_INSTITUCIONAL", nullable = false, length = 100)
    @NonNull
    private String emailInstitucional;

    @Column(name = "EMAIL_PERSONAL", nullable = false, length = 100)
    @NonNull
    private String emailPersonal;

    @Column(nullable = false, length = 50)
    @NonNull
    private String nombres;

    @Column(nullable = false, precision = 38)
    @NonNull
    private Long telefono;

    @Column(columnDefinition = "CHAR(64)", nullable = false)
    @NonNull
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO", nullable = false)
    @NonNull
    private EstadoUsuario estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LOCALIDAD", nullable = false)
    @NonNull
    private Localidad localidad;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;
}
