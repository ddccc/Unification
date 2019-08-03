// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestCG {

    public static void main (String [] arguments) {
	System.out.println("------------------------------------------");
	CG cg = new CG();

	Parser parser = new Parser(false);
	Formula out = null;
	try { out = parser.parse(
	   "&&(||(P(a) Q(b)) ||(~(P(a)) Q(b)) " +
	      "||(P(a) ~(Q(b))) ||(~(P(a)) ~(Q(b)) )) " 
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

	// create clauses
	ClausedFormula cf = new ClausedFormula(con);
	System.out.println("cf: " + cf.html());

	// get the three different kind of clauses:
	Vector negClauses = cf.getNegativeClauses();
	Vector posClauses = cf.getPositiveClauses();
	Vector mixClauses = cf.getMixedClauses();

	// add the clauses to the cg:
	CGNegNode nn = cg.addNegClause( (NegativeClause) negClauses.elementAt(0), 0);
	CGPosNode pn = cg.addPosClause( (PositiveClause) posClauses.elementAt(0), 0);
	MixedClause mc0 = (MixedClause) mixClauses.elementAt(0);
	CGMixNode mn0 = cg.addMixClause( mc0, 0 );
	MixedClause mc1 = (MixedClause) mixClauses.elementAt(1);
	CGMixNode mn1 = cg.addMixClause( mc1, 0 );

	// show some nodes in the cg:
	System.out.println("nn: " + nn.html());
	System.out.println("mn1: " + mn1.html());

	// show the cg:
	System.out.println("cg: " + cg.html());

	// resolve some links:
	System.out.println("resolve: " + cg.resolve());
	System.out.println("resolve: " + cg.resolve());
	System.out.println("resolve: " + cg.resolve());
	System.out.println("resolve: " + cg.resolve());
	System.out.println("resolve: " + cg.resolve());



} // end of main

} // end class ZTestCG


