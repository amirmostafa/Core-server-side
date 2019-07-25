package com.core.repositories;

import com.core.entites.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findFirstById(long id);

    List<User> findByActive(Boolean active);

    List<User> findByActiveTrueAndType(String userType);
}
