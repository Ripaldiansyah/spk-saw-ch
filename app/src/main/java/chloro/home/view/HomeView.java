package chloro.home.view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

import java.awt.Image;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatPopupMenu;

import chloro.App;
import chloro.UI.Icon.IconCustom;
import chloro.UI.button.ButtonCustom;
import chloro.alternative.view.ManagementAlternativeView;
import chloro.criteria.view.ManagementCriteriaView;
import chloro.dashboard.view.DashboardView;
import chloro.login.view.LoginView;
import chloro.spk.view.ManagementSpkView;
import chloro.user.model.UserModel;
import chloro.user.view.ManagementUserView;
import chloro.user.view.UserView;
import chloro.util.UserToken;

public class HomeView extends JPanel {
    static JPanel mainPanel;
    private JPanel headerPanel;

    static JPanel contentPanel;
    private FlatPopupMenu profileMenu = new FlatPopupMenu();
    private ButtonCustom btnProfile;

    public static HomeView home;
    private JPanel profilePanel;

    public HomeView() {
        initComponent();
        home = this;
    }

    private void initComponent() {

        initLayout();
        setProfile();
        setHeader();
        setContent();
        initAdd();
        initStyle();
    }

    private void initLayout() {
        setLayout(new MigLayout("insets 0, fill", "fill,center", "top,fill"));
        mainPanel = new JPanel(new MigLayout("wrap,insets 0, gap 0", "fill", "fill"));
        headerPanel = new JPanel(new MigLayout("insets 0 70 0 70,center"));

        contentPanel = new JPanel(new MigLayout("wrap, fill, insets 10 80 10 70", "center,fill", "center,fill"));
        profilePanel = new JPanel(new MigLayout("fillx, wrap", "fill, 200, left"));
    }

    private void initStyle() {
        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:#000;"
                + "arc:15");
        headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:#8490fb");

        contentPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:#ffffff");
        profilePanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%);"
                + "arc:15");

    }

    private void initAdd() {
        mainPanel.add(headerPanel);

        mainPanel.add(contentPanel, " push");
        add(mainPanel);
    }

    private void setHeader() {

        String[] title = {
                "Beranda",
                "Pengguna",
                "Alternatif",
                "Kriteria",
                "SPK",
                // "Laporan",
        };

        ActionListener[] actions = {
                e -> changeContent(new DashboardView()),
                e -> changeContent(new ManagementUserView()),
                e -> changeContent(new ManagementAlternativeView()),
                e -> changeContent(new ManagementCriteriaView()),
                e -> changeContent(new ManagementSpkView()),
                // e -> System.out.println(title[5]),

        };

        ButtonCustom[] btnMenuArray = new ButtonCustom[title.length];
        btnProfile = new ButtonCustom(
                "Profil",
                null,
                "#8490fb",
                null,
                (e) -> {
                    profileMenu.show(btnProfile, 50, btnProfile.getHeight());
                });

        if (UserToken.role.equalsIgnoreCase("admin")) {
            for (int i = 0; i < title.length; i++) {
                btnMenuArray[i] = new ButtonCustom(title[i], null, "#8490fb", "#fff", actions[i]);
                headerPanel.add(btnMenuArray[i].getButton(), "grow, h 25!,gapx 0");
            }
        }
        headerPanel.add(btnProfile, " right, h 30!, w 100!, push");

    }

    private void setContent() {
        if (UserToken.role.equalsIgnoreCase("Admin")) {
            changeContent(new DashboardView());
        } else {
            changeContent(new ManagementSpkView());
        }

    }

    private void refreshUI() {
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void refreshProfile() {
        profilePanel.removeAll();
        setProfile();
        profilePanel.revalidate();
        profilePanel.repaint();
    }

    private void setProfile() {
        String fullname = UserToken.fullname;
        String role = UserToken.role;
        JLabel lbName = new JLabel(fullname);
        JLabel lbRole = new JLabel(role);
        IconCustom iconSetting = new IconCustom("svg/setting.svg", 0.5f, null);
        IconCustom iconLogout = new IconCustom("svg/logout.svg", 0.5f, null);
        ButtonCustom btnSetting = new ButtonCustom(

                "Pengaturan",
                iconSetting.getIcon(),
                "#2f7ab1",
                "#fff",
                (e) -> {
                    handleSetting();
                });
        ButtonCustom btnLogout = new ButtonCustom(

                "Keluar",
                iconLogout.getIcon(),
                "#C32148",
                "#fff",
                (e) -> {
                    App.content.loginRegisterPanel(new LoginView());
                });

        profilePanel.add(lbName);
        profilePanel.add(lbRole);
        profilePanel.add(btnSetting, "h 30!, split 2, pushx");
        profilePanel.add(btnLogout, "h 30!");
        profileMenu.add(profilePanel);

    }

    public void changeContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        refreshUI();
    }

    private void handleSetting() {
        UserView userView = new UserView("Pengaturan Akun");
        userView.isSetting = true;
        userView.oldUsername = UserToken.username;
        userView.disableUpdate();
        UserModel userModel = new UserModel();
        userModel.setUserID(UserToken.userID);
        userModel.setUsername(UserToken.username);
        userModel.setFullname(UserToken.fullname);
        userModel.setRole(UserToken.role);
        userModel.setAddress(UserToken.address);
        userView.setField(userModel);
        changeContent(userView);
    }

}
