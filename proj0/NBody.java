public class NBody {

    private static String background = ("./images/starfield.jpg");

    /** Read radius of the universe from a .txt file. */
    private static double readRadius(String file){
        In input = new In(file);
        int firstItem = input.readInt();
        double secondItem = input.readDouble();
        return secondItem;
    }

    /** Give an array of planets corresponding to the planets in the file. */
    private static Planet[] readPlanets(String file){
        In input = new In(file);
        int num_planets = input.readInt();
        double secondItem = input.readDouble();
        Planet[] planets = new Planet[num_planets]; //We have not instantiate each Planet object yet!!!!
        for (int i = 0; i <num_planets; i++){
            double xPos = input.readDouble();
            double yPos = input.readDouble();
            double xVel = input.readDouble();
            double yVel = input.readDouble();
            double mass = input.readDouble();
            String imgFileName = input.readString(); 
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);
        }
        return planets;  
    }

    private static void drawBackground(double radius){
        StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, background);
		//StdDraw.show();
    }

    private static void drawPlanet(Planet[] planets){
        for (int i = 0; i < planets.length; i++){
            planets[i].draw();
        }
    }

    public static void main(String[] args){
        /** Collect all needed input. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /** Creat an animation. */
        StdAudio.play("./audio/2001.mid");  //Play the theme to 2001: A Space Odyssey!!!!
        StdDraw.enableDoubleBuffering(); //Prevent flickering in the animation.
        drawBackground(radius);
        drawPlanet(planets);
        StdDraw.show();
    
        for (double t = 0; t<=T; t= t + dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++){ //loop on each planet.
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            for (int i = 0; i < planets.length; i++){ 
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            drawBackground(radius);
            drawPlanet(planets);
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
        
    }
}