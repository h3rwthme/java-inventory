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
        // Use FlatLaf-like cross platform look and feel for clean bright appearance
        try {
            // Try Nimbus first for a modern, bright cross-platform look
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    // Customize Nimbus for brighter, cleaner UI
                    javax.swing.UIManager.put("control", new java.awt.Color(248, 250, 252));
                    javax.swing.UIManager.put("nimbusBase", new java.awt.Color(59, 130, 246));
                    javax.swing.UIManager.put("nimbusBlueGrey", new java.awt.Color(226, 232, 240));
                    javax.swing.UIManager.put("nimbusFocus", new java.awt.Color(59, 130, 246));
                    javax.swing.UIManager.put("nimbusLightBackground", new java.awt.Color(255, 255, 255));
                    javax.swing.UIManager.put("nimbusSelectionBackground", new java.awt.Color(219, 234, 254));
                    javax.swing.UIManager.put("text", new java.awt.Color(30, 41, 59));
                    break;
                }
            }
        } catch (Exception ex) {
            // Fallback to system L&F
            try {
                javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception e) {
                java.util.logging.Logger.getLogger(Inventory.class.getName())
                        .log(java.util.logging.Level.WARNING, "UI setup issue", e);
            }
        }

        // Enable anti-aliasing globally
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
