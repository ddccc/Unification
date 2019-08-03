// (C) OntoOO Inc 2017 Feb
package fol;

import java.util.*;
import java.io.*;

public class ZTestTheory4 {

    public static void main (String [] arguments) {
	System.out.println("=========== ZTestTheory4 ==================");
	Theory theory = new Theory();

	Parser parser = new Parser(false);
	Formula out = null;
	try { out = parser.parse(
	     "(uq ?u (uq ?v <->(Seteq(?u ?v) Seteq(?v ?u) )))"
	     //   "&&(P(a) ~(P(a)))"
	     //   "||(P(a) ~(P(a)))"
	     // "==(0 0)"
	     // "==(0 1)"
	     // "||(P(a) P(b))" // -> def == null
	     // "Seteq(a b)"
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
	Formula conjecture = out;
	// /*
	try { out = parser.parse(
	  "(uq ?s (uq ?t <->(Seteq(?s ?t) " +
	                   "(uq ?x <->(Inset(?x ?s) Inset(?x ?t) )) )))" 
				 ); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	System.out.println("out: " + out.html());
	Universal definition = (Universal)out;

	theory.addDefinition(definition);
	// */

	ProofStep ps = theory.prove(conjecture, 5);
	System.out.println("ps: " + ps.html());


} // end of main

} // end class ZTestTheory4


