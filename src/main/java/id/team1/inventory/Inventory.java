/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package id.team1.inventory;

/**
 *
 * @author team1
 */
public class Inventory {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName())
                    .log(java.util.logging.Level.WARNING, "Nimbus look and feel gagal dipakai", ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
