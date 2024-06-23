package br.com.w4solution.main.servicos;

import br.com.w4solution.main.modelos.Cep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SalvarFormatoJson {

    public void salvarCep(Cep cep) throws IOException {

        BufferedWriter arquivoCepPesquisados = new BufferedWriter(new FileWriter("CepsPesquisados.txt", true));
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        var jsonCep = gson.toJson(cep);
        arquivoCepPesquisados.append("\n"+jsonCep);
        arquivoCepPesquisados.close();
    }
}
