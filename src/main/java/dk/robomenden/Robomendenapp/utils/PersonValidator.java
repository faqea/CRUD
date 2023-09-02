package dk.robomenden.Robomendenapp.utils;

import dk.robomenden.Robomendenapp.models.User;
import dk.robomenden.Robomendenapp.services.PersonDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsServices personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsServices personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        try {
            personDetailsService.loadUserByUsername(user.getInitials());
        } catch (UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("username", "", "Человек с таким именем пользователя уже существует");

    }
}
