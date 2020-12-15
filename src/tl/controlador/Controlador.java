package tl.controlador;

import ui.UI;
import bl.entidades.*;
import bl.logica.Gestor;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.MapChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.stage.Stage;

public class Controlador extends Application {

    /*init Controlador*/
    private final UI userInterface = new UI();
    private File importAux;
    private String title;
    private String artist;
    private String albumName;
    private Image image;
    private Cliente clienteActual;
    private final Gestor gestor = new Gestor();
    public boolean sessionWard = false;
    public int chosen = 0;

    public static void ejecutar() {
        Controlador.launch(Controlador.class);
    }

    /*ejecutando*/
    @Override
    public void start(Stage primaryStage) throws Exception {

        userInterface.loadLogo();
        loadLogin();
        while (true) {
            switch (chosen) {

                case 0:
                    userInterface.initApp();
                    break;
                case 1:
                    interfazCliente(clienteActual);
                    break;
                case 2:
                    userInterface.createAccount();
                    break;
                case -1:
                    System.out.println("El App ha finalizado");
                    System.exit(0);
                    break;
            }
        }
    }

    /*interfaz del Cliente*/
    private void interfazCliente(Cliente cliente) throws FileNotFoundException, MalformedURLException, SQLException {


        Stage stage = userInterface.getMainWindow();

        ArrayList<Playlist> allPlaylists = gestor.getPlaylistsCliente(cliente.getId());
        for (int i = 0; i < allPlaylists.size(); i++) {
            userInterface.addPlaylist(allPlaylists.get(i));
        }
        userInterface.loadMainCliente(cliente, stage);
        userInterface.getCerrar().setOnAction(e -> {
            chosen = -1;
            stage.close();
        });

        userInterface.getCerrarSesion().setOnAction(e -> {
            if (userInterface.confirmBox("¿Está seguro que desea cerrar sesión?")) {
                userInterface.clear();
                chosen = 0;
                stage.close();
                sessionWard = true;
            }
        });

        /*NAVIGATION BAR*/
        userInterface.getImportarCancion().setOnAction(e -> {
            try {
                userInterface.getMainBorder().setCenter(userInterface.importScene(gestor.getArtistasCliente(cliente.getId()), gestor.getAlbumesCliente(cliente.getId())));
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        userInterface.getAlbumes().setOnAction(e -> {

            try {
                userInterface.getMainBorder().setCenter(userInterface.albumScene(gestor.getAlbumesCliente(cliente.getId())));
            } catch (SQLException ex) {

            } catch (MalformedURLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        userInterface.getCanciones().setOnAction(e -> {

            try {
                userInterface.getMainBorder().setCenter(userInterface.songsScene(gestor.getCancionesCliente(cliente.getId())));
            } catch (SQLException | MalformedURLException ex) {
            }

        });

        userInterface.getArtistas().setOnAction(e -> {

            try {
                userInterface.getMainBorder().setCenter(userInterface.artistScene(gestor.getArtistasCliente(cliente.getId())));
            } catch (SQLException ex) {

            }

        });

        userInterface.getAddPlaylist().setOnAction(e -> {

            String nombrePlaylist = userInterface.createPlaylist();

            if (nombrePlaylist != null) {
                Playlist playlist = new Playlist(nombrePlaylist);
                try {
                    gestor.addPlaylist(playlist, cliente.getId());
                    userInterface.addPlaylist(playlist);
                } catch (SQLException ex) {
                }

            }
        });
        /**
         * ****************************************************************
         */
        userInterface.getConfirmImport().setOnAction((ActionEvent e) -> {

            try {
                Cancion cancion = new Cancion();
                cancion.setNombre(userInterface.getSongName().getText());
                cancion.setRuta(importAux.getPath());
                cancion.setFechaLanzamiento(LocalDate.parse("2020-01-01"));
                Artista artista = new Artista();
                artista.setNombre(userInterface.getArtistName().getText());
                artista.setSegundoNombre("");
                artista.setApellidos("");
                artista.setPais("");
                artista.setDescripcion("");
                artista.setFechaNacimiento(LocalDate.parse("2020-01-01"));
                artista.setFechaDefuncion(LocalDate.parse("2020-01-01"));
                artista.setNombreArtistico(userInterface.getArtistName().getText());
                Album album = new Album();
                album.setNombre(userInterface.getAlbumName().getText());

                cancion = gestor.crearCancion(cancion);
                artista = gestor.crearArtista(artista);
                album = gestor.crearAlbum(album);

                System.out.println(cancion.toString());

                gestor.anadirArtistaFavorito(artista.getId(), cliente.getId());
                gestor.anadirAlbumFavorito(album.getId(), cliente.getId());
                gestor.anadirCancionFavorita(cancion.getId(), cliente.getId());

                userInterface.getArtistName().setText("");
                userInterface.getSongName().setText("");
                userInterface.getAlbumName().setText("");
                userInterface.getImportName().setText("");
                userInterface.loadRandom();

            } catch (SQLException | MalformedURLException ex) {
            }

            try {
                gestor.listarAlbumes();
                gestor.listarCanciones();
                gestor.listarArtistas();
            } catch (SQLException | MalformedURLException ex) {
            }

        });

        userInterface.getBrowseSong().setOnAction(e -> {

            importAux = userInterface.browseSong(stage);
            userInterface.getImportName().setText(importAux.getName());
            System.out.println(importAux.getPath());
            Media media = new Media(importAux.toURI().toString());
            media.getMetadata().addListener((Change<? extends String, ? extends Object> c) -> {
                if (c.wasAdded()) {
                    if ("artist".equals(c.getKey())) {
                        artist = c.getValueAdded().toString();
                    } else if ("title".equals(c.getKey())) {
                        title = c.getValueAdded().toString();
                    } else if ("album".equals(c.getKey())) {
                        albumName = c.getValueAdded().toString();
                    } else if ("image".equals(c.getKey())) {
                        image = (Image) c.getValueAdded();
                    }
                    System.out.println(c.getKey() + " " + c.getValueAdded().toString());
                }
                if (artist == null || title == null || albumName == null) {
                } else {
                    userInterface.getSongName().setText(title);
                    userInterface.getArtistName().setText(artist);
                    userInterface.getAlbumName().setText(albumName);
                    userInterface.getImportImage().setImage(image);
                }

            });

        });
        /**
         * ****************************************************************
         */

        /*showAndWait*/
        stage.showAndWait();
    }

    private void loadLogin() throws InterruptedException, FileNotFoundException {

        userInterface.getCloseLogIn().setOnAction(e -> {
            if (userInterface.confirmBox("¿Está seguro que desea cerrar?")) {
                chosen = -1;
                userInterface.getLoginWindow().close();
                userInterface.clear();
            }
        });

        userInterface.getCloseSignIn().setOnAction(e -> {
            if (userInterface.confirmBox("¿Está seguro que desea cerrar?")) {
                chosen = -1;
                userInterface.getSignInWindow().close();
                userInterface.clear();
            }
        });

        userInterface.getLoginButton().setOnAction(e -> {
            boolean centinela = false;
            try {
                for (Cliente cliente : gestor.getClientes()) {
                    if (cliente.getCorreo().equals(userInterface.getCorreo().getText().trim().toLowerCase())
                            && cliente.getContrasena().equals(userInterface.getContrasena().getText().trim())) {
                        centinela = true;
                        clienteActual = cliente;
                        chosen = 1;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("No cargo a los clientes!");
            }
            if (centinela) {
                userInterface.getLoginWindow().close();
            } else {
                userInterface.getCuadroAlerta().setText("Login Invalido");
                userInterface.getCuadroAlerta().setStyle("-fx-text-fill: red");
            }
        });

        userInterface.getCrearCuenta().setOnAction(e -> {
            chosen = 2;
            userInterface.getLoginWindow().close();
        });

        userInterface.getAvatar().setOnAction(e -> {
            importAux = userInterface.browseImage(userInterface.getSignInWindow());
            try {
                userInterface.setAvatarPreviewImage(importAux);
            } catch (FileNotFoundException | NullPointerException ex) {
            }
        });

        userInterface.getBack().setOnAction(e -> {
            chosen = 0;
            userInterface.getSignInWindow().close();
            userInterface.clear();
        });

        userInterface.getRegisterButton().setOnAction(e -> {

            Cliente cliente = new Cliente();
            cliente.setNombre(userInterface.getNombreRegistro().getText());
            cliente.setSegundoNombre(userInterface.getSegundoNombreRegistro().getText());
            cliente.setApellidos(userInterface.getApellidosRegistro().getText());
            cliente.setCorreo(userInterface.getCorreoRegistro().getText());
            cliente.setContrasena(userInterface.getContrasenaRegistro().getText());
            cliente.setNombreUsuario(userInterface.getNombreUsuarioRegistro().getText());
            cliente.setFechaNacimiento(userInterface.getFechaNacimientoRegistro());
            cliente.setPais((String) userInterface.getSeleccionarPais().getValue());
            try {
                cliente.setAvatar(importAux.getPath());
            } catch (NullPointerException ex) {
                cliente.setAvatar("default");
            }
            cliente.setCodigo(gestor.getRandomCode());
            cliente.setEstado("inactivo");

            try {
                gestor.crearCliente(cliente);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        });

    }

}
