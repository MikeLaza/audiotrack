package audiotrack.bean;

import java.io.Serializable;

public interface Entity<PK>  extends Serializable {

    PK getId();
}
