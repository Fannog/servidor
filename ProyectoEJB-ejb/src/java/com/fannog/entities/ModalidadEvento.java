package com.fannog.entities;

import com.fannog.entities.Evento;
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
@Entity(name = "ModalidadEvento")
@Table(name = "MODALIDADES_EVENTO")
@NamedQueries({
    @NamedQuery(name = "ModalidadEvento.findAll", query = "SELECT m FROM ModalidadEvento m"),
    @NamedQuery(name = "ModalidadEvento.findByNombre", query = "SELECT m FROM ModalidadEvento m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "ModalidadEvento.findByEliminado", query = "SELECT m FROM ModalidadEvento m WHERE m.eliminado = :eliminado")})
public class ModalidadEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "MODALIDAD_EVENTO_IDMODALIDADEVENTO_GENERATOR", sequenceName = "SEQ_ID_MODALIDAD_EVENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODALIDAD_EVENTO_IDMODALIDADEVENTO_GENERATOR")
    @Column(name = "ID_MODALIDAD_EVENTO", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "modalidad")
    private List<Evento> eventos;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public Evento addEvento(Evento evento) {
        getEventos().add(evento);
        evento.setModalidad(this);

        return evento;
    }

    public Evento removeEvento(Evento evento) {
        getEventos().remove(evento);
        evento.setModalidad(null);

        return evento;
    }

}
