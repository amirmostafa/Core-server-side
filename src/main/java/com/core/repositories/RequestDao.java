package com.core.repositories;

import com.core.entites.Request;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RequestDao extends PagingAndSortingRepository<Request, Long> {

    Request findFirstById(long id);

    List<Request> findByStatusOrderByIdDesc(String status);

    List<Request> findByUserIdOrderByIdDesc(long userId);

    List<Request> findByAssignedToOrderByModificationDateDesc(long userId);

    List<Request> findByOrderByIdDesc();
}
