package entities;

public class Entitie_FinanceIndicator {
    private final Integer indicatorValue;

    public Entitie_FinanceIndicator(int indicatorValue){

        if(indicatorValue < 0 || indicatorValue > 100){
            throw new IllegalArgumentException("IndicatorValue not valid");
        }

        this.indicatorValue = indicatorValue;
    }

}
