//package com.core.services;
//
//import com.core.entites.Role;
//import com.core.entites.User;
//import com.core.repositories.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) {
//        User user = userDao.findByUsername(username);
//        if (user == null) throw new UsernameNotFoundException(username);
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//    }
//}
