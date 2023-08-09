import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PlagiarismChecker {
    private Set<String> database;

    public PlagiarismChecker() {
        database = new TreeSet<>();
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
        String[] words = content.split("\\s+");
        for (String word : words) {
            database.add(word.toLowerCase());
        }
    }

    private boolean isPlagiarized(String userContent) {
        String[] userWords = userContent.split("\\s+");
        for (String word : userWords) {
            if (database.contains(word.toLowerCase())) {
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
