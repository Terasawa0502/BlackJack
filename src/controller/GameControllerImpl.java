package controller;

import data.model.GameModel;
import util.*;
import ui.GameView;

public class GameControllerImpl implements GameController, GameView.OnUserInputCallback{
    // Game表示用
    private GameView gameView;
    //Gameデータ管理用
    private GameModel gameModel;
    public GameControllerImpl(GameView view, GameModel model) {
        gameView = view;
        gameModel = model;
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
        // gameView.なんとかメソッド(this)
        // gameView playerでなんとか
        // TODO: ユーザの入力(賭ける処理)
        // TODO: ユーザの入力(カードを配る、手札を表示する)
        // TODO: ユーザの入力(賭ける処理)
        // TODO: ユーザの入力(勝負判定)
        // TODO: ユーザの入力(賭け金の払い戻し)
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
}
