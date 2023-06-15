package data;

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
        // TODO:if分で10,11,12,13は10として扱う
        // カードスコア
        int score = 0;
        for (Card card : hand) {
            // TODO: card.getNum()を読んでifで分岐させたい
            if (card.getNumber().getNum() == "10" || card.getNumber().getNum() == "J" || card.getNumber().getNum() == "Q" || card.getNumber().getNum() == "K") {
                int tempScore = 10;
                score += tempScore ;
            } else if (card.getNumber().getNum() != "A") {
                int tempScore = Integer.parseInt(card.getNumber().getNum());
                score += tempScore ;
            } else {
                if (card.getNumber().getNum() == "A" && score + 11 > 21) {
                    int tempScore = 1;
                    score += tempScore ;
                } else {
                    int tempScore = 11;
                    score += tempScore ;
                }
            }
        }
        return score;
    }
    public boolean judgeBurst(List<Card> hand) {
        boolean isBurst = false;
        return isBurst;
    }

}

