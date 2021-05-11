package valueobjects;

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
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return new String(hashedPassword);

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
