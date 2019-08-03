// (C) OntoOO Inc 2005 Jan
package fol;

import java.util.*;
import java.io.*;

public class ZtestClauser {

public static void main (String [] arguments) {


	Parser parser = new Parser(false);
	Formula out = null;
	// try { out = parser.parse("<->(Seteq(?u g(?u)) Seteq(g(?u) ?u))"); }
	// try { out = parser.parse("(uq ?u <->(Seteq(?u g(?u)) Seteq(g(?u) ?u)))"); }
	// try { out = parser.parse("(eq ?v <->(Seteq(aa ?v) Seteq(?v aa) ))"); }
	// try { out = parser.parse("(uq ?u (eq ?v <->(Seteq(?u ?v) Seteq(?v ?u) )))"); }
	// try { out = parser.parse("(uq ?u (eq ?v ->(Seteq(?u ?v) Seteq(?v ?u) )))"); }
	// try { out = parser.parse("(eq ?u (uq ?v ->(Seteq(?u ?v) Seteq(?v ?u) )))"); }
	try { out = parser.parse("(uq ?u (uq ?v <->(Seteq(?u ?v) Seteq(?v ?u) )))"); }
	// try { out = parser.parse("(uq ?v &&(Seteq(aa ?v) ~(Seteq(?v bbbb))))"); }
	/*
	try { out = parser.parse(
		    "||(P(a) &&(Q(b) ||((uq ?x R(?x)) &&(~(S(s0)) T(ttt)))))" 
		    ); }
	*/
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out )
	    System.out.println("out == null");
	else {
	    System.out.println("out: " + out.html());
	    ClausedFormula cf = new ClausedFormula(out);
	}

} // end of main

} // end class ZtestClauser


