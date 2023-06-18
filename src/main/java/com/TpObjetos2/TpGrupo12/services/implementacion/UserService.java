package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.repositories.IUserRepository;
import com.TpObjetos2.TpGrupo12.entities.UserRoles;

@Service("userService")
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("userRepository") IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.TpObjetos2.TpGrupo12.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
        return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
    }

    private User buildUser(com.TpObjetos2.TpGrupo12.entities.User user, List<GrantedAuthority> grantedAuthorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
                true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
                grantedAuthorities);
    }

    private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRoles> userRoles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserRoles userRole : userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<>(grantedAuthorities);
    }
}