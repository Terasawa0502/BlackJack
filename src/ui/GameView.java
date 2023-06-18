package ui;

import controller.GameController;
import util.Constant;
import util.StringUtil;

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

    void displaySecondBetAction(OnUserInputCallback callback);

    /**
     * ユーザー入力用コールバック
     */
    interface  OnUserInputCallback{
        // トップスクリーンの選択画面表示
        void selectTopScreenItems(TopScreenItem item);
        // FirstAction(賭け金のBet)画面表示
        void selectFirstBetAction(String playerName, int playerMoney);
        // FirstAction(トランプ配り)
        void selectFirstBetActionCard();
        // SecondBetAction(賭け方の選択)
        void selectSecondBetActionItems(SecondBetActionItem item);
        // 賭け金の精算
        void calcPlayerBetMoney(int playerBetMoney);
        // 賭け金をViewに渡す
        int returnPlayerBetMoney();
        // 現在の所持金をViewに渡す
        int returnPlayerPocketMoney();
        // 現在の所持金を表示する
        void screenPlayerPocketMoney(String playerName);
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
    enum SecondBetActionItem {
        HIT_ACTION, //1.ヒット
        DOUBLE_ACTION, //2.ダブル
        STAND_ACTION, //3.スタンド
        DROP_ACTION //4.ドロップ
    }

    /**
     * コンソールにただ表示するようメソッド
     * @param information 情報
     */
    public static void printGameInfo(String information) {
        System.out.println(StringUtil.alignCenter(information));
    }

    /**
     * 人間の手札情報表示
     * @param humanName 人間の名前
     * @param information 人間の手札
     * @param humanScore 人間の手札のスコア表示
     */
    public static void printGameHand(String humanName, String information, int humanScore) {
        String msg1 = humanName + "さんの手札";
        System.out.println(StringUtil.alignCenter(msg1));
        printGameInfo(information);
        if (humanScore == 21) {
            String score = String.valueOf(humanScore);
            String msg2 = "カードスコア ： " + humanScore + Constant.BLACKJACK;
            GameView.printGameInfo(msg2);
        } else {
            String score = String.valueOf(humanScore);
            String msg2 = "カードスコア ： " + humanScore;
            GameView.printGameInfo(msg2);
        }
        System.out.println(StringUtil.getEmptyRow());
    }

    public static void countDrawCard(int count) {
        String msg = count + "回目のカードを引いた結果です";
        GameView.printGameInfo(msg);
    }

    public enum judgeGameItem {
        PLAYER_WIN, // 1.プレイヤーの勝利
        DEALER_WIN, // 2.ディーラーの勝利
        DRAW // 3.ドロー
    }

    public static void judgeGame (String playerName, String dealerName, judgeGameItem item) {
        if (item == judgeGameItem.DEALER_WIN) {
            String msg = dealerName + Constant.WIN_MESSAGE;
            GameView.printGameInfo(msg);
        } else if (item == judgeGameItem.PLAYER_WIN) {
            String msg = playerName + Constant.WIN_MESSAGE;
            GameView.printGameInfo(msg);
        } else if (item == judgeGameItem.DRAW) {
            GameView.printGameInfo(Constant.DRAW_MESSAGE);
        } else {
            //　// 何もせず次に行動を移行する
        }

    }
}
