package com.fannog.entities;

import com.fannog.entities.Justificacion;
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
@Entity(name = "EstadoJustificacion")
@Table(name = "ESTADO_JUSTIFICACION")
@NamedQueries({
    @NamedQuery(name = "EstadoJustificacion.findAll", query = "SELECT e FROM EstadoJustificacion e"),
    @NamedQuery(name = "EstadoJustificacion.findByNombre", query = "SELECT e FROM EstadoJustificacion e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoJustificacion.findByEliminado", query = "SELECT e FROM EstadoJustificacion e WHERE e.eliminado = :eliminado")})
public class EstadoJustificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTADO_JUSTIFICACION_IDESTADOJUSTIFICACION_GENERATOR", sequenceName = "SEQ_ID_ESTADO_JUSTIFICACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_JUSTIFICACION_IDESTADOJUSTIFICACION_GENERATOR")
    @Column(name = "ID_ESTADO_JUSTIFICACION", unique = true, nullable = false, precision = 38)
    private Long id;

    @Column(nullable = false, precision = 1, columnDefinition = "NUMBER(1, 0) DEFAULT 0")
    private boolean eliminado;

    @Column(nullable = false, length = 25)
    @NonNull
    private String nombre;

    @OneToMany(mappedBy = "estadosJustificacion")
    private List<Justificacion> justificacions;

    public Justificacion addJustificacion(Justificacion justificacion) {
        getJustificacions().add(justificacion);
        justificacion.setEstadosJustificacion(this);

        return justificacion;
    }

    public Justificacion removeJustificacion(Justificacion justificacion) {
        getJustificacions().remove(justificacion);
        justificacion.setEstadosJustificacion(null);

        return justificacion;
    }

}
