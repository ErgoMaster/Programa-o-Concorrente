/* ***************************************************************
* Autor............: Gabriel Uzel Fonseca
* Matricula........: 202010028
* Inicio...........: 16/05/2022
* Ultima alteracao.: xx
* Nome.............: Carro Verde
* Funcao...........: Controla o carro verde da simulacao
*************************************************************** */

package Model;

import javafx.scene.image.ImageView;

public class CarroVerde extends Thread {
    private double x;
    private double y;
    private int velocidade;
    private ImageView carro;

    public CarroVerde(int velocidade, ImageView carro) {
        this.velocidade = velocidade;
        this.carro = carro;

        x = carro.getX();
        y = carro.getY();
    }
}
