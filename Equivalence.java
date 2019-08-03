// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class Equivalence extends Formula {
    protected Vector args;
    Equivalence(Vector formulas) {
	args = formulas;
	variables = new HashSet();
	int lng = args.size();
	for (int i = 0; i < lng; i++ ) {
	    Formula ti = (Formula) args.elementAt(i);
	    HashSet vars = ti.getVariables();
	    for ( Iterator it = vars.iterator(); it.hasNext(); ) variables.add(it.next());
	}
    }
    public Vector getArgs() { return args; }
    public Formula getLeft() { return (Formula) args.elementAt(0); }
    public Formula getRight() { return (Formula) args.elementAt(1); }
    public Formula subst(Variable var, Term term) { 
	// if ( null == variables ) return this;
	if ( !variables.contains(var) ) return this;
	Vector newArgs = new Vector();
	int arity = args.size();
	for (int i = 0; i < arity; i++) {
	    Formula ti = (Formula) args.elementAt(i);
	    HashSet tiVariables = ti.getVariables();
	    newArgs.addElement( ( tiVariables.contains(var) ?
				 ti.subst(var, term) : ti ) );
	}
	return new Equivalence(newArgs);
    }
    private String htmlString = null;
    public String html() { 
	if ( null == htmlString ) {
	    StringBuffer sb = new StringBuffer("<->(");
	    htmlString = Conjunction.html(args, sb);
	}
	return htmlString;
    }

}





