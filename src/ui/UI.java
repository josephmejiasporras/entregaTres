package ui;

import bl.entidades.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.animation.PauseTransition;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class UI extends Application {


    /*init*/
    private TextField nombreRegistro = new TextField();
    private TextField segundoNombreRegistro = new TextField();
    private TextField apellidosRegistro = new TextField();
    private TextField nombreUsuarioRegistro = new TextField();
    private TextField idRegistro = new TextField();
    private TextField correoRegistro = new TextField();
    private TextField confirmarCorreoRegistro = new TextField();
    private TextField songName = new TextField();
    private TextField artistName = new TextField();
    private TextField albumName = new TextField();
    private PasswordField contrasenaRegistro = new PasswordField();
    private PasswordField confirmarContrasenaRegistro = new PasswordField();
    private DatePicker fechaNacimientoRegistro = new DatePicker();
    private TextField correo = new TextField();
    private PasswordField contrasena = new PasswordField();
    private TextField searchPlaylistTextField = new TextField();
    private Button registerButton = new Button("⮞");
    private Button loginButton = new Button("⮞");
    private Button back = new Button();
    private HBox importar = new HBox();
    private Button confirmImport = new Button("Importar Cancion");
    private Button cerrar = new Button("");
    private Button cerrarSesion = new Button("");
    private Button canciones = new Button("Canciones");
    private HBox cuenta = new HBox(10);
    private Button artistas = new Button("Artistas");
    private Button albumes = new Button("Albumes");
    private Button AddPlaylist = new Button();
    private Button close = new Button();
    private Button closeSignIn = new Button();
    private Button closeLogIn = new Button();
    private Button crearCuenta = new Button("Crear una cuenta");
    private Button avatar = new Button("Seleccionar avatar");
    private Button browseSong = new Button("Importar desde la PC");
    private Label bibliotecaLabel = new Label("Biblioteca");
    private Label cuentaLabel = new Label("Cuenta");
    private Label importName = new Label("");
    private Label playlists = new Label("Playlists");
    private Label cuadroAlerta = new Label();
    private ComboBox seleccionarPais = new ComboBox();
    private ImageView importImage = new ImageView();
    private ImageView avatarPreview = new ImageView();
    private ListView box = new ListView();
    private VBox navigation = new VBox(10);
    private HBox containerPlaylist = new HBox();
    private Stage initWindow = new Stage();
    private Stage loginWindow = new Stage();
    private Stage signInWindow = new Stage();
    private Stage principal = new Stage();
    private Player player = new Player();
    private BorderPane mainBorder = new BorderPane();
    private String retornando;
    private int cent = 0;
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean answer = true;
    private boolean some = true;
    private ArrayList<Object> listaCanciones = new ArrayList<>();
    private ArrayList<String> listaPlaylistNames = new ArrayList<>();
    private MenuItem importarCancion = new MenuItem(" Importar Canción ");

    private ObservableList<String> listaP = FXCollections.observableArrayList();

    public UI() {

    }

    public boolean confirmBox(String message) {

        Stage window = new Stage();
        window.setMinWidth(350);
        window.setMinHeight(200);
        Label label = new Label();
        label.setText(message);

        Button yes = new Button("Si");
        Button no = new Button("No");

        yes.setOnAction(e -> {

            answer = true;
            window.close();

        });

        no.setOnAction(e -> {

            answer = false;
            window.close();

        });

        VBox layout = new VBox(10);
        HBox bttns = new HBox(20);
        bttns.getChildren().addAll(yes, no);
        bttns.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, bttns);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        try {
            window.initStyle(StageStyle.TRANSPARENT);
        } catch (Exception e) {
        }
        scene.getStylesheets().add("style4.css");
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public void loadRandom() {
        Image preview11 = new Image(this.getClass().getResourceAsStream("/ui/media/custom.png"));
        getImportImage().setImage(preview11);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public Image convertImage(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream stream = new FileInputStream(file);
        Image output = new Image(stream);
        return output;
    }

    /* Main Cliente */
    public void loadMainCliente(Persona cliente, Stage mainWindow) throws FileNotFoundException {

        mainBorder.setStyle("-fx-background-color: #030202;");

        //javafx.scene.text.Font.getFamilies().forEach(e->{            System.out.println(e);        });
        ImageView store = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/shop.png"), 25, 25, true, true));
        MenuItem tienda = new MenuItem(" Tienda ");
        tienda.setGraphic(store);
        ImageView importSong = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/addSong.png"), 25, 25, true, true));
        importarCancion.setGraphic(importSong);
        ImageView importAlbum = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/addAlbum.png"), 25, 25, true, true));
        MenuItem importarAlbum = new MenuItem(" Importar Álbum ");
        importarAlbum.setGraphic(importAlbum);
        ContextMenu cm = new ContextMenu(tienda, importarCancion, importarAlbum);
        cm.getStyleClass().add("cm");

        HBox containerImgVCuenta = new HBox();
        Label cuentaLabelA = new Label("Cuenta");
        ImageView gear = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/gear.png"), 25, 25, true, true));
        containerImgVCuenta.getChildren().addAll(gear);
        containerImgVCuenta.setPadding(new Insets(6, 6, 6, 6));
        getCuenta().getChildren().addAll(containerImgVCuenta, cuentaLabelA);
        getCuenta().getStyleClass().add("containerC");
        getCuenta().setOnMouseClicked(e -> {
            cm.hide();
        });

        HBox containerImportarIV = new HBox();
        ImageView plus = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/add.png"), 25, 25, true, true));
        containerImportarIV.getChildren().addAll(plus);
        containerImportarIV.setPadding(new Insets(6, 6, 6, 6));
        Label agregar = new Label("Agregar");
        getImportar().getChildren().addAll(containerImportarIV, agregar);
        getImportar().getStyleClass().add("containerC");
        getImportar().setOnMouseClicked(e -> {
            System.out.println(e.getTarget());
            cm.show(getNavigation(), e.getScreenX(), e.getScreenY());
            some = true;
        });

        HBox containerCanciones = new HBox(10);
        HBox containerCancionesIV = new HBox();
        ImageView song = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/songs.png"), 25, 25, true, true));
        containerCancionesIV.getChildren().addAll(song);
        containerCancionesIV.setPadding(new Insets(6, 6, 6, 6));
        containerCanciones.getChildren().addAll(containerCancionesIV, getCanciones());
        containerCanciones.getStyleClass().add("containerC");
        containerCanciones.setOnMouseClicked(e -> {
            cm.hide();
        });

        HBox containerAlbumes = new HBox(10);
        HBox containerAlbumesIV = new HBox();
        ImageView album = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/cd.png"), 25, 25, true, true));
        containerAlbumesIV.getChildren().addAll(album);
        containerAlbumesIV.setPadding(new Insets(6, 6, 6, 6));
        containerAlbumes.getChildren().addAll(containerAlbumesIV, getAlbumes());
        containerAlbumes.getStyleClass().add("containerC");
        containerAlbumes.setOnMouseClicked(e -> {
            cm.hide();
        });

        HBox containerArtistas = new HBox(10);
        HBox containerArtistasIV = new HBox();
        ImageView mic = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/mic.png"), 25, 25, true, true));
        containerArtistasIV.getChildren().addAll(mic);
        containerArtistasIV.setPadding(new Insets(6, 6, 6, 6));
        containerArtistasIV.setOnMouseClicked(e -> {
            cm.hide();
        });
        getArtistas().setOnMouseClicked(e -> {
            cm.hide();
        });
        containerArtistas.getChildren().addAll(containerArtistasIV, getArtistas());
        containerArtistas.getStyleClass().add("containerC");
        containerArtistas.setOnMouseClicked(e -> {
            cm.hide();
        });

        HBox containerBiblioteca = new HBox(10);
        containerBiblioteca.setOnMouseClicked(e -> {
            cm.hide();
        });
        ImageView biblioteca = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/biblioteca.png")));
        biblioteca.setOnMouseClicked(e -> {
            cm.hide();
        });
        biblioteca.setFitWidth(210);
        biblioteca.setPreserveRatio(true);
        biblioteca.setSmooth(true);
        containerBiblioteca.getChildren().addAll(biblioteca);
        containerBiblioteca.setPadding(new Insets(15, 15, 15, 17));

        HBox containerPlaylists = new HBox(10);
        containerPlaylists.setOnMouseClicked(e -> {
            cm.hide();
        });
        ImageView playlistsIcon = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/playlists.png")));
        playlistsIcon.setOnMouseClicked(e -> {
            cm.hide();
        });

        playlistsIcon.setFitWidth(190);
        playlistsIcon.setPreserveRatio(true);
        playlistsIcon.setSmooth(true);
        containerPlaylists.getChildren().addAll(playlistsIcon);
        containerPlaylists.setPadding(new Insets(15, 0, 15, 19));
        getContainerPlaylist().setAlignment(Pos.CENTER_LEFT);
        getContainerPlaylist().setOnMouseClicked(e -> {
            cm.hide();
        });
        //getAddPlaylist().setStyle("-fx-background-color:darkcyan;");
        //containerPlaylists.setStyle("-fx-background-color:yellow;");
        navigation = new VBox();
        getNavigation().setBackground(new Background(new BackgroundImage(new Image(this.getClass().getResourceAsStream("/ui/media/nav.png"), 1280, 1200, true, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        getNavigation().setOnMouseClicked(e -> {
            if (cm.isShowing() && !some) {
                cm.hide();
                some = true;
            }
        });

        //navigation.setStyle("-fx-background-color:yellow;");
        this.box = new ListView();
        box.setMaxHeight(220);
        //box.setStyle("-fx-background-color:darkcyan;");

        HBox searchPlaylistTextFieldContainer = new HBox(10);
        HBox boxContainer = new HBox();
        boxContainer.getChildren().addAll(box);
        boxContainer.setMinHeight(230);
        searchPlaylistTextField.getStyleClass().add("searchPlaylist");
        searchPlaylistTextField.setPromptText("Buscar...");
        searchPlaylistTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {

                searchPlaylistTextField.setStyle("-fx-border-color:#969696;");

            } else {
                searchPlaylistTextField.setStyle("-fx-border-color:#030202;");
            }
        });

        searchPlaylistTextField.setOnAction(e -> {
            System.out.println(e.getTarget());
        });

        FilteredList<String> filteredData = new FilteredList<>(listaP, p -> true);

        searchPlaylistTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(play -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every client with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (play.contains(lowerCaseFilter)) {
                    return true; //filter matches first name
                } else if (play.contains(lowerCaseFilter)) {
                    return true; //filter matches last name
                }
                return false; //Does not match
            });
        });
        SortedList<String> sortedData = new SortedList<>(filteredData);

        box.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                final Label leadLbl = new Label();
                final Tooltip tooltip = new Tooltip();
                final ListCell<String> cell = new ListCell<String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            leadLbl.setText(item);
                            setText(item);
                            tooltip.setText(item);
                            setTooltip(tooltip);
                        } else {
                            leadLbl.setText("");
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });

        box.setItems(sortedData);

        searchPlaylistTextFieldContainer.getChildren().addAll(searchPlaylistTextField);
        searchPlaylistTextFieldContainer.setPadding(new Insets(0, 0, 15, 28));
        //searchPlaylistTextField()
        getNavigation().getChildren().addAll(getCuenta(), getImportar(), containerBiblioteca,
                containerCanciones, containerAlbumes, containerArtistas, getContainerPlaylist(), searchPlaylistTextFieldContainer, boxContainer);

        getContainerPlaylist().getChildren().addAll(containerPlaylists, getAddPlaylist());
        getAddPlaylist().setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/plus.png"), 15, 15, true, true)));
        getAddPlaylist().getStyleClass().add("addPlaylist");
        HBox topMenu = new HBox();
        HBox closeMenu = new HBox(10);
        topMenu.setMinWidth(800);
        closeMenu.setMinWidth(1220);
        closeMenu.setAlignment(Pos.CENTER_RIGHT);
        closeMenu.setStyle("-fx-max-height:60px");
        getCerrar().setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/cancel.png"), 40, 40, true, true)));
        getCerrarSesion().setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/logout.png"), 25, 25, true, true)));
        ImageView minimizeButtonIv = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/minimize2.png"), 40, 40, true, true));

        Button minimize = new Button();
        minimize.setGraphic(minimizeButtonIv);
        minimize.setOnAction(e -> {
            mainWindow.setIconified(true);
        });
        closeMenu.getChildren().addAll(getCerrarSesion(), minimize, getCerrar());

        ImageView logo = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/logoHeader.png"), 273.06, 106.25, true, true));
        topMenu.getStyleClass().add("topMenu");
        topMenu.getChildren().addAll(logo, closeMenu);
        topMenu.setOnMousePressed(e -> {
            setxOffset(mainWindow.getX() - e.getScreenX());
            setyOffset(mainWindow.getY() - e.getScreenY());
        });
        topMenu.setOnMouseDragged(e -> {
            mainWindow.setX(e.getScreenX() + getxOffset());
            mainWindow.setY(e.getScreenY() + getyOffset());
        }
        );

        getCerrar().getStyleClass().add("closeButton");
        /*LeftBar*/

        getNavigation().getStyleClass().add("navigationBar");
        getNavigation().setAlignment(Pos.TOP_LEFT);

        /* bottomBar */
        HBox bottomBar = new HBox();
        HBox leftBottom = new HBox();
        HBox middleBottom = new HBox();
        HBox rightBottom = new HBox();

        bottomBar.getStyleClass().add("bottomBar");
        leftBottom.getStyleClass().add("leftBottom");
        middleBottom.getStyleClass().add("middleBottom");
        rightBottom.getStyleClass().add("rightBottom");

        bottomBar.getChildren().addAll(leftBottom, middleBottom, rightBottom);

        /*Estructura*/
        getMainBorder().setBottom(getPlayer());
        getMainBorder().setTop(topMenu);
        getMainBorder().setLeft(getNavigation());
        //getMainBorder().setMinHeight(950);
        getMainBorder().setMinWidth(1500);

        try {
            Scene scene = new Scene(getMainBorder());
            scene.getStylesheets().add("/ui/styles/style.css");
            mainWindow.initStyle(StageStyle.UNDECORATED);
            mainWindow.setScene(scene);
        } catch (IllegalArgumentException ex) {

        }

    }

    public File browseSong(Stage stage) {

        FileChooser getSong = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("WAV files (*.wav)", "*.wav");
        getSong.getExtensionFilters().add(extFilter);
        getSong.getExtensionFilters().add(extFilter2);
        getSong.setTitle("Get song");
        return getSong.showOpenDialog(stage);

    }

    public File browseImage(Stage stage) {

        FileChooser getImage = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.raw");
        getImage.getExtensionFilters().add(extFilter);
        getImage.setTitle("Get Image");
        return getImage.showOpenDialog(stage);

    }

    /* recibe objetos de control y devuelve escena de Importar cancion */
    public VBox importScene(ArrayList<Artista> artistas, ArrayList<Album> albumes) throws FileNotFoundException {

        Label nombreCancionLabel = new Label("Nombre de la canción");
        Label seleccionartista = new Label("Artista");
        Label seleccionesArtista = new Label("Artistas Seleccionados:");
        Label seleccionGenero = new Label("Género");
        Label seleccionAlbum = new Label("Album");
        Label seleccionCompositor = new Label("Compositor");
        Label seleccionesCompositor = new Label("Compositores seleccionados");
        Label seleccionFecha = new Label("Fecha de lanzamiento");
        DatePicker fechaLanzamiento = new DatePicker();
        Button reemplazarFotoCancion = new Button("Reemplazar");
        VBox seleccionesArtistaContainer = new VBox();
        VBox seleccionadoAlbumContainer = new VBox();
        VBox seleccionesCompositorContainer = new VBox();
        ComboBox seleccionarGenero = new ComboBox();
        HBox containerInfoCancion = new HBox(10);

        VBox containerArtistas = new VBox(10);
        VBox containerAlbumes = new VBox(10);
        VBox containerCompositores = new VBox(10);

        ListView artistasListView = new ListView();
        ListView artistasSeleccionadosListView = new ListView();
        artistasListView.getStyleClass().add("artistasListView");
        ObservableList<Artista> listaArtistasO = FXCollections.observableArrayList();
        artistas.forEach(e -> {
            listaArtistasO.add(e);
        });
        FilteredList<Artista> filteredData = new FilteredList<>(listaArtistasO, p -> true);
        SortedList<Artista> sortedData = new SortedList<>(filteredData);
        artistasListView.setCellFactory(new Callback<ListView<Artista>, ListCell<Artista>>() {
            @Override
            public ListCell<Artista> call(ListView<Artista> param) {
                final Label leadLbl = new Label();
                final Tooltip tooltip2 = new Tooltip();
                final ListCell<Artista> cell = new ListCell<Artista>() {
                    @Override
                    public void updateItem(Artista item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            leadLbl.setText(item.getNombreArtistico());
                            setText(item.getNombreArtistico());
                            tooltip2.setText(item.getNombreArtistico());
                            setTooltip(tooltip2);
                            if (!getText().equals("")) {
                                setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/plus_1.png"), 10, 10, true, true)));
                                setGraphicTextGap(10);
                                setStyle("-fx-font-size:15px;");
                                setOnMouseClicked(e -> {
                                    System.out.println("this " + getText());
                                    if (listaArtistasO.contains(item)) {
                                        listaArtistasO.remove(item);
                                    }
                                });
                            }

                        } else {
                            leadLbl.setText("");
                            setText("");
                            setGraphic(null);
                            setOnMouseClicked(e -> {
                            });
                        }
                    }
                };
                return cell;
            }
        });
        artistasListView.setItems(sortedData);

        TextField searchArtistas = new TextField();
        searchArtistas.setPromptText("Buscar...");
        searchArtistas.getStyleClass().add("searchImport");

        searchArtistas.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(play -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (play.getNombreArtistico().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (play.getNombreArtistico().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        ListView albumesListView = new ListView();
        albumesListView.getStyleClass().add("artistasListView");
        ObservableList<Album> listaAlbumesO = FXCollections.observableArrayList();
        albumes.forEach(e -> {
            listaAlbumesO.add(e);
        });

        FilteredList<Album> filteredData2 = new FilteredList<>(listaAlbumesO, p -> true);
        TextField searchAlbumes = new TextField();
        searchAlbumes.getStyleClass().add("searchImport");
searchAlbumes.setPromptText("Buscar...");
        searchAlbumes.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData2.setPredicate(play -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (play.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (play.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Album> sortedData2 = new SortedList<>(filteredData2);
        albumesListView.setCellFactory(new Callback<ListView<Album>, ListCell<Album>>() {
            @Override
            public ListCell<Album> call(ListView<Album> param) {
                final Label leadLbl = new Label();
                final Tooltip tooltip = new Tooltip();
                final ListCell<Album> cell = new ListCell<Album>() {
                    @Override
                    public void updateItem(Album item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            leadLbl.setText(item.getNombre());
                            setText(item.getNombre());
                            tooltip.setText(item.getNombre());
                            setTooltip(tooltip);
                            setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/plus_1.png"), 10, 10, true, true)));
                            setGraphicTextGap(10);
                            setStyle("-fx-font-size:15px;");
                            setOnMouseClicked(e -> {
                                if (listaAlbumesO.contains(item)) {
                                    listaAlbumesO.remove(item);
                                }
                            });
                        } else {
                            leadLbl.setText("");
                            setText("");
                            setGraphic(null);
                            setOnMouseClicked(e -> {
                            });
                        }
                    }
                };
                return cell;
            }
        });
        albumesListView.setItems(sortedData2);

        ListView compositor = new ListView();
        compositor.getStyleClass().add("artistasListView");
        ObservableList<Artista> listaCompositorO = FXCollections.observableArrayList();
        artistas.forEach(e -> {
            listaCompositorO.add(e);
        });
        FilteredList<Artista> filteredData3 = new FilteredList<>(listaCompositorO, p -> true);
        SortedList<Artista> sortedData3 = new SortedList<>(filteredData3);
        compositor.setCellFactory(new Callback<ListView<Artista>, ListCell<Artista>>() {
            @Override
            public ListCell<Artista> call(ListView<Artista> param) {
                final Label leadLbl = new Label();
                final Tooltip tooltip = new Tooltip();
                final ListCell<Artista> cell = new ListCell<Artista>() {
                    @Override
                    public void updateItem(Artista item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            leadLbl.setText(item.getNombreArtistico());
                            setText(item.getNombreArtistico());
                            tooltip.setText(item.getNombreArtistico());
                            setTooltip(tooltip);
                            setStyle("-fx-font-size:15px;");
                            if (!getText().equals("")) {
                                setGraphic(new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/plus_1.png"), 10, 10, true, true)));
                                setGraphicTextGap(10);
                                setOnMouseClicked(e -> {
                                    System.out.println("this " + getText());
                                    if (listaCompositorO.contains(item)) {
                                        listaCompositorO.remove(item);
                                    }
                                });
                            }

                        } else {
                            leadLbl.setText("");
                            setText("");
                            setGraphic(null);
                            setOnMouseClicked(e -> {
                            });
                        }
                    }
                };
                return cell;
            }
        });
        compositor.setItems(sortedData3);

        TextField searchCompositores = new TextField();
        searchCompositores.setPromptText("Buscar...");
        searchCompositores.getStyleClass().add("searchImport");
        searchCompositores.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData3.setPredicate(play -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (play.getNombreArtistico().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (play.getNombreArtistico().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        VBox importarCanciones = new VBox();
        HBox importarCancionesUp = new HBox();
        HBox containerInfo = new HBox();
        VBox infoRight = new VBox(10);
        VBox infoLeft = new VBox(10);
        VBox infoSong = new VBox();
        HBox confirmarImport = new HBox();
        Image preview11 = new Image(this.getClass().getResourceAsStream("/ui/media/custom.png"));
        getImportImage().setImage(preview11);
        getImportImage().setFitHeight(350);
        getImportImage().setFitWidth(350);
        HBox containerSubmit = new HBox();
        VBox containerImgV = new VBox();
        HBox containerImportSong = new HBox();
        VBox containerData = new VBox();
        containerImgV.setPadding(new Insets(20, 20, 20, 20));

        containerImportSong.setMinWidth(550);
        containerImportSong.setAlignment(Pos.TOP_RIGHT);
        containerData.setMinWidth(700);
        containerImgV.setMinWidth(400);
        containerData.setMinHeight(550);
        containerImgV.setMinHeight(550);
        containerImgV.setAlignment(Pos.TOP_CENTER);
        //containerSubmit.setAlignment(Pos.CENTER_RIGHT);
        containerInfo.setMinWidth(550);
        infoLeft.setMinWidth(225);
        infoRight.setMinWidth(225);
        infoSong.setMinWidth(550);
        confirmarImport.setMinWidth(550);
        confirmarImport.setMinHeight(100);
        seleccionesArtistaContainer.setMaxWidth(183);
        infoSong.setMinHeight(100);
        containerInfo.setPadding(new Insets(30, 20, 20, 0));

        getSongName().setPromptText("Nombre de la canción");
        getArtistName().setPromptText("Nombre del artista");
        getAlbumName().setPromptText("Nombre del album");
        getConfirmImport().setText("Crear canción");
        importarCanciones.setAlignment(Pos.CENTER_LEFT);
        confirmarImport.setAlignment(Pos.CENTER_RIGHT);
        importarCanciones.setMaxHeight(650);

        containerArtistas.getChildren().addAll(seleccionartista, searchArtistas, artistasListView);
        containerAlbumes.getChildren().addAll(seleccionAlbum, searchAlbumes, albumesListView);
        containerCompositores.getChildren().addAll(seleccionCompositor, searchCompositores, compositor);
        containerInfoCancion.getChildren().addAll(containerArtistas, containerAlbumes, containerCompositores);
        seleccionesArtistaContainer.getChildren().addAll(seleccionesArtista, artistasSeleccionadosListView);
        infoSong.getChildren().addAll(seleccionesArtistaContainer, seleccionadoAlbumContainer, seleccionesCompositorContainer);
        infoLeft.getChildren().addAll(nombreCancionLabel, seleccionFecha, seleccionGenero);
        infoRight.getChildren().addAll(getSongName(), fechaLanzamiento, seleccionarGenero);
        confirmarImport.getChildren().addAll(getConfirmImport());
        containerInfo.getChildren().addAll(infoLeft, infoRight);
        containerImportSong.getChildren().addAll(getBrowseSong());
        containerImgV.getChildren().addAll(getImportImage(), reemplazarFotoCancion);
        containerSubmit.getChildren().addAll(infoSong, confirmarImport);
        containerData.getChildren().addAll(containerImportSong, containerInfo, containerInfoCancion);
        importarCancionesUp.getChildren().addAll(containerData, containerImgV);
        importarCanciones.getChildren().addAll(importarCancionesUp, containerSubmit);

        importarCanciones.getStyleClass().add("importScene");
        /*containerData.setStyle("-fx-border-color:white; -fx-border-width: 1 1 1 1;");
        infoSong.setStyle("-fx-border-color:white; -fx-border-width: 1 1 1 1;");
        importarCanciones.setStyle("-fx-border-color:white; -fx-border-width: 1 1 1 1;");
        confirmarImport.setStyle("-fx-border-color:white; -fx-border-width: 1 1 1 1;");*/
        return importarCanciones;
    }

    public VBox optionsScene() {

        Label nombreActual = new Label("Joe");
        Button editarNombre = new Button("editar");

        return new VBox();
    }

    public VBox albumScene(ArrayList<Album> albumes) {

        VBox contenedor = new VBox();
        TilePane display = new TilePane(20, 20);
        display.setPadding(new Insets(50, 50, 50, 50));
        Label header = new Label("Mis albumes");
        int counter = 0;
        String[] nombreArtista = new String[albumes.size()];
        for (int i = 0; i < albumes.size(); i++) {
            boolean centinela = false;
            String measure = "";

            for (int j = 0; j < nombreArtista.length; j++) {
                measure = albumes.get(i).getNombre();
                try {
                    centinela = nombreArtista[j].equals(albumes.get(i).getNombre());
                } catch (NullPointerException ex) {

                }

            }

            if (!centinela) {
                nombreArtista[i] = measure;
                ImageView albumView = new ImageView(albumes.get(i).getImagen());
                albumView.setFitHeight(200);
                albumView.setFitWidth(200);
                Label goToAlbum = new Label(albumes.get(i).getNombre());
                goToAlbum.setMinWidth(200);
                VBox auxNoseCuanto = new VBox();
                auxNoseCuanto.setAlignment(Pos.CENTER);
                auxNoseCuanto.getChildren().addAll(albumView, goToAlbum);
                auxNoseCuanto.setStyle("-fx-cursor:hand");
                Album album1 = albumes.get(i);
                auxNoseCuanto.setOnMouseClicked(e -> {
                    loadAlbumView(album1);
                });
                display.getChildren().addAll(auxNoseCuanto);
            }
        }
        contenedor.getChildren().addAll(header, display);
        return contenedor;
    }

    public final void loadAlbumView(Album album) {

        VBox layout = new VBox();

        ObservableList<Cancion> listado = FXCollections.observableArrayList();
        TableView<Cancion> songsLayout = new TableView();
        songsLayout.getStyleClass().add("artistsLayout");

        for (int i = 0; i < album.getLista().size(); i++) {
            listado.add(album.getLista().get(i));
        }

        /* Columna nombre */
        TableColumn<Cancion, String> nombre = new TableColumn<>("Nombre");
        nombre.setMinWidth(350);
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Cancion, String> artista = new TableColumn<>("Artista");
        artista.setMinWidth(350);
        artista.setCellValueFactory(new PropertyValueFactory<>("listaArtistas"));
        TableColumn numberCol = new TableColumn("");
        numberCol.setCellValueFactory(new Callback<CellDataFeatures<Cancion, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Cancion, String> p) {
                return new ReadOnlyObjectWrapper(songsLayout.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        numberCol.setMinWidth(109);

        songsLayout.setOnMouseClicked((MouseEvent event) -> {

            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {

                System.out.println(songsLayout.getSelectionModel());

                if (!event.getTarget().toString().equals("TableColumnHeader[id=null, styleClass=column-header table-column]")) {

                    if (songsLayout.getSelectionModel().getSelectedItem() == null || songsLayout.getSelectionModel().getSelectedItem().equals(getPlayer().currentSong)) {
                        if ("PLAYING".equals(getPlayer().getStatus())) {
                            getPlayer().pause();
                        } else {
                            getPlayer().resume();
                        }
                    } else {
                        try {
                            getPlayer().stop();
                        } catch (NullPointerException ex) {

                        }
                        try {
                            int i = songsLayout.getSelectionModel().getFocusedIndex();
                            ArrayList<Object> listaCanciones = new ArrayList<>();
                            songsLayout.getSelectionModel().selectAll();
                            songsLayout.getSelectionModel().getSelectedItems().forEach(s -> {
                                listaCanciones.add(s);
                            });
                            player.play(listaCanciones, true, songsLayout.getSelectionModel().getFocusedIndex());
                        } catch (NullPointerException | MalformedURLException | FileNotFoundException ex) {
                        }
                    }
                }
            }
        });

        songsLayout.setItems(listado);
        songsLayout.getColumns().addAll(numberCol, nombre, artista);
        Label label = new Label(album.getNombre());
        ImageView previewImagen = new ImageView(); //no tiene la imagen del album
        previewImagen.setFitHeight(180);
        previewImagen.setFitWidth(180);
        HBox containerHeader = new HBox(20);
        containerHeader.getChildren().addAll(previewImagen, label);
        layout.getChildren().addAll(containerHeader, songsLayout);
        layout.getStyleClass().add("songsLayout");

        getMainBorder().setCenter(layout);
    }

    public VBox artistScene(ArrayList<Artista> artistas) {

        VBox layout = new VBox(5);

        ObservableList<Artista> listado = FXCollections.observableArrayList();
        ArrayList<Artista> lista = artistas;

        lista.forEach(e -> {
            e.toString();
        });
        TableView<Artista> songsLayout = new TableView();
        songsLayout.getStyleClass().add("artistsLayout");
        for (int i = 0; i < lista.size(); i++) {
            listado.add(lista.get(i));
        }

        /* Columna nombre */
        TableColumn<Artista, String> nombre = new TableColumn<>("Nombre");
        nombre.setMinWidth(580);
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Artista, String> nombreArtistico = new TableColumn<>("Nombre Artistico");
        nombreArtistico.setMinWidth(588);
        nombreArtistico.setCellValueFactory(new PropertyValueFactory<>("nombreArtistico"));

        songsLayout.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            }
        });

        nombre.setMinWidth(300);
        songsLayout.setItems(listado);
        songsLayout.getColumns().addAll(nombre, nombreArtistico);
        layout.getChildren().add(songsLayout);

        return layout;
    }

    public TableView playlistView(ArrayList<Artista> artistas) {

        ObservableList<Artista> listado = FXCollections.observableArrayList();
        ArrayList<Artista> lista = artistas;
        TableView<Artista> songsLayout = new TableView();

        for (int i = 0; i < lista.size(); i++) {
            listado.add(lista.get(i));
        }

        /* Columna nombre */
        TableColumn<Artista, String> nombre = new TableColumn<>("Título");
        nombre.setMinWidth(200);
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        songsLayout.setItems(listado);
        songsLayout.getColumns().addAll(nombre);

        return songsLayout;
    }

    public String createPlaylist() {

        Stage stageConfirm = new Stage();
        stageConfirm.setMaxWidth(250);
        stageConfirm.setMaxHeight(250);
        Label label = new Label("Escribe el nombre de la Playlist Nueva");
        Button aceptar = new Button("Aceptar");
        Button cancelar = new Button("Cancelar");
        TextField input = new TextField();
        VBox layout = new VBox(5);
        layout.getChildren().addAll(label, input, aceptar, cancelar);
        Scene scene = new Scene(layout);
        stageConfirm.initStyle(StageStyle.UNDECORATED);
        stageConfirm.setScene(scene);
        scene.getStylesheets().add("style4.css");

        aceptar.setOnAction(e -> {
            setRetornando(input.getText());
            stageConfirm.close();
        });

        cancelar.setOnAction(e -> {
            stageConfirm.close();
        });

        stageConfirm.showAndWait();

        return getRetornando();

    }

    public VBox songsScene(ArrayList<Cancion> lista) {

        VBox layout = new VBox();

        ObservableList<Cancion> listado = FXCollections.observableArrayList();
        TableView<Cancion> songsLayout = new TableView();
        songsLayout.getStyleClass().add("artistsLayout");

        for (int i = 0; i < lista.size(); i++) {
            listado.add(lista.get(i));
        }

        TableColumn<Cancion, String> colImageview = new TableColumn<>("");

        colImageview.setMinWidth(70);
        colImageview.setCellFactory((TableColumn<Cancion, String> param) -> {

            ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);

            TableCell<Cancion, String> cell = new TableCell<Cancion, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    if (item != null) {
                        File file = new File(item);
                        Media mediaPlayer = new Media(file.toURI().toString());
                        mediaPlayer.getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> c) -> {
                            if (c.wasAdded()) {
                                if ("image".equals(c.getKey())) {
                                    Image imagen = (Image) c.getValueAdded();
                                    imageview.setImage(imagen);
                                }
                            }
                        });
                    }
                }
            };
            cell.setGraphic(imageview);
            return cell;
        });
        colImageview.setCellValueFactory(new PropertyValueFactory<>("ruta"));

        TableColumn<Cancion, String> colDuracion = new TableColumn<>("Duracion");

        colDuracion.setMinWidth(200);
        colDuracion.setCellFactory((TableColumn<Cancion, String> param) -> {

            TableCell<Cancion, String> cell = new TableCell<Cancion, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    if (item != null) {
                        File file = new File(item);
                        Media mediaPlayer = new Media(file.toURI().toString());
                        MediaPlayer player = new MediaPlayer(mediaPlayer);
                        player.totalDurationProperty().addListener((ObservableValue<? extends Duration> observable, Duration duration, Duration current) -> {
                            double duracionSegundos = Math.floor(current.toSeconds());
                            double duracionMinutos = Math.floor(current.toMinutes());
                            String duracion = (int) duracionMinutos + ":" + (int) (duracionSegundos - (duracionMinutos * 60));
                            this.setText(duracion);
                            this.setAlignment(Pos.CENTER);
                        });
                    }
                }
            };
            return cell;
        });
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("ruta"));

        TableColumn<Cancion, String> nombre = new TableColumn<>("Nombre");
        nombre.setMinWidth(480);
        nombre.setStyle("-fx-padding: 0 0 0 20;-fx-alignment: center;");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Cancion, String> artista = new TableColumn<>("Artista");
        artista.setCellValueFactory(new PropertyValueFactory<>("listaArtistas"));
        artista.setMinWidth(388);
        artista.setStyle("-fx-padding:0px;-fx-alignment: center_left;");

        TableColumn<Cancion, Label> duracion = new TableColumn<>("Duracion");
        duracion.setCellValueFactory(new PropertyValueFactory<>("labelDuracion"));
        duracion.setMinWidth(200);

        songsLayout.setItems(listado);
        songsLayout.getColumns().addAll(colImageview, nombre, artista, colDuracion);

        MenuItem addToQueue = new MenuItem("Añadir a Queue");
        addToQueue.setStyle("-fx-text-fill:black;");
        addToQueue.setOnAction(e -> {

            System.out.println(" adding " + songsLayout.getSelectionModel().getSelectedItem());
            player.addToQueue(songsLayout.getSelectionModel().getSelectedItem());

        });
        MenuItem reproducir = new MenuItem("Reproducir");
        reproducir.setStyle("-fx-text-fill:black;");
        reproducir.setOnAction(e -> {

        });
        ContextMenu cm = new ContextMenu(reproducir, addToQueue);
        songsLayout.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {

            System.out.println(e.getTarget().toString());

            if (e.getButton() == MouseButton.SECONDARY) {
                if (e.getTarget().toString().substring(0, 11).equals("TableColumn") && e.getTarget().toString().substring(e.getTarget().toString().length() - 6, e.getTarget().toString().length()).equals("'null'")) {

                } else {

                    if (e.getTarget() instanceof Label) {

                    } else {
                        if (songsLayout.getSelectionModel().getSelectedItem() != null
                                && !e.getTarget().toString().substring(0, 17).equals("TableColumnHeader")) {
                            if (e.getTarget() instanceof Text) {

                                String texto = "T4bl3" + e.getTarget().toString().substring(11, e.getTarget().toString().length());
                                System.out.println(texto);
                                if (texto.contains("T4bl3Duracion") || texto.contains("T4bl3Nombre") || texto.contains("T4bl3Artista") || texto.contains("T4bl3Imagen")) {

                                } else {

                                    cm.show(songsLayout, e.getScreenX(), e.getScreenY());
                                }

                            } else {
                                cm.show(songsLayout, e.getScreenX(), e.getScreenY());
                            }
                        }

                    }

                }

            } else {
                cm.hide();
            }
        });

        songsLayout.setOnMouseClicked((MouseEvent e) -> {

            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {

                if (e.getTarget().toString().substring(0, 11).equals("TableColumn") && e.getTarget().toString().substring(e.getTarget().toString().length() - 6, e.getTarget().toString().length()).equals("'null'")) {

                } else {
                    if (songsLayout.getSelectionModel().getSelectedItem() != null
                            && !e.getTarget().toString().substring(0, 11).equals("TableColumnHeader")) {

                        ArrayList<Object> listaCancionesOrdenada = new ArrayList<>();

                        songsLayout.getItems().forEach(s -> {
                            listaCancionesOrdenada.add(s);
                        });

                        if (listaCancionesOrdenada.equals(listaCanciones) && songsLayout.getSelectionModel().getSelectedItem().equals(getPlayer().currentSong)) {
                            if (getPlayer().getStatus() != null && "PLAYING".equals(getPlayer().getStatus())) {
                                player.pause();
                            } else if ("PAUSED".equals(getPlayer().getStatus())) {
                                player.resume();
                            }
                        } else {

                            listaCanciones = listaCancionesOrdenada;
                            try {
                                player.stop();
                            } catch (NullPointerException ex) {

                            }
                            try {
                                int i = songsLayout.getSelectionModel().getFocusedIndex();
                                player.play(listaCanciones, true, songsLayout.getSelectionModel().getFocusedIndex());
                            } catch (NullPointerException | MalformedURLException | FileNotFoundException ex) {
                            }
                        }
                    }
                }

            }

            if (e.getButton().equals(MouseButton.SECONDARY)) {

            }

        });

        Label label = new Label("Mis canciones");
        label.getStyleClass().add("headerSongsLayout");
        layout.getChildren().addAll(label, songsLayout);
        layout.getStyleClass().add("songsLayout");

        return layout;
    }

    public HBox artistScene(Playlist playlist) {

        ArrayList<Cancion> lista = null; //falta aca
        HBox songsLayout = new HBox();
        /*
        for (int i = 0; i < lista.size(); i++) {
            Button button = new Button(lista.get(i).getNombre());
            songsLayout.getChildren().addAll(button);
        }*/
        return songsLayout;
    }

    /* Animacion de Bienvenida */
    public void loadLogo() throws InterruptedException, FileNotFoundException, URISyntaxException {

        Media media = new Media(this.getClass().getResource("/ui/media/logo.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        Group root = new Group();
        root.getChildren().add(mediaView);
        VBox welcomeBox = new VBox();
        welcomeBox.getChildren().add(root);
        Scene welcome = new Scene(welcomeBox);

        welcomeBox.setMinHeight(342);
        welcomeBox.setMinWidth(342);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));

        delay.setOnFinished(e -> {
            getInitWindow().close();
        });
        delay.play();
        welcome.getStylesheets().add("style2.css");
        getInitWindow().initStyle(StageStyle.TRANSPARENT);
        getInitWindow().setScene(welcome);
        getInitWindow().showAndWait();

    }

    public void initApp() throws FileNotFoundException, InterruptedException {

        /* TOP MENU */
        HBox topMenu = new HBox();

        ImageView closeButtonIv = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/cancel.png"), 40, 40, true, true));
        ImageView minimizeButtonIv = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/minimize2.png"), 40, 40, true, true));

        Button minimize = new Button();
        minimize.setGraphic(minimizeButtonIv);
        getCloseLogIn().setGraphic(closeButtonIv);
        topMenu.getChildren().addAll(minimize, getCloseLogIn());
        topMenu.setAlignment(Pos.TOP_RIGHT);
        minimize.setOnAction(e -> {
            getLoginWindow().setIconified(true);
        });

        topMenu.getStyleClass().add("topMenu");

        /* Login Scene */
 /*login button*/
        VBox layout = new VBox();

        /* correo y pw*/
        getCorreo().setPromptText("Correo electrónico");
        layout.setPadding(new Insets(5, 5, 5, 5));

        layout.setAlignment(Pos.TOP_CENTER);
        getContrasena().setPromptText("Contraseña");
        Label iniciaSesion = new Label("Inicia Sesión");
        getLoginButton().getStyleClass().add("loginButton");
        getCorreo().getStyleClass().add("input");
        getContrasena().getStyleClass().add("input");

        PseudoClass empty = PseudoClass.getPseudoClass("empty");
        getContrasena().pseudoClassStateChanged(empty, getContrasena().getText().isEmpty());
        getContrasena().textProperty().isEmpty().addListener((obs, wasEmpty, isNowEmpty)
                -> getContrasena().pseudoClassStateChanged(empty, isNowEmpty));

        getCorreo().pseudoClassStateChanged(empty, getCorreo().getText().isEmpty());
        getCorreo().textProperty().isEmpty().addListener((obs, wasEmpty, isNowEmpty)
                -> getCorreo().pseudoClassStateChanged(empty, isNowEmpty));

        /* reset y create */
        Button resetPW = new Button("Restablecer contraseña");

        resetPW.getStyleClass().add("dwnButton");
        getCrearCuenta().getStyleClass().add("dwnButton");

        ImageView logoLogin = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/logo2.png"), 300, 300, true, true));

        getCorreo().setText("ejemplo@ejemplo.com");
        getContrasena().setText("1234");

        layout.getChildren().addAll(topMenu, logoLogin, iniciaSesion, getCorreo(), getContrasena(), getCuadroAlerta(), getLoginButton(), resetPW, getCrearCuenta());
        layout.setMinWidth(400);
        layout.setMinHeight(820);

        try {
            getLoginWindow().initStyle(StageStyle.TRANSPARENT);
        } catch (IllegalStateException ex) {

        }
        topMenu.setOnMousePressed(e -> {
            resize();
            setxOffset(getLoginWindow().getX() - e.getScreenX());
            setyOffset(getLoginWindow().getY() - e.getScreenY());
        });
        topMenu.setOnMouseDragged(e -> {
            getLoginWindow().setX(e.getScreenX() + getxOffset());
            getLoginWindow().setY(e.getScreenY() + getyOffset());
        }
        );
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style3.css");
        getLoginWindow().setScene(scene);
        getLoginWindow().showAndWait();
    }

    public <S, T> void columnReorder(TableView table, TableColumn<S, T>... columns) {
        table.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    table.getColumns().setAll((Object) columns);
                    this.suspended = false;
                }
            }
        });
    }

    public Stage getMainWindow() {
        return getPrincipal();
    }

    public void createAccount() throws FileNotFoundException, URISyntaxException, MalformedURLException {

        ImageView closeButtonIv = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/cancel.png"), 40, 40, true, true));
        ImageView minimizeButtonIv = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/minimize2.png"), 40, 40, true, true));
        Button minimize = new Button();
        minimize.setGraphic(minimizeButtonIv);
        minimize.setOnAction(e -> {
            getSignInWindow().setIconified(true);
        });
        getCloseSignIn().setGraphic(closeButtonIv);

        HBox topMenu = new HBox();
        topMenu.getChildren().addAll(minimize, getCloseSignIn());
        topMenu.setAlignment(Pos.TOP_RIGHT);
        topMenu.getStyleClass().add("topMenu");
        getCorreoRegistro().getStyleClass().add("input");
        getContrasenaRegistro().getStyleClass().add("input");
        ImageView backImage = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/logoB.png"), 40, 40, true, true));
        ImageView crearCuentaLogo = new ImageView(new Image(this.getClass().getResourceAsStream("/ui/media/logoCrearCuenta.png"), 500, 157, true, true));
        crearCuentaLogo.setStyle("-fx-padding:30px;");
        getBack().setGraphic(backImage);

        VBox layout = new VBox();
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setMinWidth(850);
        layout.setMinHeight(800);

        Label nombreUsuarioLabel = new Label("Nombre de usuario");
        Label nombreLabel = new Label("Nombre");
        Label segundoNombreLabel = new Label("Segundo Nombre (Opcional)");
        Label apellidosLabel = new Label("Apellidos");
        Label correoLabel = new Label("Correo");
        Label confirmarCorreoLabel = new Label("Confirmar Correo");
        Label contrasenaLabel = new Label("Contraseña");
        Label confirmarContrasenaLabel = new Label("Confirmar contraseña");
        Label fechaNacimientoLabel = new Label("Fecha de nacimiento");
        Label nacionalidadLabel = new Label("País de nacimiento");

        getSeleccionarPais().getStyleClass().add("pais");
        getSeleccionarPais().setItems(FXCollections.observableArrayList("Afganistán", "Albania", "Alemania", "Andorra", "Angola",
                "Antigua y Barbuda", "Arabia Saudita", "Argelia", "Argentina", "Armenia",
                "Australia", "Austria", "Azerbaiyán", "Bahamas", "Bahrein", "Bangladesh",
                "Barbados", "Belarús", "Belice", "Benin", "Bhután", "Bolivia", "Bosnia y Herzegovina",
                "Botswana", "Brasil", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Bélgica",
                "Cabo Verde", "Camboya", "Camerún", "Canadá", "Chad", "Chequia", "Chile", "China", "Chipre",
                "Colombia", "Comoras", "Congo", "Costa Rica", "Croacia", "Cuba", "Côte d'Ivoire", "Dinamarca",
                "Djibouti", "Dominica", "Ecuador", "Egipto", "El Salvador", "Emiratos Árabes Unidos", "Eritrea",
                "Eslovaquia", "Eslovenia", "España", "Estados Unidos de América", "Estonia", "Eswatini", "Etiopía",
                "Federación de Rusia", "Fiji", "Filipinas", "Finlandia", "Francia", "Gabón", "Gambia", "Georgia",
                "Ghana", "Granada", "Grecia", "Guatemala", "Guinea", "Guinea Ecuatorial", "Guinea-Bissau", "Guyana",
                "Haití", "Honduras", "Hungría", "India", "Indonesia", "Iraq", "Irlanda", "Irán", "Islandia", "Islas Cook",
                "Islas Feroe", "Islas Marshall", "Islas Salomón", "Israel", "Italia", "Jamaica", "Japón", "Jordania", "Kazajstán",
                "Kenya", "Kirguistán", "Kiribati", "Kuwait", "Lesotho", "Letonia", "Liberia", "Libia", "Lituania", "Luxemburgo",
                "Líbano", "Macedonia del Norte", "Madagascar", "Malasia", "Malawi", "Maldivas", "Malta", "Malí", "Marruecos",
                "Mauricio", "Mauritania", "Micronesia", "Mongolia", "Montenegro", "Mozambique", "Myanmar", "México", "Mónaco",
                "Namibia", "Nauru", "Nepal", "Nicaragua", "Nigeria", "Niue", "Noruega", "Nueva Zelandia", "Níger", "Omán",
                "Pakistán", "Palau", "Panamá", "Papua Nueva Guinea", "Paraguay", "Países Bajos", "Perú", "Polonia", "Portugal",
                "Qatar", "Reino Unido de Gran Bretaña e Irlanda del Norte", "República Centroafricana", "República Democrática Popular Lao",
                "República Democrática del Congo", "República Dominicana", "República Popular Democrática de Corea", "República Unida de Tanzanía",
                "República de Corea", "República de Moldova", "República Árabe Siria", "Rumania", "Rwanda", "Saint Kitts y Nevis", "Samoa",
                "San Marino", "San Vicente y las Granadinas", "Santa Lucía", "Santo Tomé y Príncipe", "Senegal", "Serbia", "Seychelles",
                "Sierra Leona", "Singapur", "Somalia", "Sri Lanka", "Sudáfrica", "Sudán", "Sudán del Sur", "Suecia", "Suiza", "Suriname",
                "Tailandia", "Tayikistán", "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad y Tabago", "Turkmenistán", "Turquía", "Tuvalu",
                "Túnez", "Ucrania", "Uganda", "Uruguay", "Uzbekistán", "Vanuatu", "Venezuela", "Viet Nam", "Yemen", "Zambia", "Zimbabwe"));

        getSeleccionarPais().setValue("Costa Rica");
        getSeleccionarPais().setMaxHeight(250);
        VBox left = new VBox(10);
        VBox right = new VBox(10);
        confirmarContrasenaLabel.getStyleClass().add("labelCrearCuenta2");
        fechaNacimientoLabel.getStyleClass().add("labelCrearCuenta3");
        left.getStyleClass().add("left");
        left.setMinWidth(250);
        left.setPadding(new Insets(0, 40, 0, 50));

        left.getChildren().addAll(nombreLabel, getNombreRegistro(), segundoNombreLabel, getSegundoNombreRegistro(), apellidosLabel,
                getApellidosRegistro(), correoLabel, getCorreoRegistro(),
                confirmarCorreoLabel, getConfirmarCorreoRegistro());

        right.getChildren().addAll(nombreUsuarioLabel, getNombreUsuarioRegistro(), contrasenaLabel, getContrasenaRegistro(),
                confirmarContrasenaLabel, getConfirmarContrasenaRegistro(), fechaNacimientoLabel,
                fechaNacimientoRegistro, nacionalidadLabel, getSeleccionarPais());

        left.getChildren().forEach(e -> {
            if (e instanceof TextField) {
                e.getStyleClass().add("textFieldCrearCuenta");
            }
            if (e instanceof Label) {
                e.getStyleClass().add("labelCrearCuenta");
            }
            e.getStyleClass().add("leftColumn");
        });
        right.getChildren().forEach(e -> {
            if (e instanceof TextField) {
                e.getStyleClass().add("textFieldCrearCuenta");
            }
            if (e instanceof Label) {
                e.getStyleClass().add("labelCrearCuenta");
            }
            e.getStyleClass().add("rightColumn");
        });

        //left.setAlignment(Pos.TOP_RIGHT);
        //right.setAlignment(Pos.TOP_RIGHT);
        right.setMinWidth(260);
        right.getStyleClass().add("right");
        VBox avatarH = new VBox(30);
        avatarH.setAlignment(Pos.TOP_CENTER);

        avatarH.setMinWidth(300);
        setAvatarPreviewImage(new Image(this.getClass().getResourceAsStream("/ui/media/asset2.png")));
        getAvatarPreview().setFitHeight(250);
        getAvatarPreview().setFitWidth(250);
        getAvatarPreview().setPreserveRatio(true);
        VBox avatarContainer = new VBox();
        HBox container = new HBox(20);
        avatarContainer.setMinHeight(310);
        avatarContainer.setAlignment(Pos.CENTER);
        avatarContainer.setPadding(new Insets(30, 0, 0, 0));
        avatarContainer.getChildren().addAll(getAvatarPreview());
        getAvatar().getStyleClass().add("botonImportar");
        avatarH.getChildren().addAll(avatarContainer, getAvatar());
        container.getChildren().addAll(left, right, avatarH);
        container.setAlignment(Pos.CENTER);
        container.setMinWidth(700);
        container.setPadding(new Insets(20, 0, 20, 0));
        //container.setStyle("-fx-background-color:darkcyan;");
        HBox head = new HBox();
        HBox backH = new HBox();
        backH.getChildren().addAll(getBack());
        backH.setMinWidth(230);
        backH.setPadding(new Insets(5, 5, 5, 5));
        head.setMinWidth(780);
        //head.setStyle("-fx-background-color:red;");
        HBox crearCuentaLogoH = new HBox();
        crearCuentaLogoH.getChildren().addAll(crearCuentaLogo);
        crearCuentaLogoH.setPadding(new Insets(3, 85, 20, 0));
        head.getChildren().addAll(backH, crearCuentaLogoH, topMenu);
        head.setPadding(new Insets(5, 5, 5, 5));
        HBox continuar = new HBox();
        //continuar.setStyle("-fx-background-color:yellow;");
        continuar.setMinHeight(90);
        getRegisterButton().getStyleClass().add("loginButton");
        continuar.getChildren().addAll(getRegisterButton());
        continuar.setAlignment(Pos.BOTTOM_CENTER);
        layout.getChildren().addAll(head, container, continuar);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("crearCuenta.css");

        try {
            getSignInWindow().initStyle(StageStyle.TRANSPARENT);
        } catch (Exception e) {

        }
        getSignInWindow().setScene(scene);
        getSignInWindow().showAndWait();
    }

    public void clear() {
        nombreRegistro.setText("");
        segundoNombreRegistro.setText("");
        apellidosRegistro.setText("");
        nombreUsuarioRegistro.setText("");
        idRegistro.setText("");
        correoRegistro.setText("");
        confirmarCorreoRegistro.setText("");
        songName.setText("");
        artistName.setText("");
        albumName.setText("");
        contrasenaRegistro.setText("");
        confirmarContrasenaRegistro.setText("");
        correo.setText("");
        contrasena.setText("");
        importName.setText("");
        cuadroAlerta.setText("");
        importImage = new ImageView();
        avatarPreview = new ImageView();
        navigation = new VBox(20);
        containerPlaylist = new HBox();
        initWindow = new Stage();
        loginWindow = new Stage();
        signInWindow = new Stage();
        principal = new Stage();
        mainBorder = new BorderPane();
        retornando = "";
        cent = 0;
        xOffset = 0;
        yOffset = 0;
        answer = true;

    }

    private void resize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        System.out.println("Height: " + height + " Width: " + width);
    }

    /**
     * @return the initWindow
     */
    public Stage getInitWindow() {
        return initWindow;
    }

    /**
     * @param aInitWindow the initWindow to set
     */
    public void setInitWindow(Stage aInitWindow) {
        initWindow = aInitWindow;
    }

    /**
     * @return the loginWindow
     */
    public Stage getLoginWindow() {
        return loginWindow;
    }

    /**
     * @param aLoginWindow the loginWindow to set
     */
    public void setLoginWindow(Stage aLoginWindow) {
        loginWindow = aLoginWindow;
    }

    /**
     * @return the signInWindow
     */
    public Stage getSignInWindow() {
        return signInWindow;
    }

    /**
     * @param aSignInWindow the signInWindow to set
     */
    public void setSignInWindow(Stage aSignInWindow) {
        signInWindow = aSignInWindow;
    }

    /**
     * @return the principal
     */
    public Stage getPrincipal() {
        return principal;
    }

    /**
     * @param aPrincipal the principal to set
     */
    public void setPrincipal(Stage aPrincipal) {
        principal = aPrincipal;
    }

    /**
     * @return the importar
     */
    public HBox getImportar() {
        return importar;
    }

    /**
     * @return the confirmImport
     */
    public Button getConfirmImport() {
        return confirmImport;
    }

    /**
     * @param aConfirmImport the confirmImport to set
     */
    public void setConfirmImport(Button aConfirmImport) {
        confirmImport = aConfirmImport;
    }

    /**
     * @return the cerrar
     */
    public Button getCerrar() {
        return cerrar;
    }

    /**
     * @param aCerrar the cerrar to set
     */
    public void setCerrar(Button aCerrar) {
        cerrar = aCerrar;
    }

    /**
     * @return the cerrarSesion
     */
    public Button getCerrarSesion() {
        return cerrarSesion;
    }

    /**
     * @param aCerrarSesion the cerrarSesion to set
     */
    public void setCerrarSesion(Button aCerrarSesion) {
        cerrarSesion = aCerrarSesion;
    }

    /**
     * @return the canciones
     */
    public Button getCanciones() {
        return canciones;
    }

    /**
     * @param aCanciones the canciones to set
     */
    public void setCanciones(Button aCanciones) {
        canciones = aCanciones;
    }

    /**
     * @return the cuenta
     */
    public HBox getCuenta() {
        return cuenta;
    }

    /**
     * @return the artistas
     */
    public Button getArtistas() {
        return artistas;
    }

    /**
     * @param aArtistas the artistas to set
     */
    public void setArtistas(Button aArtistas) {
        artistas = aArtistas;
    }

    /**
     * @return the albumes
     */
    public Button getAlbumes() {
        return albumes;
    }

    /**
     * @param aAlbumes the albumes to set
     */
    public void setAlbumes(Button aAlbumes) {
        albumes = aAlbumes;
    }

    /**
     * @return the AddPlaylist
     */
    public Button getAddPlaylist() {
        return AddPlaylist;
    }

    /**
     * @param aAddPlaylist the AddPlaylist to set
     */
    public void setAddPlaylist(Button aAddPlaylist) {
        AddPlaylist = aAddPlaylist;
    }

    /**
     * @return the bibliotecaLabel
     */
    public Label getBibliotecaLabel() {
        return bibliotecaLabel;
    }

    /**
     * @param aBibliotecaLabel the bibliotecaLabel to set
     */
    public void setBibliotecaLabel(Label aBibliotecaLabel) {
        bibliotecaLabel = aBibliotecaLabel;
    }

    /**
     * @return the cuentaLabel
     */
    public Label getCuentaLabel() {
        return cuentaLabel;
    }

    /**
     * @param aCuentaLabel the cuentaLabel to set
     */
    public void setCuentaLabel(Label aCuentaLabel) {
        cuentaLabel = aCuentaLabel;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param aPlayer the player to set
     */
    public void setPlayer(Player aPlayer) {
        player = aPlayer;
    }

    /**
     * @return the containerPlaylist
     */
    public HBox getContainerPlaylist() {
        return containerPlaylist;
    }

    /**
     * @param aContainerPlaylist the containerPlaylist to set
     */
    public void setContainerPlaylist(HBox aContainerPlaylist) {
        containerPlaylist = aContainerPlaylist;
    }

    /**
     * @return the songName
     */
    public TextField getSongName() {
        return songName;
    }

    /**
     * @param aSongName the songName to set
     */
    public void setSongName(TextField aSongName) {
        songName = aSongName;
    }

    /**
     * @return the artistName
     */
    public TextField getArtistName() {
        return artistName;
    }

    /**
     * @param aArtistName the artistName to set
     */
    public void setArtistName(TextField aArtistName) {
        artistName = aArtistName;
    }

    /**
     * @return the albumName
     */
    public TextField getAlbumName() {
        return albumName;
    }

    /**
     * @param aAlbumName the albumName to set
     */
    public void setAlbumName(TextField aAlbumName) {
        albumName = aAlbumName;
    }

    /**
     * @return the importName
     */
    public Label getImportName() {
        return importName;
    }

    /**
     * @param aImportName the importName to set
     */
    public void setImportName(Label aImportName) {
        importName = aImportName;
    }

    /**
     * @return the browseSong
     */
    public Button getBrowseSong() {
        return browseSong;
    }

    /**
     * @param aBrowseSong the browseSong to set
     */
    public void setBrowseSong(Button aBrowseSong) {
        browseSong = aBrowseSong;
    }

    /**
     * @return the mainBorder
     */
    public BorderPane getMainBorder() {
        return mainBorder;
    }

    /**
     * @param aMainBorder the mainBorder to set
     */
    public void setMainBorder(BorderPane aMainBorder) {
        mainBorder = aMainBorder;
    }

    /**
     * @return the navigation
     */
    public VBox getNavigation() {
        return navigation;
    }

    /**
     * @param aNavigation the navigation to set
     */
    public void setNavigation(VBox aNavigation) {
        navigation = aNavigation;
    }

    /**
     * @return the playlists
     */
    public Label getPlaylists() {
        return playlists;
    }

    /**
     * @param aPlaylists the playlists to set
     */
    public void setPlaylists(Label aPlaylists) {
        playlists = aPlaylists;
    }

    /**
     * @return the importImage
     */
    public ImageView getImportImage() {
        return importImage;
    }

    /**
     * @param aImportImage the importImage to set
     */
    public void setImportImage(ImageView aImportImage) {
        importImage = aImportImage;
    }

    /**
     * @return the loginButton
     */
    public Button getLoginButton() {
        return loginButton;
    }

    /**
     * @param aLoginButton the loginButton to set
     */
    public void setLoginButton(Button aLoginButton) {
        loginButton = aLoginButton;
    }

    /**
     * @return the retornando
     */
    public String getRetornando() {
        return retornando;
    }

    /**
     * @param aRetornando the retornando to set
     */
    public void setRetornando(String aRetornando) {
        retornando = aRetornando;
    }

    /**
     * @return the close
     */
    public Button getClose() {
        return close;
    }

    /**
     * @param aClose the close to set
     */
    public void setClose(Button aClose) {
        close = aClose;
    }

    /**
     * @return the closeSignIn
     */
    public Button getCloseSignIn() {
        return closeSignIn;
    }

    /**
     * @param aCloseSignIn the closeSignIn to set
     */
    public void setCloseSignIn(Button aCloseSignIn) {
        closeSignIn = aCloseSignIn;
    }

    /**
     * @return the cent
     */
    public int getCent() {
        return cent;
    }

    /**
     * @param aCent the cent to set
     */
    public void setCent(int aCent) {
        cent = aCent;
    }

    /**
     * @return the correo
     */
    public TextField getCorreo() {
        return correo;
    }

    /**
     * @param aCorreo the correo to set
     */
    public void setCorreo(TextField aCorreo) {
        correo = aCorreo;
    }

    /**
     * @return the correoRegistro
     */
    public TextField getCorreoRegistro() {
        return correoRegistro;
    }

    /**
     * @param aCorreoRegistro the correoRegistro to set
     */
    public void setCorreoRegistro(TextField aCorreoRegistro) {
        correoRegistro = aCorreoRegistro;
    }

    /**
     * @return the contrasena
     */
    public PasswordField getContrasena() {
        return contrasena;
    }

    /**
     * @param aContrasena the contrasena to set
     */
    public void setContrasena(PasswordField aContrasena) {
        contrasena = aContrasena;
    }

    /**
     * @return the contrasenaRegistro
     */
    public PasswordField getContrasenaRegistro() {
        return contrasenaRegistro;
    }

    /**
     * @param aContrasenaRegistro the contrasenaRegistro to set
     */
    public void setContrasenaRegistro(PasswordField aContrasenaRegistro) {
        contrasenaRegistro = aContrasenaRegistro;
    }

    /**
     * @return the espacio2
     */
    public Label getCuadroAlerta() {
        return cuadroAlerta;
    }

    /**
     * @param cuadroAlerta the espacio2 to set
     */
    public void setCuadroAlerta(Label cuadroAlerta) {
        this.cuadroAlerta = cuadroAlerta;
    }

    /**
     * @return the xOffset
     */
    public double getxOffset() {
        return xOffset;
    }

    /**
     * @param axOffset the xOffset to set
     */
    public void setxOffset(double axOffset) {
        xOffset = axOffset;
    }

    /**
     * @return the yOffset
     */
    public double getyOffset() {
        return yOffset;
    }

    /**
     * @param ayOffset the yOffset to set
     */
    public void setyOffset(double ayOffset) {
        yOffset = ayOffset;
    }

    /**
     * @return the crearCuenta
     */
    public Button getCrearCuenta() {
        return crearCuenta;
    }

    /**
     * @param aCrearCuenta the crearCuenta to set
     */
    public void setCrearCuenta(Button aCrearCuenta) {
        crearCuenta = aCrearCuenta;
    }

    /**
     * @return the nombreRegistro
     */
    public TextField getNombreRegistro() {
        return nombreRegistro;
    }

    /**
     * @param nombreRegistro the nombreRegistro to set
     */
    public void setNombreRegistro(TextField nombreRegistro) {
        this.nombreRegistro = nombreRegistro;
    }

    /**
     * @return the segundoNombreRegistro
     */
    public TextField getSegundoNombreRegistro() {
        return segundoNombreRegistro;
    }

    /**
     * @param segundoNombreRegistro the segundoNombreRegistro to set
     */
    public void setSegundoNombreRegistro(TextField segundoNombreRegistro) {
        this.segundoNombreRegistro = segundoNombreRegistro;
    }

    /**
     * @return the apellidosRegistro
     */
    public TextField getApellidosRegistro() {
        return apellidosRegistro;
    }

    /**
     * @param apellidosRegistro the apellidosRegistro to set
     */
    public void setApellidosRegistro(TextField apellidosRegistro) {
        this.apellidosRegistro = apellidosRegistro;
    }

    /**
     * @return the nombreUsuarioRegistro
     */
    public TextField getNombreUsuarioRegistro() {
        return nombreUsuarioRegistro;
    }

    /**
     * @param nombreUsuarioRegistro the nombreUsuarioRegistro to set
     */
    public void setNombreUsuarioRegistro(TextField nombreUsuarioRegistro) {
        this.nombreUsuarioRegistro = nombreUsuarioRegistro;
    }

    /**
     * @return the idRegistro
     */
    public TextField getIdRegistro() {
        return idRegistro;
    }

    /**
     * @param idRegistro the idRegistro to set
     */
    public void setIdRegistro(TextField idRegistro) {
        this.idRegistro = idRegistro;
    }

    /**
     * @return the confirmarCorreoRegistro
     */
    public TextField getConfirmarCorreoRegistro() {
        return confirmarCorreoRegistro;
    }

    /**
     * @param confirmarCorreoRegistro the confirmarCorreoRegistro to set
     */
    public void setConfirmarCorreoRegistro(TextField confirmarCorreoRegistro) {
        this.confirmarCorreoRegistro = confirmarCorreoRegistro;
    }

    /**
     * @return the confirmarContrasenaRegistro
     */
    public PasswordField getConfirmarContrasenaRegistro() {
        return confirmarContrasenaRegistro;
    }

    /**
     * @param confirmarContrasenaRegistro the confirmarContrasenaRegistro to set
     */
    public void setConfirmarContrasenaRegistro(PasswordField confirmarContrasenaRegistro) {
        this.confirmarContrasenaRegistro = confirmarContrasenaRegistro;
    }

    /**
     * @return the avatar
     */
    public Button getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(Button avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the avatarPreview
     */
    public ImageView getAvatarPreview() {
        return avatarPreview;
    }

    /**
     * @param avatarPreview the avatarPreview to set
     */
    public void setAvatarPreview(ImageView avatarPreview) {
        this.avatarPreview = avatarPreview;
    }

    public void setAvatarPreviewImage(Image avatarImage) {

        getAvatarPreview().setImage(avatarImage);
    }

    public void setAvatarPreviewImage(File avatarImage) throws FileNotFoundException {

        getAvatarPreview().setImage(convertImage(avatarImage.getPath()));
    }

    /**
     * @return the registerButton
     */
    public Button getRegisterButton() {
        return registerButton;
    }

    /**
     * @param registerButton the registerButton to set
     */
    public void setRegisterButton(Button registerButton) {
        this.registerButton = registerButton;
    }

    /**
     * @return the back
     */
    public Button getBack() {
        return back;
    }

    /**
     * @param back the back to set
     */
    public void setBack(Button back) {
        this.back = back;
    }

    /**
     * @return the closeLogIn
     */
    public Button getCloseLogIn() {
        return closeLogIn;
    }

    /**
     * @param closeLogIn the closeLogIn to set
     */
    public void setCloseLogIn(Button closeLogIn) {
        this.closeLogIn = closeLogIn;
    }

    /**
     * @return the fechaNacimientoRegistro
     */
    public LocalDate getFechaNacimientoRegistro() {
        return this.fechaNacimientoRegistro.getValue();
    }

    /**
     * @param fechaNacimientoRegistro the fechaNacimientoRegistro to set
     */
    public void setFechaNacimientoRegistro(DatePicker fechaNacimientoRegistro) {
        this.fechaNacimientoRegistro = fechaNacimientoRegistro;
    }

    /**
     * @return the seleccionarPais
     */
    public ComboBox getSeleccionarPais() {
        return seleccionarPais;
    }

    /**
     * @param seleccionarPais the seleccionarPais to set
     */
    public void setSeleccionarPais(ComboBox seleccionarPais) {
        this.seleccionarPais = seleccionarPais;
    }

    public void addPlaylist(Playlist playlist) {
        listaPlaylistNames.add(playlist.getNombre());
        listaP.add(playlist.getNombre());
    }

    /**
     * @return the importarCancion
     */
    public MenuItem getImportarCancion() {
        return importarCancion;
    }

    /**
     * @param importarCancion the importarCancion to set
     */
    public void setImportarCancion(MenuItem importarCancion) {
        this.importarCancion = importarCancion;
    }

}
