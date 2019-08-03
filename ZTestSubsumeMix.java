// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestSubsumeMix {

    public static void main (String [] arguments) {
	System.out.println("------------------------------------------");
	CG cg = new CG();

	Parser parser = new Parser(false);
	Formula out = null;
	Formula f1 = null;
	Formula f2 = null;
	try { 
	    out = parser.parse(
		   "(uq ?x ||(P(?x)  ~(Q(f(?x) a)) ) )"  );
	    f1 = out;
	    out = parser.parse(
		   "||(P(h(yb)) ~(Q(f(h(yb)) a))  P(zc)  )"  );
	    f2 = out;
	}
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out ) {
	    System.out.println("out == null");
	    System.exit(1);
	}
	System.out.println("f1: " + f1.html());
	System.out.println("f2: " + f2.html());

	ClausedFormula cf1 = new ClausedFormula(f1);
	System.out.println("cf1: " + cf1.html());
	ClausedFormula cf2 = new ClausedFormula(f2);
	System.out.println("cf2: " + cf2.html());

	Vector mixClauses1 = cf1.getMixedClauses();
	MixedClause mixClause1 = (MixedClause)mixClauses1.elementAt(0);
	Vector mixClauses2 = cf2.getMixedClauses();
	MixedClause mixClause2 = (MixedClause)mixClauses2.elementAt(0);

	Vector clauses = new Vector();
	Vector negAtoms1 = mixClause1.getNegativeAtoms();
	Vector posAtoms1 = mixClause1.getPositiveAtoms();
	PairVectors p = new PairVectors(negAtoms1, posAtoms1);
	clauses.addElement(p);

	boolean subsume = 
	    CGMixNode.subsumeResolve(1, clauses, 
				     mixClause2.getNegativeAtoms(), mixClause2.getPositiveAtoms());
	System.out.println("subsume: " + subsume);

	/*
	Conjunction con = (Conjunction) out;
	ClausedFormula cf = new ClausedFormula(con);



	Vector negClauses = cf.getNegativeClauses();
	Vector posClauses = cf.getPositiveClauses();
	Vector mixClauses = cf.getMixedClauses();

	CGNegNode nn0 = cg.addNegClause( (NegativeClause) negClauses.elementAt(0), 0);
	CGNegNode nn1 = cg.addNegClause( (NegativeClause) negClauses.elementAt(1), 0);
	CGPosNode pn = cg.addPosClause( (PositiveClause) posClauses.elementAt(0), 0);
	CGMixNode mn = cg.addMixClause( (MixedClause) mixClauses.elementAt(0), 0);

	
	System.out.println("nn0: " + nn0.html());
	System.out.println("nn1: " + nn1.html());
	System.out.println("pn: " + pn.html());
	System.out.println("mn: " + mn.html());

	System.out.println("cg: " + cg.html());

	System.out.println("resolve: " + cg.resolve());
	System.out.println("resolve: " + cg.resolve());
	*/

} // end of main

} // end class ZTestSubsumeMix


