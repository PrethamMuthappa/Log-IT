package org.example.logit.RestTemplate;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mykeys")
public record Keys(String apikey) {
}
