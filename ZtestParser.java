// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZtestParser {

    public static void main (String [] arguments) {

	Parser parser = new Parser(true);
	/*
	Formula out = null;
	try { out = parser.parse(

	//      "&&((eq ?x &&(P(?x) (uq ?x1 Q(?x ?x1)))) " + 
	//         "(eq ?y &&(P(?y) (uq ?y1 Q(?y ?y1)))))" ); 
		 "(uq ?u (uq ?v <->(Seteq(?u ?v) Seteq(?v ?u) )))");
	}
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out )
	    System.out.println("out == null");
	else {
	    System.out.println("out: " + out.html());
	    Formula out2 = Formula.insurer1(out);
	    System.out.println("out2: " + out2.html());
	}
	*/



	Formula st = null;
	System.out.println("------------------- st ---------------------");
	try { st = parser.parse(
				// "(uq ?u (uq ?v ||(~(Seteq(?u ?v)) Seteq(?v ?u)) ) )" );
				// "||( P(a) Q(a))");
				// "||( P(a) Q(a) R(a))");
				// "(uq ?x1 ||( P(?x1) (uq ?y1 ||(Q(?x1 ?y1) R(?x1 ?y1)))))" );
				"(uq ?x1 ||( P(?x1) (uq ?y1 ||(Q(?x1 ?y1) R(?x1 ?y1)))))" );
				// "(uq ?x1 ->(P(?x1) (eq ?y1 ->(Q(?y1) (uq ?z1 R(?x1 ?y1 ?z1))))))" );
	}
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}

	Formula sk = null;
	System.out.println("------------------- sk ---------------------");
	try { sk = parser.parse(
				// "(uq ?uu (uq ?vv  ||(Seteq(?uu ?vv) ~(Seteq(?vv ?uu)))))" );
				// "||( Q(a) P(a) )");
				// "||( R(a) Q(a) P(a) )");
				// "(uq ?x2 ||( P(?x2) (uq ?y2 ||(R(?x2 ?y2) Q(?x2 ?y2)))))" );
				"(uq ?x2 ||(  (uq ?y2 ||( R(?x2 ?y2) Q(?x2 ?y2) )) P(?x2)   ))" );
				// "(uq ?x2 ->(P(?x2) (eq ?y2 ->(Q(?y2) (uq ?z2 R(?x2 ?y2 ?z2))))))" );
	}
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	System.out.println("------------------- call instance ---------------------");

	/*
	Formula st2 = Formula.insurer(st);
	Formula sk2 = Formula.insurer(sk);
	boolean b = Formula.instance(st2, sk2);
	*/

	boolean b = Formula.instance(st, sk);
	System.out.println("b: " + b);


    } // end of main
    
} // end class ZtestParser


