package dk.robomenden.Robomendenapp.services;

import dk.robomenden.Robomendenapp.models.User;
import dk.robomenden.Robomendenapp.repositories.UserRepository;
import dk.robomenden.Robomendenapp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonDetailsServices implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public PersonDetailsServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByInitials(s);

        if (!user.isPresent())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(user.get());
    }
}
