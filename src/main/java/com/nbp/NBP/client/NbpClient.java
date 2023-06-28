package com.nbp.NBP.client;

import com.nbp.NBP.dto.NbpDto;
import com.nbp.NBP.exception.RatesWithGivenDateDoesntExist;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class NbpClient {

    private static final String NBP_URL = "http://api.nbp.pl/api/exchangerates/rates/c/usd/";
    private final RestTemplate restTemplate = new RestTemplate();

    public NbpDto getDollarRate(String date) throws RatesWithGivenDateDoesntExist {
        try {
            return restTemplate.getForObject(NBP_URL + date + "/?format=json", NbpDto.class);
        } catch (Exception e) {
            throw new RatesWithGivenDateDoesntExist();
        }
    }

    public String getUrl(String date) throws RatesWithGivenDateDoesntExist {
        try {
            return restTemplate.getForObject(NBP_URL + date + "/?format=json", String.class);
        } catch (Exception e) {
            throw new RatesWithGivenDateDoesntExist();
        }
    }
}
