package api;

import enums.FlagStatus;

import java.util.UUID;

/**
 * Classe que representa uma Flag. Esta classe e responsavel por criar uma Flag, definir a sua posicao e estado.
 * Utilizamos UUID para gerar um identificador unico para cada flag.
 * @author : André Faria
 * @author : Daniela Mendes
 */
public class Flag {

    /**
     * Atributos da classe Flag
     * flagId - identificador unico da flag
     * status - estado da flag(Captured, Not Captured, Capture to Base)
     * positionFlag - posicao da flag
     * positionBase - posicao inicial da flag
     */

    private UUID flagId;
    private FlagStatus status;
    private Integer positionFlag;
    private Integer positionBase;

    /**
     * Construtor da classe Flag
     * Inicializa a flag com um identificador unico, neste caso random, a sua posicao e estado default, neste caso
     * Nao capturada.
     * @param positionFlag Posicao inicial
     */
    public Flag(int positionFlag) {
        this.flagId = UUID.randomUUID();
        this.positionFlag = positionFlag;
        this.status = FlagStatus.NOT_CAPTURED;
    }

    /**
     * Metodo que retorna o identificador da flag
     * @return id da flag
     */
    public int getId() {
        return flagId.hashCode();
    }

    /**
     * Metodo que define a posicao inicial da flag, ou seja da base do player.
     * @param positionBase Posicao a definir
     */
    public void setPositionBase(int positionBase) {
        this.positionBase = positionBase;
    }

    /**
     * Metodo que retorna a posicao inicial da flag, ou posicao da base do player.
     * @return posicao inicial
     */
    public int getPositionBase() {
        return positionBase;
    }

    /**
     * Metodo que retorna o estado da flag
     * @return estado da flag
     */
    public FlagStatus getStatus() {
        return status;
    }

    /**
     * Metodo que obtem a posicao da flag
     * @return posicao da flag
     */
    public Integer getPositionFlag() {
        return positionFlag;
    }

    /**
     * Metodo que define a posicao da flag
     * @param positionFlag Posicao a definir
     */
    public void setPositionFlag(Integer positionFlag) {
        this.positionFlag = positionFlag;
    }

    /**
     * Metodo para capturar a flag. Apenas muda o estado da flag para capturada.
     * @param player Player que capturou a flag
     */
    public void capture(Player player){
        //comparar a posição do bot do player inimigo com a posição da flag
        this.status = FlagStatus.CAPTURED;
        updateGameStateForCapture(player);
    }

    /**
     * Metodo o estado da flag para Capturada. Ao contrario do metodo capture, este metodo
     * @param player Player que capturou a flag
     */

    public void capturetoBase(Player player){
        //comparar a posição do bot do player inimigo com a posição da flag
        this.status = FlagStatus.CAPTURETOBASE;
        updateGameStateForCaptureToBase(player);
    }

    /**
     * Mensagem para indicar quem foi o player que capturou a flag.
     * @param player Player que capturou a flag
     */
    private void updateGameStateForCapture(Player player) {
        System.out.println("Flag captured by " + player.getName());
    }

    /**
     * Mensagem para indicar quem foi o player que levou a flag para a base.
     * @param player Player que capturou a flag
     */
    private void updateGameStateForCaptureToBase(Player player) {
        System.out.println("Flag captured in the base by " + player.getName());
    }

    /**
     * Metodo que nos permite resetar a flag, ou seja, definir a sua posicao e estado default.
     * Nao e utilizado, apenas implementado para futuras implementacoes.
     * @param newPositionFlag Nova posicao da flag
     */
    public void reset(int newPositionFlag) {
        this.positionFlag = newPositionFlag;
        this.status = FlagStatus.NOT_CAPTURED;
    }

    /**
     * Metodo toString. Damos override para pudermos utilizar o metodo toString para imprimir a flag.
     * @return String com a informacao da flag.
     */

    @Override
    public String toString() {
        return "Flag{" +
                "id=" + flagId +
                ", positionIndex=" + positionFlag +
                ", status=" + status +
                '}';
    }
}
