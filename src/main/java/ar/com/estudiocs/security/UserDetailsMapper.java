package ar.com.estudiocs.security;

import ar.com.estudiocs.entities.Usuarios;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsMapper {

    public static UserDetails build(Usuarios user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private static Set<? extends GrantedAuthority> getAuthorities(Usuarios retrievedUser) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        return authorities;
    }
}
