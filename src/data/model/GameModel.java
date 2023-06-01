package data.model;

import data.Card;

public interface GameModel {

    // プレイヤーとディーラーにカードを配るメソッドの呼び出し
    Card drawCardFromDeck();

}
