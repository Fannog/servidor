package com.fannog.proyectoservidor.entities;

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
@Entity(name = "EstadoEvento")
@Table(name = "ESTADOS_EVENTO")
@NamedQueries({
    @NamedQuery(name = "EstadoEvento.findAll", query = "SELECT e FROM EstadoEvento e"),
    @NamedQuery(name = "EstadoEvento.findByNombre", query = "SELECT e FROM EstadoEvento e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoEvento.findByEliminado", query = "SELECT e FROM EstadoEvento e WHERE e.eliminado = :eliminado")})
public class EstadoEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTADO_EVENTO_IDESTADOEVENTO_GENERATOR", sequenceName = "SEQ_ID_ESTADO_EVENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_EVENTO_IDESTADOEVENTO_GENERATOR")
    @Column(name = "ID_ESTADO_EVENTO", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "estado")
    private List<Evento> eventos;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public Evento addEvento(Evento evento) {
        getEventos().add(evento);
        evento.setEstado(this);

        return evento;
    }

    public Evento removeEvento(Evento evento) {
        getEventos().remove(evento);
        evento.setEstado(null);

        return evento;
    }

}
