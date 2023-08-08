import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class PlagiarismChecker {
    private Set<String> database;

    public PlagiarismChecker() {
        database = new HashSet<>();
    }

    public boolean loadFiles(String[] paths) {
        for (String path : paths) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(path)));
                processAndAddToDatabase(content);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public ResultChecker verifyPlagiarism(String path) {
        try {
            String userContent = new String(Files.readAllBytes(Paths.get(path)));
            boolean isPlagiarized = isPlagiarized(userContent);
            boolean[] results = {isPlagiarized};
            return new ResultChecker(results);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void processAndAddToDatabase(String content) {
        // Pre-procesamiento: Convertir a minúsculas y eliminar caracteres especiales
        String processedContent = content.toLowerCase().replaceAll("[^a-z0-9\\s]", "");

        // Dividir el contenido en palabras
        String[] words = processedContent.split("\\s+");

        // Agregar cada palabra al conjunto de la base de datos
        for (String word : words) {
            database.add(word);
        }
    }

    private boolean isPlagiarized(String userContent) {
        // Pre-procesamiento: Convertir a minúsculas y eliminar caracteres especiales
        String processedUserContent = userContent.toLowerCase().replaceAll("[^a-z0-9\\s]", "");

        // Dividir el contenido del usuario en palabras
        String[] userWords = processedUserContent.split("\\s+");

        // Verificar si alguna palabra del contenido del usuario está en la base de datos
        for (String word : userWords) {
            if (database.contains(word)) {
                return true; // Plagio detectado
            }
        }
        return false; // No se detectó plagio
    }

    public class ResultChecker {
        private boolean[] results;

        public ResultChecker(boolean[] results) {
            this.results = results;
        }

        public boolean[] getResults() {
            return results;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PlagiarismCheckerGUI().setVisible(true);
            }
        });
    }
}
