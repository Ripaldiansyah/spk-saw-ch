package chloro.criteria.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.FlatClientProperties;

import chloro.UI.Icon.IconCustom;
import chloro.UI.Notification.NotificationCustom;
import chloro.UI.Table.TableCustom;
import chloro.UI.button.ButtonCustom;
import chloro.criteria.controller.CriteriaController;
import chloro.criteria.model.CriteriaModel;
import chloro.criteria.model.CriteriaTableModel;
import chloro.util.Report;
import net.miginfocom.swing.MigLayout;

public class ManagementCriteriaView extends JPanel {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel headerTable;
    private JPanel contentPanel;
    private ButtonCustom btnDelete;
    private ButtonCustom btnAdd;
    private ButtonCustom btnEdit;
    private ButtonCustom btnPrint;
    private JScrollPane scrollPane;
    private JLabel lbTitle;
    private int indexRow;
    private CriteriaTableModel criteriaTableModel = new CriteriaTableModel();
    private CriteriaController controller = new CriteriaController();

    public ManagementCriteriaView() {
        initComponent();
    }

    private void initComponent() {
        initLayout();
        initAdd();
        setHeader();
        initStyle();
        setContent();
    }

    private void initLayout() {
        setLayout(new MigLayout("insets 0, fill", "fill,center", "top,fill"));
        mainPanel = new JPanel(new MigLayout("wrap,insets 10, gap 0", "fill", "fill"));
        headerPanel = new JPanel(new MigLayout("insets 10", "left", "top,30:40"));
        headerTable = new JPanel(new MigLayout("wrap,fillx,insets 10 10 0 10", "left,grow,fill", "top,fill"));
        contentPanel = new JPanel(new MigLayout("wrap,fillx,insets 0 10 10 10", "left,grow,fill", "top,fill"));
        scrollPane = new JScrollPane(contentPanel);
    }

    private void initAdd() {
        mainPanel.add(headerPanel);
        mainPanel.add(headerTable);
        mainPanel.add(scrollPane, "grow,push,top");
        add(mainPanel);
    }

    private void initStyle() {

        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%);"
                + "arc:20");
        headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%)");
        headerTable.putClientProperty(FlatClientProperties.STYLE, ""
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
        lbTitle = new JLabel("Manajemen Kriteria");

        IconCustom iconPrint = new IconCustom("svg/print.svg", 1f, null);
        IconCustom iconAdd = new IconCustom("svg/add.svg", 1f, null);
        IconCustom iconDelete = new IconCustom("svg/delete.svg", 1f, null);
        IconCustom iconEdit = new IconCustom("svg/edit.svg", 1f, null);

        btnPrint = new ButtonCustom(
                null,
                iconPrint.getIcon(),
                "#37393c",
                null,
                (e) -> {
                    Report report = new Report();
                    report.ReportCriteria();
                });
        btnAdd = new ButtonCustom(
                null,
                iconAdd.getIcon(),
                null,
                null,
                (e) -> {
                    changeContent(new CriteriaView("Tambah Kriteria"));
                });
        btnEdit = new ButtonCustom(
                null,
                iconEdit.getIcon(),
                "#B1CCF3",
                null,
                (e) -> {
                    handleUpdate();
                });
        btnDelete = new ButtonCustom(
                null,
                iconDelete.getIcon(),
                "#C32148",
                null,
                (e) -> {
                    handleDelete();
                });

        btnPrint.setToolTipText("Cetak Dokumen");
        btnAdd.setToolTipText("Tambah Kriteria");
        btnEdit.setToolTipText("Ubah Kriteria");
        btnDelete.setToolTipText("Hapus Kriteria");

        headerPanel.add(lbTitle, "pushx");
        headerPanel.add(btnPrint);
        headerPanel.add(btnDelete);
        headerPanel.add(btnEdit);
        headerPanel.add(btnAdd);
        disableButton();

    }

    private void setContent() {
        criteriaTableModel.setData(controller.getData());
        TableCustom criteriaTable = new TableCustom(criteriaTableModel, null);
        criteriaTable.getTable().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexRow = criteriaTable.getSelectedRow();
                enableButton();
            }
        });
        headerTable.add(criteriaTable.getheader(), "push,h 40!");
        contentPanel.add(criteriaTable, "grow, push");
    }

    private void changeContent(JPanel panel) {
        removeAll();
        setLayout(new MigLayout("insets 0, fill", "fill,center", "top"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%);"
                + "arc:20");
        add(panel);
        refreshUI();
    }

    private void refreshUI() {
        repaint();
        revalidate();
    }

    private void disableButton() {
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
    }

    private void enableButton() {
        btnDelete.setEnabled(true);
        btnEdit.setEnabled(true);
    }

    private void handleDelete() {
        CriteriaModel model = new CriteriaModel();
        model.setCriteriaId(criteriaTableModel.getSelectedIndex(indexRow).getCriteriaId());
        controller.deletecriteria(model);
        NotificationCustom notification = new NotificationCustom("Kriteria");
        notification.deleteNotification();
        criteriaTableModel.removeData(indexRow);
        disableButton();
    }

    private void handleUpdate() {
        CriteriaModel model = new CriteriaModel();
        CriteriaView criteriaView = new CriteriaView("Ubah Krieria");
        criteriaView.isUpdate = true;
        criteriaView.oldCriteriaName = criteriaTableModel.getSelectedIndex(indexRow).getCriteriaName();

        model.setCriteriaId(criteriaTableModel.getSelectedIndex(indexRow).getCriteriaId());
        model.setCriteriaName(criteriaTableModel.getSelectedIndex(indexRow).getCriteriaName());
        model.setCriteriaType(criteriaTableModel.getSelectedIndex(indexRow).getCriteriaType());
        model.setCriteriaWeight(criteriaTableModel.getSelectedIndex(indexRow).getCriteriaWeight());

        criteriaView.setField(model);
        changeContent(criteriaView);
    }

}
