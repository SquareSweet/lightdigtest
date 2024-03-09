package me.sqsw.lightdigtest.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ApiError {
    String message;
    String reason;
    HttpStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp;
}
