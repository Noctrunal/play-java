package validate;

import models.User;

/**
 * Validator for @Entity fields
 **/
public class EntityValidator {
    private EntityValidator() {
    }

    /**
     * Validate User not null
     **/
    public static boolean validateUserNotNull(User user) {
        return user.getName() == null || user.getLogin() == null || user.getPassword() == null || user.getRoles() == null;
    }
}
