package com.fannog.modalidadesEvento;

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
@Entity(name = "ModalidadesEvento")
@Table(name = "MODALIDADES_EVENTO")
@NamedQueries({
    @NamedQuery(name = "ModalidadesEvento.findAll", query = "SELECT m FROM ModalidadesEvento m"),
    @NamedQuery(name = "ModalidadesEvento.findById", query = "SELECT m FROM ModalidadesEvento m WHERE m.idModalidadesEvento = :idModalidadesEvento"),
    @NamedQuery(name = "ModalidadesEvento.findByNombre", query = "SELECT m FROM ModalidadesEvento m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "ModalidadesEvento.findByEliminado", query = "SELECT m FROM ModalidadesEvento m WHERE m.eliminado = :eliminado")})
public class ModalidadesEvento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "MODALIDADES_EVENTO_IDMODALIDADESEVENTO_GENERATOR", sequenceName = "SEQ_ID_MODALIDADES_EVENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODALIDADES_EVENTO_IDMODALIDADESEVENTO_GENERATOR")
    @Column(name = "ID_MODALIDADES_EVENTO", unique = true, nullable = false, precision = 38)
    private Long idModalidadesEvento;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "modalidadesEvento")
    private List<Evento> eventos;

    public Evento addEvento(Evento evento) {
        getEventos().add(evento);
        evento.setModalidadesEvento(this);

        return evento;
    }

    public Evento removeEvento(Evento evento) {
        getEventos().remove(evento);
        evento.setModalidadesEvento(null);

        return evento;
    }

}
