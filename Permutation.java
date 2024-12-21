/* *****************************************************************************
 *  Name: Susan Tan
 *  Date: 07/21/2021
 *  Description: Permutation.java takes an integer k as a command-line argument;
 * reads a sequence of strings from standard input using StdIn.readString();
 * and prints exactly k of them, uniformly at random.
 * Print each item from the sequence at most once.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rqs = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rqs.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(rqs.dequeue());
        }

    }
}
