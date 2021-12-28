package me.dio.web.challenge.digital.banking.system.amdocs.connector;

import com.google.gson.Gson;
import me.dio.web.challenge.digital.banking.system.amdocs.entities.HGBrasilQuotation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HGBrasilConnector {
    static int successCode = 200;

    public static double findQuotations(String urlToCall) throws Exception {

        try {
            URL url = new URL(urlToCall);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() != successCode)
                throw new RuntimeException("HTTP error code : " + connection.getResponseCode());

            BufferedReader response = new BufferedReader(new InputStreamReader((connection.getInputStream())));

            String resp, jsonResponse = "";

            while ((resp = response.readLine())!=null){
                jsonResponse += resp;
            }

            Gson json = new Gson();

            HGBrasilQuotation hgBrasilQuotation = json.fromJson(jsonResponse, HGBrasilQuotation.class);

            return hgBrasilQuotation.results.currencies.buy;

        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
}
