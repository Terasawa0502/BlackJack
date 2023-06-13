package ui;

import controller.GameController;
import data.Human;
import util.Constant;
import util.StringUtil;

import java.awt.*;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class GameViewImpl implements GameView{

    public GameViewImpl() {

    }
    @Override
    public void displayTopScreen(OnUserInputCallback callback) {
        // TODO: 表示処理
        System.out.println(StringUtil.alignCenter(Constant.WELCOME));
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.alignCenter(Constant.GAME_MENU));
        System.out.println(StringUtil.getEmptyRow());

        // TODO: 選択項目を表示する
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

        // TODO: プレイヤーに入力させる
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
            }
        } while (!entered);
        scanner.close();
    }

    @Override
    public void displayFirstBetAction(OnUserInputCallback callback) {
        // TODO: プレイヤー名を入力させる
        System.out.println(StringUtil.alignCenter(Constant.WHAT_IS_YOUR_NAME));
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        System.out.println(StringUtil.alignCenter(Constant.HOW_MUCH));
        int playerFirstMoney = scanner.nextInt();
        // プレイヤー名が半角英数字ならOK
        while (!playerName.matches("^[A-Za-z0-9]*$")) {
            System.out.println(StringUtil.alignCenter(Constant.MISS_IS_YOUR_NAME));
            playerName = scanner.nextLine();
            playerFirstMoney = scanner.nextInt();
        }
        // GameコントローラのselectFirstBetActionメソッドにプレイヤー名を渡す
        callback.selectFirstBetAction(playerName, playerFirstMoney);
        // TODO: 賭け金を入力させる
        System.out.println(StringUtil.alignCenter(Constant.BET_MONEY));
        int playerBetMoney = scanner.nextInt();
        // 0$以上100ドル未満
        while (playerBetMoney <= 0 || playerBetMoney > playerFirstMoney) {
            callback.screenPlayerPocketMoney(playerName);
            System.out.println(StringUtil.alignCenter(Constant.MISS_BET_MONEY_1));
            playerBetMoney = scanner.nextInt();
        }
        callback.calcPlayerBetMoney(playerBetMoney);
        // TODO: カードを配る、手札を表示する
        callback.selectFirstBetActionCard();
    }

    @Override
    public void displaySecondBetAction(OnUserInputCallback callback) {
        System.out.println(StringUtil.alignCenter(Constant.GAME_MENU));
        System.out.println(StringUtil.getEmptyRow());

        // TODO: 選択項目を表示する
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
        // TODO: プレイヤーに番号を選択させる
        Scanner scanner = new Scanner(System.in);
        boolean entered = false;
        do {
            System.out.println(StringUtil.getEmptyRow());
            String userInput = scanner.nextLine();
            int tempPocketMoney = callback.returnPlayerPocketMoney();
            int tempBetMoney = callback.returnPlayerBetMoney();
            String msg = null;

            switch (userInput) {

                case "1": // ヒット
                    //
                    while ((tempBetMoney * 2) > tempPocketMoney) {
                        msg = "ヒットするには" + (tempBetMoney*2 - tempPocketMoney) + "$たりません。";
                        System.out.println(StringUtil.alignCenter(msg));
                        System.out.println(StringUtil.getEmptyRow());
                        System.out.println(StringUtil.alignCenter(Constant.SELECT_SECOND_ANOTHER_BET_MENU));
                        userInput = scanner.nextLine();
                    }
                    entered = true;
                    callback.selectSecondBetActionItems(SecondBetActionItem.HIT_ACTION);
                    // もう一度呼べるようにしたい
                    break;
                case "2": // ダブル
                    while ( (tempBetMoney * 3) > tempPocketMoney ) {
                        msg = "ダブルするには" + (tempBetMoney*3 - tempPocketMoney) + "$たりません。";
                        System.out.println(StringUtil.alignCenter(msg));
                        System.out.println(StringUtil.getEmptyRow());
                        System.out.println(StringUtil.alignCenter(Constant.SELECT_SECOND_ANOTHER_BET_MENU));
                        userInput = scanner.nextLine();
                    }
                    entered = true;
                    callback.selectSecondBetActionItems(SecondBetActionItem.DOUBLE_ACTION);
                    break;
                case "3": // スタンド
                    entered = true;
                    callback.selectSecondBetActionItems(SecondBetActionItem.STAND_ACTION);
                    break;
                case "4": // ドロップ
                    entered = true;
                    callback.selectSecondBetActionItems(SecondBetActionItem.DROP_ACTION);
                    break;
            }
        } while (!entered);
        scanner.close();
        // TODO: ユーザの入力(賭ける処理)
        // TODO: ユーザの入力(勝負判定)
        // TODO: ユーザの入力(賭け金の払い戻し)
    }

}
