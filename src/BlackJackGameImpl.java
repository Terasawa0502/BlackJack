import controller.GameController;
import controller.GameControllerImpl;
import data.model.GameModelImpl;
import ui.GameViewImpl;

import java.rmi.server.ObjID;

public class BlackJackGameImpl implements BlackJackGame{
   //終了用処理
    private OnFinishListener onFinishListener;
    //Game用コントローラー
    private GameController gameController;

    public BlackJackGameImpl () {
        gameController = new GameControllerImpl(new GameViewImpl(), new GameModelImpl());
    }
    @Override
    public void exec(OnFinishListener listener) {
        onFinishListener = listener;
        //ゲーム開始
        gameController.startUp();
        gameController.startGame();
    }

    @Override
    public void finish(Reason reason) {
        if (onFinishListener != null)
            onFinishListener.onFinish(reason);
    }
}
