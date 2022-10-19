package com.fannog.tutor;

import com.fannog.evento.Evento;
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
@Entity(name = "Tutor")
@Table(name = "TUTOR")
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findById", query = "SELECT t FROM Tutor t WHERE t.idTutor = :idTutor"),
    @NamedQuery(name = "Tutor.findByArea", query = "SELECT t FROM Tutor t WHERE t.area = :area")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TUTOR_IDTUTOR_GENERATOR", sequenceName = "SEQ_ID_TUTOR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TUTOR_IDTUTOR_GENERATOR")
    @Column(name = "ID_TUTOR", unique = true, nullable = false, precision = 38)
    private Long idTutor;

    @Column(nullable = false, length = 50)
    @NonNull
    private String area;
    
    @Column(nullable = false, length = 50)
    @NonNull
    private String rol;

    @ManyToMany
    @JoinTable(
            name = "RESPONSABILIDAD",
            joinColumns = {
                @JoinColumn(name = "ID_TUTOR", nullable = false)
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ID_EVENTO", nullable = false)
            }
    )
    private List<Evento> eventos;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @NonNull
    private Usuario usuario;

}
