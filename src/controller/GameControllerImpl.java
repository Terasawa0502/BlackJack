package controller;

import data.model.GameModel;
import data.model.GameModelImpl;
import util.*;
import ui.GameView;

public class GameControllerImpl implements GameController, GameView.OnUserInputCallback{
    // Game表示用
    private GameView gameView;
    //Gameデータ管理用
    private GameModel gameModel;
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
        gameView.displayFirstBetAction(this);
        // TODO: 次に何をさせるのか判断する(Controllerの役割)
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

    public void selectFirstBetActionPlayer(String playerName) {
        gameModel.firstBetAction(playerName);
    }

    /**
     * 賭け金精算用のメソッド予定
     * @param playerMoney
     */
    @Override
    public void selectFirstBetActionMoney(int playerMoney) {
        System.out.println("テスト");
        System.out.println(playerMoney);
    }

}
