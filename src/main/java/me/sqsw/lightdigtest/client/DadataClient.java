package me.sqsw.lightdigtest.client;

import me.sqsw.lightdigtest.dto.PhoneNumberCleanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@FeignClient(name = "dadata-service", url = "https://cleaner.dadata.ru", path = "/api/v1/clean/phone")
public interface DadataClient {

    @PostMapping()
    List<PhoneNumberCleanDto> getCleanedPhoneNumber(@RequestHeader Map<String, String> headers, @RequestBody String phoneNumber);
}
