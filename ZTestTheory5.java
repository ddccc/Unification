// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestTheory5 {

    public static void main (String [] arguments) {
	System.out.println("=========== ZTestTheory5 ==================");
	Theory theory = new Theory();

	Parser parser = new Parser(false);
	// Parser parser = new Parser(true); // tracing
	Formula out = null;
	try { out = parser.parse(
		// "&&(P(a) ->(P(a) Q(b)) Q(b))"
		// "&&(Human(socrates) (uq ?h ->(Human(?h) Mortal(?h))) Mortal(socrates) )"

		"<->((<->((eq ?x1(uq ?y1 <->(P(?x1) P(?y1))))" +
		         "(<->((eq ?x2 Q(?x2)) (uq ?y2 P(?y2))))))" +
                    "(<->((eq ?x3(uq ?y3 <->(Q(?x3) Q(?y3))))" +
		         "(<->((eq ?x4 P(?x4)) (uq ?y4 Q(?y4)))))))" 
		); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out ) {
	    System.out.println("out == null");
	    System.exit(1);
	}

	System.out.println("out: " + out.html());
	// System.exit(1);

	ProofStep ps = theory.prove(out, 5);
	System.out.println("ps: " + ps.html());



} // end of main

} // end class ZTestTheory5


