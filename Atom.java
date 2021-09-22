// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;

public class Atom extends Formula {
    static final private boolean trace = false;
    // static final private boolean trace = true;

    protected Symbol predicate;
    protected Vector args;
    Atom(Symbol head, Vector args) {
	predicate = head;
	this.args = args;
	variables = new HashSet();
	int lng = ( null == args ? 0 : args.size() );
	for (int i = 0; i < lng; i++ ) {
	    Term ti = (Term) args.elementAt(i);
	    if ( ti instanceof Variable ) {
		variables.add(ti);
		continue;
	    }
	    HashSet vars = ti.getVariables();
	    if ( null != vars ) {
		for ( Iterator it = vars.iterator(); it.hasNext(); ) 
		    variables.add(it.next());
	    }
	}
    }
    public Symbol getPredicate() { return predicate; }
    public Vector getArgs() { return args; }

    public Formula subst(Variable var, Term term) { 
	if ( !variables.contains(var) ) return this;
	Vector newArgs = Less.subst(args, var, term);
	return new Atom(predicate, newArgs);
    }
 
    private String htmlString = null;
    public String html() { 
	if ( null == htmlString ) {
	    String head = predicate.html();
	    head = head.substring(0, -1 + head.length());
	    StringBuffer sb = new StringBuffer(head + "(");
	    htmlString = html(args, sb);
	}
	return htmlString;
    }

    static public String html(Vector vec, StringBuffer sb) {
	if ( null != vec) {
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
	}
	sb.append(") ");
	return sb.toString();
    }

    public boolean equals(Atom a2) {
	if ( a2 == this ) return true;
	Symbol a2Predicate = a2.getPredicate();
	if ( !predicate.equals(a2Predicate) ) return false;
	int lng = ( null == args ? 0 : args.size() );
	if ( 0 == lng ) return true;
	Vector args2 = a2.getArgs();
	for (int i = 0; i < lng; i++ ) {
	    Term ti = (Term) args.elementAt(i);
	    Term ti2 = (Term) args2.elementAt(i);
	    if ( !ti.equals(ti2) ) return false;
	}
	return true;
    }

    public Atom eval() { return Symbol.UNKNOWN; }
    public boolean canEvaluate() { return false; }



    // unification algorithm

    private int cnt = 0;
    public Vector unify(Atom t2) {
	if (trace) { 
	    cnt = 0;
	    System.out.println("Unify in: t1: " + this.html() +
			       " t2: " + t2.html());
	}
	if ( predicate != t2.getPredicate() ) return null;
	Vector args1 = args;
	Vector args2 = t2.getArgs(); 
	Vector out = unifyList(0, args1, args2, new Vector());
	if ( null == out ) { 
	    if (trace) 
		System.out.println("Unify out: fail");
	    return out;
	}
	if (trace) 
	    System.out.println("Unify out: " + Variable.variablesHtml(out));
	return unifyExit(true, out);
    }
    public Vector unify2(Term t1, Term t2, Vector substitutions) { 
	if (trace) {
	    cnt++;
	    System.out.println(
		"2 cnt: " + cnt +
		" t1: " + t1.html() +
		" t2: " + t2.html() +
		" subs: " + Variable.variablesHtml(substitutions) );
	}
	if ( t1.equals(t2) ) return substitutions;

	if ( t1 instanceof Variable ) {
	    Variable var = (Variable) t1;
	    return unifyVariable(var, t2, substitutions);
	}
	if ( t2 instanceof Variable ) {
	    Variable var = (Variable) t2;
	    return unifyVariable(var, t1, substitutions);
	}
	
	if ( t1 instanceof Symbol ) return unifyExit(false, substitutions);
	if ( t2 instanceof Symbol ) return unifyExit(false, substitutions);

	if ( t1 instanceof FTerm && t2 instanceof FTerm ) {
	    FTerm f1 = (FTerm) t1; FTerm f2 = (FTerm) t2; 
	    Symbol func1 = f1.getFunction(); Symbol func2 = f2.getFunction(); 
	    if ( func1 != func2 ) return unifyExit(false, substitutions);
	    Vector args1 = f1.getArgs(); Vector args2 = f2.getArgs(); 
	    return unifyList(0, args1, args2, substitutions);
	}
	return unifyExit(false, substitutions);

    }
    private Vector unifyList(int n, Vector args1, Vector args2, Vector substitutions) {
	// 0 <=n < |args1| == |args2|
	int lng = args1.size();
	while ( n < lng ) {
	    Term t1 = (Term) args1.elementAt(n);
	    Term t2 = (Term) args2.elementAt(n);
	    if (trace) 
		System.out.println(
		      "L cnt: " + cnt +
		      " t1: " + t1.html() +
		      " t2: " + t2.html() +
		      " subs: " + Variable.variablesHtml(substitutions) );
	    Vector subs = unify2(t1, t2, substitutions);
	    if ( null == subs ) return unifyExit(false, null);
	    substitutions = subs;
	    n++;
	}
	return substitutions;
    }
    private Vector unifyVariable(Variable var, Term t2, Vector substitutions) {
	if (trace) 
	      System.out.println(
                    "V cnt: " + cnt +
		    " var: " + var.html() +
		    " t2: " + t2.html() +
		    " subs: " + Variable.variablesHtml(substitutions) );

	Term varValue = var.getValue();
	if ( null != varValue ) {
	    return unify2(t2, varValue, substitutions);
	}
	if ( t2 instanceof Variable ) {
	    Variable t22 = (Variable) t2;
	    varValue = t22.getValue();
	    if ( null != varValue )
		return unify2(var, varValue, substitutions);
	    var.setValue(t2);
	    substitutions.addElement(var);
	    return substitutions;
	}

	HashSet t2Variables = t2.getVariables();
	if ( contains(var, t2Variables) ) return unifyExit(false, substitutions);
	var.setValue(t2);
	substitutions.addElement(var);
	return substitutions;
    }

    private boolean contains(Variable var, HashSet t2Variables) {
	if ( null == t2Variables) return false;
	Variable var2;
	Term var2Value;
	HashSet var2ValueVariables;
	for ( Iterator it = t2Variables.iterator(); it.hasNext(); ) {
	    var2 = (Variable) it.next();
	    if ( var == var2 ) return true;
	    var2Value = var2.getValue();
	    if ( null != var2Value ) {
		var2ValueVariables = var2Value.getVariables();
		if ( contains(var, var2ValueVariables) ) return true;
	    }
	}		
	return false;
    }
    private Vector unifyExit(boolean ok, Vector variables) { 
	if (trace) System.out.println("unify " + (ok ? "succeeds" : "fails"));
	if ( null == variables ) return null; // cleaned up allready

	if ( trace && ok ) 
	    System.out.println("Variables : " + Variable.variablesHtml(variables));

	Vector out = ( ok ? new Vector() : null );
	int lng = variables.size();
	Variable var;
	for ( int i = 0; i < lng; i++ ) {
	    var = (Variable) variables.elementAt(i);
	    if ( ok ) insertSubstitution(out, var);
	    var.setValue(null);
	}
	if (trace && ok) {
	    lng = out.size();
	    StringBuffer sb = new StringBuffer("Substitutions: ");
	    for ( int i = 0; i < lng; i++ ) {
		Substitution sub = (Substitution) out.elementAt(i);
		sb.append(sub.html());
	    }
	    System.out.println(sb.toString());
	}
	return out;
    }
    private void insertSubstitution(Vector out, Variable var) {
	Term term = var.getValue();
	if (trace) 
	    System.out.println("--------## var: " + var.html() + 
			       "term: " + term.html());
	HashSet termVariables = term.getVariables();
	int lng = out.size();
	for ( int i = 0; i < lng; i++ ) {
	    Substitution sub = (Substitution) out.elementAt(i);
	    if (trace) System.out.println("sub: " + sub.html());
	    Variable vari = sub.getVariable();
	    if ( null != termVariables && termVariables.contains(vari) ) { 
		// replace in term vari variables 
		term = (Term) term.subst(vari, sub.getTerm());
		termVariables.remove(vari);
		continue;
	    }
	    if (trace) System.out.println("term: " + term.html());
	    Term term2 = sub.getTerm();
	    HashSet term2Variables = term2.getVariables();
	    if ( null != term2Variables && term2Variables.contains(var) )  {
		// replace in term2 var variables 
		sub.setTerm((Term)term2.subst(var, term));
	    }
	    if (trace) System.out.println();
	}
	Substitution subNew = new Substitution(var, term);
	if (trace) System.out.println("##: " + subNew.html());
	out.addElement(subNew);
    }

} // end Atom








