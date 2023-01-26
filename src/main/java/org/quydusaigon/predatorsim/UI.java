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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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

        final var PARAMETER_MAP = Map.ofEntries(
                Map.entry(widthTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setWindowWidth,
                        Parameter::getWindowWidthString)),
                Map.entry(heightTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setWindowHeight,
                        Parameter::getWindowHeightString)),

                Map.entry(predatorCountTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setPredatorCount,
                        Parameter::getPredatorCountString)),
                Map.entry(predatorRunSpeedMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setPredatorSpeedMinimumRange,
                        Parameter::getPredatorSpeedMinimumRangeString)),
                Map.entry(predatorRunSpeedMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setPredatorSpeedMaximumRange,
                        Parameter::getPredatorSpeedMaximumRangeString)),
                Map.entry(predatorVisionMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setPredatorVisionMinimumRange,
                        Parameter::getPredatorVisionMinimumRangeString)),
                Map.entry(predatorVisionMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setPredatorVisionMaximumRange,
                        Parameter::getPredatorVisionMaximumRangeString)),
                Map.entry(predatorEnduranceMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setPredatorStarvationResillienceMinimumRange,
                        Parameter::getPredatorStarvationResillienceMinimumRangeString)),
                Map.entry(predatorEnduranceMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setPredatorStarvationResillienceMaximumRange,
                        Parameter::getPredatorStarvationResillienceMaximumRangeString)),
                Map.entry(predatorGroupRadiusTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setPredatorGroupRadius,
                        Parameter::getPredatorGroupRadiusString)),

                Map.entry(smallPreyCountTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreyCount,
                        Parameter::getSmallPreyCountString)),
                Map.entry(smallPreyRunSpeedMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreySpeedMinimumRange,
                        Parameter::getSmallPreySpeedMinimumRangeString)),
                Map.entry(smallPreyRunSpeedMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreySpeedMaximumRange,
                        Parameter::getSmallPreySpeedMaximumRangeString)),
                Map.entry(smallPreyVisionMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreyVisionMinimumRange,
                        Parameter::getSmallPreyVisionMinimumRangeString)),
                Map.entry(smallPreyVisionMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreyVisionMaximumRange,
                        Parameter::getSmallPreyVisionMaximumRangeString)),
                Map.entry(smallPreyNutritionMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreyNutritionMinimumRange,
                        Parameter::getSmallPreyNutritionMinimumRangeString)),
                Map.entry(smallPreyNutritionMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreyNutritionMaximumRange,
                        Parameter::getSmallPreyNutritionMaximumRangeString)),
                Map.entry(smallPreyDefenseMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreyDefenseMinimumRange,
                        Parameter::getSmallPreyDefenseMinimumRangeString)),
                Map.entry(smallPreyDefenseMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setSmallPreyDefenseMaximumRange,
                        Parameter::getSmallPreyDefenseMaximumRangeString)),

                Map.entry(mediumPreyCountTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreyCount,
                        Parameter::getMediumPreyCountString)),
                Map.entry(mediumPreyRunSpeedMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreySpeedMinimumRange,
                        Parameter::getMediumPreySpeedMinimumRangeString)),
                Map.entry(mediumPreyRunSpeedMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreySpeedMaximumRange,
                        Parameter::getMediumPreySpeedMaximumRangeString)),
                Map.entry(mediumPreyVisionMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreyVisionMinimumRange,
                        Parameter::getMediumPreyVisionMinimumRangeString)),
                Map.entry(mediumPreyVisionMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreyVisionMaximumRange,
                        Parameter::getMediumPreyVisionMaximumRangeString)),
                Map.entry(mediumPreyNutritionMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreyNutritionMinimumRange,
                        Parameter::getMediumPreyNutritionMinimumRangeString)),
                Map.entry(mediumPreyNutritionMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreyNutritionMaximumRange,
                        Parameter::getMediumPreyNutritionMaximumRangeString)),
                Map.entry(mediumPreyDefenseMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreyDefenseMinimumRange,
                        Parameter::getMediumPreyDefenseMinimumRangeString)),
                Map.entry(mediumPreyDefenseMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setMediumPreyDefenseMaximumRange,
                        Parameter::getMediumPreyDefenseMaximumRangeString)),

                Map.entry(largePreyCountTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreyCount,
                        Parameter::getLargePreyCountString)),
                Map.entry(largePreyRunSpeedMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreySpeedMinimumRange,
                        Parameter::getLargePreySpeedMinimumRangeString)),
                Map.entry(largePreyRunSpeedMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreySpeedMaximumRange,
                        Parameter::getLargePreySpeedMaximumRangeString)),
                Map.entry(largePreyVisionMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreyVisionMinimumRange,
                        Parameter::getLargePreyVisionMinimumRangeString)),
                Map.entry(largePreyVisionMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreyVisionMaximumRange,
                        Parameter::getLargePreyVisionMaximumRangeString)),
                Map.entry(largePreyNutritionMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreyNutritionMinimumRange,
                        Parameter::getLargePreyNutritionMinimumRangeString)),
                Map.entry(largePreyNutritionMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreyNutritionMaximumRange,
                        Parameter::getLargePreyNutritionMaximumRangeString)),
                Map.entry(largePreyDefenseMinTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreyDefenseMinimumRange,
                        Parameter::getLargePreyDefenseMinimumRangeString)),
                Map.entry(largePreyDefenseMaxTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setLargePreyDefenseMaximumRange,
                        Parameter::getLargePreyDefenseMaximumRangeString)));

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
                public void changed(ObservableValue<? extends Boolean> observable, Boolean wasFocusedBefore,
                        Boolean isNowFocused) {
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

        colorPickers = new ColorPicker[] {
                predatorColorPicker, smallPreyColorPicker, mediumPreyColorPicker, largePreyColorPicker
        };

        widgets = new Control[] {
                widthTextField, heightTextField,
                predatorCountTextField, predatorRunSpeedMinTextField, predatorRunSpeedMaxTextField,
                predatorVisionMinTextField, predatorVisionMaxTextField, predatorEnduranceMinTextField,
                predatorEnduranceMaxTextField, predatorGroupRadiusTextField,
                smallPreyCountTextField, smallPreyRunSpeedMinTextField, smallPreyRunSpeedMaxTextField,
                smallPreyVisionMinTextField, smallPreyVisionMaxTextField, smallPreyNutritionMinTextField,
                smallPreyNutritionMaxTextField, smallPreyDefenseMinTextField, smallPreyDefenseMaxTextField,
                mediumPreyCountTextField, mediumPreyRunSpeedMinTextField, mediumPreyRunSpeedMaxTextField,
                mediumPreyVisionMinTextField, mediumPreyVisionMaxTextField, mediumPreyNutritionMinTextField,
                mediumPreyNutritionMaxTextField, mediumPreyDefenseMinTextField, mediumPreyDefenseMaxTextField,
                largePreyCountTextField, largePreyRunSpeedMinTextField, largePreyRunSpeedMaxTextField,
                largePreyVisionMinTextField, largePreyVisionMaxTextField, largePreyNutritionMinTextField,
                largePreyNutritionMaxTextField, largePreyDefenseMinTextField, largePreyDefenseMaxTextField,
                simulationSpeedSlider, predatorColorPicker, smallPreyColorPicker, mediumPreyColorPicker,
                largePreyColorPicker
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

        Level.changeAnimalNumber(
                Parameter.getPredatorCount(),
                Parameter.getSmallPreyCount(),
                Parameter.getMediumPreyCount(),
                Parameter.getLargePreyCount());

        Prefabs.setPredatorSpeed(
                Parameter.getPredatorSpeedMinimumRange(),
                Parameter.getPredatorSpeedMaximumRange());
        Prefabs.setPredatorVisionRange(
                Parameter.getPredatorVisionMinimumRange(),
                Parameter.getPredatorVisionMaximumRange());
        Prefabs.setPredatorStarvationResilience(
                Parameter.getPredatorStarvationResillienceMinimumRange(),
                Parameter.getPredatorStarvationResillienceMaximumRange());
        Prefabs.setPredatorGroupRadius(
                Parameter.getPredatorGroupRadius());

        Prefabs.setSmallPreySpeed(
                Parameter.getSmallPreySpeedMinimumRange(),
                Parameter.getSmallPreySpeedMaximumRange());
        Prefabs.setSmallPreyVisionRange(
                Parameter.getSmallPreyVisionMinimumRange(),
                Parameter.getSmallPreyVisionMaximumRange());
        Prefabs.setSmallPreyNutrition(
                Parameter.getSmallPreyNutritionMinimumRange(),
                Parameter.getSmallPreyNutritionMaximumRange());
        Prefabs.setSmallPreyDefense(
                Parameter.getSmallPreyDefenseMinimumRange(),
                Parameter.getSmallPreyDefenseMaximumRange());

        Prefabs.setMediumPreySpeed(
                Parameter.getMediumPreySpeedMinimumRange(),
                Parameter.getMediumPreySpeedMaximumRange());
        Prefabs.setMediumPreyVisionRange(
                Parameter.getMediumPreyVisionMinimumRange(),
                Parameter.getMediumPreyVisionMaximumRange());
        Prefabs.setMediumPreyNutrition(
                Parameter.getMediumPreyNutritionMinimumRange(),
                Parameter.getMediumPreyNutritionMaximumRange());
        Prefabs.setMediumPreyDefense(
                Parameter.getMediumPreyDefenseMinimumRange(),
                Parameter.getMediumPreyDefenseMaximumRange());

        Prefabs.setLargePreySpeed(
                Parameter.getLargePreySpeedMinimumRange(),
                Parameter.getLargePreySpeedMaximumRange());
        Prefabs.setLargePreyVisionRange(
                Parameter.getLargePreyVisionMinimumRange(),
                Parameter.getLargePreyVisionMaximumRange());
        Prefabs.setLargePreyNutrition(
                Parameter.getLargePreyNutritionMinimumRange(),
                Parameter.getLargePreyNutritionMaximumRange());
        Prefabs.setLargePreyDefense(
                Parameter.getLargePreyDefenseMinimumRange(),
                Parameter.getLargePreyDefenseMaximumRange());

        Prefabs.setShowVision(isVisionShowed);
        Prefabs.setShowStat(isStatusShowed);

        App.load(Level::main);
        startButton.setDisable(false);
        stopButton.setDisable(false);
        nextButton.setDisable(true);
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

    private static GridPane gridPane = new GridPane();
    public static GridPane getGridLines(){
        gridPane.setGridLinesVisible(true);
        final int numCols = 50 ;
        final int numRows = 50 ;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            gridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            gridPane.getRowConstraints().add(rowConst);
        }
        return gridPane;
    }
}
