package chloro.spk.service;

import java.util.List;
import java.util.Map;

import chloro.spk.model.SpkModel;
import chloro.spk_result.model.SpkResultModel;

public interface SpkService {

    List<SpkModel> getSpkHistory();

    void deleteHistory(SpkModel model);

    List<Map<Object, Object>> getInfo(SpkModel model);

}
