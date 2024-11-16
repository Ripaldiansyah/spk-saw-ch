package chloro.user.service;

import java.util.List;

import chloro.user.model.UserModel;

public interface UserService {

    boolean usernameIsAvailable(UserModel model);

    void createUser(UserModel model);

    void updateUser(UserModel model);

    void deleteUser(UserModel model);

    List<UserModel> getUser();
}
