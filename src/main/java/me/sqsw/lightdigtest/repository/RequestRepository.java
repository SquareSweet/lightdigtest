package me.sqsw.lightdigtest.repository;

import me.sqsw.lightdigtest.model.Request;
import me.sqsw.lightdigtest.model.RequestState;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findByUserId(Long userId, PageRequest pageRequest);
    List<Request> findByUserIdInAndStateIn(List<Long> userId, List<RequestState> requestStates, PageRequest pageRequest);
    List<Request> findByStateIn(List<RequestState> requestStates, PageRequest pageRequest);
    Optional<Request> findByIdAndState(Long id, RequestState requestState);
}
