import java.util.Scanner;
public class ArmstrongNumber{
    public boolean armstrong(int n)
    {
        int temp = n, rem = 0, sum = 0, digit = 0;
        while(temp>0)
        {
            temp/=10;
            digit++;
        }
        temp = n;
        while(temp>0)
        {
            rem = temp%10;
            sum += Math.pow(rem, digit);
            temp/=10;
        }
        if(n==sum)
            return true;
        return false;
    }
    public String armstrong(int start, int end)
    {
        String values = " ";
        for(int i = start; i<end; i++)
        {
            if(armstrong(i))
                values += (i+" ");
        }
        return values;
    }
    public static void main(String[] args)
    {
        ArmstrongNumber an = new ArmstrongNumber();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number: ");
        int n = s.nextInt();
        if(an.armstrong(n))
        {
            System.out.println(n+" is armstrong");
        }
        System.out.println("Enter start limit: ");
        int start = s.nextInt();
        System.out.println("Enter end limit: ");
        int end = s.nextInt();
        System.out.println(an.armstrong(start, end));

        s.close();
    }
}