import javafx.application.Application;  
import javafx.scene.Scene;  
import javafx.scene.chart.*;  
import javafx.scene.layout.VBox;  
import javafx.stage.Stage;  
import java.util.*;  

public class DataAnalysisVisualization extends Application {  
    private static String analysisType;  
    private static String visualizationType;  
    private static Map<String, Integer> data;  
    private static Scanner scanner = new Scanner(System.in);  

    public static void main(String[] args) {  
        selectAnalysisType();  
        collectUserData();  
        selectVisualizationType();  
        System.out.println("Launching " + visualizationType + " for " + analysisType + "...");  
        launch(args);  
    }  

    private static void selectAnalysisType() {  
        System.out.println("Choose the type of data analysis you want:");  
        System.out.println("1: Analyze social media sentiment on a topic");  
        System.out.println("2: Predict stock market trends using historical data");  
        System.out.println("3: Visualize customer purchasing patterns");  
        System.out.print("Enter your choice (1/2/3): ");  
        int choice = scanner.nextInt();  
        scanner.nextLine();  

        switch (choice) {  
            case 1:  
                analysisType = "Social Media Sentiment Analysis";  
                break;  
            case 2:  
                analysisType = "Stock Market Trends Analysis";  
                break;  
            case 3:  
                analysisType = "Customer Purchasing Patterns";  
                break;  
            default:  
                System.out.println("Invalid choice! Exiting.");  
                System.exit(0);  
        }  
    }  

    private static void collectUserData() {  
        data = new HashMap<>();  
        System.out.println("\nEnter data for " + analysisType + ":");  

        if (analysisType.equals("Social Media Sentiment Analysis")) {  
            System.out.print("Enter the number of Positive Reactions: ");  
            int positive = scanner.nextInt();  
            System.out.print("Enter the number of Neutral Reactions: ");  
            int neutral = scanner.nextInt();  
            System.out.print("Enter the number of Negative Reactions: ");  
            int negative = scanner.nextInt();  
            data.put("Positive", positive);  
            data.put("Neutral", neutral);  
            data.put("Negative", negative);  

        } else if (analysisType.equals("Stock Market Trends Analysis")) {  
            System.out.print("Enter the number of Growth Days: ");  
            int growth = scanner.nextInt();  
            System.out.print("Enter the number of Stable Days: ");  
            int stable = scanner.nextInt();  
            System.out.print("Enter the number of Decline Days: ");  
            int decline = scanner.nextInt();  
            data.put("Growth", growth);  
            data.put("Stable", stable);  
            data.put("Decline", decline);  

        } else if (analysisType.equals("Customer Purchasing Patterns")) {  
            System.out.print("Enter the number of Electronics Purchases: ");  
            int electronics = scanner.nextInt();  
            System.out.print("Enter the number of Clothing Purchases: ");  
            int clothing = scanner.nextInt();  
            System.out.print("Enter the number of Grocery Purchases: ");  
            int groceries = scanner.nextInt();  
            System.out.print("Enter the number of Furniture Purchases: ");  
            int furniture = scanner.nextInt();  
            data.put("Electronics", electronics);  
            data.put("Clothing", clothing);  
            data.put("Groceries", groceries);  
            data.put("Furniture", furniture);  
        }  

        System.out.println("\nData collected for " + analysisType + ": " + data);  
    }  

    private static void selectVisualizationType() {  
        System.out.println("\nChoose the type of visualization for " + analysisType + ":");  
        System.out.println("1: Pie Chart");  
        System.out.println("2: Bar Chart");  
        System.out.println("3: Line Chart");  
        System.out.println("4: Scatter Plot");  
        System.out.print("Enter your choice (1/2/3/4): ");  
        int choice = scanner.nextInt();  
        scanner.nextLine();  

        switch (choice) {  
            case 1:  
                visualizationType = "Pie Chart";  
                break;  
            case 2:  
                visualizationType = "Bar Chart";  
                break;  
            case 3:  
                visualizationType = "Line Chart";  
                break;  
            case 4:  
                visualizationType = "Scatter Plot";  
                break;  
            default:  
                System.out.println("Invalid choice! Exiting.");  
                System.exit(0);  
        }  
    }  

    @Override  
    public void start(Stage stage) {  
        VBox root = new VBox();  
        Scene scene = new Scene(root, 800, 600);  

        if ("Pie Chart".equals(visualizationType)) {  
            root.getChildren().add(createPieChart());  
        } else if ("Bar Chart".equals(visualizationType)) {  
            root.getChildren().add(createBarChart());  
        } else if ("Line Chart".equals(visualizationType)) {  
            root.getChildren().add(createLineChart());  
        } else if ("Scatter Plot".equals(visualizationType)) {  
            root.getChildren().add(createScatterPlot());  
        }  

        stage.setScene(scene);  
        stage.setTitle(analysisType + " - " + visualizationType);  
        stage.show();  
    }  

    private PieChart createPieChart() {  
        PieChart pieChart = new PieChart();  
        for (Map.Entry<String, Integer> entry : data.entrySet()) {  
            PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());  
            pieChart.getData().add(slice);  
        }  
        pieChart.setTitle("Data Visualization - Pie Chart");  
        return pieChart;  
    }  

    private BarChart<String, Number> createBarChart() {  
        CategoryAxis xAxis = new CategoryAxis();  
        NumberAxis yAxis = new NumberAxis();  
        xAxis.setLabel("Category");  
        yAxis.setLabel("Values");  

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);  
        XYChart.Series<String, Number> series = new XYChart.Series<>();  
        series.setName("Data Overview");  

        for (Map.Entry<String, Integer> entry : data.entrySet()) {  
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));  
        }  

        barChart.getData().add(series);  
        barChart.setTitle("Data Visualization - Bar Chart");  
        return barChart;  
    }  

    private LineChart<String, Number> createLineChart() {  
        CategoryAxis xAxis = new CategoryAxis();  
        NumberAxis yAxis = new NumberAxis();  
        xAxis.setLabel("Category");  
        yAxis.setLabel("Values");  

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);  
        XYChart.Series<String, Number> series = new XYChart.Series<>();  
        series.setName("Data Over Time");  

        for (Map.Entry<String, Integer> entry : data.entrySet()) {  
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));  
        }  

        lineChart.getData().add(series);  
        lineChart.setTitle("Data Visualization - Line Chart");  
        return lineChart;  
    }  

    private ScatterChart<String, Number> createScatterPlot() {  
        CategoryAxis xAxis = new CategoryAxis();  
        NumberAxis yAxis = new NumberAxis();  
        xAxis.setLabel("Category");  
        yAxis.setLabel("Values");  

        ScatterChart<String, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);  
        XYChart.Series<String, Number> series = new XYChart.Series<>();  
        series.setName("Data Points");  

        for (Map.Entry<String, Integer> entry : data.entrySet()) {  
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));  
        }  

        scatterChart.getData().add(series);  
        scatterChart.setTitle("Data Visualization - Scatter Plot");  
        return scatterChart;  
    }  
}  