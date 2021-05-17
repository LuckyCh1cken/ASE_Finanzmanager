package valueobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VO_SpendingType {

    private final String spendingType;

    public static final List<String> allowedTypes = Arrays.asList("LEBENSMITTEL", "MOBILITÄT", "SPAREN", "WOHNEN", "EINNAHMEN", "GESUNDHEIT", "SONSTIGES");


    public VO_SpendingType(String spendingType) {
        if(isValid(spendingType)){
            this.spendingType = spendingType;
        }
        else {
            throw new IllegalArgumentException("Not a spendingType: " + spendingType);
        }
    }

    private boolean isValid(String spendingType) {

        return allowedTypes.contains(spendingType);
    }

    public String getSpendingType() {
        return this.spendingType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VO_SpendingType vo_spendingType) {
            return this.spendingType.equals(vo_spendingType.getSpendingType());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.spendingType);
    }

}


