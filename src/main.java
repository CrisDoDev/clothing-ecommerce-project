
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import ui.SignatureToolFrame;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new SignatureToolFrame().setVisible(true);
        });
    }
}