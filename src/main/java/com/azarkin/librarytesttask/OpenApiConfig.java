package com.azarkin.librarytesttask;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Library Api",
                description = "Test task", version = "1.0",
                contact = @Contact(
                        name = "Azarkin Vitaliy",
                        email = "v.azarkin@gmail.com",
                        url = "https://github.com/Azark1n"
                )
        )
)
public class OpenApiConfig {
}
