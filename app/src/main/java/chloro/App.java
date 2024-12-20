
package chloro;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.util.UIScale;

import chloro.login.view.LoginView;

import java.io.InputStream;

public class App extends JFrame {
    public static App content;

    public App() {

        initUI();
    }

    private void configureFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(UIScale.scale(new Dimension(1366, 768)));
        setSize(UIScale.scale(new Dimension(600, 500)));
        setLocationRelativeTo(null);
    }

    private void initUI() {
        configureFrame();
        setContentPane(new LoginView());
    }

    private void refreshUI() {
        revalidate();
        repaint();
    }

    public void loginRegisterPanel(JPanel newPanel) {
        setContentPane(newPanel);
        configureFrame();
        refreshUI();
    }

    public void changeContentPanel(JPanel newPanel) {
        setSize(UIScale.scale(new Dimension(1366, 768)));
        setLocationRelativeTo(null);
        setContentPane(newPanel);
        refreshUI();
    }

    private static void configureApplication() {
        try (InputStream is = App.class.getResourceAsStream("/font/fonnts.com-Camphor_Pro.ttf")) {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(13f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            Locale.setDefault(new Locale("id"));
            UIManager.put("defaultFont", customFont);
            FlatLightLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        configureApplication();
        java.awt.EventQueue.invokeLater(() -> {
            content = new App();
            content.setVisible(true);
        });
    }
}
