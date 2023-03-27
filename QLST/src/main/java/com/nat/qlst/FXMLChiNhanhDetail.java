/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nat.qlst;

import com.nat.pojo.ChiNhanh;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class FXMLChiNhanhDetail {
    @FXML Label lbName; 
    @FXML Label lbAddress; 
    @FXML Label lbCount;
    
    public void setChiNhanh(ChiNhanh cn){
        lbName.setText(cn.getTenchinhanh());
        lbAddress.setText(cn.getDiachi());
        lbCount.setText(String.valueOf(cn.getSoluongnhanvien()));
    }
    public void BackMainController(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLSieuThiController.fxml"));
        Parent primaryParent = loader.load();
        Scene scene = new Scene(primaryParent,840, 480);
        stage.setScene(scene);
    }
}
