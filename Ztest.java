// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class Ztest {

    public static void main (String [] arguments) {


	Parser parser = new Parser(true);
	Formula out = null;
	// try { out = parser.parse("&&(P(?z g(?z) ?x)  P(a ?y f(?y)) ) "); }
	// try { out = parser.parse("&&(P(?x g(?z) ?z)  P(f(?y) ?y a) ) "); }
	try { out = parser.parse("&&(P(?x ?x)  P(f(?y) ?y ) ) "); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out )
	    System.out.println("out == null");
	else {
	    System.out.println("out: " + out.html());

	    Conjunction con = (Conjunction) out;
	    Vector conjuncts = con.getConjuncts();
	    Atom leftTerm = (Atom) conjuncts.elementAt(0);
	    Atom rightTerm = (Atom) conjuncts.elementAt(1);

	    System.out.println("left: " + leftTerm.html());
	    System.out.println("right: " + rightTerm.html());

	    Vector u = leftTerm.unify(rightTerm);
	    if ( null == u ) 
		System.out.println("not unifiable");
	    else {
		System.out.println("unifier:");
		System.out.println(Substitution.html(u));
	    }

	}

} // end of main

} // end class Ztest


