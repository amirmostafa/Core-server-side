package com.core.repositories;

import com.core.entites.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {
}
