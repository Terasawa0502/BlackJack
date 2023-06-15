package data;

import util.*;

public class Card {

    /**
     * Card コンストラクタ
     * @param suite トランプのマーク
     * @param number トランプの数字
     */
    public Card(Suite suite, Number number) {
        this.suite = suite;
        this.number = number;
    }

    /**
     * フィールド
     */

    // トランプのマーク
    private Suite suite;
    // トランプのマークの列挙型
    public enum Suite {
        SPADE("♠"),CLUB("♣"),HEART("❤"),DIAMOND("♦");

        // コンストラクタ
        Suite (String label) {
            this.label = label;
        }

        // フィールド:トランプの柄
        private String label;

        // メソッド:トランプの柄を戻す
        public String getLabel() {
            return label;
        }

    }

    public Suite getSuite() {
        return suite;
    }

    // トランプの数字
    private Number number;
    // トランプの数字の列挙型
    public enum Number {
        ACE("A"), TWO("2"), THREE("3"), FOUR("4"),FIVE("5"),
        SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
        JACK("J"), QUEEN("Q"), KING("K");

        // コンストラクタ
        Number (String num) {
            this.num = num;
        }

        // フィールド:トランプの数字
        private String num;

        // メソッド:トランプの数字を戻す
        public String getNum() {
            return num;
        }

    }

    public Number getNumber() {
        return number;
    }

    /**
     * メソッド
     */
    @Override
    public String toString() {
        String msg = suite.getLabel() + number.getNum() + Constant.SPACE;
        return msg;
    }
}
