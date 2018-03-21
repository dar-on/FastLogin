/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastlogin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FastLogin extends Application {
    static Scene scene;
    static Parent root1;
    static Parent root2;
    static FXMLController myHandler1;
    static FXML_setupController myHandler2;
    
    public static final int COUNT = 12;
    static final String PATH_TO_PROPERTIES = "config.properties";
    static Properties prop;
    
//    public static String activ;
//    public static String name0;
//    public static String name1;
//    public static String name2;
//    public static String name3;
//    public static String name4;
//    public static String name5;
//    
//    public static String key1;
//    public static String key2;
//    public static String key3;
//    public static String key4;
//    public static String key5;
//    public static String key6;
//    
    public static String[] btn_acts = new String[COUNT];
    public static String[] names = new String[COUNT];
    public static String clientPath;
    public static String clientIniPath = "asterios/AsteriosGame.ini";
    //public static String clientFile = "AsteriosGame.ini";
    
    @Override
    public void start(Stage stage) throws Exception {
        initFL();
        System.out.println(btn_acts[0]);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("FXML.fxml"));
        root1 = loader1.load();
        myHandler1 = (FXMLController)loader1.getController();
        
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("FXML_setup.fxml"));
        root2 = loader2.load();
        myHandler2 = (FXML_setupController)loader2.getController();
       
        
        scene = new Scene(root1);
        
        stage.setScene(scene);
        stage.setTitle("Fast Login");
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void initFL(){

        try {
            File outFile = new File("config.properties");
            if(!outFile.exists()){
                OutputStream os;
                try (InputStream is = FastLogin.class.getResourceAsStream(PATH_TO_PROPERTIES)) {
                    os = new FileOutputStream(PATH_TO_PROPERTIES);
                    outFile.createNewFile();
                    int b;
                    while((b = is.read()) != -1){
                        os.write(b);
                    }
                }
                os.close();
            }
        }   catch (FileNotFoundException ex) {
            Logger.getLogger(FastLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FastLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //прочитать данные из файла
        try {
            InputStream is = new FileInputStream(PATH_TO_PROPERTIES);
            Properties prop = new Properties();
            prop.load(is);
            
            for(int i=0;i<COUNT;i++){
                btn_acts[i] = prop.getProperty("btn_act"+i);
            }
            
            for(int i=0;i<COUNT;i++){
                names[i] = prop.getProperty("name"+i);
            }
            
            clientPath = prop.getProperty("path");
            //iniPath = clientPath+"asterios/";
            
            is.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FastLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FastLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
