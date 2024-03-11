package me.sqsw.lightdigtest.utils;

import lombok.RequiredArgsConstructor;
import me.sqsw.lightdigtest.client.DadataClient;
import me.sqsw.lightdigtest.dto.PhoneNumberCleanDto;
import me.sqsw.lightdigtest.model.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PhoneUtils {
    private final DadataClient dadataClient;

    @Value("${dadata.api.key}")
    String API_KEY;
    @Value("${dadata.secret.key}")
    String SECRET_KEY;

    public PhoneNumber getCleanNumber(String phoneNumber) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Token " + API_KEY);
        headers.put("X-Secret", SECRET_KEY);

        String requestBody = "[ \"" + phoneNumber + "\" ]";

        PhoneNumberCleanDto cleanNumber = dadataClient.getCleanedPhoneNumber(headers, requestBody).get(0);
        if (!cleanNumber.getType().equals("Мобильный")) throw new IllegalArgumentException("Wrong phone number format");
        return new PhoneNumber(cleanNumber.getCountryCode(), cleanNumber.getCityCode(), cleanNumber.getNumber());
    }
}
