package com.altf4.app.model.monthlypayments;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiNinjas {
    public ApiNinjas() {
    }
    public double getEuriborRateFor6Months() throws ApiErrorException {
        double euriborRateForLoan = 0.0;
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().uri(new URI("https://api.api-ninjas.com/v1/interestrate?name=Euribor"))
                    .header("X-Api-Key", "+GFDsx7wN3KIiFEehFspgw==rS5sYuczbrFsjiHf")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new ApiErrorException();
        }

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response= null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ApiErrorException();
        }

        Gson gson = new Gson();
        EuriborWrapper euriborWrapper = gson.fromJson(response.body(), EuriborWrapper.class);
        Euribor[] euribors = euriborWrapper.getNon_central_bank_rates();

        for (Euribor euribor : euribors) {
            if(euribor.getName().equals("Euribor - 6 months")) {
                euriborRateForLoan = euribor.getRate_pct();
            }
        }
        return euriborRateForLoan/100.0;
    }


}
