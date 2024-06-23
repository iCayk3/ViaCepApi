package br.com.w4solution.main.servicos;

import br.com.w4solution.main.exceptions.CepNaoExisteException;
import br.com.w4solution.main.modelos.Cep;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepAPI {

    private static String uri = "https://viacep.com.br/ws/%s/json/";

    public static String getUri() { return uri; }

    public static final Cep consultaCep(String cep) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =HttpRequest.newBuilder()
                .uri(URI.create(getUri().formatted(cep)))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        if(response.statusCode() != 200){
            System.out.println("Requisicao invalida");
            System.out.println("Erro codigo: " + response.statusCode());
            return null;
        } else {
            var json = response.body();
            if(json.contains("\"erro\": \"true\"")){
                throw new CepNaoExisteException("Cep nao existe!");
            }
            return gson.fromJson(json, Cep.class);
        }
    }
}