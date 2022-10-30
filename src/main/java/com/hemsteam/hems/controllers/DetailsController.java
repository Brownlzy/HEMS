package com.hemsteam.hems.controllers;

import com.hemsteam.hems.datamodels.Details;
import com.hemsteam.hems.handlers.DataBaseHelper;
import com.hemsteam.hems.utils.Log;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {
    private ObservableList<Details> data;
    @FXML
    private Button export;


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
    private Label tips;

    @FXML
    void onTableSort(ActionEvent event) {

    }
    @FXML
    void onExportClick() {
        try {
            FileOutputStream fos;
            OutputStreamWriter osw;
            BufferedWriter out;
            String title= "detail"+new Date().getTime()+".csv";
            Log.d(this.getClass(),title);
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
            tips.setText(title+"文件保存成功");
        } catch (IOException e) {
            e.printStackTrace();
            tips.setText("文件保存失败");
        }
    }
}
