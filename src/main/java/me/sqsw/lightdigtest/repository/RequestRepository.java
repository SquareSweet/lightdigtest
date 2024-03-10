package me.sqsw.lightdigtest.repository;

import me.sqsw.lightdigtest.model.Request;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findByUserId(Long userId, PageRequest pageRequest);
}
