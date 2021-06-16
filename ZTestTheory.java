// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestTheory {

    public static void main (String [] arguments) {
	System.out.println("=========== ZTestTheory ==================");
	Theory theory = new Theory();

	Parser parser = new Parser(false);
	Formula out = null;
	try { out = parser.parse(
		// "&&(P(a) ->(P(a) Q(b)) Q(b))"
		"&&(Human(socrates) (uq ?h ->(Human(?h) Mortal(?h))) Mortal(socrates) )"
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
	Atom pa = (Atom) conjuncts.elementAt(0);
	Formula imp = (Formula) conjuncts.elementAt(1);
	Atom qb = (Atom) conjuncts.elementAt(2);

	theory.addAssertion(pa);
	theory.addAxiom(imp);


	ProofStep ps = theory.prove(qb, 5);
	System.out.println("ps: " + ps.html());


} // end of main

} // end class ZTestTheory


