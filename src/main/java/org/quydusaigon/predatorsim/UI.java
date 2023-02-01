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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.quydusaigon.Output;
import org.quydusaigon.predatorsim.gameengine.Time;
import org.quydusaigon.predatorsim.util.Parameter;
import org.quydusaigon.predatorsim.util.Prefabs;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UI implements Initializable {

        @FXML
        private TextField widthTextField;
        private static TextField staticWidthTextField;
        @FXML
        private TextField heightTextField;
        private static TextField staticHeightTextField;

        @FXML
        private TextField timeStepTextField;

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
        private LineChart<String,Number> lineChart;

        @FXML
        private SplitPane rightSplitPane;

        @FXML
        private BorderPane simulationWindow;
        private static SplitPane staticRightSplitPane;

        Alert warningAlert = new Alert(Alert.AlertType.NONE);

        private GridPane gridPane;
        private ScheduledExecutorService scheduledExecutorService;

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




                updateSimulationWindowSize();

                startButton.setDisable(true);
                stopButton.setDisable(true);
                nextButton.setDisable(false);


                // Grid
                gridPane = getGridLines();
                simulationWindow.setCenter(gridPane);

                initProperties();

                simulationSpeedSlider.valueProperty().addListener(
                                new ChangeListener<Number>() {

                                        public void changed(ObservableValue<? extends Number> observable,
                                                        Number oldValue, Number newValue) {
                                                Time.setSliderValue((float) simulationSpeedSlider.getValue());
                                        }
                                });
        }



        public void playLineChart(){
                final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setAnimated(false); // axis animations are removed
                yAxis.setAnimated(false); // axis animations are removed

                //defining a series to display data
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                XYChart.Series<String, Number> series3 = new XYChart.Series<>();

                // add series to chart
                lineChart.getData().add(series);
                lineChart.getData().add(series1);
                lineChart.getData().add(series2);
                lineChart.getData().add(series3);

                // this is used to display time in HH:mm:ss format
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

                // setup a scheduled executor to periodically put data into the chart
                scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

                // put dummy data onto graph per second
                scheduledExecutorService.scheduleAtFixedRate(() -> {
                        // get a random integer between 0-10
                        Integer random = ThreadLocalRandom.current().nextInt(100);
                        Integer random1 = ThreadLocalRandom.current().nextInt(100);
                        Integer random2 = ThreadLocalRandom.current().nextInt(100);
                        Integer random3 = ThreadLocalRandom.current().nextInt(100);
                        // Update the chart
                        Platform.runLater(() -> {
                                // get current time
                                Date now = new Date();
                                // put random number with current time
                                System.out.println("0: -> " + random);
                                System.out.println("1: -> " + random1);
                                System.out.println("2: -> " + random2);
                                System.out.println("3: -> " + random3);

                                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random));
                                series1.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random1));
                                series2.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random2));
                                series3.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), random3));
                                if (series.getData().size() > 5) {
                                        series.getData().remove(0);
                                        series1.getData().remove(0);
                                        series2.getData().remove(0);
                                        series3.getData().remove(0);
                                }
                        });
                }, 0, 3, TimeUnit.SECONDS);
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

        public void onStartButtonClicked(ActionEvent actionEvent) {
                playLineChart();
                App.getLoop().start();
                applyButton.setDisable(true);
                nextButton.setDisable(true);
                clearButton.setDisable(true);
        }

        public void onStopButtonClicked(ActionEvent actionEvent) {
                scheduledExecutorService.shutdownNow();
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
                lineChart.getData().clear();
                App.load(Level::main);
                startButton.setDisable(false);
                stopButton.setDisable(false);
                nextButton.setDisable(false);
        }

        public void onClearButtonClicked(ActionEvent actionEvent) {
                lineChart.getData().clear();
                App.load(() -> {
                });
        }

        /*
         * Rendering options
         */

        private static BooleanProperty showsVisionProperty;
        private static BooleanProperty showsStatusProperty;

        private void initProperties() {
                showsVisionProperty = new SimpleBooleanProperty(showVisionCheckBox.isSelected());
                showsStatusProperty = new SimpleBooleanProperty(showStatusCheckBox.isSelected());
        }

        public static BooleanProperty getShowsVisionProperty() {
                return showsVisionProperty;
        }

        public static BooleanProperty getShowsStatusProperty() {
                return showsStatusProperty;
        }

        public void onShowVisionCheckBoxClicked(ActionEvent actionEvent) {
                getShowsVisionProperty().set(showVisionCheckBox.isSelected());
        }

        public void onShowStatusCheckBoxClicked(ActionEvent actionEvent) {
                getShowsStatusProperty().set(showStatusCheckBox.isSelected());
        }

        public void onShowGridLinesCheckBoxClicked(ActionEvent actionEvent) {
                gridPane.setVisible(showGridLinesCheckBox.isSelected());
        }

        private GridPane getGridLines() {
                GridPane gridPane = new GridPane();
                gridPane.setGridLinesVisible(true);
                gridPane.setVisible(showGridLinesCheckBox.isSelected());

                final int numCols = 50;
                final int numRows = 50;
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

        public void onPredatorImageButtonClicked(ActionEvent actionEvent) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
                );
                choosePredatorImageButton.setOnAction(e -> {
                        File selectedFile = fileChooser.showOpenDialog(stage);
                        Prefabs.setPredatorImageURL(selectedFile.getAbsolutePath());
                });
        }

        public void onSmallPreyImageButtonClicked(ActionEvent actionEvent) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
                );
                chooseSmallPreyImageButton.setOnAction(e -> {
                        File selectedFile = fileChooser.showOpenDialog(stage);
                        Prefabs.setSmallPreyImageURL(selectedFile.getAbsolutePath());
                });
        }

        public void onMediumPreyImageButtonClicked(ActionEvent actionEvent) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
                );
                chooseMediumPreyImageButton.setOnAction(e -> {
                        File selectedFile = fileChooser.showOpenDialog(stage);
                        Prefabs.setMediumPreyImageURL(selectedFile.getAbsolutePath());
                });
        }

        public void onLargePreyImageButtonClicked(ActionEvent actionEvent) {
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
                );
                chooseLargePreyImageButton.setOnAction(e -> {
                        File selectedFile = fileChooser.showOpenDialog(stage);
                        Prefabs.setLargePreyImageURL(selectedFile.getAbsolutePath());
                });
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
}
