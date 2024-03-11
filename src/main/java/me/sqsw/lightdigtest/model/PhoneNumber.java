package me.sqsw.lightdigtest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PhoneNumber {
    @Column(name = "phone_country")
    String phoneCountry;
    @Column(name = "phone_city")
    String phoneCity;
    @Column(name = "phone_number")
    String phoneNumber;

    @Override
    public String toString() {
        return String.format("%s %s %s", phoneCountry, phoneCity, phoneNumber);
    }
}
