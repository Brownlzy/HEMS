package com.hemsteam.hems.controllers;

import com.hemsteam.hems.datamodels.Details;
import com.hemsteam.hems.handlers.DataBaseHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    private ObservableList<Details> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = DataBaseHelper.getInstance().getDetails();
        timeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("time"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<Details, Double>("money"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("position"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("type"));
        tipColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("tip"));
        detailsTable.setItems(data);
    }

    @FXML
    private TableView<Details> detailsTable;

    @FXML
    public TableColumn timeColumn;
    @FXML
    public TableColumn moneyColumn;
    @FXML
    public TableColumn positionColumn;
    @FXML
    public TableColumn typeColumn;
    @FXML
    public TableColumn tipColumn;

    @FXML
    void onTableSort(ActionEvent event) {

    }
}
