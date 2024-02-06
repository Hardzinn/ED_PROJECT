package org.example;

import java.util.UUID;

public class Flag {
    private UUID flagId;
    private FlagStatus status;
    private Integer positionFlag;
    private Integer positionBase;
    public Flag(int positionFlag) {
        this.flagId = UUID.randomUUID();
        this.positionFlag = positionFlag;
        this.status = FlagStatus.NOT_CAPTURED;
    }

    public int getId() {
        return flagId.hashCode();
    }


    public void setPositionBase(int positionBase) {
        this.positionBase = positionBase;
    }

    public int getPositionBase() {
        return positionBase;
    }

    public void capture(Player player){
        //comparar a posição do bot do player inimigo com a posição da flag
        this.status = FlagStatus.CAPTURED;
        updateGameStateForCapture(player);
    }

    public void capturetoBase(Player player){
        //comparar a posição do bot do player inimigo com a posição da flag
        this.status = FlagStatus.CAPTURETOBASE;
        updateGameStateForCaptureToBase(player);
    }

    private void updateGameStateForCapture(Player player) {
        System.out.println("Flag captured by " + player.getName());
    }

    private void updateGameStateForCaptureToBase(Player player) {
        System.out.println("Flag captured in the base by " + player.getName());
    }

    public void reset(int newPositionFlag) {
        this.positionFlag = newPositionFlag;
        this.status = FlagStatus.NOT_CAPTURED;
    }

    public FlagStatus getStatus() {
        return status;
    }
    public Integer getPositionFlag() {
        return positionFlag;
    }

    public void setPositionFlag(Integer positionFlag) {
        this.positionFlag = positionFlag;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "id=" + flagId +
                ", positionIndex=" + positionFlag +
                ", status=" + status +
                '}';
    }
}
