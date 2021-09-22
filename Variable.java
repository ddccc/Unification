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

    // additions for DC 2021 August
    //  String getName() is inherited from Symbol
    private Term first = null;
    public Term getFirst() { return first; }
    public void setFirst(Term t) { first = t; }
    public boolean isRoot() { return (null != first); }
    // if variables != null then first != null and contains a variable or more
    protected HashSet variables = null;

    private int size = 1;
    public int getSize() { return size; }
    public void setSize(int s) { size = s; }

    protected boolean isVroot = true; 
    protected Variable myVroot = null;

    protected boolean checking = false;
    protected boolean checked = false;

    private Term l2 = null; // for post processing
    public void setL2(Term lx) { l2 = lx; }
    public Term ready() { return l2; }

} // end Variable
