import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import questions.DireitoQuestions;
import questions.MedicinaQuestions;

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
                questions = MedicinaQuestions.getQuestions();
                addQuestionPanels();
                cardLayout.show(mainPanel, "question0");
            }
        });

        direitoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questions = DireitoQuestions.getQuestions();
                addQuestionPanels();
                cardLayout.show(mainPanel, "question0");
            }
        });

        buttonPanel.add(medicinaButton);
        buttonPanel.add(direitoButton);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
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

        JPanel optionsPanel = new JPanel(new GridLayout(5, 1));
        ButtonGroup group = new ButtonGroup();
        JRadioButton[] options = new JRadioButton[5];

        for (int i = 1; i <= 5; i++) {
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
                    if (!group.getSelection().getActionCommand().equals(questions.get(index)[6])) {
                        incorrectAnswers.add(questions.get(index));
                    } else {
                        score++;
                    }
                    currentQuestion++;
                    if (currentQuestion < questions.size()) {
                        cardLayout.show(mainPanel, "question" + currentQuestion);
                    } else {
                        showResult();
                    }
                }
            }
        });

        panel.add(nextButton, BorderLayout.SOUTH);
        return panel;
    }

    private void showResult() {
        int totalQuestions = questions.size();
        resultLabel.setText("Você acertou " + score + " de " + totalQuestions + " perguntas.");
        progressBar.setValue((int) ((double) score / totalQuestions * 100));

        StringBuilder incorrectAnswersText = new StringBuilder("Respostas incorretas:\n");
        for (String[] question : incorrectAnswers) {
            incorrectAnswersText.append("Pergunta: ").append(question[0]).append("\n");
            incorrectAnswersText.append("Resposta correta: ").append(question[6]).append("\n\n");
        }
        incorrectAnswersArea.setText(incorrectAnswersText.toString());

        cardLayout.show(mainPanel, "result");
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(resultLabel, BorderLayout.NORTH);

        progressBar = new JProgressBar(0, 100);
        panel.add(progressBar, BorderLayout.CENTER);

        incorrectAnswersArea = new JTextArea();
        incorrectAnswersArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(incorrectAnswersArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton retryButton = new JButton("Tentar novamente");
        JButton exitButton = new JButton("Sair");

        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentQuestion = 0;
                score = 0;
                incorrectAnswers.clear();
                cardLayout.show(mainPanel, "selection");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(retryButton);
        buttonPanel.add(exitButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
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
