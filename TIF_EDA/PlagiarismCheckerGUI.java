import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class PlagiarismCheckerGUI extends JFrame {
    private JTextField dbFilePathField;
    private JTextField userFilePathField;
    private JTextArea resultTextArea;

    private PlagiarismChecker plagiarismChecker;

    public PlagiarismCheckerGUI() {
        setTitle("Plagiarism Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        plagiarismChecker = new PlagiarismChecker();

        dbFilePathField = new JTextField();
        userFilePathField = new JTextField();
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        JButton loadButton = new JButton("Cargar BD");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] paths = {dbFilePathField.getText()};
                boolean success = plagiarismChecker.loadFiles(paths);
                if (success) {
                    showMessage("BD cargado exitosamente.");
                } else {
                    showMessage("error al cargar BD");
                }
            }
        });

        JButton checkButton = new JButton("verificar plagio");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userFilePath = userFilePathField.getText();
                PlagiarismChecker.ResultChecker result = 
                        plagiarismChecker.verifyPlagiarism(userFilePath);
                if (result != null) {
                    boolean isPlagiarized = result.getResults()[0];
                    if (isPlagiarized) {
                        showMessage("plagio no detectado");
                    } else {
                        showMessage("plagio detectado");
                    }
                } else {
                    showMessage("error al comprabar plagio");
                }
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Ruta de BD:"));
        inputPanel.add(dbFilePathField);
        inputPanel.add(new JLabel("Ruta del archivo del usuario:"));
        inputPanel.add(userFilePathField);
        inputPanel.add(loadButton);
        inputPanel.add(checkButton);

        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(resultScrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void showMessage(String message) {
        resultTextArea.append(message + "\n");
    }
}

