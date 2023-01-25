package org.quydusaigon.predatorsim.util;

public class Parameter {

    public static String getWindowWidth() {
        return String.valueOf(windowWidth);
    }

    public static void setWindowWidth(String windowWidth) {
        Parameter.windowWidth = Double.parseDouble(windowWidth);
    }

    public static String getWindowHeight() {
        return String.valueOf(windowHeight);
    }

    public static void setWindowHeight(String windowHeight) {
        Parameter.windowHeight = Double.parseDouble(windowHeight);
    }

    private static double windowWidth = 700;
    private static double windowHeight = 700;

    private static int predatorCount = 1;
    private static int smallPreyCount = 1;
    private static int mediumPreyCount = 1;
    private static int largePreyCount = 1;

    public static void setPredatorCount(String predatorCount) {
        Parameter.predatorCount = Integer.parseInt(predatorCount);
    }

    public static void setSmallPreyCount(String smallPreyCount) {
        Parameter.smallPreyCount = Integer.parseInt(smallPreyCount);
    }

    public static void setMediumPreyCount(String mediumPreyCount) {
        Parameter.mediumPreyCount = Integer.parseInt(mediumPreyCount);
    }

    public static void setLargePreyCount(String largePreyCount) {
        Parameter.largePreyCount = Integer.parseInt(largePreyCount);
    }

    public static void setPredatorSpeedMinimumRange(String predatorSpeedMinimumRange) {
        Parameter.predatorSpeedMinimumRange = Double.parseDouble(predatorSpeedMinimumRange);
    }

    public static void setPredatorSpeedMaximumRange(String predatorSpeedMaximumRange) {
        Parameter.predatorSpeedMaximumRange = Double.parseDouble(predatorSpeedMaximumRange);
    }

    public static void setSmallPreySpeedMinimumRange(String smallPreySpeedMinimumRange) {
        Parameter.smallPreySpeedMinimumRange = Double.parseDouble(smallPreySpeedMinimumRange);
    }

    public static void setSmallPreySpeedMaximumRange(String smallPreySpeedMaximumRange) {
        Parameter.smallPreySpeedMaximumRange = Double.parseDouble(smallPreySpeedMaximumRange);
    }

    public static void setMediumPreySpeedMinimumRange(String mediumPreySpeedMinimumRange) {
        Parameter.mediumPreySpeedMinimumRange = Double.parseDouble(mediumPreySpeedMinimumRange);
    }

    public static void setMediumPreySpeedMaximumRange(String mediumPreySpeedMaximumRange) {
        Parameter.mediumPreySpeedMaximumRange = Double.parseDouble(mediumPreySpeedMaximumRange);
    }

    public static void setLargePreySpeedMinimumRange(String largePreySpeedMinimumRange) {
        Parameter.largePreySpeedMinimumRange = Double.parseDouble(largePreySpeedMinimumRange);
    }

    public static void setLargePreySpeedMaximumRange(String largePreySpeedMaximumRange) {
        Parameter.largePreySpeedMaximumRange = Double.parseDouble(largePreySpeedMaximumRange);
    }

    public static void setPredatorVisionMinimumRange(String predatorVisionMinimumRange) {
        Parameter.predatorVisionMinimumRange = Double.parseDouble(predatorVisionMinimumRange);
    }

    public static void setPredatorVisionMaximumRange(String predatorVisionMaximumRange) {
        Parameter.predatorVisionMaximumRange = Double.parseDouble(predatorVisionMaximumRange);
    }

    public static void setSmallPreyVisionMinimumRange(String smallPreyVisionMinimumRange) {
        Parameter.smallPreyVisionMinimumRange = Double.parseDouble(smallPreyVisionMinimumRange);
    }

    public static void setSmallPreyVisionMaximumRange(String smallPreyVisionMaximumRange) {
        Parameter.smallPreyVisionMaximumRange = Double.parseDouble(smallPreyVisionMaximumRange);
    }

    public static void setMediumPreyVisionMinimumRange(String mediumPreyVisionMinimumRange) {
        Parameter.mediumPreyVisionMinimumRange = Double.parseDouble(mediumPreyVisionMinimumRange);
    }

    public static void setMediumPreyVisionMaximumRange(String mediumPreyVisionMaximumRange) {
        Parameter.mediumPreyVisionMaximumRange = Double.parseDouble(mediumPreyVisionMaximumRange);
    }

    public static void setLargePreyVisionMinimumRange(String largePreyVisionMinimumRange) {
        Parameter.largePreyVisionMinimumRange = Double.parseDouble(largePreyVisionMinimumRange);
    }

    public static void setLargePreyVisionMaximumRange(String largePreyVisionMaximumRange) {
        Parameter.largePreyVisionMaximumRange = Double.parseDouble(largePreyVisionMaximumRange);
    }

    public static void setSmallPreyNutritionMinimumRange(String smallPreyNutritionMinimumRange) {
        Parameter.smallPreyNutritionMinimumRange = Integer.parseInt(smallPreyNutritionMinimumRange);
    }

    public static void setSmallPreyNutritionMaximumRange(String smallPreyNutritionMaximumRange) {
        Parameter.smallPreyNutritionMaximumRange = Integer.parseInt(smallPreyNutritionMaximumRange);
    }

    public static void setMediumPreyNutritionMinimumRange(String mediumPreyNutritionMinimumRange) {
        Parameter.mediumPreyNutritionMinimumRange = Integer.parseInt(mediumPreyNutritionMinimumRange);
    }

    public static void setMediumPreyNutritionMaximumRange(String mediumPreyNutritionMaximumRange) {
        Parameter.mediumPreyNutritionMaximumRange = Integer.parseInt(mediumPreyNutritionMaximumRange);
    }

    public static void setLargePreyNutritionMinimumRange(String largePreyNutritionMinimumRange) {
        Parameter.largePreyNutritionMinimumRange = Integer.parseInt(largePreyNutritionMinimumRange);
    }

    public static void setLargePreyNutritionMaximumRange(String largePreyNutritionMaximumRange) {
        Parameter.largePreyNutritionMaximumRange = Integer.parseInt(largePreyNutritionMaximumRange);
    }

    public static void setSmallPreyDefenseMinimumRange(String smallPreyDefenseMinimumRange) {
        Parameter.smallPreyDefenseMinimumRange = Double.parseDouble(smallPreyDefenseMinimumRange);
    }

    public static void setSmallPreyDefenseMaximumRange(String smallPreyDefenseMaximumRange) {
        Parameter.smallPreyDefenseMaximumRange = Double.parseDouble(smallPreyDefenseMaximumRange);
    }

    public static void setMediumPreyDefenseMinimumRange(String mediumPreyDefenseMinimumRange) {
        Parameter.mediumPreyDefenseMinimumRange = Double.parseDouble(mediumPreyDefenseMinimumRange);
    }

    public static void setMediumPreyDefenseMaximumRange(String mediumPreyDefenseMaximumRange) {
        Parameter.mediumPreyDefenseMaximumRange = Double.parseDouble(mediumPreyDefenseMaximumRange);
    }

    public static void setLargePreyDefenseMinimumRange(String largePreyDefenseMinimumRange) {
        Parameter.largePreyDefenseMinimumRange = Double.parseDouble(largePreyDefenseMinimumRange);
    }

    public static void setLargePreyDefenseMaximumRange(String largePreyDefenseMaximumRange) {
        Parameter.largePreyDefenseMaximumRange = Double.parseDouble(largePreyDefenseMaximumRange);
    }

    public static void setPredatorStarvationResillienceMinimumRange(String predatorStarvationResillienceMinimumRange) {
        Parameter.predatorStarvationResillienceMinimumRange = Integer.parseInt(predatorStarvationResillienceMinimumRange);
    }

    public static void setPredatorStarvationResillienceMaximumRange(String predatorStarvationResillienceMaximumRange) {
        Parameter.predatorStarvationResillienceMaximumRange = Integer.parseInt(predatorStarvationResillienceMaximumRange);
    }

    public static void setPredatorGroupRadius(String predatorGroupRadius) {
        Parameter.predatorGroupRadius = Double.parseDouble(predatorGroupRadius);
    }

    private static double predatorSpeedMinimumRange = 5;
    private static double predatorSpeedMaximumRange = 6;
    private static double smallPreySpeedMinimumRange = 4;
    private static double smallPreySpeedMaximumRange = 5;
    private static double mediumPreySpeedMinimumRange = 3;
    private static double mediumPreySpeedMaximumRange = 4;
    private static double largePreySpeedMinimumRange = 2;
    private static double largePreySpeedMaximumRange = 3;

    private static double predatorVisionMinimumRange = 80;
    private static double predatorVisionMaximumRange = 110;
    private static double smallPreyVisionMinimumRange = 70;
    private static double smallPreyVisionMaximumRange = 90;
    private static double mediumPreyVisionMinimumRange = 70;
    private static double mediumPreyVisionMaximumRange = 90;
    private static double largePreyVisionMinimumRange = 70;
    private static double largePreyVisionMaximumRange = 90;

    private static int smallPreyNutritionMinimumRange = 15;
    private static int smallPreyNutritionMaximumRange = 25;
    private static int mediumPreyNutritionMinimumRange = 50;
    private static int mediumPreyNutritionMaximumRange = 65;
    private static int largePreyNutritionMinimumRange = 100;
    private static int largePreyNutritionMaximumRange = 120;

    private static double smallPreyDefenseMinimumRange = 0;
    private static double smallPreyDefenseMaximumRange = 10;
    private static double mediumPreyDefenseMinimumRange = 30;
    private static double mediumPreyDefenseMaximumRange = 50;
    private static double largePreyDefenseMinimumRange = 75;
    private static double largePreyDefenseMaximumRange = 100;

    private static int predatorStarvationResillienceMinimumRange = 30;
    private static int predatorStarvationResillienceMaximumRange = 50;
    private static double predatorGroupRadius = 100;

    public static String getPredatorCount() {
        return String.valueOf(predatorCount);
    }

    public static String getSmallPreyCount() {
        return String.valueOf(smallPreyCount);
    }

    public static String getMediumPreyCount() {
        return String.valueOf(mediumPreyCount);
    }

    public static String getLargePreyCount() {
        return String.valueOf(largePreyCount);
    }

    public static String getPredatorSpeedMinimumRange() {
        return String.valueOf(predatorSpeedMinimumRange);
    }

    public static String getPredatorSpeedMaximumRange() {
        return String.valueOf(predatorSpeedMaximumRange);
    }

    public static String getSmallPreySpeedMinimumRange() {
        return String.valueOf(smallPreySpeedMinimumRange);
    }

    public static String getSmallPreySpeedMaximumRange() {
        return String.valueOf(smallPreySpeedMaximumRange);
    }

    public static String getMediumPreySpeedMinimumRange() {
        return String.valueOf(mediumPreySpeedMinimumRange);
    }

    public static String getMediumPreySpeedMaximumRange() {
        return String.valueOf(mediumPreySpeedMaximumRange);
    }

    public static String getLargePreySpeedMinimumRange() {
        return String.valueOf(largePreySpeedMinimumRange);
    }

    public static String getLargePreySpeedMaximumRange() {
        return String.valueOf(largePreySpeedMaximumRange);
    }

    public static String getPredatorVisionMinimumRange() {
        return String.valueOf(predatorVisionMinimumRange);
    }

    public static String getPredatorVisionMaximumRange() {
        return String.valueOf(predatorVisionMaximumRange);
    }

    public static String getSmallPreyVisionMinimumRange() {
        return String.valueOf(smallPreyVisionMinimumRange);
    }

    public static String getSmallPreyVisionMaximumRange() {
        return String.valueOf(smallPreyVisionMaximumRange);
    }

    public static String getMediumPreyVisionMinimumRange() {
        return String.valueOf(mediumPreyVisionMinimumRange);
    }

    public static String getMediumPreyVisionMaximumRange() {
        return String.valueOf(mediumPreyVisionMaximumRange);
    }

    public static String getLargePreyVisionMinimumRange() {
        return String.valueOf(largePreyVisionMinimumRange);
    }

    public static String getLargePreyVisionMaximumRange() {
        return String.valueOf(largePreyVisionMaximumRange);
    }

    public static String getSmallPreyNutritionMinimumRange() {
        return String.valueOf(smallPreyNutritionMinimumRange);
    }

    public static String getSmallPreyNutritionMaximumRange() {
        return String.valueOf(smallPreyNutritionMaximumRange);
    }

    public static String getMediumPreyNutritionMinimumRange() {
        return String.valueOf(mediumPreyNutritionMinimumRange);
    }

    public static String getMediumPreyNutritionMaximumRange() {
        return String.valueOf(mediumPreyNutritionMaximumRange);
    }

    public static String getLargePreyNutritionMinimumRange() {
        return String.valueOf(largePreyNutritionMinimumRange);
    }

    public static String getLargePreyNutritionMaximumRange() {
        return String.valueOf(largePreyNutritionMaximumRange);
    }

    public static String getSmallPreyDefenseMinimumRange() {
        return String.valueOf(smallPreyDefenseMinimumRange);
    }

    public static String getSmallPreyDefenseMaximumRange() {
        return String.valueOf(smallPreyDefenseMaximumRange);
    }

    public static String getMediumPreyDefenseMinimumRange() {
        return String.valueOf(mediumPreyDefenseMinimumRange);
    }

    public static String getMediumPreyDefenseMaximumRange() {
        return String.valueOf(mediumPreyDefenseMaximumRange);
    }

    public static String getLargePreyDefenseMinimumRange() {
        return String.valueOf(largePreyDefenseMinimumRange);
    }

    public static String getLargePreyDefenseMaximumRange() {
        return String.valueOf(largePreyDefenseMaximumRange);
    }

    public static String getPredatorStarvationResillienceMinimumRange() {
        return String.valueOf(predatorStarvationResillienceMinimumRange);
    }

    public static String getPredatorStarvationResillienceMaximumRange() {
        return String.valueOf(predatorStarvationResillienceMaximumRange);
    }

    public static String getPredatorGroupRadius() {
        return String.valueOf(predatorGroupRadius);
    }
}


