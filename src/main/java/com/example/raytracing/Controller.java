package com.example.cs255_1;

/*Author
Upenyu Hlangabeza - 2035108
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Slider Axis_X;

    @FXML
    private Slider Axis_Y;

    @FXML
    private Slider Axis_Z;

    @FXML
    private Slider BlueSlider;

    @FXML
    private Slider GreenSlider;

    @FXML
    private ImageView ImageViewer;

    @FXML
    private Slider Radius;

    @FXML
    private Slider RedSlider;

    @FXML
    private HBox radiobuttonBox;

    static ArrayList<Sphere>spheres = new ArrayList<>();


    WritableImage image;

    ToggleGroup sphereGroup = new ToggleGroup();


    private void sphereMover(){
        final Sphere[] sphereSelected = new Sphere[1];
        sphereGroup.selectedToggleProperty().addListener((observable, oldValue, newValue)->  {
            if (newValue != null) {

                sphereSelected[0] = (Sphere) newValue.getUserData();

                Axis_X.setValue(sphereSelected[0].getCs().x);
                Axis_Y.setValue(sphereSelected[0].getCs().y);
                Axis_Z.setValue(sphereSelected[0].getCs().z);

                RedSlider.setValue(sphereSelected[0].getColour().x);

                GreenSlider.setValue(sphereSelected[0].getColour().y);
                BlueSlider.setValue(sphereSelected[0].getColour().z);

                sphereSelected[0].getColour().print();

                Radius.setValue(sphereSelected[0].getRadius());

                Axis_X.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    double x = newValue1.doubleValue();
                    sphereSelected[0].setCs(new Vector(x, sphereSelected[0].getCs().y, sphereSelected[0].getCs().z));
                    image =new WritableImage((int) ImageViewer.getFitWidth(), (int) ImageViewer.getFitHeight());
                    ImageViewer.setImage(image);
                    SphereRenderer.Render(image);
                });

                Axis_Y.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    double y = newValue1.doubleValue();
                    sphereSelected[0].setCs(new Vector(sphereSelected[0].getCs().x,y, sphereSelected[0].getCs().z));
                    image =new WritableImage((int) ImageViewer.getFitWidth(), (int) ImageViewer.getFitHeight());
                    ImageViewer.setImage(image);
                    SphereRenderer.Render(image);
                });
                Axis_Z.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    double z = newValue1.doubleValue();
                    sphereSelected[0].setCs(new Vector(sphereSelected[0].getCs().x,sphereSelected[0].getCs().y, z));
                    image =new WritableImage((int) ImageViewer.getFitWidth(), (int) ImageViewer.getFitHeight());
                    ImageViewer.setImage(image);
                    SphereRenderer.Render(image);
                });

                RedSlider.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    double x = newValue1.doubleValue();
                    sphereSelected[0].setColour(new Vector(x,sphereSelected[0].getColour().y,
                            sphereSelected[0].getColour().z ));
                    image =new WritableImage((int) ImageViewer.getFitWidth(), (int) ImageViewer.getFitHeight());
                    ImageViewer.setImage(image);
                    SphereRenderer.Render(image);
                });

                GreenSlider.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    double y = newValue1.doubleValue();
                    sphereSelected[0].setColour(new Vector(sphereSelected[0].getColour().x,
                            y,sphereSelected[0].getColour().z ));
                    image =new WritableImage((int) ImageViewer.getFitWidth(), (int) ImageViewer.getFitHeight());
                    ImageViewer.setImage(image);
                    SphereRenderer.Render(image);
                });

                BlueSlider.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    double z = newValue1.doubleValue();
                    sphereSelected[0].setColour(new Vector(sphereSelected[0].getColour().x,
                            sphereSelected[0].getColour().y,z));
                    image =new WritableImage((int) ImageViewer.getFitWidth(), (int) ImageViewer.getFitHeight());
                    ImageViewer.setImage(image);
                    SphereRenderer.Render(image);
                });

                Radius.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    double radius = newValue1.doubleValue();
                    sphereSelected[0].setRadius(radius);
                    image =new WritableImage((int) ImageViewer.getFitWidth(), (int) ImageViewer.getFitHeight());
                    ImageViewer.setImage(image);
                    SphereRenderer.Render(image);
                });
            }
        });
    }

    //Creatres the Spheres
    private void createSpheres(){
        Sphere sphere_1 = new Sphere(90,new Vector(-200, 0, 20), new Vector(200, 0, 0));
        Sphere sphere_2 = new Sphere(50, new Vector(100, 0, 50), new Vector(0, 200, 0));
        Sphere sphere_3 = new Sphere(50, new Vector(60, 20, 10), new Vector(200, 0, 250));
        Sphere sphere_4 = new Sphere(50, new Vector(20, 30, 100), new Vector(0, 250, 250));

        spheres.add(sphere_1);
        spheres.add(sphere_2);
        spheres.add(sphere_3);
        spheres.add(sphere_4);

        int num = 1;
        for (Sphere sphere : spheres) {
            RadioButton button = new RadioButton("Sphere " + num);
            button.setToggleGroup(sphereGroup);
            button.setUserData(sphere);
            radiobuttonBox.getChildren().add(button);
            num++;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image = new WritableImage((int) ImageViewer.getFitWidth(), (int) ImageViewer.getFitHeight());
        ImageViewer.setImage(image);
        createSpheres();
        SphereRenderer.Render(image);
        sphereMover();
    }
}
