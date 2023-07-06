package blackJackGame;

import blackJackGame.controller.GameController;
import blackJackGame.controller.GameControllerImpl;
import blackJackGame.data.model.GameModelImpl;
import blackJackGame.ui.GameViewImpl;

public class BlackJackGameImpl implements BlackJackGame{
   //終了用処理
    private OnFinishListener onFinishListener;
    //Game用コントローラー
    private GameController gameController;

    public BlackJackGameImpl () {
        gameController = new GameControllerImpl(new GameViewImpl(), new GameModelImpl());
        gameController.setOnFinishListener( reason -> {
            finish(reason);

        });
    }
    @Override
    public void exec(OnFinishListener listener) {
        onFinishListener = listener;
        //ゲーム開始
        gameController.startUp();
    }

    @Override
    public void finish(Reason reason) {
        if (onFinishListener != null)
            onFinishListener.onFinish(reason);
    }
}
