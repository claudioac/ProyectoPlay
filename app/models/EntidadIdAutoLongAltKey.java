package models;

import org.hibernate.annotations.GenericGenerator;
import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class EntidadIdAutoLongAltKey extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idAutonGen")
    public Long id;

    @Column(unique = true)
    protected String altKey;

    public String getAltKey() {
        return altKey;
    }

    public void setAltKey(String altKey) {
        this.altKey = altKey;
    }

    @PrePersist
    protected void prePersist() {
        setAltKey(UUID.randomUUID().toString());
    }
}