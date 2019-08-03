// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class Symbol extends Term {
    static private Hashtable symbols = new Hashtable();
    static public void addSymbol(String name, Symbol symbol) {
	symbols.put(name, symbol);
    }
    static public Symbol getSymbol(String name) {
	return (Symbol) symbols.get(name);
    }
    static public Symbol fetchSymbol(String name) {
	Symbol out = getSymbol(name);
	if ( null == out ) {
	    out = new Symbol(name);
	    addSymbol(name, out);
	}
	return out;
    }

     static final public Symbol True = new Symbol("True");
     static final public Symbol False = new Symbol("False");
     static final public Symbol Unknown = new Symbol("Unknown");
     static final public Symbol dummy = new Symbol("dummy");
     static final public Symbol equal = new Symbol("==");
     static final public Symbol unequal = new Symbol("!=");
     static final public Symbol less = new Symbol("<");
     static final public Symbol greater = new Symbol(">");
     static final public Symbol lessOrEqual = new Symbol("<=");
     static final public Symbol greaterOrEqual = new Symbol(">=");
     static final public Atom TRUE = new Atom(True, null);
     static final public Atom FALSE = new Atom(False, null);
     static final public Atom UNKNOWN = new Atom(Unknown, null);


    protected String name = "";
    Symbol(String name) { 
	// super();
	this.name = name; 
    }
    public Formula subst(Variable var, Term term) { 
	return ( var.equals(this) ? term : this ); }
    public String getName() { return name; }
    public String html() { return name + " "; }
    public boolean equals(Term t2) {
	if (this == t2) return true;
	if ( !(t2 instanceof Symbol) ) return false;
	Symbol t22 = (Symbol) t2;
	return name.equals(t22.getName());
    }
}
