package moletap.dossantos.diiage.org.moletapdossantos;

import java.io.Serializable;

/**
 * Created by Quentin on 15/03/2018.
 */

public class Score implements Serializable {
    private Integer NbPoint;
    private Integer NbManque;
    private Integer TpsReactionMax;
    private Integer TpsReactionMin;
    private Integer TpsReactionAvg;

    public Integer getNbPoint() {
        return NbPoint;
    }

    public void setNbPoint(Integer nbPoint) {
        NbPoint = nbPoint;
    }

    public Integer getNbManque() {
        return NbManque;
    }

    public void setNbManque(Integer nbManque) {
        NbManque = nbManque;
    }

    public Integer getTpsReactionMax() {
        return TpsReactionMax;
    }

    public void setTpsReactionMax(Integer tpsReactionMax) {
        TpsReactionMax = tpsReactionMax;
    }

    public Integer getTpsReactionMin() {
        return TpsReactionMin;
    }

    public void setTpsReactionMin(Integer tpsReactionMin) {
        TpsReactionMin = tpsReactionMin;
    }

    public Integer getTpsReactionAvg() {
        return TpsReactionAvg;
    }

    public void setTpsReactionAvg(Integer tpsReactionAvg) {
        TpsReactionAvg = tpsReactionAvg;
    }
}
