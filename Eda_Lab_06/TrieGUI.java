import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrieGUI extends JFrame {
    private Trie trie;
    private JTextField textField;
    private JTextArea textArea;
    private JButton insertButton;
    private JButton searchButton;
    private JButton replaceButton;

    public TrieGUI() {
        // Configuración de la ventana
        setTitle("Trie GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400); // Tamaño inicial de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Inicializar Trie
        trie = new Trie();

        // Inicializar componentes
        textField = new JTextField();
        textArea = new JTextArea();
        textArea.setEditable(false); // Bloquear el cuadro de texto para no poder editarlo manualmente
        insertButton = new JButton("Insertar");
        searchButton = new JButton("Buscar");
        replaceButton = new JButton("Reemplazar");

        // Agregar componentes a la ventana
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(textField, BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(insertButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(replaceButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Agregar oyentes de eventos
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertWord();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord();
            }
        });

        replaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replaceWord();
            }
        });
    }

    private void insertWord() {
        String word = textField.getText().trim();
        if (!word.isEmpty()) {
            trie.insert(word);
            textField.setText("");
            updateTextArea();
        }
    }

    private void searchWord() {
        String word = textField.getText().trim();
        if (!word.isEmpty()) {
            int count = trie.search(word);
            String message = "La palabra '" + word + (count > 0 ? "' se encuentra " 
            + count + " veces en el texto." : "' no se encuentra en el texto.");
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void replaceWord() {
        String word = textField.getText().trim();
        String replacement = JOptionPane.showInputDialog(this, "Ingrese la palabra de reemplazo:");
        if (!word.isEmpty() && replacement != null) {
            int count = trie.replace(word, replacement);
            String message = "Se reemplazaron " + count + 
            		" apariciones de la palabra '" + word + "' con '" + replacement + "'.";
            JOptionPane.showMessageDialog(this, message);
            textField.setText("");
            updateTextArea();
        }
    }

    private void updateTextArea() {
        String formattedText = formatText(trie.getText(), textArea.getWidth());
        textArea.setText(formattedText);
    }

    private String formatText(String text, int width) {
        StringBuilder formattedText = new StringBuilder();
        String[] words = text.split(" ");
        int lineLength = 0;

        for (String word : words) {
            if (lineLength + word.length() <= width) {
                formattedText.append(word).append(" ");
                lineLength += word.length() + 1;
            } else {
                formattedText.append("\n").append(word).append(" ");
                lineLength = word.length() + 1;
            }
        }

        return formattedText.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TrieGUI().setVisible(true);
            }
        });
    }
}

