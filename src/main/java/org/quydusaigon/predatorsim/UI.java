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
import org.quydusaigon.predatorsim.util.Prefabs;

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
    private ColorPicker preyColorPicker;

    @FXML
    private ColorPicker predatorColorPicker;

    @FXML
    private TextField predatorCountTextField;

    @FXML
    private TextField smallPreyCountTextField;

    @FXML
    private TextField mediumPreyCountTextField;

    @FXML
    private TextField largePreyCountTextField;

    @FXML
    private TextField predatorRunSpeedMinTextField;

    @FXML
    private TextField predatorRunSpeedMaxTextField;

    @FXML
    private TextField smallPreyRunSpeedMinTextField;

    @FXML
    private TextField smallPreyRunSpeedMaxTextField;

    @FXML
    private TextField mediumPreyRunSpeedMinTextField;

    @FXML
    private TextField mediumPreyRunSpeedMaxTextField;

    @FXML
    private TextField largePreyRunSpeedMinTextField;

    @FXML
    private TextField largePreyRunSpeedMaxTextField;

    @FXML
    private TextField predatorVisionMinTextField;

    @FXML
    private TextField predatorVisionMaxTextField;

    @FXML
    private TextField smallPreyVisionMinTextField;

    @FXML
    private TextField smallPreyVisionMaxTextField;

    @FXML
    private TextField mediumPreyVisionMinTextField;

    @FXML
    private TextField mediumPreyVisionMaxTextField;

    @FXML
    private TextField largePreyVisionMinTextField;

    @FXML
    private TextField largePreyVisionMaxTextField;

    @FXML
    private TextField smallPreyNutritionMinTextField;

    @FXML
    private TextField smallPreyNutritionMaxTextField;

    @FXML
    private TextField mediumPreyNutritionMinTextField;

    @FXML
    private TextField mediumPreyNutritionMaxTextField;

    @FXML
    private TextField largePreyNutritionMinTextField;

    @FXML
    private TextField largePreyNutritionMaxTextField;

    @FXML
    private TextField smallPreyDefenseMinTextField;

    @FXML
    private TextField smallPreyDefenseMaxTextField;

    @FXML
    private TextField mediumPreyDefenseMinTextField;

    @FXML
    private TextField mediumPreyDefenseMaxTextField;

    @FXML
    private TextField largePreyDefenseMinTextField;

    @FXML
    private TextField largePreyDefenseMaxTextField;

    @FXML
    private TextField predatorStarvationEduranceMinTextField;

    @FXML
    private TextField predatorStarvationEduranceMaxTextField;

    @FXML
    private TextField predatorGroupRadiusTextField;

    @FXML
    private TextField preyRebornTextField;

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

    // Button
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
        applyButton.setDisable(true);
        nextButton.setDisable(true);
        clearButton.setDisable(true);
    }

    public TextField getPredatorCountTextField() {
        return predatorCountTextField;
    }

    public void onStopButtonClicked(ActionEvent actionEvent) {
        App.getLoop().stop();
        applyButton.setDisable(false);
        nextButton.setDisable(false);
        clearButton.setDisable(false);

    }

    public void onNextButtonClicked(ActionEvent actionEvent) {
        // Call GameLoop update code with the special timestamp -1 for special setting
        // of Time
        App.getLoop().handle(-1);
    }

    public void onApplyButtonClicked(ActionEvent actionEvent) {
        try {
            PredatorNumber = Integer.parseInt(predatorCountTextField.getText());
            //LargePreyNumber = Integer.parseInt(largePreyCountTextField.getText());

            //Level.changeAnimalNumber(PredatorNumber, 0, 0, 0);
            App.load(Level::main);
            startButton.setDisable(false);
            stopButton.setDisable(false);
            nextButton.setDisable(true);
        } catch (NumberFormatException e) {
            System.out.println("Enter only number");
        }
    }

    public void onClearButtonClicked(ActionEvent actionEvent) {
        // load the level again without initializing anything
        App.load(() -> {
        });
    }

    // Barchart
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startButton.setDisable(true);
        stopButton.setDisable(true);
        nextButton.setDisable(true);
        clearButton.setDisable(true);
        loadData();

    }

    private void loadData() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("Austraia", 10000));
    }
}
