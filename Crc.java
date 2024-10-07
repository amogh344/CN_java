import java.util.Scanner;

class Crc 
{
    public String crc(String dividend, String divisor) 
    {
        int shift;
        while ((shift = dividend.length() - divisor.length()) >= 0) 
        {

            dividend = Integer.toBinaryString(Integer.parseInt(dividend, 2) ^ (Integer.parseInt(divisor, 2) << shift));

            if (dividend.length() < 16) 
            {
                while (dividend.length() != 16) 
                {
                    dividend = "0" + dividend;
                }
            }

            System.out.println("Div=" + dividend);
        }

        return dividend;
    }

    public static void main(String[] args) 
    {
        int padding;
        String data, checksum, dividend, receivedData;
        String polynomial = "10001000000100001"; 

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the data to be encrypted:");
        data = s.next();
        dividend = data;
        padding = polynomial.length() - 1;

        for (int i = 0; i < padding; i++) {
            dividend += "0";
        }

        Crc obj = new Crc();
        checksum = obj.crc(dividend, polynomial);

        data = data + checksum;
        System.out.println("Sender Checksum = " + checksum);
        System.out.println("Code word transmitted over the network = " + data);

        System.out.println("Enter the received codeword:");
        receivedData = s.next();

        String syn = obj.crc(receivedData, polynomial);
        if (Long.parseLong(syn) == 0) 
        {
            System.out.println("No error in data transmission");
        } 
        else 
        {
            System.out.println("Error in data transmission");
        }

        s.close();
    }
}
