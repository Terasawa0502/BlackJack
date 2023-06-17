package controller;

import data.*;
import data.model.GameModel;
import data.model.GameModelImpl;
import util.*;
import ui.GameView;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

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
        // タイトル画面表示(Viewの役割)
        gameView.displayTopScreen(this);
    }

    /**
     * ゲームスタート
     */
    @Override
    public void startGame() {

        //ディーラー作成//
        this.dealer = Dealer.getInstance();
        // プレイヤー名の設定・賭け金の設定・トランプ配り・手札表示
        gameView.displayFirstBetAction(this);
        // 手札を見て次のアクションを選択させる(プレイヤー)
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
        this.player = Player.getInstance(playerName, playerMoney);
        player.getPocketMoney(playerName);
    }

    /**
     * プレイヤーとディラーのカード処理
     */
    @Override
    public void selectFirstBetActionCard() {
        // TODO : プレイヤーとディーラーにカードを配りメソッドをゲームモデルから呼び出す
        player.getHand().add(gameModel.drawCardFromDeck());
        player.getHand().add(gameModel.drawCardFromDeck());
        dealer.getHand().add(gameModel.drawCardFromDeck());
        GameView.printGameHand(dealer.getName(), dealer.allHandOpen(), dealer.getScore(dealer.getHand()));
        GameView.printGameHand(player.getName(), player.allHandOpen(), player.getScore(player.getHand()));
    }

    @Override
    public void selectSecondBetActionItems(GameView.SecondBetActionItem item) {
        // SecondBetAction画面でプレイヤーが選択したものにより変える
        if (item == GameView.SecondBetActionItem.HIT_ACTION) {
            // プレイヤーが賭け金をそのままにBurstするまで手札を加えれる
            player.getHand().add(gameModel.drawCardFromDeck());
            GameView.printGameHand(player.getName(), player.allHandOpen(), player.getScore(player.getHand()));
            // 手札が21を超えていないかを判断させる
            if (player.judgeBurst(player.getScore(player.getHand()))) {
                player.surrender(player.getName());
                // ToDo : ゲーム終了
            } else {
                gameView.displaySecondBetAction(this);
            }
        } else if (item == GameView.SecondBetActionItem.DOUBLE_ACTION) {
            // プレイヤーが賭け金を2倍にできるが1枚しか引けない
            player.setBetMoney(player.getBetMoney()*2);
            player.getHand().add(gameModel.drawCardFromDeck());
            GameView.printGameHand(player.getName(), player.allHandOpen(), player.getScore(player.getHand()));
            // ディーラーが行動するメソッドを呼ぶ
            dealerAction();
        } else if (item == GameView.SecondBetActionItem.STAND_ACTION) {
            // ディーラーが行動するメソッドを呼ぶ
            dealerAction();
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

    public void dealerAction () {
        // TODO :プレイヤーの手札によってディーラー次の行動を決める(ディーラーは17以上になるまでヒット)
        int n = 1;
        while (dealer.getScore(dealer.getHand()) < 17) {
            dealer.getHand().add(gameModel.drawCardFromDeck());
            String msg = n++ +"回目のカードを引いた結果です" ;
            GameView.printGameInfo(msg);
            GameView.printGameHand(dealer.getName(), dealer.allHandOpen(), dealer.getScore(dealer.getHand()));
            if (dealer.judgeBurst(dealer.getScore(dealer.getHand()))) {
                dealer.surrender(dealer.getName());
            }
        }
    }

}
