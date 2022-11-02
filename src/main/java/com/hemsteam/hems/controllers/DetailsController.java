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
    private ObservableList<Details> data;
    @FXML
    private TableView<Details> detailsTable;
    @FXML
    private Label tips;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = DataBaseHelper.getInstance().getDetailsByMonth(Account.getYear(), Account.getMonth());
        onTableInit();
    }

    /**
     * 初始化明细表显示信息
     *
     * @author Brownlzy
     */
    @FXML
    void onTableInit() {
        //设置单元格为可编辑格式
        detailsTable.setEditable(true);
        //设置表中各列数据
        timeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("time"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<Details, Double>("moneyD"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("position"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("type"));
        tipColumn.setCellValueFactory(new PropertyValueFactory<Details, String>("tip"));
        delete.setCellValueFactory(new PropertyValueFactory<Details, Boolean>("delete"));
        //设置各列的可编辑行为
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        timeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Details, String> t) {
                ((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTime(t.getNewValue());
                saveData(((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())));
            }
        });
        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Details, String> t) {
                ((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())).setType(t.getNewValue());
                saveData(((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())));
            }
        });

        positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        positionColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Details, String> t) {
                ((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPosition(t.getNewValue());
                saveData(((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())));
            }
        });
        tipColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tipColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Details, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Details, String> t) {
                ((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTip(t.getNewValue());
                saveData(((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())));
            }
        });
        //使用DoubleStringConverter实现输入字符串与Details需要的Double类型互转
        moneyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        moneyColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Details, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Details, Double> t) {
                try {
                    ((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMoneyD(t.getNewValue());
                    saveData(((Details) t.getTableView().getItems().get(t.getTablePosition().getRow())));
                } catch (Exception ignored) {

                }
            }
        });
        //删除列的勾选框
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
                                    public void handle(ActionEvent t) { //勾选后设置明细的勾选标记为true
                                        Details vo = getTableView().getItems().get(getIndex());
                                        vo.delete = cell.isSelected();
                                    }
                                });
                    }

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {   //行不为空时
                            Details vo = getTableView().getItems().get(getIndex());
                            if (!vo.delete) {   //该条明细没有删除标志
                                cell.setSelected(false);    //勾选框不勾选
                            } else {//反之
                                cell.setSelected(true); //勾选
                            }
                        } else {    //行为空，勾选框不显示
                            cell.setVisible(false);
                        }
                        setGraphic(cell);
                    }
                };
            }
        });
        detailsTable.setItems(data);

    }

    /**
     * 增加一条空明细
     * @author Brownlzy
 */
    @FXML
    protected void onAddClick() {
        Details details = new Details(Account.getUser(), new Date(), "其他", "双击修改", 0, "双击修改");
        data.add(0, details);
        DataBaseHelper.getInstance().addDetails(details);
        onTableInit();
    }

    /**
     * 删除所有被勾选的明细
     * @author Brownlzy
 */
    @FXML
    void onDeleteClick() {
        //先逐条在数据库中删除明细
        for (Details datum : data) {
            if (datum.delete) DataBaseHelper.getInstance().delDetails(datum);
        }
        //再从表单中移除
        data.removeIf(t -> t.delete);
    }

    /**
     * 保存指定表单到数据库中
     * @author Brownlzy
     * @param details 要保存的表单
 */
    protected void saveData(Details details) {
        DataBaseHelper.getInstance().changeDetails(details);
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
            for (Details d : data) {
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
