package ui;

public interface GameView {

    /**
     * TOP画面表示
     */
    void displayTopScreen(OnUserInputCallback callback);

    /**
     * ユーザー入力用コールバック
     */
    interface  OnUserInputCallback{
        void selectTopScreenItems(TopScreenItem item);
    }

    /**
     * TOP画面選択項目
     */
    enum TopScreenItem{
        GAME_START, //ゲーム開始
        GAME_FINISH //ゲーム終了
    }


}
