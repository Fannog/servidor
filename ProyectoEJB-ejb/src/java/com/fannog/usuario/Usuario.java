package com.fannog.usuario;

import com.fannog.analista.Analista;
import com.fannog.estadosUsuario.EstadosUsuario;
import com.fannog.estudiante.Estudiante;
import com.fannog.localidad.Localidad;
import com.fannog.tutor.Tutor;
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
@Entity(name = "Usuario")
@Table(name = "USUARIO")
@EntityListeners(UsuarioListener.class)
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByDocumento", query = "SELECT u FROM Usuario u WHERE u.documento = :documento"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuario.findByEmailPersonal", query = "SELECT u FROM Usuario u WHERE u.emailPersonal = :emailPersonal"),
    @NamedQuery(name = "Usuario.findByEmailInstitucional", query = "SELECT u FROM Usuario u WHERE u.emailInstitucional = :emailInstitucional"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuario.findByEliminado", query = "SELECT u FROM Usuario u WHERE u.eliminado = :eliminado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "USUARIO_IDUSUARIO_GENERATOR", sequenceName = "SEQ_ID_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_IDUSUARIO_GENERATOR")
    @Column(name = "ID_USUARIO", unique = true, nullable = false, precision = 38)
    private Long idUsuario;

    @Column(nullable = false, length = 50)
    @NonNull
    private String apellidos;

    @Column(name = "NOMBRE_USUARIO", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(nullable = false, precision = 9)
    @NonNull
    private Long documento;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

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

    @OneToMany(mappedBy = "usuario")
    private List<Analista> analistas;

    @OneToMany(mappedBy = "usuario")
    private List<Estudiante> estudiantes;

    @OneToMany(mappedBy = "usuario")
    private List<Tutor> tutors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO", nullable = false)
    @NonNull
    private EstadosUsuario estadosUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LOCALIDAD", nullable = false)
    @NonNull
    private Localidad localidad;

    public Analista addAnalista(Analista analista) {
        getAnalistas().add(analista);
        analista.setUsuario(this);

        return analista;
    }

    public Analista removeAnalista(Analista analista) {
        getAnalistas().remove(analista);
        analista.setUsuario(null);

        return analista;
    }

    public Estudiante addEstudiante(Estudiante estudiante) {
        getEstudiantes().add(estudiante);
        estudiante.setUsuario(this);

        return estudiante;
    }

    public Estudiante removeEstudiante(Estudiante estudiante) {
        getEstudiantes().remove(estudiante);
        estudiante.setUsuario(null);

        return estudiante;
    }

    public Tutor addTutor(Tutor tutor) {
        getTutors().add(tutor);
        tutor.setUsuario(this);

        return tutor;
    }

    public Tutor removeTutor(Tutor tutor) {
        getTutors().remove(tutor);
        tutor.setUsuario(null);

        return tutor;
    }

}
