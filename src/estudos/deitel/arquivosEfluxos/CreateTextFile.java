package estudos.deitel.arquivosEfluxos;

// Figura 15.3: CreateTextFile.java
// Gravando dados em um arquivo de texto sequencial com a classe Formatter.
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class CreateTextFile {
    private static Formatter output; // envia uma saída de texto para um arquivo

    public static void main(String[] args) {
        openFile();
        addRecords();
        closeFile();
    }

    // abre o arquivo clients.txt
    public static void openFile()
    {
        try
        {
            output = new Formatter("clients.txt"); // abre o arquivo ou cria (quando não se coloca o caminho absoluto, é considerado q é o mesmo caminho padrao)
        }
        catch (SecurityException securityException)
        {
            System.err.println("Write permission denied. Terminating.");
            System.exit(1); // termina o programa System.exit com argumento 1 significa que terminou de forma má-sucedida, diferente do 0
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.err.println("Error opening file. Terminating.");
            System.exit(1); // termina o programa
        }
    }

    // adiciona registros ao arquivo
    public static void addRecords()
    {
        Scanner input = new Scanner(System.in);
        System.out.printf("%s%n%s%n? ",
                "Enter account number, first name, last name and balance.",
                "Enter end-of-file indicator to end input.");

        while (input.hasNext()) // faz um loop até o indicador de fim de arquivo / como finalizar? <Enter> <Ctrl> d
        {  try
        {
            // gera saída do novo registro para o arquivo; supõe entrada válida
            output.format("%d %s %s %.2f%n", input.nextInt(), //número de conta, o nome e o saldo do cliente (Exemplo:  100 Bob Blue 24.98)
                    input.next(), input.next(), input.nextDouble()); //format() parecido com o printf, mas da classe Formatter para enviar esses dados para o arquivo
        }
        catch (FormatterClosedException formatterClosedException)
        {
            System.err.println("Error writing to file. Terminating.");
            break;
        }
        catch (NoSuchElementException elementException) // exceção do Scanner quando um valor de um tipo não esperado é inserido
        {
            System.err.println("Invalid input. Please try again.");
            input.nextLine(); // descarta entrada para o usuário tentar de novo
        }

            System.out.print("? ");
        } // fim do while
    } // fim do método addRecords

    // fecha o arquivo
    public static void closeFile()
    {
        if (output != null)
            output.close();
    }
} // fim da classe CreateTextFil
