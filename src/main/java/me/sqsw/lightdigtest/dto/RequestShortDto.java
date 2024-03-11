package me.sqsw.lightdigtest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.sqsw.lightdigtest.model.RequestState;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RequestShortDto {
    private Long id;
    private String username;
    private String title;
    private RequestState state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sentOn;
}
