package blackJackGame.data.model;

import blackJackGame.data.Card;

public interface GameModel {

    // プレイヤーとディーラーにカードを配るメソッドの呼び出し
    Card drawCardFromDeck();

}
