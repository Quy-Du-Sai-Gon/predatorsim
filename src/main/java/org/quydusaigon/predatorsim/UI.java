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
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UI implements Initializable {

    @FXML
    private TextField widthTextField;
    private static TextField staticWidthTextField;

    @FXML
    private TextField heightTextField;
    private static TextField staticHeightTextField;

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

    @FXML
    private SplitPane rightSplitPane;
    private static SplitPane staticRightSplitPane;
    ColorPicker[] colorPickers;

    private Control[] widgets;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticWidthTextField = widthTextField;
        staticHeightTextField = heightTextField;
        staticRightSplitPane = rightSplitPane;

        final Map<TextField, String> DEFAULT_VALUES = Map.ofEntries(
                Map.entry(widthTextField, "700"),
                Map.entry(heightTextField, "700"),
                Map.entry(predatorCountTextField, "10"),
                Map.entry(predatorRunSpeedMinTextField, ""),
                Map.entry(predatorRunSpeedMaxTextField, ""),
                Map.entry(predatorVisionMinTextField, ""),
                Map.entry(predatorVisionMaxTextField, ""),
                Map.entry(predatorEnduranceMinTextField, ""),
                Map.entry(predatorEnduranceMaxTextField, ""),
                Map.entry(predatorGroupRadiusTextField, ""),
                Map.entry(smallPreyCountTextField, "10"),
                Map.entry(smallPreyRunSpeedMinTextField, ""),
                Map.entry(smallPreyRunSpeedMaxTextField, ""),
                Map.entry(smallPreyVisionMinTextField, ""),
                Map.entry(smallPreyVisionMaxTextField, ""),
                Map.entry(smallPreyNutritionMinTextField, ""),
                Map.entry(smallPreyNutritionMaxTextField, ""),
                Map.entry(smallPreyDefenseMinTextField, ""),
                Map.entry(smallPreyDefenseMaxTextField, ""),
                Map.entry(mediumPreyCountTextField, "10"),
                Map.entry(mediumPreyRunSpeedMinTextField, ""),
                Map.entry(mediumPreyRunSpeedMaxTextField, ""),
                Map.entry(mediumPreyVisionMinTextField, ""),
                Map.entry(mediumPreyVisionMaxTextField, ""),
                Map.entry(mediumPreyNutritionMinTextField, ""),
                Map.entry(mediumPreyNutritionMaxTextField, ""),
                Map.entry(mediumPreyDefenseMinTextField, ""),
                Map.entry(mediumPreyDefenseMaxTextField, ""),
                Map.entry(largePreyCountTextField, "10"),
                Map.entry(largePreyRunSpeedMinTextField, ""),
                Map.entry(largePreyRunSpeedMaxTextField, ""),
                Map.entry(largePreyVisionMinTextField, ""),
                Map.entry(largePreyVisionMaxTextField, ""),
                Map.entry(largePreyNutritionMinTextField, ""),
                Map.entry(largePreyNutritionMaxTextField, ""),
                Map.entry(largePreyDefenseMinTextField, ""),
                Map.entry(largePreyDefenseMaxTextField, "")
        );

        for (final Map.Entry<TextField, String> entry : DEFAULT_VALUES.entrySet()) {
            var field = entry.getKey();
            var defaultValue = entry.getValue();

            field.setText(defaultValue);
            field.textProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if (newValue.matches(".*[^\\d.]+.*")) {
                            field.setText(newValue.replaceAll("[^\\d.]", ""));
                        }
                        if (field.getText().isEmpty()) {
                            field.setText(defaultValue);
                        }
                    }
            );
        }

        updateSimulationWindowSize();

        startButton.setDisable(true);
        stopButton.setDisable(true);
        nextButton.setDisable(true);

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

    public static void updateSimulationWindowSize() {
        double width = Double.parseDouble(staticWidthTextField.getText());
        double height = Double.parseDouble(staticHeightTextField.getText());

        App.setSimulationWindowSize(width, height);
        App.setStageSize(width + staticRightSplitPane.getWidth(), height);
    }

    public void onApplyButtonClicked(ActionEvent actionEvent) {
        updateSimulationWindowSize();

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
        } catch (Exception e) {
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
