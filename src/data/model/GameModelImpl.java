package data.model;

import data.Card;
import data.Dealer;
import data.Deck;
import data.Player;

public class GameModelImpl implements GameModel{

    //プレイヤー
    private Player player;
    //ディーラー
    private Dealer dealer;
    //デッキ
    private Deck deck;
    //カード
    private Card card;

    public GameModelImpl () {
        //プレイヤー作成//
        this.player = new Player();
        //ディーラー作成//
        this.dealer = new Dealer();
        //デッキ作成//
        this.deck = new Deck();
        //カード作成//
        this.card = new Card();
    }

}
