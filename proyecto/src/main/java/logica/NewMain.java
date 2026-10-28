/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logica;

import grafica.consultar;

/**
 *
 * @author 58153938
 */
public class NewMain {
    public static void main(String[] args) {
        F1app F1 = F1app.getInstance();
        F1.realizarprimeraconexiondatabase("jdbc:mysql://localhost:3306/formula1", "rooy", "esoquesentischocandocontratucola");
        java.awt.EventQueue.invokeLater(() -> {
            new consultar().setVisible(true);
        });
    }
}
