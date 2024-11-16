package chloro.alternative.controller;

import java.util.List;

import chloro.UI.TextField.TextFieldCustom;
import chloro.alternative.dao.AlternativeDao;
import chloro.alternative.model.AlternativeModel;

public class AlternativeController {
    private AlternativeDao dao;
    AlternativeModel model = new AlternativeModel();

    public AlternativeController() {
        this.dao = new AlternativeDao();
    }

    public List<AlternativeModel> getData() {
        return dao.getAlternative();
    }

    public void deleteAlternative(AlternativeModel model) {
        dao.deleteAlternative(model);
    }

    public String[] tableHeader() {
        return model.getColumnHeader();
    }

    public void createAlternative(AlternativeModel model) {
        dao.createAlternative(model);
    }

    public void updateAlternative(AlternativeModel model) {
        dao.updateAlternative(model);
    }

    public AlternativeModel getInput(
            TextFieldCustom txtAlternativeName, String id) {
        AlternativeModel model = new AlternativeModel();

        model.setAlternativeName(txtAlternativeName.getText());
        model.setAlternativeId(id);

        return model;

    }

    public boolean blankValidation(AlternativeModel model) {
        boolean alternativeIsBlank = model.getAlternativeName().isEmpty();
        return alternativeIsBlank;
    }

    public boolean alternativeValidation(AlternativeModel model) {
        return dao.alternativeIsAvailable(model);
    }
}
