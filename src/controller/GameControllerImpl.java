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
        // ディーラーの行動に移る
        dealerAction();
        // 勝負判定に移る
        judgeGame();
        // TODO: 賭け金の精算
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
                // 何もせず次に行動を移行する
            } else {
                gameView.displaySecondBetAction(this);
            }
        } else if (item == GameView.SecondBetActionItem.DOUBLE_ACTION) {
            // プレイヤーが賭け金を2倍にできるが1枚しか引けない
            player.setBetMoney(player.getBetMoney()*2);
            player.getHand().add(gameModel.drawCardFromDeck());
            GameView.printGameHand(player.getName(), player.allHandOpen(), player.getScore(player.getHand()));
        } else if (item == GameView.SecondBetActionItem.STAND_ACTION) {
            // 何もせず次に行動を移行する
        } else if (item == GameView.SecondBetActionItem.DROP_ACTION) {
            // TODO: スタート画面に戻る
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
        // プレイヤーの手札によってディーラー次の行動を決める(ディーラーは17以上になるまでヒット)
        int n = 1;
        while (dealer.getScore(dealer.getHand()) < 17) {
            dealer.getHand().add(gameModel.drawCardFromDeck());
            GameView.printDrawCard(n++);
            GameView.printGameHand(dealer.getName(), dealer.allHandOpen(), dealer.getScore(dealer.getHand()));
            if (dealer.judgeBurst(dealer.getScore(dealer.getHand()))) {
                // 何もせず次に行動を移行する
            }
        }
    }

    public void judgeGame() {
        if (!player.judgeBurst(player.getScore(player.getHand())) && !dealer.judgeBurst(dealer.getScore(dealer.getHand()))) {
            // pがバーストしていないかつdがバーストしていない場合　21に近い方が勝ちもしくは同じ数字なら引き分け
            if (21 - player.getScore(player.getHand()) < 21 - dealer.getScore(dealer.getHand())) {
                // dの方が21に近いためdの勝ち
                GameView.printJudgeGame(player.getName(), dealer.getName(), GameView.judgeGameItem.DEALER_WIN);
            } else if (21 - player.getScore(player.getHand()) > 21 - dealer.getScore(dealer.getHand())) {
                GameView.printJudgeGame(player.getName(), dealer.getName(), GameView.judgeGameItem.PLAYER_WIN);
            } else {
                GameView.printJudgeGame(player.getName(), dealer.getName(), GameView.judgeGameItem.DRAW);
            }
        } else if (player.judgeBurst(player.getScore(player.getHand())) && !dealer.judgeBurst(dealer.getScore(dealer.getHand()))) {
            // pがバーストしていてdがバーストしていない場合はdの勝ち
            GameView.printJudgeGame(player.getName(), dealer.getName(), GameView.judgeGameItem.DEALER_WIN);
        } else if (!player.judgeBurst(player.getScore(player.getHand())) && dealer.judgeBurst(dealer.getScore(dealer.getHand()))) {
            // pがバーストしていていなくてdがバーストしている場合はpの勝ち
            GameView.printJudgeGame(player.getName(), dealer.getName(), GameView.judgeGameItem.PLAYER_WIN);
        } else {
            // pがバーストしていてdがバーストしていた場合はdraw
            GameView.printJudgeGame(player.getName(), dealer.getName(), GameView.judgeGameItem.DRAW);
        }
    }

}
