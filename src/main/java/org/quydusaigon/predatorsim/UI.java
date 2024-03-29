package org.quydusaigon.predatorsim;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.util.AutoSpawn;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.Prefabs;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class UI implements Initializable {

    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;

    @FXML
    private TextField timeStepTextField;

    @FXML
    private Slider simulationSpeedSlider;
    @FXML
    private Label speedLabel;
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
    private TextField predatorGroupRadiusTextField;

    @FXML
    private TextField preyRebornTextField;

    @FXML
    private CheckBox showVisionCheckBox;

    @FXML
    private CheckBox showStatusCheckBox;

    @FXML
    private CheckBox showGridLinesCheckBox;

    @FXML
    private CheckBox predatorImageCheckBox;

    @FXML
    private CheckBox smallPreyImageCheckBox;

    @FXML
    private CheckBox mediumPreyImageCheckBox;

    @FXML
    private CheckBox largePreyImageCheckBox;

    @FXML
    private Button choosePredatorImageButton;

    @FXML
    private Button chooseSmallPreyImageButton;

    @FXML
    private Button chooseMediumPreyImageButton;

    @FXML
    private Button chooseLargePreyImageButton;

    @FXML
    private CheckBox predatorSpawnCheckBox;

    @FXML
    private CheckBox smallPreySpawnCheckBox;

    @FXML
    private CheckBox mediumPreySpawnCheckBox;

    @FXML
    private CheckBox largePreySpawnCheckBox;

    @FXML
    private Button startStopButton;

    @FXML
    private Button spawnButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button applyButton;

    @FXML
    private Button clearButton;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private VBox rightSplitPane;

    @FXML
    private Label predatorOutputLabel;

    @FXML
    private Label predatorDeadOutputLabel;

    @FXML
    private Label smallPreyOutputLabel;
    @FXML
    private Label smallPreyDeadOutputLabel;
    @FXML
    private Label mediumPreyOutputLabel;
    @FXML
    private Label mediumPreyDeadOutputLabel;
    @FXML
    private Label largePreyOutputLabel;
    @FXML
    private Label largePreyDeadOutputLabel;
    @FXML
    private Label nutritionGainedOutputLabel;
    @FXML
    private Label nutritionConsumedOutputLabel;

    @FXML
    private Pane simulationWindow;

    Alert warningAlert = new Alert(Alert.AlertType.NONE);

    private GridPane gridPane;
    public static ScheduledExecutorService scheduledExecutorService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                        Parameter::getLargePreyDefenseMaximumRangeString)),
                Map.entry(timeStepTextField, new Pair<Consumer<String>, Supplier<String>>(
                        Parameter::setTimeStep,
                        Parameter::getTimeStepString)));

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
                public void changed(ObservableValue<? extends Boolean> observable,
                        Boolean wasFocusedBefore,
                        Boolean isNowFocused) {
                    if (!isNowFocused) {
                        // update param on focus lost a
                        updateParameter(paramEntry);
                    }
                }
            });
        }

        nextButton.setDisable(false);

        initProperties();
        // Grid
        gridPane = getGridLines();
        simulationWindow.getChildren().add(gridPane);

        simulationSpeedSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number> observable,
                            Number oldValue, Number newValue) {
                        Time.setSliderValue((float) simulationSpeedSlider.getValue());
                        speedLabel.setText("Speed: " + simulationSpeedSlider.getValue());
                    }

                });

        updateSimulationWindowSize();
    }

    public void playOutputAndLineChart() {
        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setAnimated(false); // axis animations are removed

        // defining a series to display data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("P");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("S");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("M");
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("L");

        // add series to chart
        lineChart.getData().add(series);
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);
        lineChart.getData().add(series3);

        // this is used to display time in format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

        // setup a scheduled executor to periodically put data into the chart
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // get a random integer between 0-100
            Integer predatorNum = Output.getInstance().predatorCount;
            Integer smallPreyNum = Output.getInstance().smallPreyCount;
            Integer mediumPreyNum = Output.getInstance().mediumPreyCount;
            Integer largePreyNum = Output.getInstance().largePreyCount;

            // Update the chart
            Platform.runLater(() -> {
                predatorOutputLabel.setText("Predator: " + Output.getInstance().predatorCount);
                predatorDeadOutputLabel.setText("Dead Predator: " + Output.getInstance().predatorDeadCount);
                smallPreyOutputLabel.setText("Small Prey: " + Output.getInstance().smallPreyCount);
                smallPreyDeadOutputLabel.setText("Dead Small Prey: " + Output.getInstance().smallPreyDeadCount);
                mediumPreyOutputLabel.setText("Medium Prey: " + Output.getInstance().mediumPreyCount);
                mediumPreyDeadOutputLabel.setText("Dead Medium Prey : " + Output.getInstance().mediumPreyDeadCount);
                largePreyOutputLabel.setText("Large Prey: " + Output.getInstance().largePreyCount);
                largePreyDeadOutputLabel.setText("Dead Large Prey: " + Output.getInstance().largePreyDeadCount);
                nutritionGainedOutputLabel.setText("Gained: " + Output.getInstance().nutritionGained);
                nutritionConsumedOutputLabel.setText("Consumed: " + Output.getInstance().nutritionConsumed);
                // get current time
                Date now = new Date();
                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), predatorNum));
                series1.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), smallPreyNum));
                series2.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), mediumPreyNum));
                series3.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), largePreyNum));
                if (series.getData().size() > 8) {
                    series.getData().remove(0);
                    series1.getData().remove(0);
                    series2.getData().remove(0);
                    series3.getData().remove(0);
                }
            });
        }, 0, 1, TimeUnit.SECONDS);
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

    public void onStartStopButtonClicked(ActionEvent actionEvent) {
        if (startStopButton.getText().equals("START")) {
            playOutputAndLineChart();
            App.getLoop().start();
            startStopButton.setText("STOP");
            applyButton.setDisable(true);
            nextButton.setDisable(true);
            clearButton.setDisable(true);
            startStopButton.setStyle("-fx-background-color: red; -fx-background-radius: 10 0 0 0;");
        } else {
            scheduledExecutorService.shutdownNow();
            App.getLoop().stop();
            startStopButton.setText("START");
            applyButton.setDisable(false);
            nextButton.setDisable(false);
            clearButton.setDisable(false);
            startStopButton.setStyle("-fx-background-color: orange; -fx-background-radius: 10 0 0 0;");
        }
    }

    public void onSpawnButtonClicked(ActionEvent actionEvent) {
        App.initLevel(Level::main);
    }

    public void onNextButtonClicked(ActionEvent actionEvent) {
        App.getLoop().handle(-1);
    }

    public void updateSimulationWindowSize() {
        // resize simulation window
        simulationWindow.setMinSize(Parameter.getWindowWidth(), Parameter.getWindowHeight());
        simulationWindow.setMaxSize(Parameter.getWindowWidth(), Parameter.getWindowHeight());

        // resize grid
        gridPane.setPrefSize(Parameter.getWindowWidth(), Parameter.getWindowHeight());

        gridPane.getColumnConstraints().clear();
        IntStream.range(0, (int) Math.ceil(Parameter.getWindowWidth() / GRID_SIZE))
                .mapToObj(i -> new ColumnConstraints(GRID_SIZE))
                .forEach(gridPane.getColumnConstraints()::add);

        gridPane.getRowConstraints().clear();
        IntStream.range(0, (int) Math.ceil(Parameter.getWindowHeight() / GRID_SIZE))
                .mapToObj(i -> new RowConstraints(GRID_SIZE))
                .forEach(gridPane.getRowConstraints()::add);

        // resize app window
        App.getStage().sizeToScene();
    }

    public void onApplyButtonClicked(ActionEvent actionEvent) {
        updateSimulationWindowSize();
        lineChart.getData().clear();
        App.load(Level::main);
        nextButton.setDisable(false);
    }

    public void onClearButtonClicked(ActionEvent actionEvent) {
        lineChart.getData().clear();
        App.clearLevel();
    }

    /*
     * Rendering options
     */

    private static BooleanProperty showsVisionProperty;
    private static BooleanProperty showsStatusProperty;
    private static BooleanProperty showsGridProperty;

    private void initProperties() {
        showsVisionProperty = new SimpleBooleanProperty(showVisionCheckBox.isSelected());
        showsStatusProperty = new SimpleBooleanProperty(showStatusCheckBox.isSelected());
        showsGridProperty = new SimpleBooleanProperty(showGridLinesCheckBox.isSelected());
    }

    public static BooleanProperty getShowsVisionProperty() {
        return showsVisionProperty;
    }

    public static BooleanProperty getShowsStatusProperty() {
        return showsStatusProperty;
    }

    public static BooleanProperty getShowsGridProperty() {
        return showsGridProperty;
    }

    public void onShowVisionCheckBoxClicked(ActionEvent actionEvent) {
        getShowsVisionProperty().set(showVisionCheckBox.isSelected());
    }

    public void onShowStatusCheckBoxClicked(ActionEvent actionEvent) {
        getShowsStatusProperty().set(showStatusCheckBox.isSelected());
    }

    public void onShowGridLinesCheckBoxClicked(ActionEvent actionEvent) {
        getShowsGridProperty().set(showGridLinesCheckBox.isSelected());
    }

    public static final int GRID_SIZE = 20;

    private GridPane getGridLines() {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setVisible(showGridLinesCheckBox.isSelected());
        getShowsGridProperty().addListener((obs, oldVal, newVal) -> {
            gridPane.setVisible(newVal);
        });

        return gridPane;
    }

    public void onSmallPreyColorChanged(ActionEvent actionEvent) {
        Prefabs.setSmallPreyColor(smallPreyColorPicker.getValue());
    }

    public void onMediumPreyColorChanged(ActionEvent actionEvent) {
        Prefabs.setMediumPreyColor(mediumPreyColorPicker.getValue());
    }

    public void onLargePreyColorChanged(ActionEvent actionEvent) {
        Prefabs.setLargePreyColor(largePreyColorPicker.getValue());
    }

    public void onPredatorColorChanged(ActionEvent actionEvent) {
        Prefabs.setPredatorColor(predatorColorPicker.getValue());
    }

    private static Stage stage;
    FileChooser fileChooser = new FileChooser();
    Alert alert = new Alert(Alert.AlertType.WARNING);

    public void onPredatorImageButtonClicked(ActionEvent actionEvent) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        try {
            Prefabs.setPredatorImageURL(selectedFile.getAbsolutePath());
        } catch (Exception a) {
            alert.setContentText("No image has been chose");
            alert.show();
        }
    }

    public void onSmallPreyImageButtonClicked(ActionEvent actionEvent) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        try {
            Prefabs.setSmallPreyImageURL(selectedFile.getAbsolutePath());
        } catch (Exception a) {
            alert.setContentText("No image has been chose");
            alert.show();
        }
    }

    public void onMediumPreyImageButtonClicked(ActionEvent actionEvent) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        try {
            Prefabs.setMediumPreyImageURL(selectedFile.getAbsolutePath());
        } catch (Exception a) {
            alert.setContentText("No image has been chose");
            alert.show();
        }
    }

    public void onLargePreyImageButtonClicked(ActionEvent actionEvent) {
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        try {
            Prefabs.setLargePreyImageURL(selectedFile.getAbsolutePath());
        } catch (Exception a) {
            alert.setContentText("No image has been chose");
            alert.show();
        }
    }

    private static boolean isPredatorImageEnable;
    private static boolean isSmallPreyImageEnable;
    private static boolean isMediumPreyImageEnable;
    private static boolean isLargePreyImageEnable;

    public static boolean isPredatorCheckBoxEnable() {
        return isPredatorImageEnable;
    }

    public static boolean isSmallPreyCheckBoxEnable() {
        return isSmallPreyImageEnable;
    }

    public static boolean isMediumPreyCheckBoxEnable() {
        return isMediumPreyImageEnable;
    }

    public static boolean isLargePreyCheckBoxEnable() {
        return isLargePreyImageEnable;
    }

    public void onPredatorImageCheckBoxClicked(ActionEvent actionEvent) {
        isPredatorImageEnable = predatorImageCheckBox.isSelected();
    }

    public void onSmallPreyImageCheckBoxClicked(ActionEvent actionEvent) {
        isSmallPreyImageEnable = smallPreyImageCheckBox.isSelected();
    }

    public void onMediumPreyImageCheckBoxClicked(ActionEvent actionEvent) {
        isMediumPreyImageEnable = mediumPreyImageCheckBox.isSelected();
    }

    public void onLargePreyImageCheckBoxClicked(ActionEvent actionEvent) {
        isLargePreyImageEnable = largePreyImageCheckBox.isSelected();
    }

    public void onPredatorSpawnCheckBoxClicked(ActionEvent actionEvent) {
        AutoSpawn.setPredatorAutoSpawnEnable(predatorSpawnCheckBox.isSelected());
    }

    public void onSmallPreySpawnCheckBoxClicked(ActionEvent actionEvent) {
        AutoSpawn.setSmallPreyAutoSpawnEnable(smallPreySpawnCheckBox.isSelected());
    }

    public void onMediumPreySpawnCheckBoxClicked(ActionEvent actionEvent) {
        AutoSpawn.setMediumPreyAutoSpawnEnable(mediumPreySpawnCheckBox.isSelected());
    }

    public void onLargePreySpawnCheckBoxClicked(ActionEvent actionEvent) {
        AutoSpawn.setLargePreyAutoSpawnEnable(largePreySpawnCheckBox.isSelected());
    }
}
