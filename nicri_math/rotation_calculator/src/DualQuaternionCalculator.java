package src;
import java.util.*;

public class DualQuaternionCalculator {
    
    int num = 0;
    Scanner input = new Scanner(System.in);
    
    public void calculator()
    {
        System.out.print("This calcualtor is a demonstration of the " + 
        "Quaternion and Dual Quaternion classes created as a foundations " + 
        "rotations and kinematics which may be used in various areas " + 
        "including animation, game development, simulation, etc." +
        "\n\nEuler rotations in this calculator are represented under " +
        "the following rules:\n\n\tTait-Bryan variants\n\tZYX rotation " +
        "order\n\tIntrinsic Rotation\n\tActive Rotation\n\tRight handed " +
        "coordinate system\n\nPress Enter to Continue:"
        );
        
        input.nextLine();
        
        
        while (true)
        {
            System.out.print("\033[H\033[2J");
            System.out.println(
            " _____   _    _    ___    ___" + "\n" + 
            "| ___ \\ | |  | |  / _ \\   | |" + "\n" +
            "| |_| | | |__| | / /_\\ \\  | |___" + "\n" +
            "|_____/  \\____/ /_/   \\_\\ |_____|" + "\n" +
            "\n" +
            " _____   _    _    ___    ________" + "\n" +
            "/ ___ \\ | |  | |  / _ \\   |__  __|  _______" + "\n" +
            "| |_| | | |__| | / /_\\ \\     ||     |_____|" + "\n" +
            "\\____,_\\ \\____/ /_/   \\_\\    ||" + "\n" +
            "\n" +
            ",----,  ____    ___  ___  ___  _____   ___  ___ " + "\n" +
            "| |__  | __ \\  |   \\ | |  | | / ___ \\ |   \\ | |" + "\n" +
            "| |__  |'--'/  | |\\ \\| |  | | | |_| | | |\\ \\| |  " + "\n" +
            "|____| |_| \\_\\ |_| \\___|  |_| \\_____/ |_| \\___|" + "\n" +
            "\n A rotation calculator by Nicholas Rivera \n\n" +
            " Vector3 = [x, y, z] \n Quaternion = Q[w, i, j, k] \n" +
            " Dual Quaternion = DQ{rQ, eQ} \n rQ = real quaternion \n eQ = dual quaternion \n" +
            "------------------------------------------------------------"
        );
            System.out.println("Please enter an int to choose a calculator (-1 to quit):");
            System.out.println("[1] Vector3 Calculator");
            System.out.println("[2] Quaternion Calculator");
            System.out.println("[3] Dual Quaternion Calculator");
            System.out.println();
            
            num = input.nextInt();
            System.out.println();
            
            if (num == -1) break;
            else if (num == 1) vectorCalculator();
            else if (num == 2) quaternionCalculator();
            else if (num == 3) dualQuaternionCalculator();
            else System.out.println("Please put a valid number");
            System.out.println();
        }
        System.out.print("\033[H\033[2J");
        System.out.println("Thank you for using my calculator\n");
        System.out.println("Sources/Credits:\n");
        System.out.println("https://danceswithcode.net/engineeringnotes/quaternions/quaternions.html");
        System.out.println("https://danceswithcode.net/engineeringnotes/rotations_in_3d/rotations_in_3d_part1.html");
        System.out.println("https://www.euclideanspace.com/maths/algebra/realNormedAlgebra/other/dualQuaternion/index.htm");
        System.out.println("https://arxiv.org/pdf/2303.13395");
        System.out.println("thttps://arxiv.org/pdf/2310.07623");
        System.out.println("thttps://cs.gmu.edu/~jmlien/teaching/cs451/uploads/Main/dual-quaternion.pdf");
        System.out.println("https://www.youtube.com/watch?v=htYh-Tq7ZBI");
        System.out.println("https://www.youtube.com/watch?v=en2QcehKJd8");
        System.out.println("https://www.youtube.com/watch?v=ichOiuBoBoQ&t=605s");
        System.out.println("https://www.youtube.com/watch?v=d4EgbgTm0Bg");
    }
    
    private void vectorCalculator()
    {
        Vector3 current = null;
        String msg = null;
        while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("-Vector Calculator-\n");
            System.out.println("-1 to quit\n");
            if (current != null) System.out.println("\n\t Current: " + current + "\n");
            if (msg != null) System.out.println("\n\t Ans: " + msg + "\n");
            msg = null;
            System.out.println("[1] Add");
            System.out.println("[2] Subtract");
            System.out.println("[3] Scale");
            System.out.println("[4] Dot");
            System.out.println("[5] Cross");
            System.out.println("[6] Hadamard");
            System.out.println("[7] Sine");
            System.out.println("[8] Cosine");
            System.out.println("[9] ToQuaternion");
            System.out.println("[10] Normalize");
            System.out.println("[11] Magnitude");
            System.out.println("[12] Average Velocity Across Points");
            System.out.println("[13] Clear");
            System.out.println();
            
            num = input.nextInt();
            
            if (num == -1) break;
            else if (num == 1)
            {
                if (current == null) current = promptVector3();
                Vector3 v = promptVector3();
                current = Vector3.add(current, v);
            }
            else if (num == 2) 
            {
                if (current == null) current = promptVector3();
                Vector3 v = promptVector3();
                current = Vector3.sub(current, v);
            }
            else if (num == 3)
            {
                if (current == null) current = promptVector3();
                System.out.println("Please enter a double value to scale by:");
                double scalar = input.nextDouble();
                current = Vector3.scale(current, scalar);
            }
            else if (num == 4) 
            {
                if (current == null) current = promptVector3();
                Vector3 v = promptVector3();
                msg = "" + Vector3.dot(current, v);
            }
            else if (num == 5)
            {
                if (current == null) current = promptVector3();
                Vector3 v = promptVector3();
                current = Vector3.cross(current, v);
            }
            else if (num == 6) 
            {
                if (current == null) current = promptVector3();
                Vector3 v = promptVector3();
                current = Vector3.hadamard(current, v);
            }
            else if (num == 7) 
            {
                if (current == null) current = promptVector3();
                Vector3 v = promptVector3();
                msg = "" + Vector3.sin(current, v);
            }
            else if (num == 8)
            {
                if (current == null) current = promptVector3();
                Vector3 v = promptVector3();
                msg = "" + Vector3.cos(current, v);
            }
            else if (num == 9)
            {
                if (current == null) current = promptVector3();
                msg = "" + current.toQuat();
            }
            else if (num == 10) 
            {
                if (current == null) current = promptVector3();
                current.normalize();
            }
            else if (num == 11) 
            {
                if (current == null) current = promptVector3();
                msg = "" + current.Mag();
            }
            else if (num == 12)
            {
                System.out.println("Please enter the desired format:\n\n\t[1][<Direction1> : Distance 1 : Speed 1, ...]\n\n");
                int num2 = input.nextInt();
                ArrayList<Vector3> vectors = new ArrayList<>();
                ArrayList<Double> distance = new ArrayList<>();
                ArrayList<Double> speed = new ArrayList<>();

                ArrayList<Vector3> displacement = new ArrayList<>();

                if (num2 == 1)
                {
                    int num3 = 1;
                    while(num3 == 1)
                    {
                        System.out.println("Please enter a direction: ");
                        vectors.add(promptVector3());
                        System.out.println("Please enter a distance: ");
                        distance.add(input.nextDouble());
                        System.out.println("Please enter a speed: ");
                        speed.add(input.nextDouble());

                        System.out.println("Add another point? Enter 1 to continue");
                        num3 = input.nextInt();
                    }
                    
                    for(int i = 0;i < vectors.size() ; i++)
                    {
                        //displacement = unit vector * distance
                        //time = distance / speed
                        vectors.get(i).normalize();
                        displacement.add(Vector3.scale(vectors.get(i), distance.get(i)));
                    }

                    

                }
                else if (num2 == 2)
                {

                }

            }
            else if (num == 13) current = null;
            else msg = "Please Enter A Valid Number";
        }
    }
    
    private void quaternionCalculator()
    {
        Quaternion current = null;
        String msg = null;
        while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("-Quaternion Calculator-\n");
            System.out.println("-1 to quit\n");
            
            if (current != null) System.out.println("\n\t Current: " + current + "\n");
            if (msg != null) System.out.println("\n\t Ans: " + msg + "\n");
            msg = null;
            
            System.out.println("[1] Add");
            System.out.println("[2] Subtract");
            System.out.println("[3] Scale");
            System.out.println("[4] Multiply");
            System.out.println("[5] Divide");
            System.out.println("[6] Conjugate");
            System.out.println("[7] Inverse");
            System.out.println("[8] Logarithm");
            System.out.println("[9] Normalize");
            System.out.println("[10] Magnitude");
            System.out.println("[11] To Euler");
            System.out.println("[12] Normalized Linear Interpolation");
            System.out.println("[13] Clear");
            
            num = input.nextInt(); 
            
            if (num == -1) break;
            else if (num == 1)
            {
                if (current == null) current = promptQuaternion();
                Quaternion q = promptQuaternion();
                current = Quaternion.add(current, q);
            }
            else if (num == 2)
            {
                if (current == null) current = promptQuaternion();
                Quaternion q = promptQuaternion();
                current = Quaternion.sub(current, q);
            }
            else if (num == 3)
            {
                if (current == null) current = promptQuaternion();
                System.out.println("Please enter a double value to scale by:");
                double scalar = input.nextDouble();
                current = Quaternion.scale(current, scalar);
            }
            else if (num == 4)
            {
                if (current == null) current = promptQuaternion();
                Quaternion q = promptQuaternion();
                current = Quaternion.mult(current, q);
            }
            else if (num == 5)
            {
                if (current == null) current = promptQuaternion();
                Quaternion q = promptQuaternion();
                current = Quaternion.div(current, q);
            }
            else if (num == 6)
            {
                if (current == null) current = promptQuaternion();
                current.conj();
            }
            else if (num == 7)
            {
                if (current == null) current = promptQuaternion();
                current.inverse();
            }
            else if (num == 8)
            {
                if (current == null) current = promptQuaternion();
                current = Quaternion.log(current);
            }
            else if (num == 9)
            {
                if (current == null) current = promptQuaternion();
                current.normalize();
            }
            else if (num == 10)
            {
                if (current == null) current = promptQuaternion();
                msg = "" + current.Mag();
            }
            else if (num == 11)
            {
                if (current == null) current = promptQuaternion();
                msg = "" + Quaternion.toEuler(current);
            }
            else if (num == 12)
            {
                if (current == null) current = promptQuaternion();
                Quaternion q = promptQuaternion();
                System.out.println("Please enter a double for t:");
                double t = input.nextDouble();
                Quaternion.nLerp(current, q, t);
                System.out.println("\n Enter to Continue");
                input.nextLine();
            }
            else if (num == 13) current = null;
            else msg = "Please Enter A Valid Number";
        }
    }
    
    private void dualQuaternionCalculator()
    {
        System.out.print("\033[H\033[2J");
        DualQuaternion current = null;
        String msg = null;
        
        while(true)
        {
            System.out.print("\033[H\033[2J");
            System.out.println("-Dual Quaternion Calculator-\n");
            System.out.println("-1 to quit\n");
            
            if (current != null) System.out.println("\n\t Current: " + current + "\n");
            if (msg != null) System.out.println("\n\t Ans: " + msg + "\n");
            msg = null;
            
            System.out.println("[1] Add");
            System.out.println("[2] Subtract");
            System.out.println("[3] Multiply");
            System.out.println("[4] Conjugate");
            System.out.println("[5] Multiply Rotation");
            System.out.println("[6] Add Rotation");
            System.out.println("[7] Add Position");
            System.out.println("[8] Scale Positon");
            System.out.println("[9] To Euler and Cartesian");
            System.out.println("[10] Normalized Linear Interpolation");
            System.out.println("[11] Clear");
            
            num = input.nextInt();
            
            if (num == -1) break;
            else if (num == 1)
            {
                if (current == null) current = promptDualQuaternion();
                DualQuaternion dq = promptDualQuaternion();
                current = DualQuaternion.add(current, dq);
                current.real.normalize();
            }
            else if (num == 2)
            {
                if (current == null) current = promptDualQuaternion();
                DualQuaternion dq = promptDualQuaternion();
                current = DualQuaternion.sub(current, dq);
                current.real.normalize();
            }
            else if (num == 3)
            {
                if (current == null) current = promptDualQuaternion();
                DualQuaternion dq = promptDualQuaternion();
                current = DualQuaternion.mult(current, dq);
                current.real.normalize();
            }
            else if (num == 4)
            {
                if (current == null) current = promptDualQuaternion();
                current = DualQuaternion.conj(current);
            }
            else if (num == 5)
            {
                if (current == null) current = promptDualQuaternion();
                Quaternion q = promptQuaternion();
                current = DualQuaternion.multRot(current, q);
            }
            else if (num == 6)
            {
                if (current == null) current = promptDualQuaternion();
                Quaternion q = promptQuaternion();
                current = DualQuaternion.addRot(current, q);
                current.real.normalize();
            }
            else if (num == 7)
            {
                if (current == null) current = promptDualQuaternion();
                Vector3 v = promptVector3();
                current = DualQuaternion.addPos(current, Quaternion.pureQuat(v));
            }
            else if (num == 8)
            {
                if (current == null) current = promptDualQuaternion();
                System.out.println("Please enter a double value to scale by:");
                double scalar = input.nextDouble();
                current = DualQuaternion.scalePos(current, scalar);
            }
            else if (num == 9)
            {
                if (current == null) current = promptDualQuaternion();
                msg = "" + DualQuaternion.toVector3x2(current);
            }
            else if (num == 10)
            {
                if (current == null) current = promptDualQuaternion();
                DualQuaternion dq = promptDualQuaternion();
                System.out.println("Please enter a time step double:");
                double t = input.nextDouble();
                DualQuaternion.nLerp(current, dq, t);
                System.out.println("Press Enter to Continue:");
                input.nextLine();
                input.nextLine();
                current = dq;
            }
            else if (num == 11) current = null;
            else msg = "Please Enter A Valid Number";
        }
    }
    
    private Vector3 promptVector3()
    {
        Vector3 result = new Vector3();
        
        System.out.println("\nPlease enter the corrosponding doubles:\n");
        System.out.print("x: ");
        result.x = input.nextDouble();
        System.out.print("y: ");
        result.y = input.nextDouble();
        System.out.print("z: ");
        result.z = input.nextDouble();
        System.out.println();
        
        return result;
    }
    
    private Quaternion promptQuaternion()
    {
        Quaternion result = new Quaternion();
        
        System.out.println("\t[1] Enter Euler Angles (Radians)");
        System.out.println("\t[2] Enter Euler Angles (Degrees)");
        System.out.println("\t[3] Enter Quaternion Values");
        
        num = 0;
        
        do
        {
            num = input.nextInt();
            
            if (num == 1)
            {
                Vector3 v = promptVector3();
                result = Quaternion.rotQuat(v);
            }
            else if (num == 2)
            {
                Vector3 v = promptVector3();
                v.x = Math.toRadians(v.x);
                v.y = Math.toRadians(v.y);
                v.z = Math.toRadians(v.z);
                result = Quaternion.rotQuat(v);
            }
            else if (num == 3)
            {
                System.out.println("\nPlease enter the corrosponding double:\n");
                System.out.println("w: ");
                result.w = input.nextDouble();
                System.out.println("i: ");
                result.i = input.nextDouble();
                System.out.println("j: ");
                result.j = input.nextDouble();
                System.out.println("k: ");
                result.k = input.nextDouble();
            }
            else System.out.println("Please put a valid number");
        }
        while (num != 1 && num != 2);
        System.out.println();
        
        return result;
    }
    
    private DualQuaternion promptDualQuaternion()
    {
        DualQuaternion dq = new DualQuaternion();
        
        dq.real = promptQuaternion();
        dq.dual = Quaternion.pureQuat(promptVector3());
        
        return dq;
    }
}