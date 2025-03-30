package org.example.bluemoon.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.bluemoon.dto.ThongKeResult;
import org.example.bluemoon.models.KhoanThu;
import org.example.bluemoon.models.NopTien;
import org.example.bluemoon.services.HoKhauService;
import org.example.bluemoon.services.KhoanThuService;
import org.example.bluemoon.services.NhanKhauService;
import org.example.bluemoon.services.NopTienService;
import org.example.bluemoon.util.SceneUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


public class DashboardController {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final HoKhauService hoKhauService = new HoKhauService();
    private final NopTienService nopTienService = new NopTienService();
    private final KhoanThuService khoanThuService = new KhoanThuService();


    //main bar
    @FXML
    private Button trang_chu_Button;
    @FXML
    private Button nop_tien_Button;
    @FXML
    private Button khoan_thu_Button;
    @FXML
    private Button can_ho_Button;
    @FXML
    private Button logoutButton;

    @FXML
    StackPane stackPane_trangChu;
    @FXML
    private Label tongHoKhau;
    @FXML
    private Label tongNhanKhau;

    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;


    @FXML
    private Button addNopTienFormButton;
    @FXML
    private TableView<NopTien> tableNopTien;
    @FXML
    TableColumn<NopTien, Integer> columnMaKhoanThu;
    @FXML
    TableColumn<NopTien, Integer> columnMaHoKhau;
    @FXML
    TableColumn<NopTien, String> columnNguoiNop;
    @FXML
    TableColumn<NopTien, Long> columnSoTien;
    @FXML
    TableColumn<NopTien, String> columnNgayNop;


    @FXML
    private Button addKhoanThuFormButton;
    @FXML
    private TableView<KhoanThu> tableKhoanThu;
    @FXML
    TableColumn<KhoanThu, Integer> columnMaKhoanThu1;
    @FXML
    TableColumn<KhoanThu, String> columnTenKhoanThu;
    @FXML
    TableColumn<KhoanThu, String> columnLoaiKhoanThu;
    @FXML
    TableColumn<KhoanThu, String> columnSoTien1;
    @FXML
    TableColumn<KhoanThu, String> columnNgayTao;
    @FXML
    TableColumn<KhoanThu, String> columnThoiHan;

    //khoan thu
    @FXML
    private StackPane stackPane_khoanThu;
    @FXML
    private TextField maKhoanThuField;
    @FXML
    private TextField tenKhoanThuField;
    @FXML
    private RadioButton tuNguyen;
    @FXML
    private RadioButton batBuoc;
    @FXML
    private TextField soTienBatBuocField;
    @FXML
    private DatePicker thoiHanDatePicker;
    @FXML
    private Label checkThemKhoanThu;

    //nop tien
    @FXML
    private StackPane stackPane_nopTien;
    @FXML
    private ComboBox<Integer> maKhoanThuComboBox;
    @FXML
    private TextField maCanHoField;
    @FXML
    private TextField nguoiNopField;
    @FXML
    private TextField soTienNopField;
    @FXML
    private DatePicker ngayNopDatePicker;
    @FXML
    private Label checkThemNopTien;


    @FXML
    public void initialize() {
        HideAllStackPane();
        trangChuButton(new ActionEvent());
        tongHoKhau.setText(String.valueOf(hoKhauService.tongSoHoKhau()));
        tongNhanKhau.setText(String.valueOf(new NhanKhauService().tongSoNhanKhau()));

        // Thiết lập dữ liệu
        loadAllData();
        // Thiết lập RadioButton
        tuNguyen.setOnAction(e -> soTienBatBuocField.setDisable(true));
        batBuoc.setOnAction(e -> soTienBatBuocField.setDisable(false));
    }

    @FXML
    private void logout(ActionEvent event) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        SceneUtil.changeScene(stage, "/org/example/bluemoon/views/login.fxml");
        stage.centerOnScreen();
    }

    @FXML
    private void trangChuButton(ActionEvent event) {
        resetButtonStyle();
        trang_chu_Button.getStyleClass().add("active");
        addNopTienFormButton.setVisible(true);
        addKhoanThuFormButton.setVisible(true);
        resetFieldsKhoanThu();
        resetFieldsNopTien();
        HideAllStackPane();

    }

    @FXML
    private void canHoButton(ActionEvent event) {
        resetButtonStyle();
        can_ho_Button.getStyleClass().add("active");
        resetFieldsKhoanThu();
        resetFieldsNopTien();
        HideAllStackPane();
        //tạo cửa sổ alert thông báo tính năng đang phát triển
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Chức năng này đang được phát triển!\nNhấn OK để quay lại trang chủ!");
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/org/example/bluemoon/style/alert-style.css").toExternalForm());
        alert.setGraphic(null);
        alert.getButtonTypes().setAll(ButtonType.OK);
        // Khi người dùng nhấn OK, gọi action của nút Trang Chủ
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                trangChuButton(new ActionEvent()); // Gọi trực tiếp phương thức
            }
        });
    }

    private void HideAllStackPane() {
        stackPane_khoanThu.setVisible(false);
        stackPane_nopTien.setVisible(false);
    }

    private void resetButtonStyle() {
        trang_chu_Button.getStyleClass().remove("active");
        nop_tien_Button.getStyleClass().remove("active");
        khoan_thu_Button.getStyleClass().remove("active");
        can_ho_Button.getStyleClass().remove("active");
    }

    private void resetFieldsNopTien() {
        maKhoanThuComboBox.setValue(null);
        maCanHoField.setText("");
        nguoiNopField.setText("");
        soTienNopField.setText("");
        ngayNopDatePicker.setValue(null);
    }

    private void resetFieldsKhoanThu() {
        maKhoanThuField.setText("");
        tenKhoanThuField.setText("");
        tuNguyen.setSelected(false);
        batBuoc.setSelected(false);
        soTienBatBuocField.setText("");
        thoiHanDatePicker.setValue(null);
    }


    @FXML
    private void openNopTienForm(ActionEvent event) {
        checkThemNopTien.setVisible(false);
        HideAllStackPane();
        stackPane_nopTien.setVisible(true);
        addNopTienFormButton.setVisible(false);
        addKhoanThuFormButton.setVisible(false);
        resetButtonStyle();
        resetFieldsKhoanThu();
        nop_tien_Button.getStyleClass().add("active");

    }

    @FXML
    private void openKhoanThuForm(ActionEvent event) {
        checkThemKhoanThu.setVisible(false);
        HideAllStackPane();
        stackPane_khoanThu.setVisible(true);
        addNopTienFormButton.setVisible(false);
        addKhoanThuFormButton.setVisible(false);
        resetButtonStyle();
        resetFieldsKhoanThu();
        khoan_thu_Button.getStyleClass().add("active");
    }

    @FXML
    private void addKhoanThuDataOnAction(ActionEvent event) {
        // Lấy dữ liệu từ các trường nhập liệu
        String maKhoanThu = maKhoanThuField.getText();
        String tenKhoanThu = tenKhoanThuField.getText();
        LocalDate thoiHan = thoiHanDatePicker.getValue();
        // Kiểm tra và xử lý dữ liệu
        if (maKhoanThu.trim().isEmpty() || tenKhoanThu.trim().isEmpty() || thoiHan == null || (!tuNguyen.isSelected() && !batBuoc.isSelected())) {
            checkThemKhoanThu.setText("Vui lòng nhập đầy đủ thông tin!");
            checkThemKhoanThu.setVisible(true);
            return;
        }
        // Lấy danh sách ID
        ObservableList<Integer> idList = maKhoanThuComboBox.getItems();

        // **Chuyển maKhoanThu sang Integer và kiểm tra trong danh sách**
        try {
            int idKhoanThuMoi = Integer.parseInt(maKhoanThu);
            if (idList.contains(idKhoanThuMoi)) {
                checkThemKhoanThu.setText("Mã khoản thu đã tồn tại!");
                checkThemKhoanThu.setVisible(true);
                return;
            }
        } catch (NumberFormatException e) {
            checkThemKhoanThu.setText("Mã khoản thu phải là số!");
            checkThemKhoanThu.setVisible(true);
            return;
        }
        checkThemKhoanThu.setVisible(false);
        if (tuNguyen.isSelected()) {
            // Nếu là khoản thu tự nguyện
            khoanThuService.addKhoanThuTuNguyen(maKhoanThu, tenKhoanThu, thoiHan);
        } else if (batBuoc.isSelected()) {
            // Nếu là khoản thu bắt buộc
            String soTienBatBuoc = soTienBatBuocField.getText();
            if (soTienBatBuoc.trim().isEmpty()) {
                checkThemKhoanThu.setVisible(true);
                return;
            }
            try {
                Long soTien = Long.parseLong(soTienBatBuoc);
                khoanThuService.addKhoanThuBatBuoc(maKhoanThu, tenKhoanThu, soTien, thoiHan);
            } catch (NumberFormatException e) {
                checkThemKhoanThu.setText("Số tiền không hợp lệ");
                checkThemKhoanThu.setVisible(true);
                return;
            }
        }

        // Cập nhật lại bảng
        loadDataKhoanThu();
        loadDataToChart();

        // Đóng form thêm khoản thu
        if (!checkThemKhoanThu.isVisible()) {
            stackPane_khoanThu.setVisible(false);
        }

    }

    @FXML
    private void addNopTienDataOnAction(ActionEvent event) {
        // Lấy dữ liệu từ các trường nhập liệu
        Integer khoanThuId = maKhoanThuComboBox.getValue(); // Lấy ID từ ComboBox
        String maCanHo = maCanHoField.getText();
        String nguoiNop = nguoiNopField.getText();
        String soTienNop = soTienNopField.getText();
        LocalDate ngayNop = ngayNopDatePicker.getValue();

        // Kiểm tra và xử lý dữ liệu
        if (khoanThuId == null || maCanHo.trim().isEmpty() || nguoiNop.trim().isEmpty() || soTienNop.trim().isEmpty() || ngayNop == null) {
            checkThemNopTien.setText("Vui lòng nhập đầy đủ thông tin!");
            checkThemNopTien.setVisible(true);
            return;
        }

        checkThemNopTien.setVisible(false);
        try {
            Long soTien = Long.parseLong(soTienNop);
            Integer maCanHoInt = Integer.parseInt(maCanHo);

            // Thêm dữ liệu vào cơ sở dữ liệu
            nopTienService.addNopTien(khoanThuId, maCanHoInt, nguoiNop, soTien);
        } catch (NumberFormatException e) {
            checkThemNopTien.setText("Số tiền hoặc mã căn hộ không hợp lệ");
            checkThemNopTien.setVisible(true);
            return;
        }

        // Cập nhật lại bảng
        loadDataNopTien();
        loadDataToChart();

        // Đóng form thêm khoản thu nếu không có lỗi
        if (!checkThemNopTien.isVisible()) {
            stackPane_nopTien.setVisible(false);
        }
    }


    private void loadAllData() {
        loadDataNopTien();
        loadDataKhoanThu();
        loadDataToChart();
    }

    private void loadDataNopTien() {
        // Lấy danh sách từ cơ sở dữ liệu hoặc service
        List<NopTien> nopTienList = nopTienService.getLatestNopTien();
        ObservableList<NopTien> data = FXCollections.observableArrayList(nopTienList);

        // Cấu hình từng cột để hiển thị dữ liệu
        columnMaKhoanThu.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getKhoanThu().getId()).asObject());

        columnMaHoKhau.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getHoNop().getMaHoKhau()).asObject());

        columnNguoiNop.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNguoiNop().getHoTen()));

        columnSoTien.setCellValueFactory(cellData ->
                new SimpleLongProperty(cellData.getValue().getSoTien()).asObject());

        columnNgayNop.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getNgayNop();
            return new SimpleStringProperty(date != null ? date.format(FORMATTER) : "");
        });

        // Đổ dữ liệu vào bảng
        tableNopTien.setItems(data);
    }

    private void loadDataKhoanThu() {
        // Lấy danh sách từ cơ sở dữ liệu hoặc service
        List<KhoanThu> khoanThuList = khoanThuService.getKhoanThuList();
        ObservableList<KhoanThu> dataKhoanThu = FXCollections.observableArrayList(khoanThuList);

        // Cấu hình từng cột để hiển thị dữ liệu
        columnMaKhoanThu1.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        columnTenKhoanThu.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTenKhoanThu()));

        columnLoaiKhoanThu.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLoaiKhoanThu().toString()));

        columnSoTien1.setCellValueFactory(cellData -> {
            Long soTien = cellData.getValue().getSoTien();
            return new SimpleStringProperty(soTien != null ? String.valueOf(soTien) : "");
        });

        columnNgayTao.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getNgayTao();
            return new SimpleStringProperty(date != null ? date.format(FORMATTER) : "");
        });

        columnThoiHan.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getThoiHan();
            return new SimpleStringProperty(date != null ? date.format(FORMATTER) : "");
        });

        // Đổ dữ liệu vào bảng
        tableKhoanThu.setItems(dataKhoanThu);
        // Đổ dữ liệu vào ComboBox
        loadIdComboBoxFromTableData(khoanThuList);
    }

    // Hàm cập nhật dữ liệu vào ComboBox từ danh sách đã có
    public void loadIdComboBoxFromTableData(List<KhoanThu> khoanThuList) {
        ObservableList<Integer> idList = FXCollections.observableArrayList();

        for (KhoanThu kt : khoanThuList) {
            idList.add(kt.getId()); // Lấy ID từ danh sách có sẵn
        }

        maKhoanThuComboBox.setItems(idList); // Cập nhật danh sách vào ComboBox
    }

    //  Hàm cập nhật dữ liệu vào biểu đồ
    private void loadDataToChart() {
        List<ThongKeResult> dataList = nopTienService.getThongKeNopTien(); // Lấy dữ liệu từ service

        // Xóa dữ liệu cũ
        barChart.getData().clear();
        xAxis.getCategories().clear();

        // Nếu danh sách rỗng, không cập nhật biểu đồ
        if (dataList.isEmpty()) {
            return;
        }

        // Lấy danh sách các tháng để đặt danh mục trục X
        List<String> categories = dataList.stream()
                .map(ThongKeResult::getThangThu)
                .toList(); // Lấy danh sách tháng thu
        setCategories(categories); // Cập nhật danh mục trục X

        // Tạo dữ liệu cho biểu đồ
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (ThongKeResult data : dataList) {
            series.getData().add(new XYChart.Data<>(data.getThangThu(), data.getTiLeNopTien()));
        }

        // Thêm dữ liệu mới vào biểu đồ
        barChart.getData().add(series);
        barChart.setLegendVisible(false); // Ẩn legend nếu không cần thiết

        adjustBarGap(); // Cập nhật khoảng cách giữa các cột
    }

    //  Cập nhật danh mục trục X
    public void setCategories(List<String> categories) {
        xAxis.getCategories().clear();
        xAxis.getCategories().addAll(categories);
    }

    //  Điều chỉnh khoảng cách giữa các cột dựa trên số lượng cột
    private void adjustBarGap() {
        int columnCount = xAxis.getCategories().size();
        double chartWidth = barChart.getPrefWidth();

        if (columnCount > 0) {
            double newBarGap = Math.max(5, chartWidth / (columnCount * 40)); // Tăng khoảng cách giữa các cột
            double newCategoryGap = Math.max(10, chartWidth / (columnCount * 15)); // Tăng khoảng cách giữa các nhóm cột

            barChart.setBarGap(newBarGap);
            barChart.setCategoryGap(newCategoryGap);
        }
    }

    //  Thêm cột mới vào biểu đồ
    public void addColumn(String category, double value) {
        if (!xAxis.getCategories().contains(category)) {
            xAxis.getCategories().add(category); // Thêm danh mục mới
        }

        Optional<XYChart.Series<String, Number>> seriesOptional = barChart.getData().stream().findFirst();
        seriesOptional.ifPresent(series -> series.getData().add(new XYChart.Data<>(category, value)));

        adjustBarGap(); // Cập nhật khoảng cách giữa các cột
    }

    //  Xóa cột khỏi biểu đồ
    public void removeColumn(String category) {
        xAxis.getCategories().remove(category); // Xóa danh mục khỏi trục X

        barChart.getData().forEach(series ->
                series.getData().removeIf(data -> data.getXValue().equals(category))
        );

        adjustBarGap(); // Cập nhật lại khoảng cách giữa các cột
    }

}