/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bl.entidades.Cancion;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

class Player extends VBox {

    Media mediaPlayer;
    MediaPlayer player;
    Image image;
    MediaView view;
    final Slider time = new Slider();
    Slider vol = new Slider();
    Button PlayButton = new Button();
    Button botonNext = new Button();
    Button botonPrev = new Button();
    Label volume = new Label();
    HBox abajo = new HBox();
    HBox info = new HBox();
    VBox data = new VBox();
    HBox controls = new HBox();
    HBox volumeBox = new HBox();
    ImageView previewImagen = new ImageView();
    HBox contenedorImagen = new HBox();
    Label songName = new Label("");
    Label songArtist = new Label("");
    Cancion currentSong;
    ArrayList<Cancion> queue = new ArrayList<>();
    int running = 0;
    int repeat = 0;

    public Player() {
        initialize();
    }

    private void playQueue() throws MalformedURLException, FileNotFoundException {
        run(queue.get(running));
    }

    

    public void run(Cancion cancion) throws MalformedURLException, FileNotFoundException {

        currentSong = cancion;
        File file = new File(cancion.getRuta());

        songName.setText(cancion.getNombre());
        songArtist.setText(cancion.getListaArtistas());
        mediaPlayer = new Media(file.toURI().toString());
        mediaPlayer.getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> c) -> {
            if (c.wasAdded()) {
                if ("image".equals(c.getKey())) {
                    image = (Image) c.getValueAdded();
                }
            }
            previewImagen.setImage(image);
        });

        player = new MediaPlayer(mediaPlayer);
        view = new MediaView(player);
        time.setMin(0.0);
        PlayButton.setOnAction(e -> {
            MediaPlayer.Status status = player.getStatus();
            if (status == MediaPlayer.Status.PLAYING) {

                if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {

                    player.seek(player.getStartTime());
                    player.play();
                } else {

                    player.pause();
                }
            }
            if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
                player.play();
            }
        });

        player.statusProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == MediaPlayer.Status.PAUSED) {
                PlayButton.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/play.png"), 30, 30, true, true)));
            } else if (newValue == MediaPlayer.Status.PLAYING) {
                PlayButton.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/pause.png"), 30, 30, true, true)));
            }
        });

        player.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration duration, Duration current) -> {
            time.setValue(current.toSeconds());

        });

        player.totalDurationProperty().addListener((ObservableValue<? extends Duration> observable, Duration duration, Duration current) -> {
            time.setMax(current.toSeconds());
        });

        time.valueProperty().addListener((Observable ov) -> {

            double progress = time.valueProperty().doubleValue() / (time.getMax() / 100);
            double ok = Math.floor(progress * 10000);

            if (time.isPressed()) {
                player.seek(Duration.seconds(time.getValue()));
            }

            StackPane trackPane = (StackPane) time.lookup(".track");
            String style = String.format("-fx-background-color: linear-gradient(to right, #2D819D %f%%, #969696 %f%%);",
                    ok / 10000, ok / 10000);
            trackPane.setStyle(style);

        });

        player.setVolume(vol.getValue() / 100);

        player.play();

    }

    private void initialize() {
        vol.setMin(0.0);
        vol.setMax(100);
        vol.setValue(100);
        vol.valueProperty().addListener((Observable ov) -> {

            if (vol.isPressed()) {
                try {
                    player.setVolume(vol.getValue() / 100);
                } catch (NullPointerException ex) {

                }
            }
            StackPane trackPane = (StackPane) vol.lookup(".track");
            String style = String.format("-fx-background-color: linear-gradient(to right, #2D819D %d%%, #969696 %d%%);",
                    vol.valueProperty().intValue(), vol.valueProperty().intValue());
            trackPane.setStyle(style);
        });
        getStyleClass().add("player");
        Image previewPlayer = new Image(this.getClass().getResourceAsStream("/ui/media/custom.png"));
        previewImagen = new ImageView(previewPlayer);
        previewImagen.setFitHeight(80);
        previewImagen.setFitWidth(80);
        previewImagen.setSmooth(true);
        previewImagen.setPreserveRatio(true);
        songName.getStyleClass().add("songName");
        songArtist.getStyleClass().add("songArtist");
        contenedorImagen.getChildren().addAll(previewImagen);
        contenedorImagen.setAlignment(Pos.CENTER_LEFT);
        contenedorImagen.getStyleClass().add("containerImagen");
        data.getChildren().addAll(songName, songArtist);
        data.setAlignment(Pos.CENTER_LEFT);
        info.getChildren().addAll(contenedorImagen, data);
        info.setAlignment(Pos.CENTER_LEFT);
        info.setMinWidth(450);
        botonPrev.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/atras.png"), 30, 30, true, true)));
        botonNext.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/adelante.png"), 30, 30, true, true)));
        PlayButton.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/play.png"), 30, 30, true, true)));

        controls.getChildren().addAll(botonPrev, PlayButton, botonNext);
        botonNext.setOnAction(e -> {
            if (player.getStatus().equals(Status.PAUSED) || player.getStatus().equals(Status.PLAYING)) {

                if (running == (queue.size() - 1)) {
                    System.out.println("se excede");
                } else {
                    try {
                        player.stop();
                        running = running + 1;
                        run(queue.get(running));
                    } catch (MalformedURLException | FileNotFoundException ex) {
                    }
                }
            }

        });
        botonPrev.setOnAction(e -> {
            if (player.getStatus().equals(Status.PAUSED) || player.getStatus().equals(Status.PLAYING)) {
                boolean decimoTrack = (double) time.getValue() > ((double) time.getMax() / 8);
                if (decimoTrack) {
                    player.seek(Duration.seconds(0));
                }
                if (running == 0) {
                    player.seek(Duration.seconds(0));
                } else {
                    try {
                        player.stop();
                        running = running - 1;
                        run(queue.get(running));
                    } catch (MalformedURLException | FileNotFoundException ex) {
                    }
                }
            }

        });
        controls.setMinWidth(520);
        controls.setAlignment(Pos.CENTER);
        volume.setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/volumen.png"), 30, 30, true, true)));
        volume.getStyleClass().add("botonControl");
        volumeBox.getChildren().addAll(volume, vol);
        volumeBox.setMinWidth(480);
        volumeBox.setAlignment(Pos.CENTER_RIGHT);
        /*controls.setStyle("-fx-background-color:yellow;");
        volumeBox.setStyle("-fx-background-color:blue;");
        info.setStyle("-fx-background-color:darkcyan;");*/
        abajo.getChildren().addAll(info, controls, volumeBox);
        abajo.setPadding(new Insets(12,0,0,0));
        abajo.setAlignment(Pos.CENTER);
        botonPrev.getStyleClass().add("botonControl");
        botonPrev.setStyle("-fx-padding: 20px;");
        botonNext.getStyleClass().add("botonControl");
        PlayButton.getStyleClass().add("botonControl");
        volume.getStyleClass().add("volumeLabel");
        vol.getStyleClass().add("volume");
        time.getStyleClass().add("timeSlider");
        getChildren().addAll(time, abajo);
        time.valueProperty().addListener(e -> {

            double valor = (double) time.valueProperty().getValue();
            double total = (double) time.getMax();
            double diferencia = (int) total - valor;
            if (diferencia < 0) {
                int next = running + 1;
                if (queue.get(next) != null) {
                    try {
                        run(queue.get(next));
                    } catch (MalformedURLException | FileNotFoundException ex) {
                    }
                    running = next;
                }
            }
        });
    }

    public void play(Object input, boolean sobreescribirQueue, int index) throws MalformedURLException, FileNotFoundException {
        if (sobreescribirQueue) {
            if (input instanceof Cancion) {
                queue = new ArrayList<>();
                queue.add((Cancion) input);
                running = 0;
                run(queue.get(running));
            } else {
                queue = (ArrayList<Cancion>) input;
                queue.forEach(e->{
                    System.out.println(e.toString());
                });
                running=index;
                run(queue.get(running));
            }
        }
    }

    public void addToQueue(Cancion cancion) {
        queue.add(cancion);
        System.out.println("Lista:");
        queue.forEach(e -> {
            System.out.println(e.toString());
        });
    }

    public void addToQueue(ArrayList<Cancion> canciones) {

    }

    public void stop() {
        player.stop();
    }

    public void resume() {
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public String getStatus() {
        return player.getStatus().toString();
    }

}
