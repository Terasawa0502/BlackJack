package data.model;

public interface GameModel {
    // プレイヤーの名前と所持金を表示させるメソッドの呼び出し
    void firstBetAction(String playerName);

    // プレイヤーとディーラーにカードを配るメソッドの呼び出し
    void distributeCard();

    // プレイヤーとディーラーのカードを表示するメソッドの呼び出し
    void openCard();
}
