// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.Serializable;

// A substitution is a pair with the variable and a term.
// Is used to perform a substitution using subst in Formula.

public class Substitution implements Serializable {
    private Variable var;
    private Term term;
    Substitution (Variable var, Term term) {
	this.var = var; this.term = term;
    }
    public Variable getVariable() { return var; }
    public Term getTerm() { return term; }
    public void setTerm(Term term) { 
	html = null; 
	this.term = term; 
    }
    private String html = null;
    public String html() {
	if ( null == html ) html = var.html() + "-> " + term.html() + " ";
	return html;
    }
    static public String html(Vector subs) {
	// System.out.println("Substitution subs: " + subs.toString());
	StringBuffer sb = new StringBuffer();
	int lng = subs.size();
	for ( int i = 0; i < lng; i++ ) {
	    Substitution sub = (Substitution) subs.elementAt(i);
	    sb.append(sub.html() + "\n");
	}
	return sb.toString();
    }
}
