package com.brenno.entrega.buscarLocalizacao.client;

import com.brenno.entrega.buscarLocalizacao.dto.CoordenadaDTO;
import com.brenno.entrega.buscarLocalizacao.dto.GoogleLocation;
import com.brenno.entrega.buscarLocalizacao.dto.GoogleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GoogleGeocodingClient {

    @Value("${google.maps.api-key}")
    private String apiKey;//Colocar a key correta

    private final RestClient restClient;

    public GoogleGeocodingClient(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public CoordenadaDTO buscarCoordenadas(String endereco) {

        GoogleResponse response =
                restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .scheme("https")
                                .host("maps.googleapis.com")
                                .path("/maps/api/geocode/json")
                                .queryParam("address", endereco)
                                .queryParam("key", apiKey)
                                .build())
                        .retrieve()
                        .body(GoogleResponse.class);
        GoogleLocation location = response.getResults().getFirst().getGeometry().getLocation();
        return new CoordenadaDTO(location.getLat(), location.getLng());
    }
}
