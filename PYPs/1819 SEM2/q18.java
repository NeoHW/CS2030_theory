import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;;

class Task extends RecursiveTask<BigInteger> {
    private final BigInteger x;
    private final BigInteger y;

    public Task(BigInteger x, BigInteger y) {
        this.x = x;
        this.y =y;
    }

    @Override
    protected BigInteger compute() {
        int n = x.max(y).toString().length();
        if (n < 2) {
            // base case: use built-in multiplication method
            return x.multiply(y);
        } else {
            // padding if necessary (both numbers must have even num of digits)
            if (n % 2 != 0) {
                n++;
            }
            String xs = x.toString();
            String ys = y.toString();
            xs = String.format("%0" + n + "d", xs);
            ys = String.format("%0" + n + "d", ys);
            BigInteger a = new BigInteger(xs.substring(0, n/2));
            BigInteger b = new BigInteger(xs.substring(n/2));
            BigInteger c = new BigInteger(ys.substring(0, n/2));
            BigInteger d = new BigInteger(ys.substring(n/2));

            // Karatsuba algorithm
            Task acTask = new Task(a, c);
            Task bdTask = new Task(b, d);
            Task abcdTask = new Task(a.add(b), c.add(d));
            acTask.fork();
            bdTask.fork();
            BigInteger ac = acTask.join();
            BigInteger bd = bdTask.join();
            BigInteger abcd = abcdTask.compute();
            BigInteger adbc = abcd.subtract(ac).subtract(bd);

            // combine the results
            BigInteger tenPowN = BigInteger.TEN.pow(n);
            BigInteger tenPowNby2 = BigInteger.TEN.pow(n/2);
            return ac.multiply(tenPowN)
                .add(adbc.multiply(tenPowNby2))
                .add(bd);
        }
    }

}