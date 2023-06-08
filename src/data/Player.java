package data;

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

    /**
     * コンストラクタ
     */
    public Player(String name, int money) {
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
        System.out.println(StringUtil.alignCenter(msg));
    }

    public int getPocketMoney() {
        return this.pocketMoney;
    }
}
