package com.fannog.estadosEvento;

import com.fannog.evento.Evento;
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
@Entity(name = "EstadosEvento")
@Table(name = "ESTADOS_EVENTO")
@NamedQueries({
    @NamedQuery(name = "EstadosEvento.findAll", query = "SELECT e FROM EstadosEvento e"),
    @NamedQuery(name = "EstadosEvento.findById", query = "SELECT e FROM EstadosEvento e WHERE e.idEstadosEvento = :idEstadosEvento"),
    @NamedQuery(name = "EstadosEvento.findByNombre", query = "SELECT e FROM EstadosEvento e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadosEvento.findByEliminado", query = "SELECT e FROM EstadosEvento e WHERE e.eliminado = :eliminado")})
public class EstadosEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTADOS_EVENTO_IDESTADOSEVENTO_GENERATOR", sequenceName = "SEQ_ID_ESTADOS_EVENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADOS_EVENTO_IDESTADOSEVENTO_GENERATOR")
    @Column(name = "ID_ESTADOS_EVENTO", unique = true, nullable = false, precision = 38)
    private Long idEstadosEvento;

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
