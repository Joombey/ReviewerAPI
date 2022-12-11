package com.example.reviewerapi.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "ReviwerAPI",
        description = "API построенная в рамках работы над 5 практической работой по дисциплине Встраиваемые Системы Управления Базами Данных",
        contact = @Contact(name = "Фарух Рахматуллаев", url = "https://vk.com/farushok", email = "f-rakhm@yandex.ru"),
        version = "0.0.0.0.0.0.0.0.123123190283701982730918273098261391"
))
public class OpenAPIConfig {}
