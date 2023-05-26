package ui;

import controller.GameController;
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
        System.out.println(StringUtil.getSeparator());
        System.out.println(StringUtil.alignCenter(Constant.WELCOME));
        System.out.println(StringUtil.getSeparator());
        System.out.println(StringUtil.getEmptyRow());
        System.out.println(StringUtil.alignCenter(Constant.GAME_START));
        System.out.println(StringUtil.getEmptyRow());

        // TODO: 選択項目を表示する
        // 1. START
        System.out.println(StringUtil.alignCenter(Constant.START_SELECT));
        // 2. FINISH
        System.out.println(StringUtil.alignCenter(Constant.FINISH_SELECT));
        // 空白
        System.out.println(StringUtil.getEmptyRow());

        // TODO: プレイヤーに入力させる
        Scanner scanner = new Scanner(System.in);

        boolean entered = false;
        do {
            System.out.print(StringUtil.alignCenter(Constant.SELECT_NUMBER));
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1": //START
                    entered = true;
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
        // System.out.println(playerName.matches("^[A-Za-z0-9]*$")); 試験用ログ
        // プレイヤー名が半角英数字ならOK
        while (!playerName.matches("^[A-Za-z0-9]*$")) {
            System.out.println(StringUtil.alignCenter(Constant.MISS_IS_YOUR_NAME));
            playerName = scanner.nextLine();
        }
        // GameコントローラのselectFirstBetActionメソッドにプレイヤー名を渡す
        callback.selectFirstBetActionPlayer(playerName);
        // TODO: 賭け金を入力させる
        System.out.println(StringUtil.alignCenter(Constant.BET_MONEY));
        int playerMoney = Integer.parseInt(scanner.nextLine());
        String alterPlayerMoney = String.valueOf(playerMoney);
        // 半角英数字以外の場合
        while (playerMoney <= 0 || playerMoney > 100) {
            System.out.println(StringUtil.alignCenter(Constant.MISS_BET_MONEY_2));
            playerMoney = Integer.parseInt(scanner.nextLine());
        }
        scanner.close();
        // TODO: ユーザの入力(カードを配る、手札を表示する)
        // TODO: ユーザの入力(賭ける処理)
        // TODO: ユーザの入力(勝負判定)
        // TODO: ユーザの入力(賭け金の払い戻し)
        // モデルに渡す
    }


}
