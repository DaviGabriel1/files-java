package estudos.deitel.arquivosEfluxos.exercicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Basico {
    private static Scanner saida;
    private static Formatter format;
    private static Scanner entrada;

    public static void main(String[] args) {
        abrirArquivo("meu_arquivo.txt");
        //escreverArquivo();
        //lerArquivo();
        //transferirConteudo("Arquivo.txt","ArquivoCopia.txt");
        existe("Arquivo.txt");
        //System.out.println(contarLinhas("Arquivo.txt"));
        //contarPalavra("Arquivo.txt","ola");
        //substituirPalavra("Arquivo.txt","ola","mundo");
        fechar();
    }

    public static void abrirArquivo(String path){
        try{
            saida = new Scanner(Paths.get(path));
            format = new Formatter(path);
            entrada = new Scanner(System.in);
        }catch(IOException e){
            System.err.println("erro ao abrir o arquivo");
            System.exit(1);
        }
    }
    public static void lerArquivo(){
        try{
            while(saida.hasNext()){
                System.out.println(saida.nextLine());
            }
        }catch(NoSuchElementException e){
            System.err.println("tipo de entrada incompativel");
        }catch(IllegalStateException ie){
            System.err.println("erro de leitura do arquivo");
            System.exit(1);
        }
    }
    public static void escreverArquivo(){
        System.out.print("Digite o que terá no arquivo:");
        while(entrada.hasNext()) {
            try {
                format.format(entrada.nextLine());
                System.out.println("?");
            } catch (SecurityException se) {
                System.err.println("não há permissão para acessar esse arquivo");
                System.exit(1);
            }
        }
    }
    public static void transferirConteudo(String irlPartida, String irlChegada) {
        try {
            saida = new Scanner(Paths.get(irlPartida));
            format = new Formatter(irlChegada);
            StringBuffer sb = new StringBuffer();
            while(saida.hasNext()){
                sb.append(saida.nextLine());
                sb.append("\n");
            }
            format.format(sb.toString());
        }
        catch(FileNotFoundException fnf){
            System.err.println("arquivo não encontrado");
            System.exit(1);
            }
        catch(IOException e){
            System.err.println("erro ao acessar o arquivo");
            System.exit(1);
        }
    }
    public static void fechar(){
        if(format != null){
            format.close();
        }
        if(saida != null){
            saida.close();
        }
        if(entrada != null){
            entrada.close();
        }
    }
    public static void existe(String path){
        if(Files.exists(Path.of(path))){
            System.out.printf("o arquivo '%s' existe",Paths.get(path).getFileName());
        }else{
            System.out.printf("o arquivo '%s' não existe",Paths.get(path).getFileName());
        }
    }
    public static int contarLinhas(String path){
        int linhas = 0;
        try {
            saida = new Scanner(Paths.get(path));
            while (saida.hasNext()) {
                linhas++;
                saida.nextLine();
            }
        }catch(IOException e){
            System.err.println("erro ao acessar o arquivo");
            System.exit(1);
        }
        return linhas;
    }
    public static void contarPalavra(String path,String palavra){
        int linha= 1,contador=0;
        try{
            saida = new Scanner(Paths.get(path));
            while(saida.hasNext()){
                if(saida.nextLine().contains(palavra)){
                    System.out.println(linha);
                    contador++;
                }
                linha++;
            }
            if(contador == 0){
                System.out.println("não existe a palavra buscada no arquivo");
            }
        }catch(IOException e){
            System.err.println("erro ao acessar o arquivo");
        }
    }
    public static void substituirPalavra(String path,String palavra,String substituto){
        StringBuffer sb = new StringBuffer();
        try{
            saida = new Scanner(Paths.get(path));
            while(saida.hasNext()){
                String linha = saida.nextLine();
                sb.append(linha.replaceAll(palavra,substituto));
                sb.append("\n");
            }
            saida.close();
            format = new Formatter(path);
            format.format(sb.toString());
            System.out.println("substituido com sucesso!");
        }catch(IOException e){
            System.err.println("erro ao acessar o arquivo");
        }
    }



}
