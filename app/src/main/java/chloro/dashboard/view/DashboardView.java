package chloro.dashboard.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class DashboardView extends JPanel {

    private JPanel profilePanel;

    public DashboardView() {
        initComponent();
    }

    private void initComponent() {
        initLayout();
        initAdd();
        setProfile();
        initStyle();
    }

    private void initLayout() {
        setLayout(new MigLayout("insets 0, fillx", "center", "top"));
        profilePanel = new JPanel(new MigLayout("wrap,fillx,insets 0 10 10 10", "left,grow,fill", "top,fill"));
    }

    private void initAdd() {
        add(profilePanel);
    }

    private void initStyle() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:#ffffff");
        profilePanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%);"
                + "arc:20");
    }

    private void setProfile() {
        ImageIcon originalIcon = new ImageIcon(this.getClass().getResource("/Images/logo.png"));
        profilePanel.add(new JLabel(originalIcon));

    }

}
