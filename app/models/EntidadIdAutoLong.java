package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * @author Claudo Acuña
 */
@MappedSuperclass
public abstract class EntidadIdAutoLong extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idAutonGen")
    @Column(unique = true)
    public Long id;

}
