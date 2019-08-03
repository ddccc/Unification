// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestTheory2 {

    public static void main (String [] arguments) {
	System.out.println("=========== ZTestTheory2 ==================");
	Theory theory = new Theory();

	Parser parser = new Parser(false);
	Formula out = null;
	try { out = parser.parse(
	      //"&&( (uq ?x ||(P(?x a) P(a ?x))) (uq ?y ||(~(P(?y a)) ~(P(a ?y)))) )" 
		"&&( (uq ?x ||(P(?x a) P(a ?x))) (eq ?y &&(P(?y a) P(a ?y)) ))" 
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
	Conjunction con = (Conjunction) out;

	Vector conjuncts = con.getConjuncts();
	Formula f0 = (Formula) conjuncts.elementAt(0);
	Formula f1 = (Formula) conjuncts.elementAt(1);

	theory.addAxiom(f0);


	ProofStep ps = theory.prove(f1, 5);
	System.out.println("ps: " + ps.html());


} // end of main

} // end class ZTestTheory2


