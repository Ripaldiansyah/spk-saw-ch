package chloro.spk_count.service;

import java.util.List;

import chloro.spk_count.model.SPKCountModel;

public interface SPKCountTableService {
    void setData(List<List<Object>> criteria);

    void removeData(int index);

    void clear();

    Object getSelectedIndex(int index);
}
