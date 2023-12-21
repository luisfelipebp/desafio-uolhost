package com.luisfelipebp.service;

import com.luisfelipebp.config.ConfigurationRestTemplate;
import com.luisfelipebp.model.DTO.VingadorDTO;
import com.luisfelipebp.model.DTO.VingadoresDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class ConsumeJsonService {

    @Autowired
    private ConfigurationRestTemplate configurationRestTemplate;

    public List<VingadorDTO> consumeJson() throws Exception {
        RestTemplate restTemplate = configurationRestTemplate.restTemplate();

        URI uri = new URI("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json");
        VingadoresDTO response = restTemplate.getForObject(uri, VingadoresDTO.class);

        if(response != null) return response.vingadores();
        else throw new Exception("Falha ao consumir o Json");
    }
}
