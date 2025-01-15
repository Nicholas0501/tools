package src;
import java.util.*;

public class Quaternion {
    
    public double i, j, k, w;
    
    
    /*
            1   i   j   k
            
        1   1   i   j   k
                
        i   i   -1  k   -j
                
        j   j   -k  -1  i
                
        k   k   j   -i  -1
    */
    
    public Quaternion(double w, double i, double j, double k)
    {
        this.i = i;
        this.j = j;
        this.k = k;
        this.w = w;
    }
    
    public Quaternion()
    {
        this.i = 0.0;
        this.j = 0.0;
        this.k = 0.0;
        this.w = 1.0;
    }
    
    //Demonstrational function
    public static void nLerp(Quaternion start, Quaternion end, double t)
    {
        if (t > 1) 
        {
            System.out.println("Error out of bounds (0.0, 1.0)");
            return;
        }
        
        double tS = t;
        
        while (tS < 1.0)
        {
            Quaternion q = Quaternion.add(
                start,
                Quaternion.scale(
                    Quaternion.sub(end, start),
                    tS
                )
            );
            
            q.normalize();
            System.out.println(q);
            
            tS += t;
        }
        System.out.println(end);
    }
    
    public static Quaternion pureQuat(Vector3 v)
    {
        return new Quaternion(0, v.x, v.y, v.z);
    }
    
    public static Quaternion pureQuat(double i, double j, double k)
    {
        return new Quaternion(0, i, j, k);
    }
    
    public static Quaternion rotQuat(Vector3 v)
    {
        double yaw = v.x;
        double pitch = v.y;
        double roll = v.z;
        
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
    
    public static Vector3 toEuler(Quaternion q)
    {
        q.normalize();
        
        double x = q.i;
        double y = q.j;
        double z = q.k;
        double w = q.w;
        
        double t0 = 2.0 * (w * x + y * z);
        double t1 = 1.0 - 2.0 * (x * x + y * y);
        double roll = Math.atan2(t0, t1);
        double t2 = 2.0 * (w * y - z * x);
        t2 = t2 > 1.0 ? 1.0 : t2;
        t2 = t2 < -1.0 ? -1.0 : t2;
        double pitch = Math.asin(t2);
        double t3 = 2.0 * (w * z + x * y);
        double t4 = 1.0 - 2.0 * (y * y + z * z);
        double yaw = Math.atan2(t3, t4);
        
        Vector3 result = new Vector3(yaw, pitch, roll);
        return result;
    }
    
    public static Quaternion add(Quaternion q1, Quaternion q2)
    {
        return new Quaternion(q1.w + q2.w, q1.i + q2.i, q1.j + q2.j, q1.k + q2.k);
    }
    
    public static Quaternion sub(Quaternion q1, Quaternion q2)
    {
        q2 = Quaternion.scale(q2, -1);
        return Quaternion.add(q1, q2);
    }
    
    public static Quaternion scale(Quaternion q, double scalar)
    {
        return new Quaternion(q.w * scalar, q.i * scalar, q.j * scalar, q.k * scalar);
    }
    
    public static Quaternion mult(Quaternion q1, Quaternion q2)
    {
        Vector3 v1 = new Vector3(q1.i, q1.j, q1.k);
        Vector3 v2 = new Vector3(q2.i, q2.j, q2.k);
        double real = q1.w*q2.w - Vector3.dot(v1, v2);
        Vector3 imag = Vector3.add(Vector3.add(Vector3.scale(v2, q1.w), Vector3.scale(v1, q2.w)), Vector3.cross(v1, v2));
        return new Quaternion(real, imag.x, imag.y, imag.x);
    }
    
    public static Quaternion div(Quaternion q1, Quaternion q2)
    {
        return Quaternion.mult(q1, Quaternion.inverse(q2));
    }
    
    public static Quaternion log(Quaternion q)
    {
        Quaternion result = new Quaternion();
        Vector3 imag = new Vector3(q.i, q.j, q.k);
        
        double scalar = (1 / imag.Mag()) * Math.acos(q.w / q.Mag());
        
        result.w = Math.log(q.Mag());
        result.i = q.i * scalar;
        result.j = q.j * scalar;
        result.k = q.k * scalar;
        
        return result;
    }
    
    public static Quaternion normalize(Quaternion q)
    {
        double mag = q.Mag();
        return new Quaternion(q.w / mag, q.i / mag, q.j / mag, q.k / mag);
    }
    
    public static Quaternion conj(Quaternion q)
    {
        return new Quaternion(q.w, q.i * -1, q.j * -1, q.k * -1);
    }
    
    public static Quaternion inverse(Quaternion q)
    {
        double scalar = 1.0 / q.Mag();
        return Quaternion.scale(Quaternion.conj(q), scalar); 
    }
    
    public void conj()
    {
        i *= -1;
        j *= -1;
        k *= -1;
    }
    
    public void inverse()
    {
        double mag = Mag();
        i = (i * -1) / mag;
        j = (j * -1) / mag;
        k = (k * -1) / mag;
    }
    
    public double Mag()
    {
        return Math.sqrt(w * w + i * i + j * j + k * k);
    }
    
    public void normalize()
    {
        double mag = Mag();
        w /= mag;
        i /= mag;
        j /= mag;
        k /= mag;
    }
    
    public String toString()
    {
        return "Q[" + w + ", " + i + ", " + j + ", " + k + "]";
    }
}
