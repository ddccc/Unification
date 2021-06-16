// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestBoolInt2 {

    public static void main (String [] arguments) {
	System.out.println("=========== ZTestBoolInt2 ==================");
	Theory theory = new Theory();

	Parser parser = new Parser(true);
	Formula out = null;
	try { out = parser.parse(
	 //			 "<(-33 +100 )"); }
	 //     "||(<(2 0) <(3 2) <(1 0) <(3 1) <(3 0) <(3 3) " +
	 //	" <(0 0) <(3 0) &&(<(0 3) <(3 2)) &&(<(0 0) <(0 1)))");}

	  "&&(<(2 a) ==(333 333))"); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out ) {
	    System.out.println("out == null");
	    System.exit(1);
	}
	System.out.println("out: " + out.html());

	ProofStep ps = theory.prove(out, 5);
	System.out.println("ps: " + ps.html());


} // end of main

} // end class ZTestBoolInt2


