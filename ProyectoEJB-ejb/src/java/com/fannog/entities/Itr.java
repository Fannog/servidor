package com.fannog.entities;

import com.fannog.entities.Evento;
import com.fannog.entities.Localidad;
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
@Entity(name = "Itr")
@Table(name = "ITRs")
@NamedQueries({
    @NamedQuery(name = "Itr.findAll", query = "SELECT i FROM Itr i"),
    @NamedQuery(name = "Itr.findById", query = "SELECT i FROM Itr i WHERE i.idItr = :idItr"),
    @NamedQuery(name = "Itr.findByNombre", query = "SELECT i FROM Itr i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Itr.findByEliminado", query = "SELECT i FROM Itr i WHERE i.eliminado = :eliminado")})
public class Itr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ITR_IDITR_GENERATOR", sequenceName = "SEQ_ID_ITR", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITR_IDITR_GENERATOR")
    @Column(name = "ID_ITR", unique = true, nullable = false, precision = 38)
    private Long idItr;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "itr")
    private List<Evento> eventos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LOCALIDAD", nullable = false)
    @NonNull
    private Localidad localidad;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    public Evento addEvento(Evento evento) {
        getEventos().add(evento);
        evento.setItr(this);

        return evento;
    }

    public Evento removeEvento(Evento evento) {
        getEventos().remove(evento);
        evento.setItr(null);

        return evento;
    }

}
