package chloro.login.service;

import chloro.login.model.LoginModel;

public interface LoginService {

    boolean isRegistered(LoginModel model);
}
