// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class Disjunction extends Formula {
    protected Vector disjuncts;
    Disjunction(Vector formulas) {
	disjuncts = formulas;
	variables = new HashSet();
	int lng = formulas.size();
	for (int i = 0; i < lng; i++ ) {
	    Formula ti = (Formula) formulas.elementAt(i);
	    HashSet vars = ti.getVariables();
	    for ( Iterator it = vars.iterator(); it.hasNext(); ) variables.add(it.next());
	}
    }
    public Vector getDisjuncts() { return disjuncts; }
    public Formula subst(Variable var, Term term) { 
	// if ( null == variables ) return this;
	if ( !variables.contains(var) ) return this;
	Vector newArgs = new Vector();
	int arity = disjuncts.size();
	for (int i = 0; i < arity; i++) {
	    Formula ti = (Formula) disjuncts.elementAt(i);
	    HashSet tiVariables = ti.getVariables();
	    newArgs.addElement( ( tiVariables.contains(var) ?
				 ti.subst(var, term) : ti ) );
	}
	return new Disjunction(newArgs);
    }
    private String htmlString = null;
    public String html() { 
	if ( null == htmlString ) {
	    StringBuffer sb = new StringBuffer("||(");
	    htmlString = Conjunction.html(disjuncts, sb);
	}
	return htmlString;
    }

}





