package entities;

public class FinanceIndicator {
    private final Integer indicatorValue;

    public FinanceIndicator(int indicatorValue){

        if(indicatorValue < 0 || indicatorValue > 100){
            throw new IllegalArgumentException("IndicatorValue not valid");
        }

        this.indicatorValue = indicatorValue;
    }

}
