package me.sqsw.lightdigtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberCleanDto {
    @JsonProperty("country_code")
    String countryCode;
    @JsonProperty("city_code")
    String cityCode;
    String number;
    String type;
}
