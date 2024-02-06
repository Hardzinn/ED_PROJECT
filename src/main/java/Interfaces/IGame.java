package Interfaces;

import Exceptions.EmptyCollectionException;
import Exceptions.NonComparableElementException;
import api.Player;

public interface IGame {

    public Player determineTurnOrder() throws EmptyCollectionException;
    public void playRound(Player player) throws EmptyCollectionException, NonComparableElementException;
    public void setWinner(Player player);



}
