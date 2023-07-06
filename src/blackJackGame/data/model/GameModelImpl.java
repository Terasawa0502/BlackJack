package blackJackGame.data.model;

import blackJackGame.data.Card;
import blackJackGame.data.Deck;

public class GameModelImpl implements GameModel{

    //デッキ
    private Deck deck;

    public GameModelImpl () {
        //デッキ作成//
        this.deck = Deck.getInstance();
    }

    // プレイヤーとディーラーがデッキからカートを引くメソッドの呼び出し
    @Override
    public Card drawCardFromDeck() {
        // デッキのカードを配るメソッドの呼び出し
        return deck.distribute();
    }
}
