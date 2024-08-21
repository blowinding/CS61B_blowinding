public class Planet {
    private static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return G * mass * p.mass / (dx * dx + dy * dy);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        return f * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        return f * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] arr) {
        double netForceX = 0;
        for (Planet p : arr) {
            if (!p.equals(this)) {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] arr) {
        double netForceY = 0;
        for (Planet p : arr) {
            if (!p.equals(this)) {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        double scale = (double) (200 - (-200)) / 2 / 2.50e+11;
        StdDraw.picture(xxPos * scale, yyPos * scale, "images/" + imgFileName);
    }
}
