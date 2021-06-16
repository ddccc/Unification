// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class Existential extends Formula {
    protected Variable var;
    protected Formula body;
    Existential(Variable var, Formula body) {
	this.var = var;
	this.body = body;
	variables = (HashSet) body.getVariables().clone();
	variables.remove(var);
    }
    public Variable getVariable() { return var; }
    public Formula getBody() { return body; }
    public Formula subst(Variable varx, Term term) { 
	if ( !variables.contains(varx) ) return this;
	if ( var == varx ) return this; // !!!
	return new Existential(var, body.subst(varx, term));
    }
    private String htmlString = null;
    public String html() { 
	if ( null == htmlString ) {
	    Vector args = new Vector();
	    args.addElement(var);
	    args.addElement(body);
	    StringBuffer sb = new StringBuffer("(eq ");
	    htmlString = Conjunction.html(args, sb);
	}
	return htmlString;
    }

}





