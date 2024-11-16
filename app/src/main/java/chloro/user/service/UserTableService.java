package chloro.user.service;

import java.util.List;

import chloro.user.model.UserModel;

public interface UserTableService {
    void setData(List<UserModel> user);

    void removeData(int index);

    void clear();

    UserModel getSelectedIndex(int index);
}
