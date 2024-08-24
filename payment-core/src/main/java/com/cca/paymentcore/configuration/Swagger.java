package com.cca.paymentcore.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Value("${domain-url}")
    private String domainUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(domainUrl + "api/auth"))
                .info(new Info().title("My API")
                        .version("1.0")
                        .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
    }

    @Bean
    public OpenApiCustomizer customGlobalHeaders() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation.addParametersItem(createHeaderParameter("Content-Type"))
                        .addParametersItem(createHeaderParameter("channel"))
                        .addParametersItem(createHeaderParameter("deviceId"))
                        .addParametersItem(createHeaderParameter("deviceName"))
                        .addParametersItem(createHeaderParameter("deviceType"))
                        .addParametersItem(createHeaderParameter("deviceManufacturer"))
                        .addParametersItem(createHeaderParameter("deviceMacAddress"))
                        .addParametersItem(createHeaderParameter("deviceManufacturingYear"))
                        .addParametersItem(createHeaderParameter("deviceImeiNo"))
                        .addParametersItem(createHeaderParameter("appName"))
                        .addParametersItem(createHeaderParameter("channelName"))
                        .addParametersItem(createHeaderParameter("appLanguage"))
                        .addParametersItem(createHeaderParameter("appVersion"))
                        .addParametersItem(createHeaderParameter("appVersionCode"))
                        .addParametersItem(createHeaderParameter("reqId"))
                        .addParametersItem(createHeaderParameter("Authorization"))
                        .addParametersItem(createHeaderParameter("Cookie"))
                        .addParametersItem(createHeaderParameter("userType"))
                        .addParametersItem(createHeaderParameter("sessionId"))
                        .addParametersItem(createHeaderParameter("uniqueIdentifier"))
                        .addParametersItem(createHeaderParameter("checkSum"))
                        .addParametersItem(createHeaderParameter("osModelName"))
                        .addParametersItem(createHeaderParameter("osVersion"))
                        .addParametersItem(createHeaderParameter("osName"))
                        .addParametersItem(createHeaderParameter("osProductName"))
                        .addParametersItem(createHeaderParameter("osFirmwareBuild"))
                        .addParametersItem(createHeaderParameter("subscriberId"))
                        .addParametersItem(createHeaderParameter("portalDeviceAddress"))
                        .addParametersItem(createHeaderParameter("digitalKey"))

                );

    }

    private Parameter createHeaderParameter(String name) {
        return new HeaderParameter().name(name).schema(new StringSchema());
    }
}