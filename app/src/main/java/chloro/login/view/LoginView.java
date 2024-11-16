package chloro.login.view;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.formdev.flatlaf.FlatClientProperties;

import chloro.UI.Icon.IconCustom;
import chloro.UI.Notification.NotificationCustom;
import chloro.UI.PasswordField.PasswordFieldCustom;
import chloro.UI.TextField.TextFieldCustom;
import chloro.UI.button.ButtonCustom;
import chloro.login.controller.LoginController;
import chloro.login.model.LoginModel;
import net.miginfocom.swing.MigLayout;

public class LoginView extends JPanel {

    private JPanel leftPanel;
    private JLabel lbDescription;
    private ButtonCustom btnLogin;
    private TextFieldCustom txtUsername;
    private PasswordFieldCustom txtPassword;
    private LoginModel model = new LoginModel();
    private LoginController controller = new LoginController();
    private NotificationCustom notification = new NotificationCustom("Login");

    public LoginView() {
        initComponent();
    }

    private void initComponent() {
        initLayout();
        initLogin();
        initAdd();
        initStyle();
    }

    private void initLayout() {
        setLayout(new MigLayout("fill,insets 10", "[center, fill]", "[center, fill]"));
        leftPanel = new JPanel(new MigLayout("fill, insets 100,wrap", "[fill,center, 250:280]", "[center]"));

    }

    private void initLogin() {

        lbDescription = new JLabel("Selamat Datang");
        IconCustom iconLogin = new IconCustom("svg/login.svg", 1f, null);
        txtUsername = new TextFieldCustom(
                "Masukan nama pengguna Anda",
                null,
                true);
        txtPassword = new PasswordFieldCustom(
                "Masukan Kata andi Anda",
                null,
                true);

        btnLogin = new ButtonCustom(
                null,
                iconLogin.getIcon(),
                null,
                null,
                (e) -> {
                    model = controller.getInput(txtUsername, txtPassword);
                    if (!controller.isRegistered(model)) {
                        notification.wrongAccount();
                        return;
                    }
                    controller.login();
                });

        leftPanel.add(lbDescription);
        leftPanel.add(txtUsername, "gapy 8");
        leftPanel.add(txtPassword);
        leftPanel.add(btnLogin, "gapy 1, w 80!, h 40!, push, right");
    }

    private void initAdd() {
        add(leftPanel, " h 450!");

    }

    private void initStyle() {
        lbDescription.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+10;"
                + "foreground:lighten(@foreground,30%)");
    }

}
