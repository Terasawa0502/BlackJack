package data;

import ui.GameView;
import util.Constant;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public abstract class Human {

    /**
     * フィールド
     */
    // 名前
    protected String name;

    // 手札
    protected List<Card> hand = new ArrayList<>();

    // 手札のカードを全てOpen
    public String allHandOpen() {
        // TODO : handフィールドを全て取り出しshowCardメソッド
        StringBuilder sb = new StringBuilder();
        for (Card card: hand) {
            sb.append(card.toString());
            sb.append(Constant.SPACE);
        }
        return sb.toString();
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore(List<Card> hand) {
        // カードスコア
        int score = 0;
        // TODO: Aを後にして並び替えたい
        for (Card card : hand) {
            if (card.getNumber().getNum() == "10" || card.getNumber().getNum() == "J" || card.getNumber().getNum() == "Q" || card.getNumber().getNum() == "K") {
                // 数字が10,J,Q,Kならscoreに10代入
                int tempScore = 10;
                score += tempScore ;
            } else if (card.getNumber().getNum() != "A") {
                // 数字がA以外ならscoreにその数字を代入
                int tempScore = Integer.parseInt(card.getNumber().getNum());
                score += tempScore ;
            } else {
                if (card.getNumber().getNum() == "A" && score + 11 > 21) {
                    // 数字がAでかつ21を超えるなら11ではなく1として扱う
                    int tempScore = 1;
                    score += tempScore ;
                } else {
                    // 数字がAでかつ21を超えないなら11として計算する
                    int tempScore = 11;
                    score += tempScore ;
                }
            }
        }
        return score;
    }
    public boolean judgeBurst(int score) {
        boolean isBurst = false;
        if (score > 21) {
            isBurst =true;
        }
        return isBurst;
    }

    public void surrender (String humanName) {
        String msg = "手札のスコアが21を超えたため"+humanName + "さんの負けです";
        GameView.printGameInfo(msg);
    }

}

