package chloro.criteria.service;

import java.util.List;

import chloro.criteria.model.CriteriaModel;

public interface CriteriaService {

    boolean criteriaIsAvailable(CriteriaModel model);

    void createCriteria(CriteriaModel model);

    void updateCriteria(CriteriaModel model);

    void deleteCriteria(CriteriaModel model);

    List<CriteriaModel> getCriteria();
}
