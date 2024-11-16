package chloro.login.controller;

import chloro.App;
import chloro.UI.PasswordField.PasswordFieldCustom;
import chloro.UI.TextField.TextFieldCustom;
import chloro.home.view.HomeView;
import chloro.login.dao.LoginDao;
import chloro.login.model.LoginModel;

public class LoginController {

    private LoginDao dao;

    public LoginController() {
        this.dao = new LoginDao();
    }

    public LoginModel getInput(
            TextFieldCustom txtUsername,
            PasswordFieldCustom txtPassword) {
        LoginModel model = new LoginModel();

        model.setUsername(txtUsername.getText());
        model.setPassword(txtPassword.getText());

        return model;
    }

    public boolean isRegistered(LoginModel model) {
        return dao.isRegistered(model);
    }

    public void login() {
        App.content.changeContentPanel(new HomeView());
    }

}
