package hi.verkefni.vinnsla;

public class HighScore {
    /**
     * Nafn: Róbert A. Jack
     * Tölvupóstur: ral9@hi.is
     * <p>
     * Lýsing: Klasi sem skilgreinir HighScore.
     */
    public static int highScore;

    /**
     * Set-er fyrir highScore.
     *
     * @param hScore
     */
    public static void setHighScore(int hScore) {
        highScore = hScore;
    }

    /**
     * Get-er fyrir highScore.
     *
     * @return int - highScore sem int.
     */
    public static int getHighScore() {
        return highScore;
    }
}
