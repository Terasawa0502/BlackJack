package data;

import data.model.GameModel;
import ui.*;
import util.*;
public class Player extends Human {
    /**
     * フィールド
     */

    // プレイヤーの名前
    private String name;
    // プレイヤーの所持金
    private int pocketMoney;
    // プレイヤーの賭け金
    private int betMoney;

    private static Player instance;

    public static Player getInstance(String name, int money) {
        if (instance == null) {
            instance = new Player(name, money);
        }
        return instance;
    }

    /**
     * コンストラクタ
     */
    private Player(String name, int money) {
        this.name = name;
        this.pocketMoney = money;
    }

    /**
     * メソッド
     */

    // 賭け金(getter)
    public int getBetMoney() {
        return(this.betMoney);
    }

    // 賭け金(setter)
    public void setBetMoney(int playerBetMoney) {
        this.betMoney = playerBetMoney;
    }

    // 所持金表示(getter)
    public void getPocketMoney(String playerName) {
        String msg = playerName + "さんの現在の所持金は" + pocketMoney + "$です";
        GameView.printGameInfo(msg);
    }

    // プレイヤーの名前表示(getter)
    public String getName() {
        return this.name;
    }

    public int getPocketMoney() {
        return this.pocketMoney;
    }
}
