// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class Less extends BoolInt2 {
    // protected Symbol predicate;
    // protected Vector args;
    Less(Vector args) {
	super(Symbol.less, args);
    }
    public Atom eval() { 
	if ( canEvaluate() ) return (
	       leftSymbol().getValue() < rightSymbol().getValue() ?
	       Symbol.TRUE : Symbol.FALSE );
	else return Symbol.UNKNOWN;
    }
    public Formula subst(Variable var, Term term) { 
	if ( !variables.contains(var) ) return this;
	Vector newArgs = subst(args, var, term);
	return new Less(newArgs);
    }

    static public Vector subst(Vector args, Variable var, Term term) {
	Vector newArgs = new Vector();
	int arity = args.size();
	for (int i = 0; i < arity; i++) {
	    Term ti = (Term) args.elementAt(i);
	    if ( ti instanceof Variable ) {
		newArgs.addElement((ti == var) ? term : ti);
		continue;
	    }
	    newArgs.addElement(ti.subst(var, term));
	}
	return newArgs;
    }
}







