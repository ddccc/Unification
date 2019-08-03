// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class Conjunction extends Formula {
    protected Vector conjuncts;
    Conjunction(Vector formulas) {
	conjuncts = formulas;
	variables = new HashSet();
	int lng = formulas.size();
	for (int i = 0; i < lng; i++ ) {
	    Formula ti = (Formula) formulas.elementAt(i);
	    HashSet vars = ti.getVariables();
	    for ( Iterator it = vars.iterator(); it.hasNext(); ) variables.add(it.next());
	}
    }
    public Vector getConjuncts() { return conjuncts; }
    public Formula subst(Variable var, Term term) { 
	// if ( null == variables ) return this;
	if ( !variables.contains(var) ) return this;
	Vector newArgs = new Vector();
	int arity = conjuncts.size();
	for (int i = 0; i < arity; i++) {
	    Formula ti = (Formula) conjuncts.elementAt(i);
	    HashSet tiVariables = ti.getVariables();
	    newArgs.addElement( ( tiVariables.contains(var) ?
				 ti.subst(var, term) : ti ) );
	}
	return new Conjunction(newArgs);
    }
    private String htmlString = null;
    public String html() { 
	if ( null == htmlString ) {
	    StringBuffer sb = new StringBuffer("&&(");
	    htmlString = html(conjuncts, sb);
	}
	return htmlString;
    }

    static public String html(Vector vec, StringBuffer sb) {
	int lng = vec.size();
	for (int i = 0; i < lng; i++) {
	    Formula formula = (Formula) vec.elementAt(i);
	    String formulaString = formula.html();
	    if ( i+1 < lng ) {
		sb.append(formulaString);
		continue;
	    }
	    if ( !formulaString.endsWith(" ") ) {
		sb.append(formulaString);
		continue;
	    }
	    sb.append(formulaString.substring(0, -1 + formulaString.length()));
	}
	sb.append(") ");
	return sb.toString();
    }

}





