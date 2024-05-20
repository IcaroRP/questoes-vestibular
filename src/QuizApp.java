import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;

public class QuizApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int currentQuestion = 0;
    private int score = 0;

    private ArrayList<String[]> questions = new ArrayList<>();
    private ArrayList<String[]> incorrectAnswers = new ArrayList<>();

    private JLabel resultLabel;
    private JProgressBar progressBar;
    private JTextArea incorrectAnswersArea;

    public QuizApp() {
        setTitle("Quiz de Vestibular");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createSelectionPanel(), "selection");
        mainPanel.add(createResultPanel(), "result");

        add(mainPanel);
        cardLayout.show(mainPanel, "selection");
    }

    private JPanel createSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Escolha o tipo de questões:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton medicinaButton = new JButton("Medicina");
        JButton direitoButton = new JButton("Direito");

        medicinaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadQuestionsFromFile("src\\medicina.txt");
                addQuestionPanels();
                cardLayout.show(mainPanel, "question0");
            }
        });

        direitoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadQuestionsFromFile("src\\direito.txt");
                addQuestionPanels();
                cardLayout.show(mainPanel, "question0");
            }
        });

        buttonPanel.add(medicinaButton);
        buttonPanel.add(direitoButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private void loadQuestionsFromFile(String filename) {
        questions.clear();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            StringBuilder questionBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() && questionBuilder.length() > 0) {
                    questions.add(parseQuestion(questionBuilder.toString()));
                    questionBuilder.setLength(0);
                } else {
                    questionBuilder.append(line).append("\n");
                }
            }
            if (questionBuilder.length() > 0) {
                questions.add(parseQuestion(questionBuilder.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] parseQuestion(String questionBlock) {
        String[] parts = questionBlock.split("\n");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Número incorreto de linhas para a questão");
        }
        String questionText = parts[0].trim();
        String[] options = new String[4];
        System.arraycopy(parts, 1, options, 0, 4);
        String correctAnswer = parts[5].trim();
        return new String[]{questionText, options[0], options[1], options[2], options[3], correctAnswer};
    }    


    private void addQuestionPanels() {
        for (int i = 0; i < questions.size(); i++) {
            mainPanel.add(createQuestionPanel(i), "question" + i);
        }
    }

    private JPanel createQuestionPanel(int index) {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea questionArea = new JTextArea(questions.get(index)[0]);
        questionArea.setWrapStyleWord(true);
        questionArea.setLineWrap(true);
        questionArea.setEditable(false);
        JScrollPane questionScrollPane = new JScrollPane(questionArea);
        panel.add(questionScrollPane, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        ButtonGroup group = new ButtonGroup();
        JRadioButton[] options = new JRadioButton[4];

        for (int i = 1; i <= 4; i++) {
            options[i - 1] = new JRadioButton(questions.get(index)[i]);
            options[i - 1].setActionCommand(String.valueOf((char) ('A' + i - 1)));
            group.add(options[i - 1]);
            optionsPanel.add(options[i - 1]);
        }

        panel.add(optionsPanel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Próxima");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (group.getSelection() != null) {
                    if (!group.getSelection().getActionCommand().equals(questions.get(index)[5])) {
                        incorrectAnswers.add(questions.get(index));
                    } else {
                        score++;
                    }
                    currentQuestion++;
                    if (currentQuestion < questions.size()) {
                        cardLayout.show(mainPanel, "question" + currentQuestion);
                    } else {
                        updateResult();
                        cardLayout.show(mainPanel, "result");
                    }
                }
            }
        });

        panel.add(nextButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JLabel performanceLabel = new JLabel("Seu desempenho foi:");
        performanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(performanceLabel);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        topPanel.add(progressBar);

        panel.add(topPanel, BorderLayout.NORTH);

        resultLabel = new JLabel("Seu desempenho: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(resultLabel, BorderLayout.CENTER);

        incorrectAnswersArea = new JTextArea();
        incorrectAnswersArea.setEditable(false);
        incorrectAnswersArea.setLineWrap(true);
        incorrectAnswersArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(incorrectAnswersArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton retryButton = new JButton("Tentar Novamente");
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetQuiz();
                cardLayout.show(mainPanel, "selection");
            }
        });

        JButton finishButton = new JButton("Finalizar");
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        bottomPanel.add(retryButton);
        bottomPanel.add(finishButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void updateResult() {
        int percentage = (int) ((double) score / questions.size() * 100);
        resultLabel.setText("Seu desempenho: " + score + " de " + questions.size() + " respostas corretas.");
        progressBar.setValue(percentage);
        displayIncorrectAnswers();
    }

    private void displayIncorrectAnswers() {
        StringBuilder sb = new StringBuilder();
        for (String[] question : incorrectAnswers) {
            sb.append("Questão: ").append(question[0]).append("\n");
            sb.append("Resposta correta: ").append(getCorrectAnswerText(question)).append("\n\n");
        }
        incorrectAnswersArea.setText(sb.toString());
    }

    private String getCorrectAnswerText(String[] question) {
        char correctAnswer = question[5].charAt(0);
        int correctAnswerIndex = correctAnswer - 'A' + 1; // Convert 'A' to 1, 'B' to 2, etc.
        return question[correctAnswerIndex];
    }

    private void resetQuiz() {
        currentQuestion = 0;
        score = 0;
        incorrectAnswers.clear();
        mainPanel.removeAll();
        mainPanel.add(createSelectionPanel(), "selection");
        mainPanel.add(createResultPanel(), "result");
        addQuestionPanels();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApp().setVisible(true);
            }
        });
    }
}
