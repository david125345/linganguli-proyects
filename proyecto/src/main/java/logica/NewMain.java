/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logica;

import grafica.consultar;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.SwingUtilities;


/**
 *
 * @author 58153938
 */
public class NewMain {
    public static void main(String[] args) {
        F1app F1 = F1app.getInstance();
        F1.realizarprimeraconexiondatabase("jdbc:mysql://localhost:3306/formula1", "rooy", "bomboclat");
        FlatDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            new consultar().setVisible(true);
        });
    }
}
