import java.util.*;
import java.util.stream.*;

class q17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int j = sc.nextInt();
        IntStream
            // 0 -> length of largest num (e.g. 2089 length is 4)
            .rangeClosed(1, Integer.toString(Math.max(i,j)).length()) // 1 to 4
            .reduce(0, (carry, idx) -> {
                int digit1 = i % 10;
                int digit2 = j % 10;
                i /= 10;
                j /= 10;

                // logic
                int sum = digit1 + digit2 + carry;
                int result = sum % 10;
                int carryNew = sum / 10;
                
                if(carry == 0) {
                    if(carryNew == 0) {
                        System.out.printf("%d + %d = %d%n",
                            digit1, digit2, result);
                    } else {
                        System.out.printf("%d + %d = %d carry %d%n",
                            digit1, digit2, result, carryNew);
                    }
                } else {
                    if(carryNew == 0) {
                        System.out.printf("%d + %d + (%d) = %d%n",
                            digit1, digit2, carry, result);
                    } else {
                        System.out.printf("%d + %d + (%d) = %d carry %d%n",
                            digit1, digit2, carry, result, carryNew);
                    }
                }
                return carryNew;
            });
    }
}