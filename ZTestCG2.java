// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestCG2 {

    public static void main (String [] arguments) {
	System.out.println("------------------------------------------");
	CG cg = new CG();

	Parser parser = new Parser(false);
	Formula out = null;
	try { out = parser.parse(
	   "&&((uq ?x ||(P(a ?x) P(?x a))) " +
	   "   (uq ?y ||(~(P(a ?y)) ~(P(?y a)))) )"
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
	ClausedFormula cf = new ClausedFormula(con);
	System.out.println("cf: " + cf.html());
	Vector negClauses = cf.getNegativeClauses();
	Vector posClauses = cf.getPositiveClauses();
	CGNegNode nn = cg.addNegClause( (NegativeClause) negClauses.elementAt(0), 0);
	CGPosNode pn = cg.addPosClause( (PositiveClause) posClauses.elementAt(0), 0);

	
	System.out.println("nn: " + nn.html());
	System.out.println("pn: " + pn.html());

	System.out.println("cg: " + cg.html());

	System.out.println("resolve: " + cg.resolve());
	System.out.println("resolve: " + cg.resolve());
	/*
	System.out.println("resolve: " + cg.resolve());
	System.out.println("resolve: " + cg.resolve());
	System.out.println("resolve: " + cg.resolve());
	*/

} // end of main

} // end class ZTestCG2


