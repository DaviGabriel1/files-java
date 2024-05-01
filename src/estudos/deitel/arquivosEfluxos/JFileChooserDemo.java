package estudos.deitel.arquivosEfluxos;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JFileChooserDemo extends JFrame
{
    private final JTextArea outputArea; // exibe o conteúdo do arquivo

    // configura a GUI
    public JFileChooserDemo() throws IOException
    {
        super("JFileChooser Demo");
        outputArea = new JTextArea();
        add(new JScrollPane(outputArea)); // outputArea é rolável
        analyzePath(); // obtém o Path do usuário e exibe informações
    }

    // exibe informações sobre o arquivo ou diretório que o usuário especifica
    public void analyzePath() throws IOException
    {
        // obtém o Path para o arquivo ou diretório selecionado pelo usuário
        Path path = getFileOrDirectoryPath();

        if (path != null && Files.exists(path)) // se existir, exibe as informações
        {
            // coleta as informações sobre o arquivo (ou diretório)
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("%s:%n", path.getFileName()));
            builder.append(String.format("%s a directory%n",
                    Files.isDirectory(path) ? "Is" : "Is not"));
            builder.append(String.format("%s an absolute path%n",
                    path.isAbsolute() ? "Is" : "Is not"));
            builder.append(String.format("Last modified: %s%n",
                    Files.getLastModifiedTime(path)));
            builder.append(String.format("Size: %s%n", Files.size(path)));
            builder.append(String.format("Path: %s%n", path));
            builder.append(String.format("Absolute path: %s%n",
                    path.toAbsolutePath()));

            if (Files.isDirectory(path)) // listagem de diretório de saída
            {
                builder.append(String.format("%nDirectory contents:%n"));

                // objeto para iteração pelo conteúdo de um diretório
                DirectoryStream<Path> directoryStream =
                        Files.newDirectoryStream(path);
                for (Path p : directoryStream)
                    builder.append(String.format("%s%n", p));
            }

            outputArea.setText(builder.toString()); // exibe o conteúdo de String
        }
        else // Path não existe
        {
            JOptionPane.showMessageDialog(this, path.getFileName() +
                    " does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    } // fim do método analyzePath

    // permite que o usuário especifique o nome de arquivo ou diretório
    private Path getFileOrDirectoryPath()
    {
        // configura o diálogo permitindo a seleção de um arquivo ou diretórioÿ
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(
                JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);

        // se o usuário clicou no botão Cancel no diálogo, retorna
        if (result == JFileChooser.CANCEL_OPTION)
            System.exit(1);

        // retorna o Path representando o arquivo selecionado
        return fileChooser.getSelectedFile().toPath();
    }
} // fim da classe JFileChooserDemo