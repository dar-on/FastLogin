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
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Андрей
 */
public class FXML_setupController implements Initializable {

    @FXML
    private Button btn_s0;
    @FXML
    private Button btn_s1;
    @FXML
    private Button btn_s2;
    @FXML
    private Button btn_s3;
    @FXML
    private Button btn_s4;
    @FXML
    private Button btn_s5;
    @FXML
    private Button btn_s6;
    @FXML
    private Button btn_s7;
    @FXML
    private Button btn_s8;
    @FXML
    private Button btn_s9;
    @FXML
    private Button btn_s10;
    @FXML
    private Button btn_s11;
    
    @FXML
    private Button btn_save;
    @FXML
    private TextField txtfld_gameLoc;
    @FXML
    private TextField txtf_0;
    @FXML
    private TextField txtf_1;
    @FXML
    private TextField txtf_2;
    @FXML
    private TextField txtf_3;
    @FXML
    private TextField txtf_4;
    @FXML
    private TextField txtf_5;
    @FXML
    private TextField txtf_6;
    @FXML
    private TextField txtf_7;
    @FXML
    private TextField txtf_8;
    @FXML
    private TextField txtf_9;
    @FXML
    private TextField txtf_10;
    @FXML
    private TextField txtf_11;
    
    @FXML
    private ImageView imgVw_0;
    @FXML
    private ImageView imgVw_1;
    @FXML
    private ImageView imgVw_2;
    @FXML
    private ImageView imgVw_3;
    @FXML
    private ImageView imgVw_4;
    @FXML
    private ImageView imgVw_5;
    @FXML
    private ImageView imgVw_6;
    @FXML
    private ImageView imgVw_7;
    @FXML
    private ImageView imgVw_8;
    @FXML
    private ImageView imgVw_9;
    @FXML
    private ImageView imgVw_10;
    @FXML
    private ImageView imgVw_11;
    
    @FXML
    private Button btn_back;
    @FXML
    private Label lbl_gameLoc;
    @FXML
    private Button btn_change;

    String hashKey;
    TextField[] arr_txtf = new TextField[FastLogin.COUNT];
    ImageView[] arr_imgVw = new ImageView[FastLogin.COUNT];
    Image imageKey = new Image(this.getClass().getResourceAsStream("key-24x24.png"));
    ArrayList<LoginSet> arrLB = FastLogin.arrLB;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createArrayElementsGUI();
        dataToElementsGUI();
        // TODO
    }    
                    //INIT
    void createArrayElementsGUI(){
        arr_txtf[0] = txtf_0;
        arr_txtf[1] = txtf_1;
        arr_txtf[2] = txtf_2;
        arr_txtf[3] = txtf_3;
        arr_txtf[4] = txtf_4;
        arr_txtf[5] = txtf_5;
        arr_txtf[6] = txtf_6;
        arr_txtf[7] = txtf_7;
        arr_txtf[8] = txtf_8;
        arr_txtf[9] = txtf_9;
        arr_txtf[10] = txtf_10;
        arr_txtf[11] = txtf_11;
        
        arr_imgVw[0] = imgVw_0;
        arr_imgVw[1] = imgVw_1;
        arr_imgVw[2] = imgVw_2;
        arr_imgVw[3] = imgVw_3;
        arr_imgVw[4] = imgVw_4;
        arr_imgVw[5] = imgVw_5;
        arr_imgVw[6] = imgVw_6;
        arr_imgVw[7] = imgVw_7;
        arr_imgVw[8] = imgVw_8;
        arr_imgVw[9] = imgVw_9;
        arr_imgVw[10] = imgVw_10;
        arr_imgVw[11] = imgVw_11;
    }    
        
    void dataToElementsGUI(){
        for(int i=0;i<FastLogin.COUNT;i++){
            if(arrLB.get(i).isActive()){
                arr_imgVw[i].setImage(imageKey);
                arr_imgVw[i].setVisible(true);
                arr_txtf[i].setText(arrLB.get(i).getName());
            }else{
                arr_imgVw[i].setVisible(false);
                arr_txtf[i].setText("");
            }
        }
        lbl_gameLoc.setVisible(true);
        lbl_gameLoc.setText(FastLogin.clientPath);
        txtfld_gameLoc.setVisible(false);
        btn_change.setVisible(true);
        btn_save.setVisible(false);     
    }
    
    @FXML       //push F1-F6
    private void click_btn_sF(ActionEvent event) {
//        String s = ((Button)event.getSource()).getId().substring(5);
//        int n = Integer.parseInt(s);
//        if(!FastLogin.btn_acts[n].equalsIgnoreCase("1")){
//            setLoginKey(n);
//        }
    }
    @FXML
    private void click_btn(MouseEvent event) {
        String s = ((Button)event.getSource()).getId().substring(5);
        int n = Integer.parseInt(s);
        if(event.isControlDown()){
            
            //System.out.println("ctrl+click");
            clrLoginKey(n);
        }else{
            if(!arrLB.get(n).isActive()){
                setLoginKey(n);
            } 
        }
    }
    
    @FXML       //SAVE
    private void click_btn_save(ActionEvent event) {    
        if(!txtfld_gameLoc.getText().isEmpty()){
            String s = txtfld_gameLoc.getText();
            if(!s.equalsIgnoreCase(FastLogin.clientPath)){
                File file = new File(s+FastLogin.clientIniPath);
                if(file.exists()){
                    FastLogin.clientPath = s;
                    //lbl_gameLoc.setVisible(true);
                    lbl_gameLoc.setText(FastLogin.clientPath);
                    //txtfld_gameLoc.setVisible(false);
                    //btn_change.setVisible(true);
                    //btn_save.setVisible(false);

                    try {
                        Properties prop = new Properties();
                        InputStream is = new FileInputStream(FastLogin.PATH_TO_PROPERTIES);
                        prop.load(is);
                        is.close();
                        prop.setProperty("path", s);
                        OutputStream os = new FileOutputStream(FastLogin.PATH_TO_PROPERTIES);
                        prop.store(os, null);
                        os.close();
                        prop = null;
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FXML_setupController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXML_setupController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(file.getAbsolutePath());
                }else{
                    System.out.println("Wrong way");
                }
            }else{
                System.out.println("way equal");
            }
            lbl_gameLoc.setVisible(true);
            txtfld_gameLoc.setVisible(false);
            btn_change.setVisible(true);
            btn_save.setVisible(false);
        }else{
            System.out.println("way empty");
        }
    }
    
    @FXML       //BACK
    private void click_btn_back(ActionEvent event) {
        lbl_gameLoc.setVisible(true);
        lbl_gameLoc.setText(FastLogin.clientPath);
        txtfld_gameLoc.setVisible(false);
        btn_change.setVisible(true);
        btn_save.setVisible(false); 
        FastLogin.scene.setRoot(FastLogin.root1);
        FastLogin.initFL();
        FastLogin.myHandler1.dataToGui();
    }

    @FXML       //CHANGE
    private void click_btn_change(ActionEvent event) {
        lbl_gameLoc.setVisible(false);
        txtfld_gameLoc.setVisible(true);
        txtfld_gameLoc.setText(FastLogin.clientPath);
        btn_change.setVisible(false);
        btn_save.setVisible(true);        
    }


  
    void setLoginKey(int btnNum){
        if(!arr_txtf[btnNum].getText().isEmpty()){
            String newName = arr_txtf[btnNum].getText();
            System.out.println("newname0"+newName);
            File file = new File(FastLogin.clientPath+FastLogin.clientIniPath);
            if(file.exists()){
                try (BufferedReader br = new BufferedReader(new FileReader(file))){
                    String s;
                    System.out.println("newname1"+newName);
                    while((s=br.readLine())!=null){
                        if(s.contains("Key")){
                            hashKey = s.substring(4);
                        }
                    }
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(FXML_setupController.class.getName()).log(Level.SEVERE, null, ex);
                }
                writeProp(btnNum, "1", newName, hashKey);

                
                arr_imgVw[btnNum].setImage(imageKey);
                arr_imgVw[btnNum].setVisible(true);
            }
        }else{
            System.out.println("no name");
        }
    }
    
    void clrLoginKey(int btnNum){
        writeProp(btnNum, "0", "", "");
        arr_txtf[btnNum].setText("");
        arr_imgVw[btnNum].setVisible(false);        
    }
    
    void writeProp(int btnNum,String btn_act, String name, String key){
        try {
           // Properties prop = new Properties();
           // InputStream is = new FileInputStream(FastLogin.PATH_TO_PROPERTIES);
           // prop.load(is);
           // is.close();
            prop.setProperty("btn_act"+btnNum, btn_act);
            FastLogin.btn_acts[btnNum] = btn_act;
            System.out.println("newname"+name);
            prop.setProperty("name"+btnNum, name);
            prop.setProperty("key"+btnNum, key);
            OutputStream os = new FileOutputStream(FastLogin.PATH_TO_PROPERTIES);
            prop.store(os, null);
            os.close();
            prop = null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXML_setupController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXML_setupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}    