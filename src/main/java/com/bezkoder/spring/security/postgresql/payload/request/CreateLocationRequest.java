package com.bezkoder.spring.security.postgresql.payload.request;

import com.neovisionaries.i18n.CountryCode;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateLocationRequest {
    private CountryCode countryCode;
    private String city;
    private String adress;
    private String region;
    private Float lattitude;
    private Float longitude;
    private Float radius;
}
