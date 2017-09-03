import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main extends Application {
    private Stage window;
    Scene scene;
    VBox layout;
    //Table Data
    TableView<Record> table;
    TableColumn<Record, RecordDate> seriesDateColumn;
    TableColumn<Record, RecordDate> purchaseDateColumn;
    TableColumn<Record, RecordDate> saleDateColumn;
    TableColumn<Record, String> equityNameColumn;
    TableColumn<Record, Integer> numberOfLotsColumn;
    TableColumn<Record, Integer> lotSizeColumn;
    TableColumn<Record, BigDecimal> purchasePriceColumn;
    TableColumn<Record, BigDecimal> salePriceColumn;
    TableColumn<Record, BigDecimal> purchaseBrokerageColumn;
    TableColumn<Record, BigDecimal> saleBrokerageColumn;
    TableColumn<Record, BigDecimal> netProfitLossColumn;
    ObservableList<Record> RECORD_LIST;
    //Add Record Data
    HBox addRecordSection;
    TextField equityNameInput;
    TextField numberOfLotsInput;
    TextField lotSizeInput;
    TextField purchasePriceInput;
    TextField salePriceInput;
    Button addButton;

    DatePicker seriesDatePicker;
    DatePicker purchaseDatePicker;
    DatePicker saleDatePicker;

    //Remove Record
    Button removeButton;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Database.loadData();
        setUserInterface();
        addButton.setOnAction(e -> onClickAddButton());
        removeButton.setOnAction(e -> onClickRemoveButton());
        window.setOnCloseRequest(new javafx.event.EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                onClose(event);
            }
        });
        window.show();
    }
    private void setUserInterface() {
        RECORD_LIST = FXCollections.observableArrayList(Database.RECORDS);

        window.setTitle("Equity Transaction Record Management");

        seriesDateColumn = new TableColumn<>("Series Date");
        seriesDateColumn.setMinWidth(100);
        seriesDateColumn.setCellValueFactory(new PropertyValueFactory<>("seriesDate"));

        purchaseDateColumn = new TableColumn<>("Purchase Date");
        purchaseDateColumn.setMinWidth(100);
        purchaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

        saleDateColumn = new TableColumn<>("Sale Date");
        saleDateColumn.setMinWidth(100);
        saleDateColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));

        equityNameColumn = new TableColumn<>("Equity Name");
        equityNameColumn.setMinWidth(100);
        equityNameColumn.setCellValueFactory(new PropertyValueFactory<>("equityName"));

        numberOfLotsColumn = new TableColumn<>("Number of Lots");
        numberOfLotsColumn.setMinWidth(100);
        numberOfLotsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfLots"));

        lotSizeColumn = new TableColumn<>("Lot Size");
        lotSizeColumn.setMinWidth(100);
        lotSizeColumn.setCellValueFactory(new PropertyValueFactory<>("lotSize"));

        purchasePriceColumn = new TableColumn<>("Purchase Price");
        purchasePriceColumn.setMinWidth(100);
        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));

        salePriceColumn = new TableColumn<>("Sale Price");
        salePriceColumn.setMinWidth(100);
        salePriceColumn.setCellValueFactory(new PropertyValueFactory<>("salePrice"));

        purchaseBrokerageColumn = new TableColumn<>("Purchase Brokerage");
        purchaseBrokerageColumn.setMinWidth(100);
        purchaseBrokerageColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseBrokerage"));

        saleBrokerageColumn = new TableColumn<>("Sale Brokerage");
        saleBrokerageColumn.setMinWidth(100);
        saleBrokerageColumn.setCellValueFactory(new PropertyValueFactory<>("saleBrokerage"));

        netProfitLossColumn = new TableColumn<>("Net Profit/Loss");
        netProfitLossColumn.setMinWidth(100);
        netProfitLossColumn.setCellValueFactory(new PropertyValueFactory<>("netProfitLoss"));

        table = new TableView<>(RECORD_LIST);
        table.getColumns().addAll(seriesDateColumn, purchaseDateColumn, saleDateColumn,
                equityNameColumn, numberOfLotsColumn, lotSizeColumn,
                purchasePriceColumn, salePriceColumn, purchaseBrokerageColumn, saleBrokerageColumn,
                netProfitLossColumn);

        addRecordSection = new HBox(10);
        addRecordSection.setPadding(new Insets(10, 10, 10, 10));

        seriesDatePicker = new DatePicker();
        seriesDatePicker.setMinWidth(100);
        seriesDatePicker.setPromptText("Series Date");
        seriesDatePicker.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });

        purchaseDatePicker = new DatePicker();
        purchaseDatePicker.setMinWidth(100);
        purchaseDatePicker.setPromptText("Purchase Date");
        purchaseDatePicker.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });

        saleDatePicker = new DatePicker();
        saleDatePicker.setMinWidth(100);
        saleDatePicker.setPromptText("Sale Date");
        saleDatePicker.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });

        equityNameInput = new TextField();
        equityNameInput.setMinWidth(100);
        equityNameInput.setPromptText("Equity Name");

        numberOfLotsInput = new TextField();
        numberOfLotsInput.setMinWidth(100);
        numberOfLotsInput.setPromptText("Number of Lots");

        lotSizeInput = new TextField();
        lotSizeInput.setMinWidth(100);
        lotSizeInput.setPromptText("Lot Size");

        purchasePriceInput = new TextField();
        purchasePriceInput.setMinWidth(100);
        purchasePriceInput.setPromptText("Purchase Price");

        salePriceInput = new TextField();
        salePriceInput.setMinWidth(100);
        salePriceInput.setPromptText("Sale Price");

        addButton = new Button("Add");
        addButton.setMinWidth(50);

        removeButton = new Button("Remove");
        removeButton.setMinWidth(60);

        addRecordSection.getChildren().addAll(/*seriesDateInput, purchaseDateInput, saleDateInput,*/
                seriesDatePicker, purchaseDatePicker, saleDatePicker,
                equityNameInput, numberOfLotsInput, lotSizeInput, purchasePriceInput, salePriceInput,
                addButton, removeButton);

        layout = new VBox();
        layout.getChildren().addAll(table, addRecordSection);
        scene = new Scene(layout, 1100, window.getHeight());
        window.setScene(scene);
    }
    private void onClickAddButton(){
        Record record = new Record();
        try{
            //record.seriesDate.setDateByString(seriesDateInput.getText());
            //record.purchaseDate.setDateByString(purchaseDateInput.getText());
            //record.saleDate.setDateByString(saleDateInput.getText());

            record.seriesDate.setDateByStringFromDatePicker(seriesDatePicker.getValue().toString());
            record.purchaseDate.setDateByStringFromDatePicker(purchaseDatePicker.getValue().toString());
            record.saleDate.setDateByStringFromDatePicker(saleDatePicker.getValue().toString());

            record.setEquityName(equityNameInput.getText());
            record.setNumberOfLots(Integer.parseInt(numberOfLotsInput.getText()));
            record.setLotSize(Integer.parseInt(lotSizeInput.getText()));
            record.setPurchasePrice(new BigDecimal(purchasePriceInput.getText()));
            record.setSalePrice(new BigDecimal(salePriceInput.getText()));
            record.calculate();
            record.convertToStrings();

            table.getItems().addAll(record);

            equityNameInput.clear();
            numberOfLotsInput.clear();
            lotSizeInput.clear();
            purchasePriceInput.clear();
            salePriceInput.clear();
            seriesDatePicker.setValue(null);
            purchaseDatePicker.setValue(null);
            saleDatePicker.setValue(null);

        }catch(Exception e){
            MessageBox.display("Invalid values","Please enter all values correctly");
        }
    }
    private void onClickRemoveButton(){
        ObservableList<Record> selectedProducts, allProducts;
        allProducts = table.getItems();
        selectedProducts = table.getSelectionModel().getSelectedItems();
        boolean choice = YesNoBox.display("Are you sure you want to remove the selected record?");
        if(choice)
            selectedProducts.forEach(allProducts::remove);
    }
    private void onClose(WindowEvent event){
        boolean choice = YesNoBox.display("Are you sure you want to quit?");
        if(choice){
            RECORD_LIST = table.getItems();
            ArrayList<Record> finalData = new ArrayList<>();
            for(int i = 0; i < RECORD_LIST.size(); i++)
                finalData.add(RECORD_LIST.get(i));
            Database.RECORDS = finalData;
            boolean ch = Database.saveData();
            if(!ch){
                event.consume();
                return;
            }
            window.close();
        }else{
            event.consume();
        }
    }
}
