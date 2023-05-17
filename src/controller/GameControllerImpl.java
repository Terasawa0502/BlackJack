package controller;

import data.Card;
import data.Dealer;
import data.Deck;
import data.Player;
import data.model.GameModel;
import ui.GameView;
import util.Constant;

public class GameControllerImpl implements GameController{
    // Game表示用
    private GameView gameView;
    //Gameデータ管理用
    private GameModel gameModel;
    //プレイヤー
    private Player player;
    //ディーラー
    private Dealer dealer;
    //デッキ
    private Deck deck;
    //カード
    private Card card;
    public GameControllerImpl(GameView view, GameModel model) {
        gameView = view;
        gameModel = model;
        //プレイヤー作成//
        this.player = new Player();
        //ディーラー作成//
        this.dealer = new Dealer();
        //デッキ作成//
        this.deck = new Deck();
        //カード作成//
        this.card = new Card();
    }

    /**
     * ゲーム起動
     */
    @Override
    public void startUp() {
        // TODO: タイトル画面表示(Viewの役割)
        gameView.gameInfoPrint(Constant.SEPARATOR_STR);
        gameView.gameInfoPrint(Constant.WELCOME);
        gameView.gameInfoPrint(Constant.SEPARATOR_STR);
        gameView.gameInfoPrint(Constant.EMPTY);
        gameView.gameInfoPrint(Constant.GAME_START);
        gameView.gameInfoPrint(Constant.EMPTY);
    }

    /**
     * ゲームスタート
     */
    @Override
    public void startGame() {
        System.out.println(player.getMoney());
        gameView.gameInfoPrint(Constant.BET_MONEY);
        // TODO: ユーザの入力(Viewの役割)
        // TODO: 次に何をさせるのか判断する(Controllerの役割)
    }
}
