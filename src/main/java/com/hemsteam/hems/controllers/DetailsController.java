package com.hemsteam.hems.controllers;

import com.hemsteam.hems.datamodels.Details;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    private ObservableList<Details> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = DataBaseHelper.getInstance().getDetailsByMonth(Account.getYear(), Account.getMonth());
//        timeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("time"));
//        moneyColumn.setCellValueFactory(new PropertyValueFactory<Details, Double>("money"));
//        positionColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("position"));
//        typeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("type"));
//        tipColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("tip"));
//        detailsTable.setItems(data);
        onEnableEdit();
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
    void onEnableEdit() {
        detailsTable.setEditable(true);
        timeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("time"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<Details, Double>("money"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("position"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("type"));
        tipColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("tip"));

        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        timeColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Details, String> t) {
                        ((Details) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setTime(t.getNewValue());
                        saveData(((Details) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ));
                    }
                }
        );
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Details, String> t) {
                        ((Details) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setType(t.getNewValue());
                        saveData(((Details) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ));
                    }
                }
        );

        positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        positionColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Details, String> t) {
                        ((Details) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPosition(t.getNewValue());
                        saveData(((Details) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ));
                    }
                }
        );
        tipColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tipColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Details, String> t) {
                        ((Details) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setTip(t.getNewValue());
                        saveData(((Details) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ));
                    }
                }
        );

        moneyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        moneyColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Details, String> t) {
                        try {
                            ((Details) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setMoney(t.getNewValue());
                            saveData(((Details) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ));
                        } catch (Exception ignored) {

                        }
                    }
                }
        );
        detailsTable.setItems(data);
        //detailsTable.getColumns().addAll(timeColumn, moneyColumn, typeColumn);
    }

    @FXML
    protected void onAddClick() {
        Details details =
                new Details(
                        Account.getUser(),
                        new Date(),
                        "双击修改",
                        "双击修改",
                        0,
                        "双击修改",
                        String.valueOf(new Date().getTime()));
        data.add(details);
        DataBaseHelper.getInstance().addDetails(details);
        onEnableEdit();
    }

    protected void saveData(Details details) {
        DataBaseHelper.getInstance().putDetails(details);
    }
}
