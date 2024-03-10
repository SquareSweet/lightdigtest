package me.sqsw.lightdigtest.service;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.RequestCreateDto;
import me.sqsw.lightdigtest.dto.RequestFullDto;
import me.sqsw.lightdigtest.exception.RequestNotFoundException;
import me.sqsw.lightdigtest.mapper.RequestMapper;
import me.sqsw.lightdigtest.model.Request;
import me.sqsw.lightdigtest.model.User;
import me.sqsw.lightdigtest.repository.RequestRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserService userService;
    private final RequestMapper requestMapper;

    @Override
    @Transactional
    public RequestFullDto create(RequestCreateDto requestDto) {
        Request request = requestMapper.toRequest(requestDto, getUserFromContext());
        request = requestRepository.save(request);
        return requestMapper.toRequestFull(request);
    }

    @Override
    public RequestFullDto getOwnRequest(Long requestId) {
        User user = getUserFromContext();
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        if (!request.getUser().equals(user)) throw new AccessDeniedException("Access denied");
        return requestMapper.toRequestFull(request);
    }

    @Override
    public List<RequestFullDto> getUserRequests() {
        User user = getUserFromContext();
        return requestRepository.findByUserId(user.getId()).stream()
                .map(requestMapper::toRequestFull)
                .collect(Collectors.toList());
    }

    private User getUserFromContext() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userService.getUserByUsername(username); //throws exception if not found;;
    }
}
