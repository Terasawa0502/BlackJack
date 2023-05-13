package controller;

import data.model.GameModel;
import ui.GameView;
public class GameControllerImpl implements GameController{
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
        // TODO: ユーザの入力(Viewの役割)
        System.out.println("BlackJack:スタート画面表示");
        // TODO: 次に何をさせるのか判断する(Controllerの役割)

    }
}
