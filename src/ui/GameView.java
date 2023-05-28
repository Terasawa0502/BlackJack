package ui;

import controller.GameController;

public interface GameView {

    /**
     * TOP画面表示
     * @param callback
     */
    void displayTopScreen(OnUserInputCallback callback);

    /**
     * 最初のBet画面
     * @param callback
     */
    void displayFirstBetAction(OnUserInputCallback callback);

    /**
     * ユーザー入力用コールバック
     */
    interface  OnUserInputCallback{
        // トップスクリーンの選択画面表示
        void selectTopScreenItems(TopScreenItem item);
        // FirstAction(賭け金のBet)画面表示
        void selectFirstBetActionPlayer(String playerName);
        // FirstAction(トランプ配り)
        void selectFirstBetActionCard();
        // FirstAction(賭け金の精算)
        void calcBetMoney(int playerMoney);
    }

    /**
     * TOP画面選択項目
     */
    enum TopScreenItem {
        GAME_START, //ゲーム開始
        GAME_FINISH //ゲーム終了
    }

    /**
     * SecondAction選択項目
     */
    enum SecondActionItem {
        HIT_ACTION, //ヒット
        DOUBLE_ACTION, //ダブル
        STAND_ACTION, //スタンド
        DROP_ACTION //ドロップ
    }

}
