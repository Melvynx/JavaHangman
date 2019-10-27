package pendu.Pages;

import java.io.Serializable;

public class UserSaveScore implements Serializable {
    private String name;
    private int score;
    public UserSaveScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.name+" à eu "+this.score+" point !";
    }

    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
}
