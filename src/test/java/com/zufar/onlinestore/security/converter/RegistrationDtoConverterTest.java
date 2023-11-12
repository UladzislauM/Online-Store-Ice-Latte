package com.zufar.onlinestore.security.converter;

import com.zufar.onlinestore.security.dto.UserRegistrationRequest;
import com.zufar.onlinestore.openapi.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {RegistrationDtoConverterTest.Config.class})
class RegistrationDtoConverterTest {

    @Configuration
    public static class Config {

        @Bean
        public RegistrationDtoConverter registrationDtoConverter() {
            return Mappers.getMapper(RegistrationDtoConverter.class);
        }
    }

    @Autowired
    RegistrationDtoConverter registrationDtoConverter;

    @Test
    @DisplayName("ToDto should convert UserRegistrationRequest to UserDto with accurate data")
    void shouldConvertUserRegistrationRequestToUserDtoWithAccurateData() {
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest(
                "John", "Doe", "john.doe@example.com", "password123"
        );

        UserDto userDto = registrationDtoConverter.toDto(userRegistrationRequest);

        assertEquals(userRegistrationRequest.firstName(), userDto.getFirstName());
        assertEquals(userRegistrationRequest.lastName(), userDto.getLastName());
        assertEquals(userRegistrationRequest.email(), userDto.getEmail());
        assertEquals(userRegistrationRequest.password(), userDto.getPassword());
    }
}

