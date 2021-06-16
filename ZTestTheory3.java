// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestTheory3 {

    public static void main (String [] arguments) {
	System.out.println("=========== ZTestTheory3 ==================");
	Parser parser = new Parser(false);

	String query = "(eq ?t &&(LaunchedPageAdvice(?t AngelRoot) <(?t ?variablexyz) ))";
	Formula out = null;
	try { out = parser.parse(query); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out ) {
	    System.out.println("ZTestTheory3 query out == null");
	    System.exit(1);
	}
	Existential queryFormula = (Existential) out;
	Variable tVariable = queryFormula.getVariable();
	Conjunction queryConjunction = (Conjunction) queryFormula.getBody();
	Vector queryConjuncts = queryConjunction.getConjuncts();
	Atom queryConjunct0 = (Atom) queryConjuncts.elementAt(0);
	Atom queryConjunct1 = (Atom) queryConjuncts.elementAt(1);


	Vector queryConjunct1Args = queryConjunct1.getArgs();
	Variable variablexyz = (Variable) queryConjunct1Args.elementAt(1);

	String triggerStr = "AtPageCounter(309 AngelRoot 6)";
	try { out = parser.parse(triggerStr); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out ) {
	    System.out.println("ZTestTheory3 triggerStr out == null");
	    System.exit(1);
	}
	Atom trigger = (Atom) out;
	Vector triggerArgs = trigger.getArgs();
	IntSymbol cnt = (IntSymbol) triggerArgs.elementAt(0);
	System.out.println("cnt: " + cnt.html());
	Existential queryFormulaX = (Existential) queryFormula.subst(variablexyz, cnt);
	System.out.println("queryFormulaX: " + queryFormulaX.html());

	Theory alertEvents = new Theory();

	ProofStep ps = alertEvents.prove(queryFormulaX, 5);
	Atom result = ps.getResult(); 
	System.out.println("result: " + result.html());
	System.out.println("----------------------------------------------");

	Atom newAssertion = (Atom) queryConjunct0.subst(tVariable, cnt);
	System.out.println("newAssertion: " + newAssertion.html());
	alertEvents.addAssertion(newAssertion);

	triggerStr = "AtPageCounter(409 AngelRoot 6)";
	try { out = parser.parse(triggerStr); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out ) {
	    System.out.println("ZTestTheory3 triggerStr out == null");
	    System.exit(1);
	}

	trigger = (Atom) out;
	triggerArgs = trigger.getArgs();
	cnt = (IntSymbol) triggerArgs.elementAt(0);
	System.out.println("cnt: " + cnt.html());
	queryFormulaX = (Existential) queryFormula.subst(variablexyz, cnt);
	System.out.println("queryFormulaX: " + queryFormulaX.html());

	ps = alertEvents.prove(queryFormulaX, 5);
	result = ps.getResult(); 
	System.out.println("result: " + result.html());



} // end of main

} // end class ZTestTheory3


