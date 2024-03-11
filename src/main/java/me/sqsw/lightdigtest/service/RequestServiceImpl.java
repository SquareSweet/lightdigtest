package me.sqsw.lightdigtest.service;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.dto.RequestCreateDto;
import me.sqsw.lightdigtest.dto.RequestFullDto;
import me.sqsw.lightdigtest.dto.RequestShortDto;
import me.sqsw.lightdigtest.exception.RequestNotFoundException;
import me.sqsw.lightdigtest.mapper.RequestMapper;
import me.sqsw.lightdigtest.model.Request;
import me.sqsw.lightdigtest.model.RequestState;
import me.sqsw.lightdigtest.model.User;
import me.sqsw.lightdigtest.repository.RequestRepository;
import me.sqsw.lightdigtest.utils.PhoneUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserService userService;
    private final RequestMapper requestMapper;
    private final PhoneUtils phoneUtils;

    @Override
    @Transactional
    public RequestFullDto create(RequestCreateDto requestDto) {

        Request request = requestMapper.toRequest(requestDto, getUserFromContext(),
                phoneUtils.getCleanNumber(requestDto.getPhoneNumber()));
        request = requestRepository.save(request);
        return requestMapper.toRequestFull(request);
    }

    @Override
    public List<RequestShortDto> geAllRequests(String username, List<RequestState> states, Integer page, String sort) {
        Sort sortValue;
        if (sort == null) {
            sortValue = Sort.unsorted();
        } else if (sort.equals("old")) {
            sortValue = Sort.by("sentOn").ascending();
        } else if (sort.equals("new")) {
            sortValue = Sort.by("sentOn").descending();
        } else {
            throw new IllegalArgumentException("Unknown sort value: " + sort);
        }
        if (username != null) {
            List<Long> userIds = userService.getUsersByPartialUsername(username).stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            return requestRepository.findByUserIdInAndStateIn(userIds, states,
                            PageRequest.of(page, 5, sortValue)).stream()
                    .map(requestMapper::requestShort)
                    .collect(Collectors.toList());
        } else {
            return requestRepository.findByStateIn(states, PageRequest.of(page, 5, sortValue)).stream()
                    .map(requestMapper::requestShort)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public RequestFullDto getOwnRequest(Long requestId) {
        User user = getUserFromContext();
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        if (!request.getUser().equals(user) || request.getState() == RequestState.SENT) {
            throw new AccessDeniedException("Access denied");
        }
        return requestMapper.toRequestFull(request);
    }

    @Override
    public List<RequestShortDto> getUserOwnRequests(Integer page, String sort) {
        User user = getUserFromContext();
        Sort sortValue;
        if (sort == null) {
            sortValue = Sort.unsorted();
        } else if (sort.equals("old")) {
            sortValue = Sort.by("createdOn").ascending();
        } else if (sort.equals("new")) {
            sortValue = Sort.by("createdOn").descending();
        } else {
            throw new IllegalArgumentException("Unknown sort value: " + sort);
        }
        return requestRepository.findByUserId(user.getId(), PageRequest.of(page, 5, sortValue)).stream()
                .map(requestMapper::requestShort)
                .collect(Collectors.toList());
    }

    @Override
    public RequestFullDto getRequest(Long requestId) {
        Request request = requestRepository.findByIdAndState(requestId, RequestState.SENT)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        return requestMapper.toRequestFull(request);
    }

    @Override
    @Transactional
    public RequestFullDto edit(RequestCreateDto requestDto, Long requestId) {
        User user = getUserFromContext();
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        if (!request.getUser().equals(user) || request.getState() != RequestState.DRAFT) {
            throw new AccessDeniedException("Access denied");
        }
        if (requestDto.getTitle() != null && !requestDto.getTitle().isBlank()) {
            request.setTitle(requestDto.getTitle());
        }
        if (requestDto.getText() != null && !requestDto.getText().isBlank()) {
            request.setText(requestDto.getText());
        }
        if (requestDto.getPhoneNumber() != null && !requestDto.getPhoneNumber().isBlank()) {
            request.setPhoneNumber(phoneUtils.getCleanNumber(requestDto.getPhoneNumber()));
        }
        request = requestRepository.save(request);
        return requestMapper.toRequestFull(request);
    }

    @Override
    @Transactional
    public RequestFullDto send(Long requestId) {
        User user = getUserFromContext();
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        if (!request.getUser().equals(user) || request.getState() != RequestState.DRAFT) {
            throw new AccessDeniedException("Access denied");
        }
        request.setState(RequestState.SENT);
        request.setSentOn(LocalDateTime.now());
        request = requestRepository.save(request);
        return requestMapper.toRequestFull(request);
    }

    @Override
    @Transactional
    public RequestFullDto accept(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        if (request.getState() != RequestState.SENT) {
            throw new AccessDeniedException("Access denied");
        }
        request.setState(RequestState.ACCEPTED);
        request = requestRepository.save(request);
        return requestMapper.toRequestFull(request);
    }

    @Override
    @Transactional
    public RequestFullDto deny(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException(requestId));
        if (request.getState() != RequestState.SENT) {
            throw new AccessDeniedException("Access denied");
        }
        request.setState(RequestState.DENIED);
        request = requestRepository.save(request);
        return requestMapper.toRequestFull(request);
    }

    private User getUserFromContext() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userService.getUserByUsername(username); //throws exception if not found;;
    }
}
