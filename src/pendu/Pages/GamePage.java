package pendu.Pages;

import pendu.Navigation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.LinkedList;

public class GamePage extends JPanel {

    private String[] lettersOfButton = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //Un bouton par élément à afficher
    private JButton[] buttonsLetters = new JButton[lettersOfButton.length];

    private JButton scorePageButton = new JButton("Voir les scores");
    private JButton homePage = new JButton("Retours menu");
    private JButton restartButton = new JButton("Continuer !");
    private JButton stopButton = new JButton("Arrêter !");
    private JButton savePoint = new JButton("Not see");
    
    //JMenuItem[] itemLetters = new JMenuItem[lettersOfButton.length];

    private JLabel countWordLabel = new JLabel("Nombre de mot trouvé : ");
    private JLabel countScoreLabel = new JLabel("Votre score actuel est de : ");
    private JLabel countMistake = new JLabel("Vous avez effectuez 0 fautes.");
    private JLabel wordToFind = new JLabel("xxxxxx");
    private ImagePanel penduImage = new ImagePanel();
    private Integer imageChoose = 1;

    private LinkedList<Character> randomWord = new LinkedList<>();
    private LinkedList<Character> randomLetterFind = new LinkedList<>();

    private JPanel componentWord = new JPanel();
    private JPanel componentLetters = new JPanel();
    private JPanel componentImage = new JPanel(new BorderLayout());

    private Integer counterTry = 0;

    private boolean gameFinish = false;

    private String nameUser = "";
    private int scoreUser = 0;
    private int pointPlayer = 0;

    public GamePage(Navigation navigation) {
        countScoreLabel.setText("Votre score actuel est de : "+ navigation.getPointPlayer());
        initComponent();
        initRandomWord();
        JPanel component = new JPanel();
        component.setLayout(new GridLayout(1, 2));
        component.add(componentWord, BorderLayout.CENTER);
        component.add(componentImage, BorderLayout.EAST);
        this.add(component);

        scorePageButton.addActionListener(e -> {
            navigation.setPage(3);
            navigation.resetPointPlay();
        });
        homePage.addActionListener(e -> navigation.setPage(0));
        restartButton.addActionListener(e -> navigation.setPage(2));
        stopButton.addActionListener(e -> {
            int option = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment arrêter votre partie ?", "Arrêt de partie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.YES_OPTION){
                System.out.println(pointPlayer);
                scoreUser = navigation.getPointPlayer();
                if (scoreUser > 1){
                    partieFinish();
                    scorePageButton.setVisible(true);
                    homePage.setVisible(true);
                    navigation.resetPointPlay();
                } else {
                    navigation.setPage(0);
                }
            }
        });
        savePoint.addActionListener(e -> {
            navigation.addPointPlayer(pointPlayer);
            System.out.println(navigation.getPointPlayer());
            scoreUser = navigation.getPointPlayer();
        });
        scoreUser = navigation.getPointPlayer();
    }

    private void initComponent() {
        penduImage.setIndexSet(imageChoose);
        componentImage.add(penduImage, BorderLayout.CENTER);
        JPanel componentButton = new JPanel(new GridLayout(1,2));
        componentButton.add(scorePageButton);
        componentButton.add(restartButton);
        componentButton.add(stopButton);
        componentButton.add(homePage);
        componentButton.setPreferredSize(new Dimension(400,50));
        componentImage.add(componentButton, BorderLayout.SOUTH);

        componentLetters.setLayout(new GridLayout(4, 7));
        scorePageButton.setVisible(false);
        restartButton.setVisible(false);
        homePage.setVisible(false);
        Dimension dimButtonLetter = new Dimension(30, 30);
        for (int i = 0; i < lettersOfButton.length; i++) {
            buttonsLetters[i] = new JButton(lettersOfButton[i]);
            buttonsLetters[i].setPreferredSize(dimButtonLetter);
            buttonsLetters[i].addActionListener(new ButtonLettersListener(lettersOfButton[i].toLowerCase().charAt(0)));
            buttonsLetters[i].setEnabled(true);
            if (i == 21) {
                JPanel panVide = new JPanel();
                componentLetters.add(panVide);
                componentLetters.add(buttonsLetters[21]);
            } else {
                componentLetters.add(buttonsLetters[i]);
            }
        }
        //Set keybord event
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(e -> {
                    if(!gameFinish){
                        if(e.getID() == KeyEvent.KEY_RELEASED){
                            for (String s : lettersOfButton) {
                                if (e.getKeyChar() == s.toLowerCase().charAt(0)) {
                                    for (JButton buttonsLetter : buttonsLetters) {
                                        if (buttonsLetter.getText().toLowerCase().charAt(0) == e.getKeyChar()) {
                                            if (buttonsLetter.isEnabled())
                                                new ButtonLettersListener(e.getKeyChar()).click();
                                            buttonsLetter.setEnabled(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return false;
                });
        Font fontInfoLabel = new Font("Chalkboard", Font.ITALIC, 25);
        Font wordToFindFont = new Font("Chalkboard", Font.BOLD, 40);
        countWordLabel.setFont(fontInfoLabel);
        countScoreLabel.setFont(fontInfoLabel);
        countScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        countWordLabel.setHorizontalAlignment(JLabel.CENTER);
        countMistake.setHorizontalAlignment(JLabel.CENTER);
        countMistake.setFont(fontInfoLabel);

        wordToFind.setFont(wordToFindFont);
        wordToFind.setHorizontalAlignment(JLabel.CENTER);

        JPanel componentInfo = new JPanel(new GridLayout(3, 1));
        componentWord.setLayout(new BorderLayout());
        componentWord.setPreferredSize(new Dimension(500, 500));
        componentLetters.setPreferredSize(new Dimension(500, 200));
        wordToFind.setPreferredSize(new Dimension(500, 150));
        componentInfo.add(countWordLabel);
        componentInfo.add(countMistake);
        componentInfo.add(countScoreLabel);
        componentWord.add(componentInfo, BorderLayout.NORTH);
        JPanel componentWordAndAnswer = new JPanel(new BorderLayout());
        componentWordAndAnswer.add(wordToFind, BorderLayout.NORTH);
        componentWordAndAnswer.add(componentLetters, BorderLayout.CENTER);
        componentWord.add(componentWordAndAnswer);


    }

    private void initRandomWord(){
        StringBuilder wordToFindStar = new StringBuilder();
        String randomWordGenerate = genereateWord();
        int randomWordLength = randomWordGenerate.length();

        for (int i = 0; i < randomWordLength; i++){
            wordToFindStar.append("-");
            randomWord.add(randomWordGenerate.charAt(i));
            randomLetterFind.add('-');
        }

        wordToFind.setText(wordToFindStar.toString());
    }

    private String genereateWord() {
        int randomNumber = (int)(Math.random()*336000);
        String answer = null;
        LineNumberReader dictionnaireText;
        try {
            dictionnaireText = new LineNumberReader(new FileReader(new File("file/dictionnaire.txt")));
            for (int i = 0; i < randomNumber + 1; i++) {
                dictionnaireText.readLine();
                if (randomNumber == dictionnaireText.getLineNumber() ) {
                    answer = dictionnaireText.readLine();
                    System.out.println("The number find is : "+randomNumber+". THE WORD IS "+answer);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
    }

    public class ButtonLettersListener implements ActionListener{
        private final char letter;

        ButtonLettersListener(char letter) {
            this.letter = letter;
        }

        public void actionPerformed(ActionEvent e) {
            if (!gameFinish){
                click();
                ((JButton)e.getSource()).setEnabled(false);
            }

        }

        private void click() {
            char typedChar = letter;
            boolean someoneLetterFind = false;
            boolean gameIsWine = true;
            String newWordToDisplay = "";

            for (int i = 0; i<randomWord.size(); i++){
                char lettreRandomTry = randomWord.get(i);
                if (typedChar == lettreRandomTry) {
                    randomLetterFind.set(i, typedChar);
                    someoneLetterFind = true;
                }
            }
            for (Character character : randomLetterFind) {
                newWordToDisplay += character;
                wordToFind.setText(newWordToDisplay);
            }
            if (!someoneLetterFind){
                counterTry++;
                countMistake.setText("Vous avez effectuez "+counterTry+" fautes.");
                if (imageChoose < 7){
                    imageChoose++;
                    penduImage.setIndexSet(imageChoose);
                    penduImage.repaint();
                } else {
                    StringBuilder wordCorrect = new StringBuilder();
                    //SI le joueurs à perdu
                    //Image
                    imageChoose = 10;
                    penduImage.setIndexSet(imageChoose);
                    penduImage.repaint();
                    wordToFind.setForeground(Color.red);
                    //Changement de text
                    countMistake.setForeground(Color.red);
                    countMistake.setText("PERDU");
                    //create the correct word
                    for (Character character : randomWord)
                        wordCorrect.append(character);
                    wordToFind.setText(wordCorrect.toString());
                    //Do false all button
                    for (int i = 0; i<lettersOfButton.length; i++)
                        buttonsLetters[i].setEnabled(false);

                    if(scoreUser > 1) {
                        getPoint(counterTry);
                        partieFinish();
                    }
                    scorePageButton.setVisible(true);
                    restartButton.setVisible(true);
                    homePage.setVisible(true);
                }
            }
            for (Character character : randomLetterFind) {
                if (character == '-') {
                    gameIsWine = false;
                    break;
                }
            }
            if(gameIsWine){
                wordToFind.setForeground(Color.green);
                countMistake.setForeground(Color.green);
                countMistake.setText("GAGNEZ");
                imageChoose = 11;
                penduImage.setIndexSet(imageChoose);
                penduImage.repaint();
                for (int i = 0; i<lettersOfButton.length; i++)
                    buttonsLetters[i].setEnabled(false);

                getPoint(counterTry);
                gameFinish = true;

                restartButton.setVisible(true);
            }
        }
    }
    private void writeNewScore() {
        System.out.println("Writing");
        if(nameUser == null){
            System.out.println("Nameuser null");
            return;
        }
        //Try if : is on the word
        String[] testUser = nameUser.split(":");
        if (testUser.length > 1){
            JOptionPane.showMessageDialog(null, "<html>Le pseudo ne doit pas contenir de \" : \".<br/> Impossible de sauvegarder.</html>", "Error syntaxe", JOptionPane.ERROR_MESSAGE);
            partieFinish();
            return;
        }
        if(nameUser.length() > 50){
            JOptionPane.showMessageDialog(null, "<html>Le pseudo ne doit pas contenir plus de 50 caractères.</html>", "Error length", JOptionPane.ERROR_MESSAGE);
            partieFinish();
            return;
        }
        try {
            System.out.println("passed");
            String formatText = nameUser + ":" + scoreUser+"\n";
            PrintWriter pw = new PrintWriter(new FileWriter("file/score.txt", true));
            pw.write(formatText);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void partieFinish(){

        gameFinish = true;

        nameUser = JOptionPane.showInputDialog(null, "Inscrivez votre pseudo !", "Meilleurs Score", JOptionPane.QUESTION_MESSAGE);
        writeNewScore();
    }
    private void getPoint(Integer countLose){
        switch (countLose){
            case 0:
                pointPlayer = 100;
                break;
            case 1:
                pointPlayer = 50;
                break;
            case 2:
                pointPlayer = 40;
                break;
            case 3:
                pointPlayer = 25;
                break;
            case 4:
                pointPlayer = 15;
                break;
            case 5:
                pointPlayer = 10;
                break;
            case 6:
                pointPlayer = 5;
                break;
            default:
                pointPlayer = 0;
                break;
        }
        System.out.println(pointPlayer);
        savePoint.doClick();
    }
}
