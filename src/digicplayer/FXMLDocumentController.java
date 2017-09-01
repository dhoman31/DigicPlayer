/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digicplayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author daniel
 */
public class FXMLDocumentController implements Initializable {
    
    private MediaPlayer digicPlayer;
    boolean playing = false;
    
    @FXML
    private MediaView mediaview;
    
    
    @FXML
    private Label label;
    private String filePath;
    
    //open the wanted file through file explorer
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select file", "*.mp4");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(null);
            filePath  = file.toURI().toString();
            
            if(filePath != null){
                Media media = new Media(filePath);
                digicPlayer = new MediaPlayer(media);
                mediaview.setMediaPlayer(digicPlayer);
                
                //set range
                DoubleProperty width = mediaview.fitWidthProperty();
                DoubleProperty height = mediaview.fitHeightProperty();
                
                width.bind(Bindings.selectDouble(mediaview.sceneProperty(), "width"));
                height.bind(Bindings.selectDouble(mediaview.sceneProperty(), "height"));
                
                playing = true;
                digicPlayer.play();
            }
    }
    
    //play and pause the video file
    @FXML
    private void playVideo(ActionEvent event){
        
        digicPlayer.setRate(1);
        
        if(playing){
            playing = false;
            digicPlayer.pause();
        }else{
            playing = true;
            digicPlayer.play();
        }
    }
    
    //stop video playback
    @FXML
    private void stopVideo(ActionEvent event){
        digicPlayer.stop();
        playing = false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
