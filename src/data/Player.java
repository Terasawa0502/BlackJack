package data;

import util.*;
public class Player extends Human {
    /**
     * フィールド
     */

    // プレイヤーの名前
    private String name;
    // プレイヤーの所持金
    private int money;

    /**
     * コンストラクタ
     */
    public Player() {
        this(100);
    }

    public Player(int money) {
        this.money = money;
    }

    /**
     * メソッド
     */

    //所持金表示
    public void getMoney(String playerName) {
        String msg = playerName + "さんの現在の所持金は" + money + "$です";
        System.out.println(StringUtil.alignCenter(msg));
    }
}
