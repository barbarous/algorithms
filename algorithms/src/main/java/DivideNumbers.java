import java.util.ArrayList;
import java.util.List;
/*
    answer: [3, 8, 1, 6, 5, 4, 7, 2, 9]
    3 % 1 = 0
    38 % 2 = 0
    381 % 3 = 0
    ...
    381..9 % 9 = 0
 */
public class DivideNumbers
{
    static int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static int counter;

    public static void main(String[] args)
    {
        List accumulator = new ArrayList();
        combine(accumulator);
        System.out.println(counter);
    }

    static void combine(List<Integer> accumulator)
    {
        if(accumulator.size() >= numbers.length)
        {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < accumulator.size(); i++)
            {
                int result = Integer.valueOf(sb.append(accumulator.get(i)).toString());
                if (result % (i+1) == 0)
                {
                    if(i == accumulator.size()-1)
                    {
                        System.out.println(accumulator);
                        System.exit(0);
                    }
                }
                else
                {
                    return;
                }
            }
            counter++;
            return;
        }
        for(int i = 0; i < numbers.length; i++)
        {
            int nextNumber = numbers[i];
            if(!accumulator.contains(nextNumber))
            {
                accumulator.add(nextNumber);
                combine(accumulator);
                accumulator.remove(accumulator.size()-1);
            }
        }
    }
}
