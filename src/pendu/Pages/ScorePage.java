package pendu.Pages;

import pendu.Navigation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.util.LinkedList;

public class ScorePage extends JPanel {
    private LinkedList<UserSaveScore> savedScore = new LinkedList<>();
    private JPanel content = new JPanel(new GridLayout(1, 2));
    private JButton restart = new JButton("Rejouer !");
    private JButton homePage = new JButton("Aller à la homePage");

    public ScorePage(Navigation navigation) {
        tryGetScore();
        initComponent();

        restart.addActionListener(e -> navigation.setPage(2));
        homePage.addActionListener(e -> navigation.setPage(0));

        this.setPreferredSize(new Dimension(800,500));
        this.add(content);

    }
    private void initComponent(){
        ImagePanel penduImage = new ImagePanel();
        penduImage.setIndexSet(0);
        JPanel contentTopScore = new JPanel(new GridLayout(10, 1));
        for(int i = 0; i<10; i++){
            Font fontDescending = new Font("Chalkboard", Font.PLAIN, 30-(i*2));
            JLabel[] topScore = new JLabel[10];
            topScore[i] = new JLabel();
            topScore[i].setFont(fontDescending);
            contentTopScore.add(topScore[i]);
            if(i < savedScore.size()){
                topScore[i].setText(savedScore.get(i).toString());
            }else{
                topScore[i].setText("Pas encore définit !");
            }
        }


        JPanel contentImage = new JPanel(new BorderLayout());
        contentImage.add(penduImage, BorderLayout.CENTER);
        JPanel contentButton = new JPanel();
        contentButton.add(restart);
        contentButton.add(homePage);
        homePage.setPreferredSize(new Dimension(200,75));
        restart.setPreferredSize(new Dimension(200,75));
        contentButton.setPreferredSize(new Dimension(500, 100));
        contentImage.add(contentButton, BorderLayout.SOUTH);
        contentTopScore.setPreferredSize(new Dimension(400,600));

        Border border =  contentTopScore.getBorder();
        Border margin = new EmptyBorder(10,50,10,5);
        contentTopScore.setBorder(new CompoundBorder(border, margin));

        content.add(contentTopScore);
        content.add(contentImage);
    }

    private void tryGetScore() {
        BufferedReader lecteurAvecBuffer;
        String ligne;
        savedScore = new LinkedList<>();
        try
        {
            lecteurAvecBuffer = new BufferedReader(new FileReader(new File("file/score.txt")));
            while ((ligne = lecteurAvecBuffer.readLine()) != null){
                String[] ligneTab = ligne.split(":");
                String name = ligneTab[0];
                int score = Integer.parseInt(ligneTab[1]);
                UserSaveScore userSaveScore = new UserSaveScore(name,score);
                savedScore.add(userSaveScore);
            }
            lecteurAvecBuffer.close();

        }
        catch(FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture");
        } catch (IOException e){
            e.printStackTrace();
        }

        triBulles(savedScore);

    }
    private void triBulles(LinkedList<UserSaveScore> scoreList)
    {
        for (int i=0 ;i<=(scoreList.size()-2);i++)
            for (int j=(scoreList.size()-1);i < j;j--)
                if (scoreList.get(j).getScore() > scoreList.get(j-1).getScore())
                {
                    int score =scoreList.get(j-1).getScore();
                    String name = scoreList.get(j-1).getName();
                    scoreList.set(j-1, new UserSaveScore(scoreList.get(j).getName(), scoreList.get(j).getScore()));
                    scoreList.set(j, new UserSaveScore(name, score));
                }
    }
}

