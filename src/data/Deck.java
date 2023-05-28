package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    /**
     * コンストラクタ
     */
    public Deck() {
        this.deck = set(this.card);
        shuffle();
    }

    /**
     * フィールド
     */

    // カード
    private Card card;

    // デッキ
    private List<Card> deck;

    /**
     * メソッド
     */

    /**
     *
     * @param card
     * @return Deck
     * デッキをセットする
     */
    public List<Card> set(Card card) {
        List<Card> allDeck = new ArrayList<>();
        for (Card.Suite suite : card.suite.values()) {
            for (Card.Number number : card.number.values())
                allDeck.add(new Card(suite,number));
        }
        return allDeck;
    }

    // デッキをシャッフルする
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    /**
     *
     * @return Card
     * デッキからカードを引く
     */
    public Card drawDeck() {
        shuffle();
        return deck.get(0);
    }

}
