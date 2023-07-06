package blackJackGame.controller;

import blackJackGame.BlackJackGame;

public interface GameController {
    /**
     * ゲーム起動用メソッド
     */
    void startUp();

    /**
     * ゲーム開始メソッド
     */
    void startGame();

    void setOnFinishListener(OnFinishListener listener);

    interface OnFinishListener {
        void onFinish(BlackJackGame.Reason reason);
    }
}
