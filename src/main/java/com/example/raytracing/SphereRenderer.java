package com.example.cs255_1;

/*Author
Upenyu Hlangabeza - 2035108
 */

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class SphereRenderer {

    public static Sphere selectedSphere;
    static int x_position = 0; //just for the test example
    static int y_position = 0;
    static int z_position = 0;

    static Vector position;
    static Vector lookAt;
    static Vector up;
    static int radius = 0;
    static double blue = 255;
    static double red = 255;
    static double green = 255;

    static double rad = radius;

    public static void Render(WritableImage image) {

        //Get image dimensions, and declare loop variables
        int w = (int) image.getWidth(), h = (int) image.getHeight(), i, j;
        PixelWriter image_writer = image.getPixelWriter();

        /*Vector position = new Vector(0, 0, 0);
        Vector lookAt = new Vector(6, 9, -1);
        Vector up = new Vector(0, 1, 0);
         */

        double x_cods = x_position;
        double y_cords=y_position;
        double z_cords = z_position;

        double redCol = red / 255.0;
        double greenCol = green / 255.0;
        double blueCol = blue / 255.0;

        //Vector VRP = new Vector(z)

        Vector d = new Vector(0, 0, 1);
        Vector cs = new Vector(x_cods, y_cords, z_cords);
        //Vector sphere_col = new Vector(redCol, greenCol, blueCol);
        Vector bkg_col = new Vector(0, 0, 0);

        Vector p = new Vector(0, 0, 0);
        double t;
        double a, b, c;
        Vector v;
        Vector v1;
        Vector Light = new Vector(0, 250, -400);


        //WHERE EVER YOU USED ANYTHING RELATED TO SPHERE USE
        //sphere.

        //WHERE EVER YOU USED ANYTHING RELATED TO Radius USE
        //color.

        //closest t and empty sphere
        //as you do the comparison you check if the t u have is lest than the closest t and set the closest t
        for (j = 0; j < h; j++) {
            for (i = 0; i < w; i++) {
                double closest = Double.MAX_VALUE;
                Sphere sphere1 = null;
                for (Sphere sphere : Controller.spheres) {
                    Vector o = new Vector(i - w / 2., j - h / 2., -400);
                    o.x = i - 250;
                    o.y = j - 250;
                    o.z = -200;
                    v = o.sub(sphere.getCs());
                    a = d.dot(d);
                    b = 2 * v.dot(d);
                    c = v.dot(v) - sphere.getRadius() * sphere.getRadius();
                    double disc = b * b - 4 * a * c;
                    if (disc < 0) {
                        continue;

                    } else {
                        t = (-b - sqrt(disc)) / 2 * a;
                        if (t < closest) {
                            closest = t;
                            sphere1 = sphere;
                        }

                        if (sphere1 == null) {
                            break;
                        } else {
                            p = o.add(d.mul(closest));
                            Vector Lv = Light.sub(p);
                            Lv.normalise();
                            Vector n = p.sub(sphere1.getCs());
                            n.normalise();
                            double dp = Lv.dot(n);
                            if (dp < 0) {
                                dp = 0;
                            }
                            if (dp > 1) {
                                dp = 1;
                            }
                            image_writer.setColor(i, j, Color.color(dp * sphere1.getColour().x / 255, dp * sphere1.getColour().y/ 255, dp * sphere1.getColour().z / 255, 1.0));
                        }

                    }
                }
                // column loop
            }

        }
    }
}
