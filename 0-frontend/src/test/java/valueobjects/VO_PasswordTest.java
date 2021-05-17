package valueobjects;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VO_PasswordTest {

    @Test
    void testHashPassword() {
        VO_Password password = new VO_Password("John", false);
        Assert.assertNotEquals("John", password.getHashedPassword());

    }

    @Test
    void testIsValid() {

        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    VO_Password password = new VO_Password("Jo", false);
                }
        );
        assertEquals("Password 'Jo' is invalid for a password! It must have at least four letter",
                exception.getMessage());
    }


}