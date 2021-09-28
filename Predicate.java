public class Predicate {
    public static boolean TriTriIntersect(Vertex a, Vertex b, Vertex c, Vertex d, Vertex e, Vertex f) {
        boolean intersects = (Intersect(a, b, d, e) || Intersect(a, b, e, f) || Intersect(a, b, f, d) ||
                Intersect(b, c, d, e) || Intersect(b, c, d, e) || Intersect(b, c, f, d) ||
                Intersect(c, a, d, e) || Intersect(c, a, e, f) || Intersect(c, a, f, d));
        boolean inside = (isInside(a, b, c, d) && isInside(a, b, c, e) && isInside(a, b, c, f)) ||
                (isInside(d, e, f, a) && isInside(d, e, f, b) && isInside(d, e, f, c));

        return intersects || inside;
    }

    // Helper function
    static double area(Vertex a, Vertex b, Vertex c) {
        return Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y)+ c.x *(a.y - b.y)) / 2.0);
    }

    // Helper function
    static boolean isInside(Vertex a, Vertex b, Vertex c,  Vertex d) {
        // Calculate area of triangle ABC
        double A = area (a, b, c);

        // Calculate area of triangle PBC
        double A1 = area (d, b, c);

        // Calculate area of triangle PAC
        double A2 = area (a, d, c);

        // Calculate area of triangle PAB
        double A3 = area (a, b, d);

        // Check if sum of A1, A2 and A3 is same as A
        return (A == A1 + A2 + A3);
    }

    //Here is the predicates from the book, adjusted to java
    public static boolean Left(Vertex a, Vertex b, Vertex c) {
        return Area2(a, b, c) > 0;
    }

    public static boolean LeftOn(Vertex a, Vertex b, Vertex c) {
        return Area2( a, b, c ) >= 0;
    }

    public static boolean Collinear(Vertex a, Vertex b, Vertex c) {
        return Area2( a, b, c ) == 0;
    }

    public static boolean IntersectProp(Vertex a, Vertex b, Vertex c, Vertex d) {
        if (Collinear(a,b,c) || Collinear(a,b,d) || Collinear (c, d, a) || Collinear(c,d,b))
            return false;

        return Xor(Left(a,b,c), Left(a,b,d)) && Xor(Left(c,d,a), Left(c,d,b));
    }

    public static boolean Between(Vertex a, Vertex b, Vertex c) {
        if ( ! Collinear ( a, b, c ) )
            return false;

        if (a.x != b.x)
            return ((a.x <= c.x) && (c.x <= b.x )) || ((a.x >= c.x) && (c.x >= b.x));

        return ((a.y <= c.y) && (c.y <= b.y)) || ((a.y >= c.y) && (c.y >= b.y));
    }

    public static boolean Intersect(Vertex a, Vertex b, Vertex c, Vertex d) {
        if (IntersectProp(a, b, c, d))
            return true;
        else if ( Between ( a, b, c) || Between (a, b, d) || Between (c, d, a) || Between (c, d, b))
            return true;
        return false;
    }

    public static int Area2(Vertex a, Vertex b, Vertex c) {
        return ((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y));
    }

    public static boolean Xor( boolean x, boolean y ) {
        return x != y;
    }


}
