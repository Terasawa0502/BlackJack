import controller.GameController;
import controller.GameControllerImpl;
import data.model.GameModelImpl;
import ui.GameViewImpl;

public class BlackJackGameImpl implements BlackJackGame{
   //
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
    }

    @Override
    public void finish(Reason reason) {
        if (onFinishListener != null)
            onFinishListener.onFinish(reason);
    }
}
