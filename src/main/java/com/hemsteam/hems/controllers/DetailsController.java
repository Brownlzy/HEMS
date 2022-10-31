package com.hemsteam.hems.controllers;

import com.hemsteam.hems.datamodels.Details;
import com.hemsteam.hems.handlers.Account;
import com.hemsteam.hems.handlers.DataBaseHelper;
import com.hemsteam.hems.utils.Log;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    private ObservableList<Details> data;
    @FXML
    private Button export;


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
    public TableColumn delete;
    @FXML
    private Label tips;

    @FXML
    void onEnableEdit() {
        detailsTable.setEditable(true);
        timeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("time"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<Details, Double>("moneyD"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("position"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("type"));
        tipColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("tip"));
        delete.setCellValueFactory(new PropertyValueFactory<Details, Boolean>("delete"));

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

        moneyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        moneyColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Details, Double>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Details, Double> t) {
                        try {
                            ((Details) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setMoneyD(t.getNewValue());
                            saveData(((Details) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ));
                        } catch (Exception ignored) {

                        }
                    }
                }
        );

        delete.setCellFactory(new Callback<TableColumn<Details, Boolean>, TableCell<Details, Boolean>>() {

            @Override

            public TableCell<Details, Boolean> call(TableColumn<Details, Boolean> p) {

                return new TableCell<Details, Boolean>() {

                    final CheckBox cell = new CheckBox();

                    {
                        setAlignment(Pos.CENTER);
                    }

                    {
                        cell.setOnAction(

                                new EventHandler<ActionEvent>() {
                                    @Override

                                    public void handle(ActionEvent t) {

                                        Details vo = getTableView().getItems().get(getIndex());

                                        vo.delete = cell.isSelected();
                                    }
                                });
                    }

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            Details vo = getTableView().getItems().get(getIndex());
                            if (!vo.delete) {
                                cell.setSelected(false);
                            } else {
                                cell.setSelected(true);
                            }
                        } else {
                            cell.setVisible(false);
                        }
                        setGraphic(cell);
                    }
                };
            }
        });
        detailsTable.setItems(data);

    }

    @FXML
    protected void onAddClick() {
        Details details =
                new Details(
                        Account.getUser(),
                        new Date(),
                        "其他",
                        "双击修改",
                        0,
                        "双击修改",
                        String.valueOf(new Date().getTime()));
        data.add(0, details);
        DataBaseHelper.getInstance().addDetails(details);
        onEnableEdit();
    }

    @FXML
    void onDeleteClick() {
        for (Details datum : data) {
            if (datum.delete)
                DataBaseHelper.getInstance().delDetails(datum);
        }
        data.removeIf(t -> t.delete);
        onEnableEdit();
    }

    protected void saveData(Details details) {
        DataBaseHelper.getInstance().putDetails(details);
    }

    @FXML
    void onExportClick() {
        try {
            FileOutputStream fos;
            OutputStreamWriter osw;
            BufferedWriter out;
            String title = "detail" + new Date().getTime() + ".csv";
            Log.d(this.getClass(), title);
            fos = new FileOutputStream(title);
            osw = new OutputStreamWriter(fos, "UTF-8");
            out = new BufferedWriter(osw);
            //追加BOM标识
            fos.write(0xef);
            fos.write(0xbb);
            fos.write(0xbf);
            out.write(Details.toFormatString());
            out.newLine();
            for (Details d :
                    data) {
                out.write(d.toString());
                out.newLine();
            }
            //关闭流
            out.flush();
            osw.flush();
            fos.flush();
            out.close();
            osw.close();
            fos.close();
            tips.setText(title + "文件保存成功");
        } catch (IOException e) {
            e.printStackTrace();
            tips.setText("文件保存失败");
        }
    }
}
