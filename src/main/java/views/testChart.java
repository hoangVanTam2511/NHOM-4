/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import javax.swing.WindowConstants;

/**
 *
 * @author Admin
 */
public class testChart {
    
    public static void main(String[] args) {
        testJfreechart barras = new testJfreechart();
        barras.setSize(800,400);
        barras.setLocationRelativeTo(null);
        barras.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        barras.setVisible(true);
    }
    
}
