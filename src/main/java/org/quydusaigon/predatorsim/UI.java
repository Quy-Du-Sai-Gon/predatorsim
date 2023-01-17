package org.quydusaigon.predatorsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.quydusaigon.predatorsim.gameengine.GameLoop;

import java.net.URL;
import java.util.ResourceBundle;

public class UI implements Initializable {

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private Slider simulationSpeedSlider;

    @FXML
    private TextField predatorCountTextField;

    @FXML
    private TextField predatorHealthTextField;

    @FXML
    private TextField predatorAttackPowerTextField;

    @FXML
    private TextField predatorRunSpeedTextField;

    @FXML
    private TextField predatorEnduranceTextField;

    @FXML
    private TextField predatorDefenceTextField;

    @FXML
    private TextField predatorGroupRadiusTextField;

    @FXML
    private ColorPicker predatorColorPicker;

    @FXML
    private TextField preyCountTextField;

    @FXML
    private TextField preyHealthTextField;

    @FXML
    private TextField preyAttackPowerTextField;

    @FXML
    private TextField preyMinSizeTextField;

    @FXML
    private TextField preyMaxSizeTextField;

    @FXML
    private TextField preyNutritionTextField;

    @FXML
    private TextField preyRebornTextField;

    @FXML
    private ColorPicker preyColorPicker;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button applyButton;

    @FXML
    private Button clearButton;

    @FXML
    private BarChart<String, Double> barChart;


    int width = 100, height = 100;



    //Button
    public void onPredatorColorChanged(ActionEvent actionEvent) {
    }

    public void onPreyColorChanged(ActionEvent actionEvent) {
    }

    public int PredatorNumber;
    public int SmallPreyNumber;
    public int MediumPreyNumber;
    public int LargePreyNumber;

    public void onStartButtonClicked(ActionEvent actionEvent) {
        App.getLoop().start();
    }

    public TextField getPredatorCountTextField(){
        return predatorCountTextField;
    }

    public void onStopButtonClicked(ActionEvent actionEvent) {
        App.getLoop().stop();
    }

    public void onNextButtonClicked(ActionEvent actionEvent) {
        // Call GameLoop update code with the special timestamp -1 for special setting of Time
        App.getLoop().handle(-1);
    }

    public void onApplyButtonClicked(ActionEvent actionEvent) {
        try{
            PredatorNumber = Integer.parseInt(predatorCountTextField.getText());
            LargePreyNumber = Integer.parseInt(preyCountTextField.getText());
            Level.changeAnimalNumber(PredatorNumber, LargePreyNumber);
            App.load(Level::main);

        } catch (NumberFormatException e){
            System.out.println("Enter only number");
        }
    }

    public void onClearButtonClicked(ActionEvent actionEvent) {
        // load the level again without initializing anything
        App.load(() -> {});
    }



    //Barchart
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    private void loadData(){
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("Austraia", 10000));
        series1.getData().add(new XYChart.Data("Russia", 30000));
        series1.getData().add(new XYChart.Data("France", 8000));
        series1.getData().add(new XYChart.Data("Germany", 20000));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data("Austraia", 9000));
        series2.getData().add(new XYChart.Data("Russia", 35000));
        series2.getData().add(new XYChart.Data("France", 100));
        series2.getData().add(new XYChart.Data("Germany", 7000));
        barChart.getData().addAll(series1, series2);
    }
}