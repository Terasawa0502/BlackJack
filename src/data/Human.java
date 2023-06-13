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
    // 手札の何枚目かを表す
    protected int handIndex = 0;

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

}

