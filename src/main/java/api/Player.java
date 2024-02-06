package api;

import Estruturas.Lists.ArrayOrderedList;
import Exceptions.EmptyCollectionException;
import Interfaces.IPlayer;


public class Player implements IPlayer {
    private String name;
    private int numPlayer;
    private int numBots;
    private int botIndex;
    private Flag flag;
    private ArrayOrderedList<Bot> bots;

    public Player(String name) {
        this.name = name;
        this.bots = new ArrayOrderedList<Bot>();
        this.botIndex = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPlayer() {
        return numPlayer;
    }

    public void setNumPlayer(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public ArrayOrderedList<Bot> getBots() {
        return bots;
    }

    public void setBots(ArrayOrderedList<Bot> bots) {
        this.bots = bots;
    }

    public int getNumBots() {
        return numBots;
    }

    public void setNumBots(int numBots) {
        this.numBots = numBots;
    }

    public int getPositionFlag() {
        return flag.getPositionFlag();
    }


    public void checkBotHaveFlagIsInSamePos(Player player1, Player player2) {
        if (player1.getBots() == null || player2.getBots() == null) {
            throw new IllegalArgumentException("Bots list cannot be null");
        }

        for (int i = 0; i < player1.getNumBots(); i++) {
            for (int j = 0; j < player2.getNumBots(); j++) {
                Bot bot1 = player1.getBots().get(i);
                Bot bot2 = player2.getBots().get(j);

                if (bot1.getPosition() == bot2.getPosition() && bot1.hasFlag() && bot2.hasFlag()) {
                    System.out.println("Bots with flags cant have the same position. \n");
                    System.out.println("Flag returned to base. \n");
                    System.out.println(bot1.getId() + " and " + bot2.getId() + " are in the same position");
                    bot1.setFlag(false);
                    bot2.setFlag(false);

                    if (player1.getFlag() != null) {
                        player1.getFlag().setPositionFlag(player1.getFlag().getPositionBase());
                    }

                    if (player2.getFlag() != null) {
                        player2.getFlag().setPositionFlag(player2.getFlag().getPositionBase());
                    }
                }
            }
        }
    }

    public void checkFlagReturnToBase(Player player1, Player player2, int location) {
        for (int i = 0; i < player2.getNumBots(); i++) {
            Bot bot = player2.getBots().get(i);
            if (bot.getPosition() == location && bot.hasFlag()) {
                System.out.println("Bot " + bot.getId() + " from " + player2.getName() + " has returned the flag to base.");
                player1.getFlag().setPositionFlag(player1.getFlag().getPositionBase());
            }
        }
    }


    public int moveBot(Player player1, Player player2, MapaType type) throws EmptyCollectionException {
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(i);

            System.out.println("\n" + player1.getName());
            if (!currentBot.hasFlag()) {
                bots.get(i).move();
            }else
            {
                flag.capture(player1);
                System.out.println("\nBASE");
                if (type == MapaType.MAPA_UNIDIRECIONAL) {
                    return moveBotToBaseUnidirecional(player1, player2);
                } else {
                    return moveBotToBase(player1, player2);
                }

            }

        }
        return 0;
    }

    public int moveBotToBase(Player player1, Player player2) throws EmptyCollectionException {
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(botIndex);

            System.out.println(player1.getName());

            currentBot.moveToBase(player1);

            botIndex = (botIndex + 1) % bots.size();

            checkFlagReturnToBase(player1, player2, currentBot.getPosition());

            if(currentBot.hasFlag()){
                checkBotHaveFlagIsInSamePos(player1, player2);
            }

            if (currentBot.isBlocked()) {
                flag.capturetoBase(player1);
                return 1;
            }
        }
        return 0;
    }


    public int moveBotToBaseUnidirecional(Player player1,  Player player2) throws EmptyCollectionException {
        for (int i = 0; i < numBots; i++) {
            Bot currentBot = bots.get(botIndex);

            System.out.println(player1.getName());

            currentBot.moveUniToBase(player1);

            botIndex = (botIndex + 1) % bots.size();

            checkFlagReturnToBase(player1, player2, currentBot.getPosition());

            if(currentBot.hasFlag()){
                checkBotHaveFlagIsInSamePos(player1, player2);
            }

            if (currentBot.isBlocked()) {
                flag.capturetoBase(player1);
                return 1;
            }
        }
        return 0;
    }

}
