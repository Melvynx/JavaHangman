package pendu.Pages;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import pendu.Navigation;
import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;

public class ScorePage extends JPanel {
    ImagePanel penduImage = new ImagePanel();
    JLabel[] topScore = new JLabel[10];
    JPanel contentTopScore = new JPanel(new GridLayout(10,1));
    JPanel contentImage = new JPanel(new BorderLayout());
    JPanel content = new JPanel(new GridLayout(1,2));
    LinkedList<UserSaveScore> savedScore = new LinkedList<>();
    JButton restart = new JButton("Rejouer !");

    public ScorePage(Navigation navigation) {
        tryGetScore();

        penduImage.setIndexSet(0);
        for(int i = 0; i<10;i++){
            Font fontDescending = new Font("Chalkboard", Font.LAYOUT_LEFT_TO_RIGHT, 30-(i*2));
            topScore[i] = new JLabel();
            topScore[i].setFont(fontDescending);
            contentTopScore.add(topScore[i]);
            if(i < savedScore.size()){
                topScore[i].setText(savedScore.get(i).toString());
            }else{
                topScore[i].setText("Pas encore définit !");
            }
        }
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigation.setPage(2);
            }
        });
        contentImage.add(penduImage, BorderLayout.CENTER);
        contentImage.add(restart, BorderLayout.SOUTH);
        contentTopScore.setPreferredSize(new Dimension(400,600));
        content.add(contentTopScore);
        content.add(contentImage);
        this.setPreferredSize(new Dimension(800,500));
        this.add(content);

    }

    private void tryGetScore() {
        BufferedReader lecteurAvecBuffer;
        String ligne;
        savedScore = new LinkedList<>();
        try
        {
            lecteurAvecBuffer = new BufferedReader(new FileReader(new File("file/score.txt")));
            while ((ligne = lecteurAvecBuffer.readLine()) != null){
                System.out.println(ligne);
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
    public void triBulles(LinkedList<UserSaveScore> scoreList)
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

