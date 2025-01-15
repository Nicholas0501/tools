package src;
import java.util.*;

public class DualQuaternion {
    
    Quaternion real;
    Quaternion dual;
    
    public DualQuaternion(Quaternion real, Quaternion dual)
    {
        this.real = real;
        this.dual = dual;
    }
    
    public DualQuaternion()
    {
        real = new Quaternion();
        dual = new Quaternion();
    }
    
    public static DualQuaternion add(DualQuaternion dq1, DualQuaternion dq2)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = Quaternion.add(dq1.real, dq2.real);
        result.dual = Quaternion.add(dq1.dual, dq2.dual);
        
        result.real.normalize();
        
        return result;
    }
    
    public static DualQuaternion sub(DualQuaternion dq1, DualQuaternion dq2)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = Quaternion.sub(dq1.real, dq2.real);
        result.dual = Quaternion.sub(dq1.dual, dq2.dual);
        
        result.real.normalize();
        
        return result;
    }
    
    public static DualQuaternion scale(DualQuaternion dq, double scalar)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = Quaternion.scale(dq.real, scalar);
        result.dual = Quaternion.scale(dq.dual, scalar);
        
        return result;
    }
    
    public static DualQuaternion addRot(DualQuaternion dq, Quaternion q)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = Quaternion.add(dq.real, q);
        result.dual = dq.dual;
        
        return result;
    }
    
    public static DualQuaternion multRot(DualQuaternion dq, Quaternion q)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = Quaternion.mult(dq.real, q);
        result.dual = dq.dual;
        
        return result;
    }
    
    public static DualQuaternion scaleRot(DualQuaternion dq, double scalar)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = Quaternion.scale(dq.real, scalar);
        result.dual = dq.dual;
        
        return result;
    }
    
    public static DualQuaternion addPos(DualQuaternion dq, Quaternion q)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = dq.real;
        result.dual = Quaternion.add(dq.dual, q);
        
        return result;
    }
    
    public static DualQuaternion scalePos(DualQuaternion dq, double scalar)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = dq.real;
        result.dual = Quaternion.scale(dq.dual, scalar);
        
        return result;
    }
    
    public static DualQuaternion mult(DualQuaternion dq1, DualQuaternion dq2)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = Quaternion.mult(dq1.real, dq2.real);
        result.dual = Quaternion.add(Quaternion.mult(dq1.real, dq2.dual), Quaternion.mult(dq2.real, dq2.dual));
        
        return result;
    }
    
    public static DualQuaternion conj(DualQuaternion dq)
    {
        DualQuaternion result = new DualQuaternion();
        
        result.real = Quaternion.conj(result.real);
        result.dual = Quaternion.scale(result.dual, -1.0);
        
        return result;
    }
    
    public static void nLerp(DualQuaternion start, DualQuaternion end, double t)
    {
        if (t > 1) 
        {
            System.out.println("Error out of bounds (0.0, 1.0)");
            return;
        }
        
        double tS = t;
        
        System.out.println(DualQuaternion.toVector3x2(start) + "\n");
        System.out.println(start);
        
        while (tS < 1.0)
        {
            Quaternion real = Quaternion.add(
                start.real,
                Quaternion.scale(
                    Quaternion.sub(end.real, start.real),
                    tS
                )
            );

            Quaternion dual = Quaternion.add(
                start.dual,
                Quaternion.scale(
                    Quaternion.sub(end.dual, start.dual),
                    tS
                )
            );
            
            DualQuaternion dq = new DualQuaternion(real, dual);
            
            System.out.println(dq);
            tS += t;
        }
        System.out.println(end + "\n");
        System.out.println(DualQuaternion.toVector3x2(end));
    }
    
    public static ArrayList<Vector3> toVector3x2(DualQuaternion dq)
    {
        ArrayList<Vector3> result = new ArrayList<Vector3>();
        
        result.add(Quaternion.toEuler(dq.real));
        result.add(new Vector3(dq.dual.i, dq.dual.j, dq.dual.k));
        
        return result;
    }
    
    public String toString()
    {
        return "DQ {r" + real + ", e" + dual + "}";
    }
}