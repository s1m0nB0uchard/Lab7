
import java.io.*;
import java.util.ArrayList;
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

}
