package com.fannog.estadosJustificacion;

import com.fannog.justificacion.Justificacion;
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
@Entity(name = "EstadosJustificacion")
@Table(name = "ESTADOS_JUSTIFICACION")
@NamedQueries({
    @NamedQuery(name = "EstadosJustificacion.findAll", query = "SELECT e FROM EstadosJustificacion e"),
    @NamedQuery(name = "EstadosJustificacion.findById", query = "SELECT e FROM EstadosJustificacion e WHERE e.idEstadosJustificacion = :idEstadosJustificacion"),
    @NamedQuery(name = "EstadosJustificacion.findByNombre", query = "SELECT e FROM EstadosJustificacion e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadosJustificacion.findByEliminado", query = "SELECT e FROM EstadosJustificacion e WHERE e.eliminado = :eliminado")})
public class EstadosJustificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ESTADOS_JUSTIFICACION_IDESTADOSJUSTIFICACION_GENERATOR", sequenceName = "SEQ_ID_ESTADOS_JUSTIFICACION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADOS_JUSTIFICACION_IDESTADOSJUSTIFICACION_GENERATOR")
    @Column(name = "ID_ESTADOS_JUSTIFICACION", unique = true, nullable = false, precision = 38)
    private Long idEstadosJustificacion;

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
