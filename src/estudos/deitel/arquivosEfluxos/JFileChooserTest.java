package estudos.deitel.arquivosEfluxos;

import javax.swing.*;
import java.io.IOException;

public class JFileChooserTest {

    public static void main(String[] args) throws IOException {
        JFileChooserDemo aplication = new JFileChooserDemo();
        aplication.setSize(400,400);
        aplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplication.setVisible(true);
    }
}
