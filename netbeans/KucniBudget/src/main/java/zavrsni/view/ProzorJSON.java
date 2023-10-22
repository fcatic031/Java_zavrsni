package zavrsni.view;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import zavrsni.controller.ObradaDnevnaPotrosnja;
import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;
import zavrsni.util.BudgetException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProzorJSON {
    private JTextArea txtJSON;
    protected JPanel panel;
    private JButton btnDownload;
    private ObradaDnevnaPotrosnja obrada;

public ProzorJSON(String uvjet) {
    radSJSON(uvjet);

}

    private  void radSJSON(String uvjet) {


        Type listType = new TypeToken<List<DnevnaPotrosnja>>() {}.getType();
        List<DnevnaPotrosnja> target = new ObradaDnevnaPotrosnja().read(uvjet);


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setExclusionStrategies(new ProzorJSON.CustomExclusionStrategy()).create();
        String json = gson.toJson(target, listType);
        txtJSON.setText(json);
        //System.out.println(json);
    }

    void ucitajIzJSON(){
        try {

            //Nov 28, 2018, 7:09:02?AM
            Type listType = new TypeToken<List<DnevnaPotrosnja>>(){}.getType();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            List<DnevnaPotrosnja> list = gson.fromJson(Files.readString(Path.of("podaci.json")), listType);


            ObradaDnevnaPotrosnja odp = new ObradaDnevnaPotrosnja();
            for(DnevnaPotrosnja dp : list){
                dp.setId(null);
                odp.setEntitet(dp);
                try {
                    odp.create();
                } catch (BudgetException e) {
                    System.out.println(e.getPoruka());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public class CustomExclusionStrategy implements ExclusionStrategy {

        public boolean shouldSkipField(FieldAttributes f) {

            if(f.getDeclaringClass() == Kategorija.class && f.getName().equals("potrosnje")){
                return true;
            }

            if(f.getDeclaringClass() == Korisnik.class && f.getName().equals("potrosnje")){
                return true;
            }

            if(f.getDeclaringClass() == Obitelj.class && f.getName().equals("clanovi")){
                return true;
            }

            return false;
        }

        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

    }



}
