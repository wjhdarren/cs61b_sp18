
public class Planet {

    /** Declare constants*/

    public static final double G = 6.67e-11; //Gravity constant

    /** Instance variables */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** Constructors */
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }  

    /**A method that calculates the distance between two Planets */
    public double calcDistance(Planet p){
        double dist_x = p.xxPos - this.xxPos;
        double dist_y = p.yyPos - this.yyPos;
        return Math.sqrt(dist_x * dist_x + dist_y * dist_y);
    }

    /** A method takes in a planet, 
     * and returns a double describing the force exerted on this planet by the given planet */
    public double calcForceExertedBy(Planet p){
        return G * this.mass * p.mass/(this.calcDistance(p)*this.calcDistance(p));
    }

    /** Two methods describe the force exerted in the X and Y directions, respectively. */
    public double calcForceExertedByX(Planet p){
        double total_gravity = this.calcForceExertedBy(p);
        return total_gravity * (p.xxPos - this.xxPos)/this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        double total_gravity = this.calcForceExertedBy(p);
        return total_gravity * (p.yyPos - this.yyPos)/this.calcDistance(p);
    }

    /** Two methods each take in an array of Planets and calculate the 
     * net X and net Y force exerted by all planets in that array upon the current Planet.*/
    public double calcNetForceExertedByX(Planet[] planets){
        double netforce = 0;
        for (int i=0; i < planets.length; i++){
            if (planets[i]==this){
                continue;
            }
            netforce += this.calcForceExertedByX(planets[i]);
        } 
        return netforce; 
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double netforce = 0;
        for (int i=0; i < planets.length; i++){
            if (planets[i]==this){
                continue;
            }
            netforce += this.calcForceExertedByY(planets[i]);
        } 
        return netforce; 
    }

    /** A method that determines how much the forces exerted on the planet 
     * will cause that planet to accelerate, and the resulting change in 
     * the planet’s velocity and position in a small period of time dt. */
    public void update(double dt, double Xforce, double Yforce){
        double accelerationX = Xforce/this.mass;
        double accelerationY = Yforce/this.mass;
        this.xxVel = this.xxVel + accelerationX * dt;
        this.yyVel = this.yyVel + accelerationY * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;

    }

}

