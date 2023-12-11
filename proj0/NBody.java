public class NBody {
    /** Read radius of the universe from a .txt file. */
    public static double readRadius(String file){
        In input = new In(file);
        int firstItem = input.readInt();
        double secondItem = input.readDouble();
        return secondItem;
    }

    /** Give an array of planets corresponding to the planets in the file. */
    public static Planet[] readPlanets(String file){
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
    public static void main(String[] args){
        /** Collect all needed input. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /** Draw the background. */
        String background = ("./images/starfield.jpg");
        StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, background);
		StdDraw.show();
		//StdDraw.pause(2000);

        /** Draw each planet. */
        for (int i = 0; i < planets.length; i++){
            planets[i].draw();
        }
        
    }
}