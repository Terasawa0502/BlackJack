package data;

import java.util.*;

public class Deck {

    /**
     * コンストラクタ
     */
    private Deck() {
        createDeck();
    }

    private static Deck instance;

    /**
     * 静的メソッド
     */
    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }
    /**
     * フィールド
     */
    // カード
    private Card card;

    // デッキ
    private Queue<Card> deck;

    /**
     * メソッド
     */

    /**
     *
     * デッキをセットする
     */
    private void createDeck () {
        List<Card> allDeck = new ArrayList<>();
        for (Card.Suite suite : Card.Suite.values()) {
            for (Card.Number number : Card.Number.values()) {
                allDeck.add(new Card(suite, number));
            }
        }
        Collections.shuffle(allDeck);
        deck = new LinkedList<>(allDeck);
    }

    /**
     *
     * @return Card
     * デッキからカードを配る
     */
    public Card distribute() {
        return deck.poll();
    }

}
