package entidades;

import java.io.Serializable;
import javax.persistence.Transient;

public abstract class Entidade implements Serializable {

    public abstract Serializable getId();
    @Transient
    public boolean flagRemover;

    public Boolean getFlagRemover() {
        return flagRemover;
    }

    public void setFlagRemover(Boolean flagRemover) {
        this.flagRemover = flagRemover;
    }
}
