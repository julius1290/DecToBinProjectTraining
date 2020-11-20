package main;

import javax.swing.*;

public class DecToBin {
    public static void main(String[] args) {
        String inputText = JOptionPane.showInputDialog("Zahl eingeben!");
        final long inputValue = Long.parseLong(inputText);

        System.out.print("Die groesste 1-Bit-Gruppe von " + inputValue + " (Binaer:");
        getBinaryString(inputValue);
        System.out.print(") hat "+ getActiveBitGroupSize(inputValue) + " Bit(s)\n\n");
    }

    private static void getBinaryString(long inputValue) // 0000.0110
    {
        long bitComparer = 1;       // 0000.0001

        if(inputValue == 0) {
            System.out.print("0");
            return;
        }

        //Increase bitComparer while bitComparer >= input
        while(bitComparer <= inputValue) {
            bitComparer <<= 1;
        }

        //Half bitComparer because bit length will be greater than input
        bitComparer >>= 1;

        while(bitComparer != 0) //100
        {
            //Compare the Bits of Both Numbers if result > 0 print 1
            if((inputValue & bitComparer) != 0) // 100 & 110 = 100 --> 4 | 010 & 110 = 010 --> 2 |
                System.out.print("1");
            else
                System.out.print("0");
            //Shift the Bit of bitComparer right
            bitComparer>>=1;
        }
    }



    static int getActiveBitGroupSize(long inputValue)
    {                          //0000.0101
        long bitComparer = 1;  //0000.0010
        int maxBitGroupSize = 0;
        int actualBitGroupSize = 0;

        while(bitComparer <= inputValue) {
            //Compare if the current Bit is active
            if((inputValue & bitComparer) != 0) {
                //Increase count of active bits
                actualBitGroupSize++;
            }
            else {
                //Compare if current active group is greater then max active Group size
                if(actualBitGroupSize > maxBitGroupSize) {
                    maxBitGroupSize = actualBitGroupSize;
                }
                //reset current active group size
                actualBitGroupSize = 0;
            }
            bitComparer <<= 1;
        }

        //Compare, this is needed because last compared bit can be active
        if(actualBitGroupSize > maxBitGroupSize)
            maxBitGroupSize = actualBitGroupSize;

        return(maxBitGroupSize);
    }
}
