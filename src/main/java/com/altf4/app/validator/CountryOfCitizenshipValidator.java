package com.altf4.app.validator;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CountryOfCitizenshipValidator implements ConstraintValidator<CountryOfCitizenshipConstraint, String> {

    @SneakyThrows
    @Override
    public boolean isValid(String searchedCountry, ConstraintValidatorContext context) {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://restcountries.com/v3.1/all"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONArray countries = new JSONArray(response.body());

        for (Object obj : countries) {
            org.json.JSONObject country = (JSONObject) obj;
            String countryName = country.getJSONObject("name").getString("common");
            if (countryName.equalsIgnoreCase(searchedCountry)) {
                return true;
            }
        }

        return false;
    }
}