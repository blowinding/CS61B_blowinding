import java.util.InputMismatchException;

public class NBody {
    public static double radius;
    public static double downScale;
    public static double upScale;
    public static double readRadius(String file) {
        In in = new In(file);
        double radius = 0;
        try {
            in.readInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        try {
            radius = in.readDouble();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return radius;
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int planets;
        try {
            planets = in.readInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return null;
        }
        Planet[] planet = new Planet[planets];
        in.readDouble();
        for (int i = 0; i < planets; i++) {
            try {
                double xxPos = in.readDouble();
                double yyPos = in.readDouble();
                double xxVel = in.readDouble();
                double yyVel = in.readDouble();
                double mass = in.readDouble();
                String imgFileName = in.readString();
                planet[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("there");
                return null;
            }
        }
        return planet;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java NBody <T> <dt> <filename>");
            return;
        }
        // read from cmd arguments
        double T;
        double dt;
        String filename;
        try {
            T = Double.parseDouble(args[0]);
            dt = Double.parseDouble(args[1]);
            filename = args[2];
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        // read from file
        radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        if (planets == null) {
            System.out.println(filename + "is invalid");
            return;
        }
        // set drawing params
        String universeImgFile = "images/starfield.jpg";
        downScale = -200;
        upScale = 200;
        // start drawing
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        StdDraw.enableDoubleBuffering();
        while (T >= 0) {
            StdDraw.clear();
            StdDraw.setScale(downScale, upScale);
            StdDraw.picture(0, 0, universeImgFile);
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            T -= dt;
        }
        // print result
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
