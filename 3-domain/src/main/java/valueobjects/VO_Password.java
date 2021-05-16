package valueobjects;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Objects;


public final class VO_Password {

    private final String hashedPassword;


    public VO_Password(String password, boolean isAlreadyHashed) {

        if (isAlreadyHashed){
            this.hashedPassword = password;
            return;
        }
        if(isValid(password)) {
            this.hashedPassword = hashPassword(password);
        } else {
            throw new IllegalArgumentException("Password '" + password + "' is invalid for a password! It must have at least four letter");
        }
    }

    private String hashPassword(String password){

        try{

            return DigestUtils.sha256Hex(password);

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return "Error";

    }

    private boolean isValid(String password) {

        return password.length() > 3;
    }

    public String getHashedPassword(){
        return this.hashedPassword;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VO_Password vo_password) {
            return this.hashedPassword.equals(vo_password.getHashedPassword());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.hashedPassword);
    }


}
