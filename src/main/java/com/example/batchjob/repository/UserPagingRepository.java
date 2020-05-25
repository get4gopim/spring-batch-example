package com.example.batchjob.repository;

import com.example.batchjob.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserPagingRepository extends PagingAndSortingRepository<User, Integer> {

}
