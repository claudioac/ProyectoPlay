package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
public abstract class EntidadIdAutoLongAltKey extends Model {

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