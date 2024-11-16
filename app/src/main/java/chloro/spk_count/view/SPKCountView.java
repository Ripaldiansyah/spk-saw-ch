package chloro.spk_count.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.formdev.flatlaf.FlatClientProperties;

import chloro.UI.Notification.NotificationCustom;
import chloro.UI.Table.TableCustom;
import chloro.UI.button.ButtonCustom;
import chloro.spk.view.ManagementSpkView;
import chloro.spk_count.controller.SPKCountController;
import chloro.spk_count.model.SPKCountModel;
import chloro.spk_count.model.SPKCountTableModel;
import chloro.spk_count.util.SAWAlgorithm;
import chloro.spk_result.model.SpkResultModel;
import chloro.spk_result.view.SpkResultView;
import net.miginfocom.swing.MigLayout;

import javax.swing.JOptionPane;

public class SPKCountView extends JPanel {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel alternativeTitle;
    private JPanel contentPanel;
    private ButtonCustom btnCount;
    private ButtonCustom btnBack;
    private JScrollPane scrollPane;
    private JLabel lbTitle;
    private SPKCountTableModel spkCountTableModel = new SPKCountTableModel();
    private SPKCountController controller = new SPKCountController();
    private List<List<Object>> rowData = new ArrayList<>();
    private List<Map<Object, Object>> alternativeListMap = new ArrayList<>();
    private List<Map<Object, Object>> criteriaListMap = new ArrayList<>();
    public static SpkResultModel resultModel;
    private String nameSave;

    public SPKCountView() {
        initComponent();
    }

    private void initLayout() {
        setLayout(new MigLayout("insets 0, fill", "fill,center", "top,fill"));
        mainPanel = new JPanel(new MigLayout("wrap,insets 10, gap 0, fill", "fill", "fill"));
        headerPanel = new JPanel(new MigLayout("insets 10", "left", "top,30:40"));
        alternativeTitle = new JPanel(new MigLayout("wrap,fillx,insets 10 10 0 10", "left,grow,fill", "top,fill"));
        contentPanel = new JPanel(new MigLayout("wrap,fill,insets 0 10 10 10", "left,grow,fill", "top,fill"));
        scrollPane = new JScrollPane(contentPanel);
    }

    private void initAdd() {

        mainPanel.add(headerPanel);
        mainPanel.add(alternativeTitle);
        mainPanel.add(scrollPane, "grow,push,top");
        add(mainPanel);
    }

    private void initComponent() {

        initLayout();
        initAdd();
        setHeader();
        initStyle();
        setContent();
    }

    private void initStyle() {

        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%);"
                + "arc:20");
        headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%)");
        alternativeTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%)");
        contentPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%)");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10");
        scrollPane.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0");
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "width:10");
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

    }

    private void setHeader() {
        lbTitle = new JLabel("Hitung SPK");

        btnCount = new ButtonCustom(
                "Hitung",
                null,
                null,
                null,
                (e) -> {
                    nameSave = JOptionPane.showInputDialog("Simpan sebagai");
                    if (nameSave == null) {
                        JOptionPane.showMessageDialog(null, "Masukan nama save");
                        return;
                    }
                    if (nameSave.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Masukan nama save");
                        return;
                    }
                    getDataInput();
                });
        btnBack = new ButtonCustom(
                "Kembali",
                null,
                "#f2f2f2",
                "#000",
                (e) -> {
                    changeContent(new ManagementSpkView());
                });

        headerPanel.add(lbTitle, "pushx");
        headerPanel.add(btnBack, "h 40!, w 100!");
        headerPanel.add(btnCount, "h 40!, w 100!");

    }

    private void setContent() {
        spkCountTableModel.setData(controller.getData());
        TableCustom countTable = new TableCustom(spkCountTableModel, null);

        countTable.getTable().setRowSorter(null);
        alternativeTitle.add(countTable.getheader(), "push,h 40!");
        contentPanel.add(countTable, "grow, push");
    }

    public void changeContent(JPanel panel) {
        removeAll();
        setLayout(new MigLayout("insets 0, fill", "fill,center", "top"));
        add(panel);
        refreshUI();
    }

    private void getDataInput() {
        int rowCount = spkCountTableModel.getRowCount();
        int columnCount = spkCountTableModel.getColumnCount();

        for (int i = 0; i < rowCount; i++) {
            List<Object> columnData = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) {
                if (spkCountTableModel.getValueAt(i, j) == null) {
                    NotificationCustom notification = new NotificationCustom("Perhitungan");
                    notification.fieldBlankNotification();
                    rowData.clear();
                    return;
                }

                columnData.add(spkCountTableModel.getValueAt(i, j));
            }
            rowData.add(columnData);
        }
        spkCountTableModel.setData(rowData);
        convertToMap();

    }

    private void convertToMap() {
        int criteriaSize = rowData.get(0).size();
        for (int i = 1; i < criteriaSize; i++) {
            Map<Object, Object> criteriaMap = new LinkedHashMap<>();
            SPKCountModel model = new SPKCountModel();
            model.setCriteriaName(controller.tableHeader()[i]);
            criteriaMap.put("criteriaName", controller.tableHeader()[i]);
            criteriaMap.put("criteriaType", controller.getCriteriaType(model));
            criteriaListMap.add(criteriaMap);
        }

        for (int i = 0; i < rowData.size(); i++) {
            Map<Object, Object> alternativeMap = new LinkedHashMap<>();
            alternativeMap.put("alternativeName", rowData.get(i).get(0));
            for (int j = 1; j < rowData.get(i).size(); j++) {
                alternativeMap.put(controller.tableHeader()[j], Double.parseDouble(rowData.get(i).get(j).toString()));
            }
            alternativeListMap.add(alternativeMap);
        }
        SAWAlgorithm saw = new SAWAlgorithm(alternativeListMap, criteriaListMap, nameSave);

        changeContent(new SpkResultView(resultModel));
    }

    public void refreshUI() {
        repaint();
        revalidate();
    }

}
