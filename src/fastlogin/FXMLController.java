/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastlogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Андрей
 * D:\la2\Asterios.exe /desktop /autoplay
 */
public class FXMLController implements Initializable {
    
    private Label label;
    @FXML
    private Button btn_0;
    @FXML
    private Button btn_1;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_4;
    @FXML
    private Button btn_5;
    @FXML
    private Button btn_6;
    @FXML
    private Button btn_10;
    @FXML
    private Button btn_9;
    @FXML
    private Button btn_8;
    @FXML
    private Button btn_7;
    @FXML
    private Button btn_11;
    
    @FXML
    private Label lbl_0;
    @FXML
    private Label lbl_1;
    @FXML
    private Label lbl_2;
    @FXML
    private Label lbl_3;
    @FXML
    private Label lbl_4;
    @FXML
    private Label lbl_5;
    @FXML
    private Label lbl_6;
    @FXML
    private Label lbl_7;
    @FXML
    private Label lbl_8;
    @FXML
    private Label lbl_9;
    @FXML
    private Label lbl_10;
    @FXML
    private Label lbl_11;
    
    @FXML
    private Button btn_setting;
    
    Label[] arrLbl = new Label[FastLogin.COUNT];
    Button[] arrBtn = new Button[FastLogin.COUNT];
    static File tmpFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myInitMain();
        System.out.println("init_ctrl");
    }    
    
    @FXML       //CLICK F1-12
    private void click_btnF(ActionEvent event) {
        String s = ((Button)event.getSource()).getId().substring(4);
        int n = Integer.parseInt(s);
        startGame(n);
    }

    @FXML       //SETTING
    private void click_btn_setting(ActionEvent event) {
        FastLogin.scene.setRoot(FastLogin.root2);
    }

    void myInitMain(){
        arrLbl[0] = lbl_0;
        arrLbl[1] = lbl_1;
        arrLbl[2] = lbl_2;
        arrLbl[3] = lbl_3;
        arrLbl[4] = lbl_4;
        arrLbl[5] = lbl_5;
        arrLbl[6] = lbl_6;
        arrLbl[7] = lbl_7;
        arrLbl[8] = lbl_8;
        arrLbl[9] = lbl_9;
        arrLbl[10] = lbl_10;
        arrLbl[11] = lbl_11;
        
        arrBtn[0] = btn_0;
        arrBtn[1] = btn_1;
        arrBtn[2] = btn_2;
        arrBtn[3] = btn_3;
        arrBtn[4] = btn_4;
        arrBtn[5] = btn_5; 
        arrBtn[6] = btn_6;
        arrBtn[7] = btn_7;
        arrBtn[8] = btn_8;
        arrBtn[9] = btn_9;
        arrBtn[10] = btn_10;
        arrBtn[11] = btn_11; 
        dataToGui();

    }

    void dataToGui(){
                //запись данных в гуи мейна
        for (int i=0;i<FastLogin.COUNT;i++) {
            System.out.println(FastLogin.btn_acts[i]+"dataToGui");
            if(FastLogin.btn_acts[i].equals("1")){
                arrLbl[i].setText(FastLogin.names[i]);
                arrBtn[i].setText("F"+(i+1));
            }else{
                arrLbl[i].setText("");
                arrBtn[i].setText("*");
            }
        }
    }
    
    void startGame(int btnNum){
        if(FastLogin.btn_acts[btnNum].equalsIgnoreCase("1")){
            File fileIni = new File(FastLogin.clientPath+FastLogin.clientIniPath);
            if(fileIni.exists()){
                FileInputStream fis;
                try (BufferedReader br = new BufferedReader(new FileReader(fileIni))){
                    tmpFile = File.createTempFile("AsteriosGame", ".tmp");
                    FileWriter fw = new  FileWriter(tmpFile,true);
                    String hashKey = getKey(btnNum);
                    System.out.println(hashKey);
                    String s;
                    while((s=br.readLine())!=null){
                        if(s.contains("Key")){
                            fw.write("Key="+hashKey+"\r\n");
                        }else{
                             fw.write(s+"\r\n");
                        }
                        fw.flush();
                    }
                    br.close();

                    Files.delete(fileIni.toPath());
                    fis = new FileInputStream(tmpFile);
                    Files.copy(fis, fileIni.toPath());
                    fis.close();
                    String param1 = "/desktop";
                    String param2 = "/autoplay";
                    Runtime.getRuntime().exec(FastLogin.clientPath+"Asterios.exe"+" "+param1+" "+param2);
                    System.out.println(FastLogin.clientPath+"Asterios.exe"+" "+param1+" "+param2);
                    //Runtime.getRuntime().exec(D:\la2\Asterios.exe /desktop /autoplay)
                                            //try {
        //                } catch (IOException ex) {
        //                    System.out.println("ini-not delet");
        //                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        //                }  

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }  
    }  
    
    String getKey(int btnNum){
      
        InputStream is = null;
        String key = null;
        try {
            Properties prop = new Properties();
            is = new FileInputStream(FastLogin.PATH_TO_PROPERTIES);
            prop.load(is);
            is.close();
             key = prop.getProperty("key"+btnNum);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return key;
    }

    @FXML
    private void push_keyF(KeyEvent event) {
//        if(event.getCode()== KeyCode.F1 ){
//            
//        }
    }
}
