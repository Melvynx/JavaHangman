package pendu.Pages;

import java.io.Serializable;

public class userSaveScore implements Serializable {
    private String name;
    private int score;
    public userSaveScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "userSaveScore{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
}
