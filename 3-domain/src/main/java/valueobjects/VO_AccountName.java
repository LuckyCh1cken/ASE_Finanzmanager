package valueobjects;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Objects;

public class VO_AccountName {

    private final String name;


    public VO_AccountName(String name) {

        if(isValid(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name '" + name + "' is invalid for a Name! It must have at least four letter");
        }
    }


    private boolean isValid(String name) {

        return name.length() > 3;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VO_AccountName vo_accountName) {
            return this.name.equals(vo_accountName.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
