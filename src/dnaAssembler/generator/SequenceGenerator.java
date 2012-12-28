/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnaAssembler.generator;

/**
 * 
 * @author Jacek
 */
public class SequenceGenerator {
	public static final String A = "A";
	public static final String C = "C";
	public static final String G = "G";
	public static final String T = "T";
	public static final double INTERVAL_A = 0.75;
	public static final double INTERVAL_C = 0.5;
	public static final double INTERVAL_G = 0.25;
	public static final double INTERVAL_T = 0.0;

	public static String generateSequence(final int n) {
		final StringBuilder sequence = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			final double random = Math.random();
			if (random > INTERVAL_A) {
				sequence.append(A);
			} else if (random > INTERVAL_C) {
				sequence.append(C);
			} else if (random > INTERVAL_G) {
				sequence.append(G);
			} else {
				sequence.append(T);
			}
		}

		return sequence.toString();
	}
}
