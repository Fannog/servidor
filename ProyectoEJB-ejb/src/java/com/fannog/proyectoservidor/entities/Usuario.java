package com.fannog.proyectoservidor.entities;

import com.fannog.proyectoservidor.listeners.UsuarioListener;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
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
    @Size(max = 50, min = 2, message = "El campo apellidos debe contener entre 2 a 50 caracteres")
    @NotNull(message = "El campo apellidos no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "El campo apellidos no puede contener números")
    @NonNull
    private String apellidos;

    @Column(name = "NOMBRE_USUARIO", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(nullable = false, precision = 9)
    @NotBlank(message = "El campo documento no puede estar vacío")
    @NotNull(message = "El campo documento no puede estar vacío")
    @NonNull
    private String documento;

    @Column(name = "EMAIL", nullable = false, length = 100)
    @NonNull
    private String email;

    @Column(nullable = false, length = 50)
    @Size(max = 50, min = 2, message = "El campo nombres debe contener entre 2 a 50 caracteres")
    @NotNull(message = "El campo nombres no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "El campo nombres no puede contener números")
    @NonNull
    private String nombres;

    @Column(nullable = false, precision = 38)
    @Positive(message = "El campo telefono no puede contener valores negativos")
    @NonNull
    private Integer telefono;

    @Column(columnDefinition = "CHAR(64)", nullable = false)
    @NotNull(message = "El campo password no puede estar vacío")
    @NonNull
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO", nullable = false)
    @NonNull
    private EstadoUsuario estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LOCALIDAD", nullable = false)
    @NotNull(message = "Debes seleccionar una localidad")
    @NonNull
    private Localidad localidad;
    
    @Column(nullable = false)
    @NonNull
    private Integer rolUsuario;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;
}
