package br.com.w4solution.main;

import br.com.w4solution.main.modelos.Cep;
import br.com.w4solution.main.servicos.SalvarFormatoJson;
import br.com.w4solution.main.servicos.ViaCepAPI;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String sair = "";
        var sc = new Scanner(System.in);

        while (!sair.equals("sair")){
            System.out.println("Digite \"sair\" para sair");
            System.out.print("Informe o CEP que deseja pesquisar: ");
            var cep = sc.nextLine();
            if(cep.equals("sair")){
                sair = cep;
                break;
            }

            System.out.println(ViaCepAPI.consultaCep(cep));
            if(ViaCepAPI.consultaCep(cep) != null){
                System.out.println("Salvar CEP? (Sim ou Nao)");
                var salvarResposta = sc.nextLine().toLowerCase();
                if(salvarResposta.equals("sim")){
                    System.out.println("passou pelo sim");
                    new SalvarFormatoJson().salvarCep(ViaCepAPI.consultaCep(cep));
                }
            }
        }

        System.out.println("Ate a proxima!! =D");


    }
}
