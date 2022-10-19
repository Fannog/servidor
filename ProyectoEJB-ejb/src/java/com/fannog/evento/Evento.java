package com.fannog.evento;

import com.fannog.estadosEvento.EstadosEvento;
import com.fannog.itr.Itr;
import com.fannog.justificacion.Justificacion;
import com.fannog.modalidadesEvento.ModalidadesEvento;
import com.fannog.tiposEvento.TiposEvento;
import com.fannog.tutor.Tutor;
import java.io.Serializable;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Evento")
@Table(name = "EVENTO")
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findById", query = "SELECT e FROM Evento e WHERE e.idEvento = :idEvento"),
    @NamedQuery(name = "Evento.findByFecHoraInicio", query = "SELECT e FROM Evento e WHERE e.fecHoraInicio = :fecHoraInicio"),
    @NamedQuery(name = "Evento.findByFecHoraFinal", query = "SELECT e FROM Evento e WHERE e.fecHoraFinal = :fecHoraFinal"),
    @NamedQuery(name = "Evento.findByEliminado", query = "SELECT e FROM Evento e WHERE e.eliminado = :eliminado")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "EVENTO_IDEVENTO_GENERATOR", sequenceName = "SEQ_ID_EVENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENTO_IDEVENTO_GENERATOR")
    @Column(name = "ID_EVENTO", unique = true, nullable = false, precision = 38)
    private Long idEvento;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(name = "FEC_HORA_FINAL", nullable = false)
    @NonNull
    private LocalDateTime fecHoraFinal;

    @Column(name = "FEC_HORA_INICIO", nullable = false)
    @NonNull
    private LocalDateTime fecHoraInicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO", nullable = false)
    @NonNull
    private EstadosEvento estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ITR", nullable = false)
    private Itr itr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MODALIDAD", nullable = false)
    @NonNull
    private ModalidadesEvento modalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO", nullable = false)
    @NonNull
    private TiposEvento tipo;

    @OneToMany(mappedBy = "evento")
    private List<Justificacion> justificacions;

    @ManyToMany(mappedBy = "eventos")
    private List<Tutor> tutors;

    public Justificacion addJustificacion(Justificacion justificacion) {
        getJustificacions().add(justificacion);
        justificacion.setEvento(this);

        return justificacion;
    }

    public Justificacion removeJustificacion(Justificacion justificacion) {
        getJustificacions().remove(justificacion);
        justificacion.setEvento(null);

        return justificacion;
    }

}
