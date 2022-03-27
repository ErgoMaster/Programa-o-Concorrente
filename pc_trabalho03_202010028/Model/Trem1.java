/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 20/03/2022
* Ultima alteracao.: xx
* Nome.............: Trem 1
* Funcao...........: Modifica a posicao do trem 1 na simulacao
*************************************************************** */

package Model;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Trem1 extends Thread {
    private int self; // Variavel que indica o index representante do trem para a solucao de Peterson
    private ImageView trem; // Variavel do tipo image view para a manipulacao da parte grafica
    private int duracao; // Duracao da animacao que indicara a velocidade do trem

    public Trem1(ImageView trem, int duracao) {
        self = 0; // Para o trem 1, essa variavel sera 0
        this.trem = trem; 
        this.duracao = duracao;
    }

    public void run() {
        int pauseInteiro = 0;
        Variavel.setVelocidade(self, duracao);

        // Regiao nao critica 1
        TranslateTransition translate1 = new TranslateTransition(Duration.millis(duracao), trem);
        translate1.setByX(120);

        TranslateTransition translate2 = new TranslateTransition(Duration.millis(duracao), trem);
        translate2.setByX(30);
        translate2.setByY(30);

        
        Variavel.setFlag(self, 1); // Indica intecao de passar pela regiao critica
        Variavel.setVez(1 - self); // Seta a vez para a outra thread 

        /* Para esse while teremos: Caso a outra thread tenha interesse em passar pela regiao critica e seja vez dessa thread passar, 
        a thread atual entrara no loop e eseperara a outra thread terminar de executar a regiao critica, caso seja a sua vez de passar 
        ou a outra thread nao tenha interesse, a thread atual continuara normalmente para a regiao critica */
        while(Variavel.getFlagB() == 1 && Variavel.getVez() == 1 - self) {
            pauseInteiro++; // Loop de espera
        } 

        PauseTransition pause = new PauseTransition(Duration.millis(pauseInteiro));
        System.out.println(pauseInteiro);

        TranslateTransition translate3 = new TranslateTransition(Duration.millis(duracao), trem);
        translate3.setByX(160);
        Variavel.setVez(1); // Seta a vez para a outra thread
        Variavel.setFlag(self, 0); // Sinaliza que a thread atual nao possui mais interesse em passar pela regiao critica */

        // Regiao nao critica 2
        TranslateTransition translate4 = new TranslateTransition(Duration.millis(duracao), trem);
        translate4.setByY(30);
        translate4.setByX(30);

        TranslateTransition translate5 = new TranslateTransition(Duration.millis(duracao), trem);
        translate5.setByX(150);

        SequentialTransition seqT = new SequentialTransition(translate1, translate2, pause, translate3, translate4, translate5);
        seqT.play();
    }
}
