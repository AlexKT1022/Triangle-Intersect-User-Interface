public class Predicate {

    public static boolean TriTriIntersect(Vertex a, Vertex b, Vertex c, Vertex d, Vertex e, Vertex f)
    {
        //Insert your code here
        return false; //default return value
    }

    //Here is the predicates from the book, adjusted to java
    public static boolean Left(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) > 0;
    }
    public static boolean LeftOn(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) >= 0;
    }
    public static boolean Collinear(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) == 0;
    }
    public static boolean IntersectProp(Vertex a, Vertex b, Vertex c, Vertex d)
    {
        if (Collinear(a,b,c) || Collinear(a,b,d) || Collinear (c, d, a) || Collinear(c,d,b))
            return false;
        return Xor(Left(a,b,c), Left(a,b,d)) && Xor(Left(c,d,a), Left(c,d,b));
    }
    public static boolean Between(Vertex a, Vertex b, Vertex c)
    {
        if ( ! Collinear ( a, b, c ) )
            return false;
        if ( a.x != b.x )
            return ((a.x <= c.x) && (c.x <= b.x )) || ((a.x >= c.x) && (c.x >= b.x));
        return ((a.y <= c.y) && (c.y <= b.y)) || ((a.y >= c.y) && (c.y >= b.y));
    }
    public static boolean Intersect(Vertex a, Vertex b, Vertex c, Vertex d)
    {
        if (IntersectProp( a, b, c, d ) )
            return true;
        else if ( Between ( a, b, c) || Between (a, b, d) || Between (c, d, a) || Between (c, d, b))
            return true;
        return true;
    }
    public static int Area2(Vertex a, Vertex b, Vertex c)
    {
        return ((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y));
    }
    public static boolean Xor( boolean x, boolean y )
    {
        return x != y;
    }
}
