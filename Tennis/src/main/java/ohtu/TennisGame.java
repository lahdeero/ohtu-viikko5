package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
			if (player1Score > 3) {
				return "Deuce";
			}
            return numberToString(player1Score) + "-All";
        } else if (player1Score >= 4 || player2Score >= 4) {
            return tieBreak(player1Score - player2Score);
        }
        return scoreToString();
    }

    private String tieBreak(int minusResult) {
        if (minusResult == 1) {
            return "Advantage player1";
        } else if (minusResult == -1) {
            return "Advantage player2";
        } else if (minusResult >= 2) {
            return "Win for player1";
        }
        return "Win for player2";
    }

    private String scoreToString() {
		return numberToString(player1Score) + "-" + numberToString(player2Score);
    }

    private String numberToString(int tempScore) {
        switch (tempScore) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return "";
    }
}
