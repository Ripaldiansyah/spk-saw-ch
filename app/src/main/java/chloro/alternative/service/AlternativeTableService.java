package chloro.alternative.service;

import java.util.List;

import chloro.alternative.model.AlternativeModel;

public interface AlternativeTableService {
    void setData(List<AlternativeModel> criteria);

    void removeData(int index);

    void clear();

    AlternativeModel getSelectedIndex(int index);
}
