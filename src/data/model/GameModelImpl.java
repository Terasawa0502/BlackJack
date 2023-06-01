package data.model;

import data.Card;
import data.Dealer;
import data.Deck;
import data.Player;
import util.StringUtil;

import java.awt.*;

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
