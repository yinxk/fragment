package fragment.framework.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}", username);
        String pass = passwordEncoder.encode("123");
        log.info("pa:{}", pass);
        return new User(username, "$2a$10$vvvyq/qdOctSaPEEq47TWOO86BfRbO34FY7xVvy6WAqP0TFYx27ta", true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        "admin"));

    }

}
