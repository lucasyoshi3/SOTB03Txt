package view;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import controll.CTRL;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um nome de feriado:");
        String nomeFeriado = scanner.nextLine();
        String os=System.getProperty("os.name");
        

        try {
            URL url = new URL("https://date.nager.at/api/v2/publicholidays/2020/US");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String linha;
            StringBuilder dados = new StringBuilder();
            while ((linha = reader.readLine()) != null) {
                dados.append(linha).append("\n");
            }
            reader.close();
            
            String caminho;
            if(os.equals("Linux")) {
            	caminho="/tmp/hol.json";
            }else {
            	caminho = "C:\\TEMP\\hol.json";
            }
            BufferedWriter escrever = new BufferedWriter(new FileWriter(caminho));
            escrever.write(dados.toString());
            escrever.close();
            
            CTRL control = new CTRL();
            String feriado = control.acharFeriado(nomeFeriado, caminho);
            System.out.println("Data do feriado '" + nomeFeriado + "': " + feriado);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}