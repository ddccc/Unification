// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class Ztest2 {

public static void main (String [] arguments) {

	Parser parser = new Parser(true);
	Formula out = null;
	// try { out = parser.parse("&&(P(?x ?y) P(?y ?x)) "); }
	// try { out = parser.parse("&&(P(?x ?y) P(f(?y) ?x)) "); }
	// try { out = parser.parse("&&(P(?x ?y) P(f(?y) ?x)) "); }
	try { out = parser.parse("&&(P(?x ?y) P(?y ?x)) "); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null != out ) System.out.println("out: " + out.html());
	Conjunction con = (Conjunction) out;
	Vector conjuncts = con.getConjuncts();
	NegativeClause nc = new NegativeClause(conjuncts);
	System.out.println("nc: " + nc.html());

	Formula out2 = null;
	// try { out2 = parser.parse("&&(P(?x ?y) P(?y ?x)) "); }
	// try { out2 = parser.parse("&&(P(?x ?y) P(f(?y) ?x)) "); }
	// try { out2 = parser.parse("&&(P(?x ?y) P(f(?y) ?x)) "); }
	try { out2 = parser.parse("&&(P(a b) P(b a)) "); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null != out2 ) System.out.println("out2: " + out2.html());
	Conjunction con2 = (Conjunction) out2;
	Vector conjuncts2 = con2.getConjuncts();
	PositiveClause nc2 = new PositiveClause(conjuncts2);
	System.out.println("nc2: " + nc2.html());

	Vector rr = nc.resolvents(nc2, false);



} // end of main

} // end class Ztest2



