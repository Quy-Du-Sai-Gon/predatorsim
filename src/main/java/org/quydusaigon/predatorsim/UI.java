package org.quydusaigon.predatorsim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.quydusaigon.predatorsim.gameengine.GameLoop;
import org.quydusaigon.predatorsim.util.Parameter;
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
    private CheckBox showVisionCheckBox;

    @FXML
    private CheckBox showStatusCheckBox;

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

    Alert a = new Alert(Alert.AlertType.NONE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticWidthTextField = widthTextField;
        staticHeightTextField = heightTextField;
        staticRightSplitPane = rightSplitPane;

        final Map<TextField, String> DEFAULT_VALUES = Map.ofEntries(
                Map.entry(widthTextField, "700"),
                Map.entry(heightTextField, "700"),
                Map.entry(predatorCountTextField, Integer.toString(Parameter.DEFAULT_PREDATOR_COUNT)),
                Map.entry(predatorRunSpeedMinTextField, Double.toString(Parameter.DEFAULT_PREDATOR_SPEED_MINIMUM_RANGE)),
                Map.entry(predatorRunSpeedMaxTextField, Double.toString(Parameter.DEFAULT_PREDATOR_SPEED_MAXIMUM_RANGE)),
                Map.entry(predatorVisionMinTextField, Double.toString(Parameter.DEFAULT_PREDATOR_VISION_MINIMUM_RANGE)),
                Map.entry(predatorVisionMaxTextField, Double.toString(Parameter.DEFAULT_PREDATOR_VISION_MAXIMUM_RANGE)),
                Map.entry(predatorEnduranceMinTextField, Integer.toString(Parameter.DEFAULT_PREDATOR_STARVATION_RESILLIENCE_MINIMUM_RANGE)),
                Map.entry(predatorEnduranceMaxTextField, Integer.toString(Parameter.DEFAULT_PREDATOR_STARVATION_RESILLIENCE_MAXIMUM_RANGE)),
                Map.entry(predatorGroupRadiusTextField, Double.toString(Parameter.DEFAULT_PREDATOR_GROUP_RADIUS)),
                Map.entry(smallPreyCountTextField, Integer.toString(Parameter.DEFAULT_SMALL_PREY_COUNT)),
                Map.entry(smallPreyRunSpeedMinTextField, Double.toString(Parameter.DEFAULT_SMALL_PREY_SPEED_MINIMUM_RANGE)),
                Map.entry(smallPreyRunSpeedMaxTextField, Double.toString(Parameter.DEFAULT_SMALL_PREY_SPEED_MAXIMUM_RANGE)),
                Map.entry(smallPreyVisionMinTextField, Double.toString(Parameter.DEFAULT_SMALL_PREY_VISION_MINIMUM_RANGE)),
                Map.entry(smallPreyVisionMaxTextField, Double.toString(Parameter.DEFAULT_SMALL_PREY_VISION_MAXIMUM_RANGE)),
                Map.entry(smallPreyNutritionMinTextField, Integer.toString(Parameter.DEFAULT_SMALL_PREY_NUTRITION_MINIMUM_RANGE)),
                Map.entry(smallPreyNutritionMaxTextField, Integer.toString(Parameter.DEFAULT_SMALL_PREY_NUTRITION_MAXIMUM_RANGE)),
                Map.entry(smallPreyDefenseMinTextField, Double.toString(Parameter.DEFAULT_SMALL_PREY_DEFENSE_MINIMUM_RANGE)),
                Map.entry(smallPreyDefenseMaxTextField, Double.toString(Parameter.DEFAULT_SMALL_PREY_DEFENSE_MAXIMUM_RANGE)),
                Map.entry(mediumPreyCountTextField, Integer.toString(Parameter.DEFAULT_MEDIUM_PREY_COUNT)),
                Map.entry(mediumPreyRunSpeedMinTextField, Double.toString(Parameter.DEFAULT_MEDIUM_PREY_SPEED_MINIMUM_RANGE)),
                Map.entry(mediumPreyRunSpeedMaxTextField, Double.toString(Parameter.DEFAULT_MEDIUM_PREY_SPEED_MAXIMUM_RANGE)),
                Map.entry(mediumPreyVisionMinTextField, Double.toString(Parameter.DEFAULT_MEDIUM_PREY_VISION_MINIMUM_RANGE)),
                Map.entry(mediumPreyVisionMaxTextField, Double.toString(Parameter.DEFAULT_MEDIUM_PREY_VISION_MAXIMUM_RANGE)),
                Map.entry(mediumPreyNutritionMinTextField, Integer.toString(Parameter.DEFAULT_MEDIUM_PREY_NUTRITION_MINIMUM_RANGE)),
                Map.entry(mediumPreyNutritionMaxTextField, Integer.toString(Parameter.DEFAULT_MEDIUM_PREY_NUTRITION_MAXIMUM_RANGE)),
                Map.entry(mediumPreyDefenseMinTextField, Double.toString(Parameter.DEFAULT_MEDIUM_PREY_DEFENSE_MINIMUM_RANGE)),
                Map.entry(mediumPreyDefenseMaxTextField, Double.toString(Parameter.DEFAULT_MEDIUM_PREY_DEFENSE_MAXIMUM_RANGE)),
                Map.entry(largePreyCountTextField, Integer.toString(Parameter.DEFAULT_LARGE_PREY_COUNT)),
                Map.entry(largePreyRunSpeedMinTextField, Double.toString(Parameter.DEFAULT_LARGE_PREY_SPEED_MINIMUM_RANGE)),
                Map.entry(largePreyRunSpeedMaxTextField, Double.toString(Parameter.DEFAULT_LARGE_PREY_SPEED_MAXIMUM_RANGE)),
                Map.entry(largePreyVisionMinTextField, Double.toString(Parameter.DEFAULT_LARGE_PREY_VISION_MINIMUM_RANGE)),
                Map.entry(largePreyVisionMaxTextField, Double.toString(Parameter.DEFAULT_LARGE_PREY_VISION_MAXIMUM_RANGE)),
                Map.entry(largePreyNutritionMinTextField, Integer.toString(Parameter.DEFAULT_LARGE_PREY_NUTRITION_MINIMUM_RANGE)),
                Map.entry(largePreyNutritionMaxTextField, Integer.toString(Parameter.DEFAULT_LARGE_PREY_NUTRITION_MAXIMUM_RANGE)),
                Map.entry(largePreyDefenseMinTextField, Double.toString(Parameter.DEFAULT_LARGE_PREY_DEFENSE_MINIMUM_RANGE)),
                Map.entry(largePreyDefenseMaxTextField, Double.toString(Parameter.DEFAULT_LARGE_PREY_DEFENSE_MAXIMUM_RANGE))
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


                        if ((field.getText().isEmpty() && field.getId().equals("widthTextField")) || (field.getText().isEmpty() && field.getId().equals("heightTextField"))) {
                            a.setAlertType(Alert.AlertType.WARNING);
                            a.setContentText("IMPORTANT TEXTFIELD IS EMPTY");
                            a.show();
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
        App.setStageSize(width + staticRightSplitPane.getWidth() + 50, height + 50);
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

            Prefabs.setPredatorSpeed(Double.parseDouble(predatorRunSpeedMinTextField.getText()),
                Double.parseDouble(predatorRunSpeedMaxTextField.getText()));
            Prefabs.setPredatorVisionRange(Double.parseDouble(predatorVisionMinTextField.getText()),
                Double.parseDouble(predatorVisionMaxTextField.getText()));
            Prefabs.setPredatorStarvationResilience(Integer.parseInt(predatorEnduranceMinTextField.getText()),
                Integer.parseInt(predatorEnduranceMaxTextField.getText()));
            Prefabs.setPredatorGroupRadius(Double.parseDouble(predatorGroupRadiusTextField.getText()));

            Prefabs.setSmallPreySpeed(Double.parseDouble(smallPreyRunSpeedMinTextField.getText()),
                Double.parseDouble(smallPreyRunSpeedMaxTextField.getText()));
            Prefabs.setSmallPreyVisionRange(Double.parseDouble(smallPreyVisionMinTextField.getText()),
                Double.parseDouble(smallPreyVisionMaxTextField.getText()));
            Prefabs.setSmallPreyNutrition(Integer.parseInt(smallPreyNutritionMinTextField.getText()),
                Integer.parseInt(smallPreyNutritionMaxTextField.getText()));
            Prefabs.setSmallPreyDefense(Double.parseDouble(smallPreyDefenseMinTextField.getText()),
                Double.parseDouble(smallPreyDefenseMaxTextField.getText()));

            Prefabs.setMediumPreySpeed(Double.parseDouble(mediumPreyRunSpeedMinTextField.getText()),
                Double.parseDouble(mediumPreyRunSpeedMaxTextField.getText()));
            Prefabs.setMediumPreyVisionRange(Double.parseDouble(mediumPreyVisionMinTextField.getText()),
                Double.parseDouble(mediumPreyVisionMaxTextField.getText()));
            Prefabs.setMediumPreyNutrition(Integer.parseInt(mediumPreyNutritionMinTextField.getText()),
                Integer.parseInt(mediumPreyNutritionMaxTextField.getText()));
            Prefabs.setMediumPreyDefense(Double.parseDouble(mediumPreyDefenseMinTextField.getText()),
                Double.parseDouble(mediumPreyDefenseMaxTextField.getText()));

            Prefabs.setLargePreySpeed(Double.parseDouble(largePreyRunSpeedMinTextField.getText()),
                Double.parseDouble(largePreyRunSpeedMaxTextField.getText()));
            Prefabs.setLargePreyVisionRange(Double.parseDouble(largePreyVisionMinTextField.getText()),
                Double.parseDouble(largePreyVisionMaxTextField.getText()));
            Prefabs.setLargePreyNutrition(Integer.parseInt(largePreyNutritionMinTextField.getText()),
                Integer.parseInt(largePreyNutritionMaxTextField.getText()));
            Prefabs.setLargePreyDefense(Double.parseDouble(largePreyDefenseMinTextField.getText()),
                Double.parseDouble(largePreyDefenseMaxTextField.getText()));

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
