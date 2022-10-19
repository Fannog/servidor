package com.fannog.tutor;

import com.fannog.estadosUsuario.EstadosUsuario;
import com.fannog.evento.Evento;
import com.fannog.localidad.Localidad;
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
@Entity(name = "Tutor")
@Table(name = "TUTOR")
@NamedNativeQuery(name = "Tutor.findById", query = "SELECT * FROM TUTOR WHERE ID_TUTOR = ?1")
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByArea", query = "SELECT t FROM Tutor t WHERE t.area = :area"),
    @NamedQuery(name = "Tutor.findByRol", query = "SELECT t FROM Tutor t WHERE t.rol = :rol")})
public class Tutor extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public Tutor(String area, String rol, String apellidos, Long documento, String emailInstitucional, String emailPersonal, String nombres, Long telefono, String password, EstadosUsuario estado, Localidad localidad) {
        super(apellidos, documento, emailInstitucional, emailPersonal, nombres, telefono, password, estado, localidad);
        this.area = area;
        this.rol = rol;
    }

}
