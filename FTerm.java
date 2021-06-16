// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;


public  class FTerm extends Term {
    static private int cnt = 0;
    static public Symbol getSkolem() {
	cnt++;
	String func = "fz" + cnt;
	return Symbol.fetchSymbol(func); 
    }
    static public Term getSkolem(Vector vars) {
	Symbol sym = getSkolem();
	Term out = null;
	if ( 0 == vars.size() ) out = sym;
	else 
	    out = new FTerm(sym, vars);
	return  out;
    }

    protected Symbol function = null;
    protected Vector args = null; // elements are Symbol|Variable|FTerm
    protected int arity = 0;
    FTerm(Symbol function, Vector args) {
	super();
	this.function = function;
	this.args = args;
	HashSet variablesX = new HashSet();
	arity = args.size();
	for (int i = 0; i < arity; i++) {
	    Term ti = (Term) args.elementAt(i);
	    if ( ti instanceof Variable ) {
		variablesX.add(ti);
		continue;
	    }
	    HashSet vars = ti.getVariables();
	    if ( null != vars ) {
		for ( Iterator it = vars.iterator(); it.hasNext(); ) variablesX.add(it.next()); 
	    }
	}
	if ( 0 < variablesX.size() ) variables = variablesX;
    }
    public Symbol getFunction() { return function; }
    public Vector getArgs() { return args; }
    public boolean equals(Term t2) {
	if ( t2 == this ) return true;
	if ( ! (t2 instanceof FTerm) ) return false;
	FTerm t22 = (FTerm) t2;
	Symbol function2 = t22.getFunction();
	if ( !function.equals(function2) ) return false;
	int lng = ( null == args ? 0 : args.size() );
	if ( 0 == lng ) return true;
	Vector args2 = t22.getArgs();
	for (int i = 0; i < lng; i++ ) {
	    Term ti = (Term) args.elementAt(i);
	    Term ti2 = (Term) args2.elementAt(i);
	    if ( !ti.equals(ti2) ) return false;
	}
	return true;
    }
    public Formula subst(Variable var, Term term) { 
	if ( null == variables ) return this;
	if ( !variables.contains(var) ) return this;
	Vector newArgs = new Vector();
	for (int i = 0; i < arity; i++) {
	    Term ti = (Term) args.elementAt(i);
	    if ( ti instanceof Variable ) {
		newArgs.addElement((ti == var) ? term : ti);
		continue;
	    }
	    newArgs.addElement(ti.subst(var, term));
	}
	return new FTerm(function, newArgs);
    }
    private String htmlString = null;
    public String html() {
	if ( null == htmlString ) {
	    StringBuffer sb = new StringBuffer(function.getName());
	    sb.append("(");
	    for (int i = 0; i < arity; i++) {
		Term term = (Term) args.elementAt(i);
		String termString = term.html();
		if ( i+1 < arity ) {
		    sb.append(termString);
		    continue;
		}
		if ( !termString.endsWith(" ") ) {
		    sb.append(termString);
		    continue;
  		}
		sb.append(termString.substring(0, -1 + termString.length()));
	    }
	    sb.append(") ");
	    htmlString = sb.toString();
	}
	return htmlString;
    }
}
