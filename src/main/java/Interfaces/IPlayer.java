package Interfaces;

import Exceptions.EmptyCollectionException;
import api.Player;

public interface IPlayer {

    void checkBotHaveFlagIsInSamePos(Player player1, Player player2) ;
    void checkFlagReturnToBase(Player player1, Player player2, int location);
    public int moveBot(Player player1, Player player2, int o) throws EmptyCollectionException;
    public int moveBotToBase(Player player1, Player player2) throws EmptyCollectionException;
    public int moveBotToBaseUnidirecional(Player player1,  Player player2) throws EmptyCollectionException;


}
