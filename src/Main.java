import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        BorderPane bp = new BorderPane();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mois");
        yAxis.setLabel("Température");


        //importer
        Menu importer = new Menu("Importer");
        MenuItem lignes = new MenuItem("Lignes");
        lignes.setOnAction((n) -> {
            try {
                XYChart.Series series = new XYChart.Series();
                String chemin = afficherDossiers(window).getAbsolutePath();
                List mois = Saves.getMois(chemin);
                List<String> temperature = Saves.getTemps(chemin);
                LineChart<String, Number> root = new LineChart<>(xAxis, yAxis);
                root.setTitle("LineChart");
                root.setLegendVisible(false);
                for (int i = 0; i < mois.size(); i++) {
                    series.getData().add(new XYChart.Data(mois.get(i), Integer.parseInt(temperature.get(i))));
                }
                root.getData().add(series);
                bp.setCenter(root);
            } catch (NullPointerException ex) {
                Label label = new Label("Aucun fichier sélectionné");
                bp.setCenter(label);
            }
        });

        MenuItem barres = new MenuItem("Barrres");
        barres.setOnAction((n) -> {
            try {
                XYChart.Series series = new XYChart.Series();
                String chemin = afficherDossiers(window).getAbsolutePath();
                List mois = Saves.getMois(chemin);
                List<String> temperature = Saves.getTemps(chemin);
                BarChart<String, Number> root = new BarChart<>(xAxis, yAxis);
                root.setTitle("BarChart");
                root.setLegendVisible(false);
                for (int i = 0; i < mois.size(); i++) {
                    series.getData().add(new XYChart.Data(mois.get(i), Integer.parseInt(temperature.get(i))));
                }
                root.getData().add(series);
                bp.setCenter(root);
            } catch (NullPointerException ex) {
                Label label = new Label("Aucun fichier sélectionné");
                bp.setCenter(label);
            }
        });

        MenuItem regions = new MenuItem("Régions");
        regions.setOnAction((n) -> {
            try {
                XYChart.Series series = new XYChart.Series();
                String chemin = afficherDossiers(window).getAbsolutePath();
                List mois = Saves.getMois(chemin);
                List<String> temperature = Saves.getTemps(chemin);
                AreaChart<String, Number> root = new AreaChart<>(xAxis, yAxis);
                root.setTitle("AreaChart");
                root.setLegendVisible(false);
                for (int i = 0; i < mois.size(); i++) {
                    series.getData().add(new XYChart.Data(mois.get(i), Integer.parseInt(temperature.get(i))));
                }
                root.getData().add(series);
                bp.setCenter(root);
            } catch (NullPointerException ex) {
                Label label = new Label("Aucun fichier sélectionné");
                bp.setCenter(label);
            }


        });
        importer.getItems().addAll(lignes, barres, regions);


        //exporter
        Menu exporter = new Menu("Exporter");
        MenuItem gif = new MenuItem("GIF");
        gif.setOnAction((n) -> {

        });

        MenuItem PNG = new MenuItem("PNG");
        PNG.setOnAction((n) -> {
            if (bp.getCenter() != null) {
                afficherSauvegarde(window,bp);
            }
            else {
                Label erreur = new Label("Veuillez d'abord afficher un graphique");
                erreur.setTextFill(Color.RED);
                bp.setCenter(erreur);
            }

        });

        exporter.getItems().addAll(PNG, gif);


        //affichage
        window.setTitle("Graph Viewer");
        bp.setTop(new MenuBar(importer, exporter));
        Scene scene = new Scene(bp, 400, 400);
        window.setScene(scene);
        window.show();

    }

    private File afficherDossiers(Stage window) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Veuillez sélectionner un fichier");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers texte", "*.dat"));
        return fc.showOpenDialog(window);
    }

    private void afficherSauvegarde(Stage window, BorderPane bp) {

       try {
           FileChooser fc = new FileChooser();
           fc.setTitle("Veuillez sélectionner un fichier");
           fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers image", "*.png"));
           File file =fc.showSaveDialog(window);
           Saves.savePNG(bp,file.getAbsolutePath());
       }
       catch (NullPointerException ex){
           Label erreur = new Label("Vous n'avez pas sélectionné de fichier");
           erreur.setTextFill(Color.RED);
           bp.setCenter(erreur);
       }

    }
}
