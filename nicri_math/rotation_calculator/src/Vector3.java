package src;
import java.util.*;

public class Vector3 {
    
    public double x;
    public double y;
    public double z;
    
    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3()
    {
        x = 0;
        y = 0;
        z = 0;
    }
    
    public double Mag()
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
    
    public void normalize()
    {
        double mag = this.Mag();
        x /= mag;
        y /= mag;
        z /= mag;
    }
    
    public static Vector3 scale(Vector3 v1, double scalar)
    {
        return new Vector3(v1.x * scalar, v1.y * scalar, v1.z * scalar);
    }

    public static Vector3 divide(Vector3 v1, double scalar)
    {
        return Vector3.scale(v1, 1 / scalar);
    }
    
    public static Vector3 add(Vector3 v1, Vector3 v2)
    {
        return new Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }
    
    public static Vector3 sub(Vector3 v1, Vector3 v2)
    {
        return new Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }
    
    public static double dot(Vector3 v1, Vector3 v2)
    {
        return (v1.Mag() * v2.Mag()) * Vector3.cos(v1, v2);
    }
    
    public static double dotCoord(Vector3 v1, Vector3 v2)
    {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }
    
    public static Vector3 cross(Vector3 v1, Vector3 v2)
    {
        return new Vector3(v1.y*v2.z-v1.z*v2.y, v1.z*v2.x-v1.x*v2.z, v1.x*v2.y-v1.y*v2.x);
    }
    
    public static Vector3 hadamard(Vector3 v1, Vector3 v2)
    {
        return new Vector3(v1.x * v2.x, v1.y * v2.y, v1.z * v2.z);
    }
    
    public static double cos(Vector3 v1, Vector3 v2)
    {
        return Vector3.dotCoord(v1, v2) / ((v1.Mag()) * (v2.Mag()));
    }
    
    public static double cosSq2(Vector3 v1, Vector3 v2)
    {
        return  0.5 * (1.0 + Vector3.cos(v1, v2));
    }   
    
    public static double sin(Vector3 v1, Vector3 v2)
    {
        return Math.sqrt(1.0 - Vector3.cosSq2(v1, v2));
    }
    
    public static double sinSq2(Vector3 v1, Vector3 v2)
    {
        return 0.5 * (1.0 + Vector3.cos(v1, v2));
    }
    
    public Quaternion toQuat()
    {
        double yaw = x;
        double pitch = y;
        double roll = z;
        
        double cx = Math.cos(roll * 0.5);
        double cy = Math.cos(pitch * 0.5);
        double cz = Math.cos(yaw * 0.5);
        double sx = Math.sin(roll * 0.5);
        double sy = Math.sin(pitch * 0.5);
        double sz = Math.sin(yaw * 0.5);
        
        Quaternion q = new Quaternion();
        
        q.w = cx * cy * cz + sx * sy * sz;
        q.i = sx * cy * cz - cx * sy * sz;
        q.j = cx * sy * cz + sx * cy * sz;
        q.k = cx * cy * sz - sx * sy * cz;
        
        return q;
    }
    
    public String toString()
    {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}
