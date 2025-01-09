package com.beymo.traffic.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Collen Serudzai",
                        email = "seruedev@gmail.com",
                        url = "https://collen.com"
                ),
                description = "OpenApi documentation for Spring Security",
                title = "OpenApi specification - Collen",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://someurl.com"
                ),
                termsOfService = " Terms of Service"
        ),
        servers = {
                @Server(
                        description = "local ENV",
                        url = "http://localhost:8088"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://some-url.com/courses"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)

@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
