package com.brenno.entrega.user.endereco.buscarLocalizacao.service;

import com.brenno.entrega.user.endereco.buscarLocalizacao.client.GoogleGeocodingClient;
import com.brenno.entrega.user.endereco.buscarLocalizacao.dto.CoordenadaDTO;
import com.brenno.entrega.user.endereco.dto.EnderecoRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class BuscarLocalizacaoService {

    private final GoogleGeocodingClient client;

    public BuscarLocalizacaoService(GoogleGeocodingClient client) {
        this.client = client;
    }

    public EnderecoRequestDTO buscarLocalizacao(
            EnderecoRequestDTO endereco) {
        CoordenadaDTO coordenada = client.buscarCoordenadas(endereco.toString());
        endereco.setLatitude(coordenada.latitude());
        endereco.setLongitude(coordenada.longitude());
        return endereco;
    }
}
