package chloro.user.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import com.formdev.flatlaf.FlatClientProperties;

import chloro.UI.Notification.NotificationCustom;
import chloro.UI.PasswordField.PasswordFieldCustom;
import chloro.UI.TextField.TextFieldCustom;
import chloro.UI.button.ButtonCustom;
import chloro.spk.view.ManagementSpkView;
import chloro.user.controller.UserController;
import chloro.user.model.UserModel;
import net.miginfocom.swing.MigLayout;

public class UserView extends JPanel {
        private JPanel mainPanel;
        private JPanel contentPanel;
        private JLabel lbTitle;
        private TextFieldCustom txtFullname;
        private TextFieldCustom txtUsername;
        private PasswordFieldCustom txtPassword;
        private PasswordFieldCustom txtPasswordConfirm;
        private TextFieldCustom txtAddress;
        private ButtonCustom btnCancel;
        private ButtonCustom btnSave;
        private String title;
        private JComboBox<String> comboRole;
        private UserController controller = new UserController();
        private UserModel model = new UserModel();
        private NotificationCustom notification = new NotificationCustom("Pengguna");
        public boolean isSetting = false;
        public boolean isUpdate = false;
        public String oldUsername;

        public UserView(
                        String title) {
                this.title = title;
                initComponent();
        }

        private void initComponent() {
                initLayout();
                initAdd();
                setContent();
                initStyle();

        }

        private void initLayout() {
                setLayout(new MigLayout("insets 0, fill", "fill,center", "top,fill"));
                mainPanel = new JPanel(new MigLayout("wrap,insets 10, gap 0, fill", "fill", "fill"));
                contentPanel = new JPanel(new MigLayout("wrap,fillx,insets 0 10 10 10", "left,grow,fill", "top,fill"));
        }

        private void initAdd() {
                mainPanel.add(contentPanel);
                add(mainPanel);
        }

        private void initStyle() {
                mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,7%);"
                                + "arc:20");
                contentPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,7%)");
                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +5");
        }

        private void setContent() {
                lbTitle = new JLabel(title);
                String[] role = { "Pengguna", "Admin", };
                txtFullname = new TextFieldCustom(
                                "Masukan Nama lengkap",
                                null,
                                false);
                txtUsername = new TextFieldCustom(
                                "Masukan Nama Pengguna",
                                null,
                                false);

                comboRole = new JComboBox<String>(role);
                comboRole.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20");
                txtPassword = new PasswordFieldCustom(
                                "Masukan Kata Sandi",
                                null,
                                false);
                txtPasswordConfirm = new PasswordFieldCustom(
                                "Konfirmasi Kata Sandi",
                                null,
                                false);
                txtAddress = new TextFieldCustom(
                                "Masukan alamat lengkap",
                                null,
                                false);

                btnCancel = new ButtonCustom(
                                "Batal",
                                null,
                                "#37393c",
                                "#fff",
                                (e) -> {
                                        changeContent(new ManagementUserView());
                                });
                btnSave = new ButtonCustom(
                                "Simpan",
                                null,
                                "#8490fb",
                                "#fff",
                                (e) -> {
                                        getInput();
                                        if (isSetting || isUpdate) {
                                                handleUpdate();
                                        } else {
                                                handleInsert();
                                        }

                                });

                contentPanel.add(lbTitle);
                contentPanel.add(new JSeparator(), "gapy 5");
                contentPanel.add(new JLabel("Nama Lengkap"), "gapy 5");
                contentPanel.add(txtFullname);
                contentPanel.add(new JLabel("Nama pengguna"), "gapy 5, split 2, w 110:140");
                contentPanel.add(new JLabel("Role"), "gapy 5, w 110:140");
                contentPanel.add(txtUsername, "split 2, pushx, w 110:140");
                contentPanel.add(comboRole, "w 110:140");
                contentPanel.add(new JLabel("Kata Sandi"), "gapy 5, split 2");
                contentPanel.add(new JLabel("Kata Sandi Konfirmasi"), "gapy 5, gapx 80");
                contentPanel.add(txtPassword, "split 2");
                contentPanel.add(txtPasswordConfirm);
                contentPanel.add(new JLabel("Alamat"), "gapy 5");
                contentPanel.add(txtAddress, "h 100!");
                contentPanel.add(new JSeparator(), "gapy 5");
                contentPanel.add(btnCancel, "pushx, split 2, w 100!, h 50!");
                contentPanel.add(btnSave, "w 100!,h 50!");

        }

        private void handleInsert() {
                if (controller.blankValidation(model)) {
                        notification.fieldBlankNotification();
                        return;
                }

                if (controller.passwordValidation(model)) {
                        notification.errorPasswordNotification();
                        return;
                }

                if (controller.usernameValidation(model)) {
                        notification.IsnAvailableNotification();
                        return;
                }

                controller.createUser(model);
                notification.successCreateNotification();
                changeContent(new ManagementUserView());
        }

        private void handleUpdate() {
                if (controller.blankValidation(model)) {
                        notification.fieldBlankNotification();
                        return;
                }

                if (controller.passwordValidation(model)) {
                        notification.errorPasswordNotification();
                        return;
                }

                if (!oldUsername.equalsIgnoreCase(txtUsername.getText())
                                && controller.usernameValidation(model)) {
                        notification.IsnAvailableNotification();
                        return;
                }

                controller.updateUser(model);
                notification.successUpdateNotification();

                if (isSetting) {
                        changeContent(new ManagementSpkView());
                        controller.refreshToken(model);
                } else {
                        changeContent(new ManagementUserView());
                }
        }

        private void getInput() {
                model = controller.getInput(
                                txtFullname,
                                txtUsername,
                                txtPassword,
                                txtPasswordConfirm,
                                txtAddress,
                                comboRole,
                                model.getUserID());
        }

        public void setField(UserModel userModel) {
                txtFullname.setText(userModel.getFullname());
                txtUsername.setText(userModel.getUsername());
                comboRole.setSelectedItem(userModel.getRole());
                txtAddress.setText(userModel.getAddress());
                model.setUsername(userModel.getUsername());
                model.setUserID(userModel.getUserID());
        }

        private void changeContent(JPanel panel) {
                removeAll();
                setLayout(new MigLayout("insets 0, fill", "fill,center", "top"));
                putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,7%);"
                                + "arc:20");
                add(panel);
                refreshUI();
        }

        public void disableUpdate() {
                comboRole.setEnabled(false);
        }

        private void refreshUI() {
                repaint();
                revalidate();
        }

}
