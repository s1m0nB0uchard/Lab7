
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Saves {



    public static String[] readDAT(String chemin) {
        String mois="";
        String temp= "";
        String result;
        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {
            int i =0;
            while ((result = br.readLine()) != null) {
               if (i==0){
                   mois+=result;
               }
               else temp+=result;
               i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tabSortie[] = {mois,temp};
        return tabSortie;

    }
    static List<String> getMois(String chemin){
        return  Arrays.asList(readDAT(chemin)[0].split(", "));

    }
    static List<String> getTemps(String chemin){
        return  Arrays.asList(readDAT(chemin)[1].split(", "));
    }

    static void save(BorderPane bp, String path, String format){
        File file = new File(path);
        WritableImage image = bp.getCenter().snapshot(new SnapshotParameters(),null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), format, file);
        }
        catch (IOException ex){
            System.out.println(ex.toString());
        }


    }
    static void saveGIF(BorderPane bp, String path){

    }

}
