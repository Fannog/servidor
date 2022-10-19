package com.fannog.tiposEvento;

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
@Entity(name = "TiposEvento")
@Table(name = "TIPOS_EVENTO")
@NamedQueries({
    @NamedQuery(name = "TiposEvento.findAll", query = "SELECT t FROM TiposEvento t"),
    @NamedQuery(name = "TiposEvento.findById", query = "SELECT t FROM TiposEvento t WHERE t.idTiposEvento = :idTiposEvento"),
    @NamedQuery(name = "TiposEvento.findByNombre", query = "SELECT t FROM TiposEvento t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TiposEvento.findByEliminado", query = "SELECT t FROM TiposEvento t WHERE t.eliminado = :eliminado")})
public class TiposEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TIPOS_EVENTO_IDTIPOSEVENTO_GENERATOR", sequenceName = "SEQ_ID_TIPOS_EVENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOS_EVENTO_IDTIPOSEVENTO_GENERATOR")
    @Column(name = "ID_TIPOS_EVENTO", unique = true, nullable = false, precision = 38)
    private Long idTiposEvento;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "tiposEvento")
    private List<Evento> eventos;

    public Evento addEvento(Evento evento) {
        getEventos().add(evento);
        evento.setTiposEvento(this);

        return evento;
    }

    public Evento removeEvento(Evento evento) {
        getEventos().remove(evento);
        evento.setTiposEvento(null);

        return evento;
    }
}
