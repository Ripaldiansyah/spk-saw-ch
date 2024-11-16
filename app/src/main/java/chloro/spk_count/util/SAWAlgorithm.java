package chloro.spk_count.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import chloro.spk_count.controller.SPKCountController;
import chloro.spk_count.model.SPKCountModel;
import chloro.spk_count.view.SPKCountView;
import chloro.spk_result.controller.SpkResultController;
import chloro.spk_result.model.SpkResultModel;

public class SAWAlgorithm {

    public SAWAlgorithm(
            List<Map<Object, Object>> inputList,
            List<Map<Object, Object>> criteriaTypeMap,
            String nameSave) {
        this.inputListMap = inputList;
        this.criteriaTypeMap = criteriaTypeMap;
        this.nameSave = nameSave;
        getCriteriaWeight();
        getInterest();
        getDivider();
        normalizeMatrix();
        preference();
        rank();
        // setTable();
    }

    // SAW Proses ke-1
    void getCriteriaWeight() {
        for (Map<Object, Object> criteria : criteriaTypeMap) {
            String criteriaName = criteria.get("criteriaName").toString();
            model.setCriteriaName(criteriaName);
            criteriaWeightList.add(controller.getCriteriaWeight(model));
        }

        sumCriteriaWeight = criteriaWeightList.stream().reduce(0.0, Double::sum);
    }

    // SAW Proses ke-2
    void getInterest() {
        for (double criteriaWeight : criteriaWeightList) {
            double interest = criteriaWeight / sumCriteriaWeight;
            interestList.add(interest);
        }
    }

    // SAW Proses ke-3
    void getDivider() {
        int after = 1;
        for (int i = 0; i < criteriaTypeMap.size(); i++) {
            List<Double> dividerTemp = new ArrayList<>();

            for (Map<Object, Object> input : inputListMap) {
                int before = 0;
                for (Map.Entry<Object, Object> criteriaInput : input.entrySet()) {
                    if (before == after) {
                        double value = (Double) criteriaInput.getValue();
                        dividerTemp.add(value);

                    }
                    before++;
                }
            }
            String criteriaType = criteriaTypeMap.get(i).get("criteriaType").toString();
            criteriaTypeList.add(criteriaType);
            boolean isBenefit = criteriaType.equalsIgnoreCase("benefit") ? true : false;
            double divider = 0;
            if (isBenefit) {
                divider = Collections.max(dividerTemp);
            } else {
                divider = Collections.min(dividerTemp);
            }
            dividerList.add(divider);
            after++;
        }
    }

    // SAW Proses ke-4
    void normalizeMatrix() {
        int after = 1;
        for (int i = 0; i < criteriaTypeMap.size(); i++) {
            for (Map<Object, Object> input : inputListMap) {
                boolean isCost = criteriaTypeList.get(i).equalsIgnoreCase("cost") ? true : false;
                int before = 0;
                for (Map.Entry<Object, Object> criteriaInput : input.entrySet()) {
                    if (before == after) {
                        double value = (Double) criteriaInput.getValue();
                        double normalize;
                        if (isCost) {
                            normalize = dividerList.get(i) / (double) value;
                        } else {
                            normalize = (double) value / dividerList.get(i);
                        }
                        normalizeList.add(normalize);
                    }
                    before++;
                }
            }
            after++;
        }
    }

    // SAW Proses ke-5
    void preference() {
        for (int indexInput = 0; indexInput < inputListMap.size(); indexInput++) {
            double preferenceValue = 0;
            int index = indexInput;
            for (int indexCriteria = 0; indexCriteria < criteriaTypeMap.size(); indexCriteria++) {
                double value = (interestList.get(indexCriteria) * normalizeList.get(index));
                preferenceValue += value;
                index += inputListMap.size();
            }

            preferenceList.add(preferenceValue);

        }

    }

    void rank() {
        List<Map<Object, Object>> alternativeListMap = new ArrayList<>(inputListMap);
        List<Double> sortedList = new ArrayList<>(preferenceList);
        Collections.sort(sortedList, Collections.reverseOrder());

        for (int i = 0; i < preferenceList.size(); i++) {
            Map<Object, Object> rankMap = new LinkedHashMap<>();
            Map<Object, Object> rowData = alternativeListMap.get(i);
            rankMap.put("alternativeName", rowData.get("alternativeName"));
            int rank = sortedList.indexOf(preferenceList.get(i)) + 1;
            rankMap.put("rank", rank);
            rankListMap.add(rankMap);
        }
        setTable();
    }

    void setTable() {
        SpkResultModel resultModel = new SpkResultModel();
        resultModel.setRankListMap(rankListMap);
        resultModel.setSpkSaveName(nameSave);
        SPKCountView.resultModel = resultModel;
        SpkResultController controllerResult = new SpkResultController();
        controllerResult.saveSpk(resultModel);

    }

    public List<Map<Object, Object>> inputListMap = new ArrayList<>();
    public List<Map<Object, Object>> criteriaTypeMap = new ArrayList<>();
    List<Double> criteriaWeightList = new ArrayList<>();
    List<String> criteriaTypeList = new ArrayList<>();
    List<Double> interestList = new ArrayList<>();
    List<Double> dividerList = new ArrayList<>();
    List<Double> normalizeList = new ArrayList<>();
    List<Double> preferenceList = new ArrayList<>();
    List<Map<Object, Object>> rankListMap = new ArrayList<>();
    double sumCriteriaWeight;
    public String nameSave;
    private SPKCountController controller = new SPKCountController();
    private SPKCountModel model = new SPKCountModel();

}
