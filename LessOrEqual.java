// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class LessOrEqual extends BoolInt2 {
    // protected Symbol predicate;
    // protected Vector args;
    LessOrEqual(Vector args) {
	super(Symbol.lessOrEqual, args);
    }
    public Atom eval() { 
	if ( canEvaluate() ) return (
	       leftSymbol().getValue() <= rightSymbol().getValue() ?
	       Symbol.TRUE : Symbol.FALSE );
	else return Symbol.UNKNOWN;
    }

    public Formula subst(Variable var, Term term) { 
	if ( !variables.contains(var) ) return this;
	Vector newArgs = Less.subst(args, var, term);
	return new LessOrEqual(newArgs);
    }

}







