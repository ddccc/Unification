// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class Variable extends Symbol {
    static private int cnt = 0;
    static public Variable gensym(Variable var) {
	String name = var.getName();
	int idx = name.indexOf('$');
	return ( idx < 0 ? gensym(name) : 
		 gensym(name.substring(0, idx)) );
    }
    static public Variable gensym(String name) {
	cnt++;
	name = name + "$" + cnt;
	return new Variable(name);
    }

    static public String variablesHtml(Vector variables) {
	int lng = variables.size();
	if ( lng <= 0 ) return "";
	StringBuffer sb = new StringBuffer("variables [");
	for ( int i = 0; i < lng; i++ ) {
	    Variable var = (Variable) variables.elementAt(i);
	    String termString = var.html() + "-> " + var.getValue().html();
	    if ( i + 1 < lng ) {
		sb.append(termString);
		continue;
	    }
	    if ( !termString.endsWith(" ") ) {
		sb.append(termString);
		continue;
	    }
	    sb.append(termString.substring(0, -1 + termString.length()));
	}
	sb.append("]");
	return sb.toString();
    }

    public Variable(String name) {
	super(name);
	/*
	variables = new HashSet();
	variables.add(this);
	*/
    }
    private Term value = null;
    public Term getValue() { return value; }
    public void setValue(Term term) { value = term; }

    public String html() { return "?" + super.html(); }
}
