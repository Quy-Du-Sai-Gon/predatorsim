package org.quydusaigon.predatorsim;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.quydusaigon.predatorsim.gameengine.GameLoop;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.Prefabs;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;
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

        final Map<TextField, Pair<Consumer<String>, Supplier<String>>> PARAMETER_MAP = Map.ofEntries(
                Map.entry(widthTextField, new Pair<>(Parameter::setWindowWidth, Parameter::getWindowWidth)),
                Map.entry(heightTextField, new Pair<>(Parameter::setWindowHeight, Parameter::getWindowHeight)),

                Map.entry(predatorCountTextField, new Pair<>(Parameter::setPredatorCount, Parameter::getPredatorCount)),
                Map.entry(predatorRunSpeedMinTextField, new Pair<>(Parameter::setPredatorSpeedMinimumRange, Parameter::getPredatorSpeedMinimumRange)),
                Map.entry(predatorRunSpeedMaxTextField, new Pair<>(Parameter::setPredatorSpeedMaximumRange, Parameter::getPredatorSpeedMaximumRange)),
                Map.entry(predatorVisionMinTextField, new Pair<>(Parameter::setPredatorVisionMinimumRange, Parameter::getPredatorVisionMinimumRange)),
                Map.entry(predatorVisionMaxTextField, new Pair<>(Parameter::setPredatorVisionMaximumRange, Parameter::getPredatorVisionMaximumRange)),
                Map.entry(predatorEnduranceMinTextField, new Pair<>(Parameter::setPredatorStarvationResillienceMinimumRange, Parameter::getPredatorStarvationResillienceMinimumRange)),
                Map.entry(predatorEnduranceMaxTextField, new Pair<>(Parameter::setPredatorStarvationResillienceMaximumRange, Parameter::getPredatorStarvationResillienceMaximumRange)),
                Map.entry(predatorGroupRadiusTextField, new Pair<>(Parameter::setPredatorGroupRadius, Parameter::getPredatorGroupRadius)),

                Map.entry(smallPreyCountTextField, new Pair<>(Parameter::setSmallPreyCount, Parameter::getSmallPreyCount)),
                Map.entry(smallPreyRunSpeedMinTextField, new Pair<>(Parameter::setSmallPreySpeedMinimumRange, Parameter::getSmallPreyVisionMinimumRange)),
                Map.entry(smallPreyRunSpeedMaxTextField, new Pair<>(Parameter::setSmallPreySpeedMaximumRange, Parameter::getSmallPreySpeedMaximumRange)),
                Map.entry(smallPreyVisionMinTextField, new Pair<>(Parameter::setSmallPreyVisionMinimumRange, Parameter::getSmallPreyVisionMinimumRange)),
                Map.entry(smallPreyVisionMaxTextField, new Pair<>(Parameter::setSmallPreyVisionMaximumRange, Parameter::getSmallPreyVisionMaximumRange)),
                Map.entry(smallPreyNutritionMinTextField, new Pair<>(Parameter::setSmallPreyNutritionMinimumRange, Parameter::getSmallPreyNutritionMinimumRange)),
                Map.entry(smallPreyNutritionMaxTextField, new Pair<>(Parameter::setSmallPreyNutritionMaximumRange, Parameter::getSmallPreyNutritionMaximumRange)),
                Map.entry(smallPreyDefenseMinTextField, new Pair<>(Parameter::setSmallPreyDefenseMinimumRange, Parameter::getSmallPreyDefenseMinimumRange)),
                Map.entry(smallPreyDefenseMaxTextField, new Pair<>(Parameter::setSmallPreyDefenseMaximumRange, Parameter::getSmallPreyDefenseMaximumRange)),

                Map.entry(mediumPreyCountTextField, new Pair<>(Parameter::setMediumPreyCount, Parameter::getMediumPreyCount)),
                Map.entry(mediumPreyRunSpeedMinTextField, new Pair<>(Parameter::setMediumPreySpeedMinimumRange, Parameter::getMediumPreySpeedMinimumRange)),
                Map.entry(mediumPreyRunSpeedMaxTextField, new Pair<>(Parameter::setMediumPreySpeedMaximumRange, Parameter::getMediumPreySpeedMaximumRange)),
                Map.entry(mediumPreyVisionMinTextField, new Pair<>(Parameter::setMediumPreyVisionMinimumRange, Parameter::getMediumPreyVisionMinimumRange)),
                Map.entry(mediumPreyVisionMaxTextField, new Pair<>(Parameter::setMediumPreyVisionMaximumRange, Parameter::getMediumPreyVisionMaximumRange)),
                Map.entry(mediumPreyNutritionMinTextField, new Pair<>(Parameter::setMediumPreyNutritionMinimumRange, Parameter::getMediumPreyNutritionMinimumRange)),
                Map.entry(mediumPreyNutritionMaxTextField, new Pair<>(Parameter::setMediumPreyNutritionMaximumRange, Parameter::getMediumPreyNutritionMaximumRange)),
                Map.entry(mediumPreyDefenseMinTextField, new Pair<>(Parameter::setMediumPreyDefenseMinimumRange, Parameter::getMediumPreyDefenseMinimumRange)),
                Map.entry(mediumPreyDefenseMaxTextField, new Pair<>(Parameter::setMediumPreyDefenseMaximumRange, Parameter::getMediumPreyDefenseMaximumRange)),

                Map.entry(largePreyCountTextField, new Pair<>(Parameter::setLargePreyCount, Parameter::getLargePreyCount)),
                Map.entry(largePreyRunSpeedMinTextField, new Pair<>(Parameter::setLargePreySpeedMinimumRange, Parameter::getLargePreySpeedMinimumRange)),
                Map.entry(largePreyRunSpeedMaxTextField, new Pair<>(Parameter::setLargePreySpeedMaximumRange, Parameter::getLargePreySpeedMaximumRange)),
                Map.entry(largePreyVisionMinTextField, new Pair<>(Parameter::setLargePreyVisionMinimumRange, Parameter::getLargePreyVisionMinimumRange)),
                Map.entry(largePreyVisionMaxTextField, new Pair<>(Parameter::setLargePreyVisionMaximumRange, Parameter::getLargePreyVisionMaximumRange)),
                Map.entry(largePreyNutritionMinTextField, new Pair<>(Parameter::setLargePreyNutritionMinimumRange, Parameter::getLargePreyNutritionMinimumRange)),
                Map.entry(largePreyNutritionMaxTextField, new Pair<>(Parameter::setLargePreyNutritionMaximumRange, Parameter::getLargePreyNutritionMaximumRange)),
                Map.entry(largePreyDefenseMinTextField, new Pair<>(Parameter::setLargePreyDefenseMinimumRange, Parameter::getLargePreyDefenseMinimumRange)),
                Map.entry(largePreyDefenseMaxTextField, new Pair<>(Parameter::setLargePreyDefenseMaximumRange, Parameter::getLargePreyDefenseMaximumRange))
        );


        for (var paramEntry : PARAMETER_MAP.entrySet()) {
            // update to default value
            updateParameter(paramEntry);

            var textField = paramEntry.getKey();
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        // update param on enter key pressed
                        updateParameter(paramEntry);
                    }
                }
            });
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean wasFocusedBefore, Boolean isNowFocused) {
                    if (!isNowFocused) {
                        // update param on focus lost a
                        updateParameter(paramEntry);
                    }
                }
            });

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

    private void updateParameter(Map.Entry<TextField, Pair<Consumer<String>, Supplier<String>>> paramEntry) {
        var textField = paramEntry.getKey();
        var value = paramEntry.getValue();
        var setter = value.getKey();
        var getter = value.getValue();

        try {
            // try setting the param to the new text
            setter.accept(textField.getText());
        } catch (Exception e) {
            // reset the text to the current param value if failed to parse text
            textField.setText(getter.get());
        }
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

            Prefabs.setShowVision(isVisionShowed);
            Prefabs.setShowStat(isStatusShowed);

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


    private boolean isVisionShowed;
    private boolean isStatusShowed;

    public void onShowVisionCheckBoxClicked(ActionEvent actionEvent) {
        isVisionShowed = showVisionCheckBox.isSelected();
    }

    public void onShowStatusCheckBoxClicked(ActionEvent actionEvent) {
        isStatusShowed = showStatusCheckBox.isSelected();
    }
}
