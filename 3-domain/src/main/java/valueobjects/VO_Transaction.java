package valueobjects;

import java.util.Date;

public final class VO_Transaction {
    private final double value;
    private final String description;
    private final Date date;

    public VO_Transaction(double value, String description,Date date){

        this.value = value;
        this.description = description;
        this.date = date;
    }

}
