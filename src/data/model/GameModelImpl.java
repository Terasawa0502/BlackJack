package data.model;

import data.Card;
import data.Dealer;
import data.Deck;
import data.Player;
import util.StringUtil;

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

    // プレイヤーの名前と所持金を表示させるメソッドの呼び出し
    @Override
    public void firstBetAction(String playerName) {
        player.getMoney(playerName);
    }

    // プレイヤーとディーラーにカードを配るメソッドの呼び出し
    @Override
    public void distributeCard() {
        // TODO: Gamemodelのメソッド:プレイヤーとディーラーを引数にカードが戻るメソッド？
    }

    // プレイヤーとディーラーのカードを表示するメソッドの呼び出し
    @Override
    public void openCard() {
        // TODO: Gamemodelのメソッド:プレイヤーとディーラーのカードを表示するメソッド？
    }
}
