package controller;

import data.Dealer;
import data.Player;
import data.model.GameModel;
import data.model.GameModelImpl;
import util.*;
import ui.GameView;

public class GameControllerImpl implements GameController, GameView.OnUserInputCallback{
    // Game表示用
    private GameView gameView;
    //Gameデータ管理用
    private GameModel gameModel;
    //プレイヤー
    private Player player;
    //ディーラー
    private Dealer dealer;

    public GameControllerImpl(GameView view, GameModel model) {
        gameView = view;
        this.gameModel = new GameModelImpl();
    }

    /**
     * ゲーム起動
     */
    @Override
    public void startUp() {
        // TODO: タイトル画面表示(Viewの役割)
        gameView.displayTopScreen(this);
    }

    /**
     * ゲームスタート
     */
    @Override
    public void startGame() {

        //ディーラー作成//
        this.dealer = new Dealer();
        // プレイヤー名の設定・賭け金の設定・トランプ配り・手札表示
        gameView.displayFirstBetAction(this);
        // 手札を見て次のアクションを選択させる
        gameView.displaySecondBetAction(this);

    }

    @Override
    public void selectTopScreenItems(GameView.TopScreenItem item) {
        // TODO: TOP画面でユーザが選択した項目によって次の処理をする
        if (item == GameView.TopScreenItem.GAME_START) {
            // START
            startGame();
        } else if (item == GameView.TopScreenItem.GAME_FINISH) {
            // FINISH
            // TODO: 終了処理
        }
    }

    public void selectFirstBetAction(String playerName, int playerMoney) {
        // プレイヤーの所持金を表示させるメソッドをゲームモデルから呼び出す
        //プレイヤー作成//
        this.player = new Player(playerName, playerMoney);
        player.getPocketMoney(playerName);
    }

    /**
     * プレイヤーとディラーのカード処理
     */
    @Override
    public void selectFirstBetActionCard() {
        // TODO : プレイヤーとディーラーにカードを配りメソッドをゲームモデルから呼び出す
        player.getHand().add(gameModel.drawCardFromDeck());
        dealer.getHand().add(gameModel.drawCardFromDeck());
        System.out.println(player.allHandOpen());
        System.out.println(dealer.allHandOpen());
    }

    @Override
    public void selectSecondBetActionItems(GameView.SecondBetActionItem item) {
        // TODO: SecondBetAction画面でプレイヤーが選択したものにより変える
        if (item == GameView.SecondBetActionItem.HIT_ACTION) {
            // 2回目以降で処理を変更したい
            player.setBetMoney(player.getBetMoney()*2);
            System.out.println(player.getBetMoney());
            player.getHand().add(gameModel.drawCardFromDeck());
            System.out.println(player.allHandOpen());
        } else if (item == GameView.SecondBetActionItem.DOUBLE_ACTION) {
            player.setBetMoney(player.getBetMoney()*3);
            player.getHand().add(gameModel.drawCardFromDeck());
            System.out.println(player.allHandOpen());
        } else if (item == GameView.SecondBetActionItem.STAND_ACTION) {
            // 手札Openして勝負するメソッドを呼ぶ
        } else if (item == GameView.SecondBetActionItem.DROP_ACTION) {
            // スタート画面に戻る
            startUp();
        }
    }

    /**
     * 賭け金精算用のメソッド予定
     * @param playerBetMoney
     */
    @Override
    public void calcPlayerBetMoney(int playerBetMoney) {
        player.setBetMoney(playerBetMoney);
    }

    @Override
    public int returnPlayerBetMoney() {
        return player.getBetMoney();
    }

    @Override
    public int returnPlayerPocketMoney() {
        return player.getPocketMoney();
    }

    /**
     *
     * @param playerName
     */
    @Override
    public void screenPlayerPocketMoney(String playerName) {
        player.getPocketMoney(playerName);
    }

}
