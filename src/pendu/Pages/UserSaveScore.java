package pendu.Pages;

import java.io.Serializable;

public class UserSaveScore implements Serializable {
    private String name;
    private int score;
    UserSaveScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.name+" à eu "+this.score+" point !";
    }

    int getScore() {
        return score;
    }
    String getName() {
        return name;
    }
}
