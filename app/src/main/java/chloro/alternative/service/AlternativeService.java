package chloro.alternative.service;

import java.util.List;

import chloro.alternative.model.AlternativeModel;

public interface AlternativeService {

    boolean alternativeIsAvailable(AlternativeModel model);

    void createAlternative(AlternativeModel model);

    void updateAlternative(AlternativeModel model);

    void deleteAlternative(AlternativeModel model);

    List<AlternativeModel> getAlternative();
}
