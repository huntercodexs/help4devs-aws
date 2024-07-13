package com.huntercodexs.demo.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class Help4DevsAwsSdkS3Client {

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpRequestHeaders(String authorization, String file) {
        HttpHeaders httpHeaders = new HttpHeaders();

        switch (file) {
            case "file" -> httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            case "json" -> httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            case "text" -> httpHeaders.setContentType(MediaType.TEXT_PLAIN);
            case "html" -> httpHeaders.setContentType(MediaType.TEXT_HTML);
        }

        if (!authorization.isEmpty()) {
            httpHeaders.set("Authorization", authorization);
        }

        return httpHeaders;
    }

    private String urlFix(String url) {
        return url.replaceAll("%2F", "/");
    }

    private HttpComponentsClientHttpRequestFactory httpClientFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    public ResponseEntity<String> upload(String url, byte[] file) {
        HttpEntity<?> httpEntity = new HttpEntity<>(file, httpRequestHeaders("", "file"));
        return restTemplate.exchange(urlFix(url), HttpMethod.PUT, httpEntity, String.class);
    }

    public ResponseEntity<String> download(String s3Url) {
        HttpEntity<?> httpEntity = new HttpEntity<>(httpRequestHeaders("", ""));
        return restTemplate.exchange(urlFix(s3Url), HttpMethod.GET, httpEntity, String.class);
    }

    public ResponseEntity<String> delete(String s3Url) {
        HttpEntity<?> httpEntity = new HttpEntity<>(httpRequestHeaders("", "text"));
        return restTemplate.exchange(urlFix(s3Url), HttpMethod.DELETE, httpEntity, String.class);
    }

}
