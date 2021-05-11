package valueobjects;

import java.util.Date;
import java.util.Objects;

public final class VO_Transaction {
    private final double value;
    private final VO_SpendingType spendingType;
    private final Date date;

    public double getValue() {
        return value;
    }

    public VO_SpendingType getSpendingType() {
        return spendingType;
    }

    public Date getDate() {
        return date;
    }

    public VO_Transaction(double value, VO_SpendingType spendingType, Date date){

        this.value = value;
        this.spendingType = spendingType;
        this.date = date;
    }


    //Do is valid funkt

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VO_Transaction vo_transaction) {
            return (this.value == vo_transaction.value && this.spendingType.equals(vo_transaction.spendingType) && this.date.equals(vo_transaction.date));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value + this.spendingType.hashCode() + this.date.toString());
    }

}
