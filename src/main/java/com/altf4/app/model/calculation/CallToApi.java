package com.altf4.app.model.calculation;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CallToApi {
    public CallToApi() {
    }
    public Double euriborRate()  {
        Double euriborRateForLoan = 0.0;
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().uri(new URI("https://api.api-ninjas.com/v1/interestrate?name=Euribor"))
                    .header("X-Api-Key", "+GFDsx7wN3KIiFEehFspgw==rS5sYuczbrFsjiHf")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response= null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();
        EuriborWrapper euriborWrapper = gson.fromJson(response.body(), EuriborWrapper.class);
        Euribor[] euribors = euriborWrapper.getNon_central_bank_rates();

        for (Euribor euribor : euribors) {
            if(euribor.getName().equals("Euribor - 6 months")) {
                euriborRateForLoan = euribor.getRate_pct();
            }
        }
        return euriborRateForLoan;
    }


}
