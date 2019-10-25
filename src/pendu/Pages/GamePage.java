package pendu.Pages;

import pendu.Navigation;
import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Principal;
import java.util.LinkedList;

public class GamePage extends JPanel {

    String[] lettersOfButton = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //Un bouton par élément à afficher
    JButton[] buttonsLetters = new JButton[lettersOfButton.length];
    JButton continueButton = new JButton("Continuer");
    //JMenuItem[] itemLetters = new JMenuItem[lettersOfButton.length];

    JLabel countWordLabel = new JLabel("Nombre de mot trouvé : ");
    JLabel countScoreLabel = new JLabel("Votre score actuel est de : ");
    JLabel countMistake = new JLabel("Vous avez effectuez 0 fautes.");
    JLabel wordToFind = new JLabel("xxxxxx");
    ImagePanel penduImage = new ImagePanel();
    Integer imageChoose = 1;

    LinkedList<Character> randomWord = new LinkedList<>();
    LinkedList<Character> randomLetterFind = new LinkedList<>();

    JPanel component = new JPanel();
    JPanel componentWord = new JPanel();
    JPanel componentLetters = new JPanel();
    JPanel componentImage = new JPanel(new BorderLayout());

    Integer counterTry = 0;

    boolean gameWine = false;

    String nameUser = "";
    int scoreUser = 0;

    public GamePage(Navigation navigation) {
        initComponent();
        initRandomWord();
        component.setLayout(new GridLayout(1, 2));
        component.add(componentWord, BorderLayout.CENTER);
        component.add(componentImage, BorderLayout.EAST);
        this.add(component);

        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(gameWine)
                    navigation.setPage(3);
                else
                    navigation.setPage(1);
            }
        });
    }

    public void initComponent() {
        penduImage.setIndexSet(imageChoose);
        componentImage.add(penduImage, BorderLayout.CENTER);
        componentImage.add(continueButton, BorderLayout.SOUTH);
        componentLetters.setLayout(new GridLayout(4, 7));
        continueButton.setVisible(false);
        Dimension dimButtonLetter = new Dimension(30, 30);
        for (int i = 0; i < lettersOfButton.length; i++) {
            buttonsLetters[i] = new JButton(lettersOfButton[i]);
            buttonsLetters[i].setPreferredSize(dimButtonLetter);
            buttonsLetters[i].addActionListener(new ButtonLettersListener(lettersOfButton[i].toLowerCase().charAt(0)));
            buttonsLetters[i].setEnabled(true);
            //itemLetters[i] = new JMenuItem(lettersOfButton[i]);
            //itemLetters[i].addActionListener(new ButtonLettersListener());
            //itemLetters[i].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.SHIFT_MASK));
            if (i == 21) {
                JPanel panVide = new JPanel();
                componentLetters.add(panVide);
                componentLetters.add(buttonsLetters[21]);
            } else {
                componentLetters.add(buttonsLetters[i]);
            }
        }
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if(gameWine){
                            return false;
                        }
                        if(e.getID() == KeyEvent.KEY_RELEASED){
                            for(int y = 0; y<lettersOfButton.length; y++){
                                if(e.getKeyChar() == lettersOfButton[y].toLowerCase().charAt(0)){
                                    new ButtonLettersListener(e.getKeyChar()).click();
                                    System.out.println("Got key event!" + e.getKeyChar());
                                    for(int i = 0; i<buttonsLetters.length; i++){
                                        if(buttonsLetters[i].getText().toLowerCase().charAt(0) == e.getKeyChar()){
                                            buttonsLetters[i].setEnabled(false);
                                        }
                                    }
                                }
                            }
                        }
                        return false;
                    }
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

    public void initRandomWord(){
        String wordToFindStar = "";
        String randomWordGenerate = genereateWord();
        int randomWordLength = randomWordGenerate.length();

        for (int i = 0; i < randomWordLength; i++){
            wordToFindStar+= "-";
            randomWord.add(randomWordGenerate.charAt(i));
            randomLetterFind.add('-');
        }

        wordToFind.setText(wordToFindStar);
    }

    public String genereateWord() {
        int randomNumber = (int)(Math.random()*336000);
        String answer = null;
        LineNumberReader dictionnaireText;
        try {
            dictionnaireText = new LineNumberReader(new FileReader(new File("file/dictionnaire.txt")));
            String s = dictionnaireText.readLine();
            for (int i = 0; i < randomNumber + 1; i++) {
                dictionnaireText.readLine();
                if (randomNumber == dictionnaireText.getLineNumber() ) {
                    answer = dictionnaireText.readLine();
                    System.out.println("The number find is : "+randomNumber+". THE WORD IS "+answer);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
            click();
            ((JButton)e.getSource()).setEnabled(false);

        }

        public void click() {
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
            for (int y = 0; y<randomLetterFind.size(); y++){
                newWordToDisplay+=randomLetterFind.get(y);
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
                    System.out.println("Game finish");
                    imageChoose = 8;
                    penduImage.setIndexSet(imageChoose);
                    penduImage.repaint();
                    wordToFind.setForeground(Color.red);

                    countMistake.setForeground(Color.red);
                    countMistake.setText("PERDU");
                    for (int i = 0; i < randomWord.size(); i++)
                        wordCorrect.append(randomWord.get(i));
                    for (int i = 0; i<lettersOfButton.length; i++)
                        buttonsLetters[i].setEnabled(false);

                    wordToFind.setText(wordCorrect.toString());
                    continueButton.setVisible(true);
                }
            }
            for (int i = 0; i<randomLetterFind.size(); i++){
                if (randomLetterFind.get(i) == '-')
                    gameIsWine = false;
            }
            if(gameIsWine){
                wordToFind.setForeground(Color.green);
                countMistake.setForeground(Color.green);
                countMistake.setText("GAGNEZ");

                for (int i = 0; i<lettersOfButton.length; i++)
                    buttonsLetters[i].setEnabled(false);

                continueButton.setVisible(true);

                gameWine = true;

                JOptionPane answerName = new JOptionPane();
                nameUser = answerName.showInputDialog(null, "Veuillez décliné votre identité !", "Gendarmerie National", JOptionPane.QUESTION_MESSAGE);
                writeNewScore();
            }
            System.out.println(newWordToDisplay);
        }
    }
    private void writeNewScore() {
        try {
            String formatText = nameUser + ":" + scoreUser+"\n";
            PrintWriter pw = new PrintWriter(new FileWriter("file/score.txt", true));
            pw.write(formatText);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
