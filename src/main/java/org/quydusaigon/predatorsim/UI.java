package org.quydusaigon.predatorsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
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
    private ColorPicker smallPreyColorPicker;

    @FXML
    private ColorPicker mediumPreyColorPicker;

    @FXML
    private ColorPicker largePreyColorPicker;




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
    private TextField predatorEnduranceMinTextField;

    @FXML
    private TextField predatorEnduranceMaxTextField;

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

    TextField[] inputTextFields;
    ColorPicker[] colorPickers;

    private Control[] widgets;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startButton.setDisable(true);
        stopButton.setDisable(true);
        nextButton.setDisable(true);



        inputTextFields = new TextField[]{
                widthTextField, heightTextField,
                predatorCountTextField, predatorRunSpeedMinTextField, predatorRunSpeedMaxTextField, predatorVisionMinTextField, predatorVisionMaxTextField, predatorEnduranceMinTextField, predatorEnduranceMaxTextField, predatorGroupRadiusTextField,
                smallPreyCountTextField, smallPreyRunSpeedMinTextField, smallPreyRunSpeedMaxTextField, smallPreyVisionMinTextField, smallPreyVisionMaxTextField, smallPreyNutritionMinTextField, smallPreyNutritionMaxTextField, smallPreyDefenseMinTextField, smallPreyDefenseMaxTextField,
                mediumPreyCountTextField, mediumPreyRunSpeedMinTextField, mediumPreyRunSpeedMaxTextField, mediumPreyVisionMinTextField, mediumPreyVisionMaxTextField, mediumPreyNutritionMinTextField, mediumPreyNutritionMaxTextField, mediumPreyDefenseMinTextField, mediumPreyDefenseMaxTextField,
                largePreyCountTextField, largePreyRunSpeedMinTextField, largePreyRunSpeedMaxTextField, largePreyVisionMinTextField, largePreyVisionMaxTextField, largePreyNutritionMinTextField, largePreyNutritionMaxTextField, largePreyDefenseMinTextField, largePreyDefenseMaxTextField,
        };

        colorPickers = new ColorPicker[]{
                predatorColorPicker, smallPreyColorPicker, mediumPreyColorPicker, largePreyColorPicker
        };

        widgets = new Control[]{
                widthTextField, heightTextField,
                predatorCountTextField, predatorRunSpeedMinTextField, predatorRunSpeedMaxTextField, predatorVisionMinTextField, predatorVisionMaxTextField, predatorEnduranceMinTextField, predatorEnduranceMaxTextField, predatorGroupRadiusTextField,
                smallPreyCountTextField, smallPreyRunSpeedMinTextField, smallPreyRunSpeedMaxTextField, smallPreyVisionMinTextField, smallPreyVisionMaxTextField, smallPreyNutritionMinTextField, smallPreyNutritionMaxTextField, smallPreyDefenseMinTextField, smallPreyDefenseMaxTextField,
                mediumPreyCountTextField, mediumPreyRunSpeedMinTextField, mediumPreyRunSpeedMaxTextField, mediumPreyVisionMinTextField, mediumPreyVisionMaxTextField, mediumPreyNutritionMinTextField, mediumPreyNutritionMaxTextField, mediumPreyDefenseMinTextField, mediumPreyDefenseMaxTextField,
                largePreyCountTextField, largePreyRunSpeedMinTextField, largePreyRunSpeedMaxTextField, largePreyVisionMinTextField, largePreyVisionMaxTextField, largePreyNutritionMinTextField, largePreyNutritionMaxTextField, largePreyDefenseMinTextField, largePreyDefenseMaxTextField,
                simulationSpeedSlider, predatorColorPicker, smallPreyColorPicker, mediumPreyColorPicker, largePreyColorPicker
        };

        simulationSpeedSlider.setValue(1);

        for( final TextField field: inputTextFields){
            field.textProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if (newValue.matches(".*[^\\d.]+.*")) {
                            field.setText(newValue.replaceAll("[^\\d.]", ""));
                        }
                    }
            );
        }
    }

    Alert a = new Alert(Alert.AlertType.NONE);

    public void onPredatorColorChanged(ActionEvent actionEvent) {
    }

    public void onPreyColorChanged(ActionEvent actionEvent) {
    }

    public void onStartButtonClicked(ActionEvent actionEvent) {
        App.getLoop().start();
        applyButton.setDisable(true);
        nextButton.setDisable(true);
        clearButton.setDisable(true);
    }

    public void onStopButtonClicked(ActionEvent actionEvent) {
        App.getLoop().stop();
        applyButton.setDisable(false);
        nextButton.setDisable(false);
        clearButton.setDisable(false);

    }

    public void onNextButtonClicked(ActionEvent actionEvent) {
        App.getLoop().handle(-1);
    }

    public void onApplyButtonClicked(ActionEvent actionEvent) {
        try {
            Level.changeAnimalNumber(
                    Integer.parseInt(predatorCountTextField.getText()),
                    Integer.parseInt(smallPreyCountTextField.getText()),
                    Integer.parseInt(mediumPreyCountTextField.getText()),
                    Integer.parseInt(largePreyCountTextField.getText())
            );

            App.load(Level::main);
            startButton.setDisable(false);
            stopButton.setDisable(false);
            nextButton.setDisable(true);
        } catch (Exception e){
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("ENTER INTEGER ONLY");
            a.show();
        }
    }

    public void onClearButtonClicked(ActionEvent actionEvent) {
        App.load(() -> {
        });
    }
}
