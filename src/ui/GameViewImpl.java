package ui;

import util.Constant;
import util.StringUtil;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameViewImpl implements GameView{

    public GameViewImpl() {

    }
    @Override
    public void displayTopScreen(OnUserInputCallback callback) {
        // 表示処理
        System.out.println(StringUtil.alignCenter(Constant.WELCOME));
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.alignCenter(Constant.GAME_MENU));
        System.out.println(StringUtil.getEmptyRow());

        // 選択項目を表示する
        System.out.println(StringUtil.getSeparator());
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.alignCenter(Constant.SELECT_MENU));
        // 1. START
        System.out.println(StringUtil.alignCenter(Constant.START_SELECT));
        // 2. FINISH
        System.out.println(StringUtil.alignCenter(Constant.FINISH_SELECT));
        // 空白
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.getSeparator());

        // プレイヤーに入力させる
        Scanner scanner = new Scanner(System.in);

        boolean entered = false;
        do {
            System.out.print(StringUtil.alignCenter(Constant.SELECT_NUMBER));
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1": //START
                    entered = true;
                    System.out.println(StringUtil.getEmptyRow());
                    System.out.println(StringUtil.alignCenter(Constant.GAME_START));
                    System.out.println(StringUtil.getEmptyRow());
                    callback.selectTopScreenItems(TopScreenItem.GAME_START);
                    break;
                case "2": //FINISH
                    callback.selectTopScreenItems(TopScreenItem.GAME_FINISH);
                    entered = true;
                    break;
                default:
                    System.out.println();
                    System.out.println(StringUtil.alignCenter(Constant.MISS_SELECT));
                    break;
            }
        } while (!entered);
        scanner.close();
    }

    @Override
    public void displayFirstBetAction(OnUserInputCallback callback) {
        // プレイヤー名を入力させる
        System.out.println(StringUtil.alignCenter(Constant.WHAT_IS_YOUR_NAME));
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        System.out.println(StringUtil.alignCenter(Constant.HOW_MUCH));
        int playerFirstMoney = -1;
        do {
            try {
                playerFirstMoney = scanner.nextInt();
            } catch (NoSuchElementException | IllegalStateException e) {
                e.printStackTrace();
                // TODO: エラーメッセージを出す
                // TODO: エラーだったらどうするか
            }
        } while (playerFirstMoney == -1);
        // プレイヤー名が半角英数字ならOK
        while (!playerName.matches("^[A-Za-z0-9]*$")) {
            System.out.println(StringUtil.alignCenter(Constant.MISS_IS_YOUR_NAME));
            playerName = scanner.nextLine();
            playerFirstMoney = scanner.nextInt();
        }
        // GameコントローラのselectFirstBetActionメソッドにプレイヤー名を渡す
        callback.selectFirstBetAction(playerName, playerFirstMoney);
        // 賭け金を入力させる
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.alignCenter(Constant.BET_MONEY));
        int playerBetMoney = scanner.nextInt();
        // 0$以上100ドル未満
        while (playerBetMoney <= 0 || playerBetMoney > playerFirstMoney) {
            callback.screenPlayerPocketMoney(playerName);
            System.out.println(StringUtil.alignCenter(Constant.MISS_BET_MONEY_1));
            playerBetMoney = scanner.nextInt();
        }
        callback.setPlayerBetMoney(playerBetMoney);
        // カードを配る、手札を表示する
        callback.selectFirstBetActionCard();
    }

    @Override
    public void displaySecondBetAction(OnUserInputCallback callback) {
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.alignCenter(Constant.GAME_MENU));
        System.out.println(StringUtil.getEmptyRow());

        // 選択項目を表示する
        System.out.println(StringUtil.getSeparator());
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.alignCenter(Constant.SELECT_SECOND_BET_MENU));
        // 1. ヒット
        System.out.println(StringUtil.alignCenter(Constant.SELECT_SECOND_BET_HIT));
        // 2. ダブル
        System.out.println(StringUtil.alignCenter(Constant.SELECT_SECOND_BET_DOUBLE));
        // 3. スタンド
        System.out.println(StringUtil.alignCenter(Constant.SELECT_SECOND_BET_STAND));
        // 4. ドロップ
        System.out.println(StringUtil.alignCenter(Constant.SELECT_SECOND_BET_DROP));
        // 空白
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.getSeparator());
        // プレイヤーに番号を選択させる
        Scanner scanner = new Scanner(System.in);
        SecondBetActionItem selectItem;

            System.out.println(StringUtil.getEmptyRow());
            String userInput = scanner.nextLine();
            int tempPocketMoney = callback.getPlayerPocketMoney();
            int tempBetMoney = callback.getPlayerBetMoney();
            String msg = null;

        switch (userInput) {
            case "1" : // ヒット
                    selectItem = SecondBetActionItem.HIT_ACTION;
                    break;
            // もう一度呼べるようにしたい
            case "2" : // ダブル
                while ((tempBetMoney * 2) > tempPocketMoney) {
                    msg = "ダブルするには" + (tempBetMoney * 2 - tempPocketMoney) + "$たりません。";
                    System.out.println(StringUtil.alignCenter(msg));
                    System.out.println(StringUtil.getEmptyRow());
                    System.out.println(StringUtil.alignCenter(Constant.SELECT_SECOND_ANOTHER_BET_MENU));
                    userInput = scanner.nextLine();
                }
                selectItem = SecondBetActionItem.DOUBLE_ACTION;
                break;
            case "3" : // スタンド
                    selectItem = SecondBetActionItem.STAND_ACTION;
                    break;
            // ドロップ
            default : selectItem = SecondBetActionItem.DROP_ACTION;
        }
        callback.selectSecondBetActionItems(selectItem);
        scanner.close();
    }

    /**
     * コンソールにただ表示するようメソッド
     * @param information 情報
     */
    @Override
    public void printGameInfo(String information) {
        System.out.println(StringUtil.alignCenter(information));
    }

    /**
     * 人間の手札情報表示
     * @param humanName 人間の名前
     * @param information 人間の手札
     * @param humanScore 人間の手札のスコア表示
     */
    @Override
    public void printGameHand(String humanName, String information, int humanScore) {
        String msg1 = humanName + "さんの手札";
        System.out.println(StringUtil.alignCenter(msg1));
        printGameInfo(information);
        if (humanScore == 21) {
            String score = String.valueOf(humanScore);
            String msg2 = "カードスコア ： " + humanScore + Constant.BLACKJACK;
            printGameInfo(msg2);
        } else {
            String score = String.valueOf(humanScore);
            String msg2 = "カードスコア ： " + humanScore;
            printGameInfo(msg2);
        }
        System.out.println(StringUtil.getEmptyRow());
    }

    /**
     * プレイヤーの所持金を表示するメソッド
     * @param playerName プレイヤー名
     * @param pocketMoney 所持金
     */
    @Override
    public void printPlayerInformation(String playerName, int pocketMoney) {
        String msg = playerName + "さんの現在の所持金は" + pocketMoney + "$になります";
        printGameInfo(msg);
    }

    /**
     * ディーラーが何回目に引いたかを表示するメソッド
     * @param count ディーラーのドロー回数
     */
    @Override
    public void printDrawCard(int count) {
        String msg = count + "回目のカードを引いた結果です";
        printGameInfo(msg);
    }

    /**
     * 勝負判定を表示するメソッド
     * @param playerName プレイヤー名
     * @param dealerName ディーラー名
     * @param item 勝利条件
     */
    @Override
    public void printJudgeGame(String playerName, String dealerName, judgeGameItem item) {
        if (item == judgeGameItem.DEALER_WIN) {
            String msg = dealerName + Constant.WIN_MESSAGE;
            printGameInfo(msg);
        } else if (item == judgeGameItem.PLAYER_WIN) {
            String msg = playerName + Constant.WIN_MESSAGE;
            printGameInfo(msg);
        } else if (item == judgeGameItem.DRAW) {
            printGameInfo(Constant.DRAW_MESSAGE);
        } else {
            //　// 何もせず次に行動を移行する
        }
    }

    // ディーラーに行動移行用
    @Override
    public void printDealerTurn() {
        System.out.println(StringUtil.alignCenter(Constant.TRANSFER_DEALER));
        System.out.println(StringUtil.getEmptyRow());
    }


}
