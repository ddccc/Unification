// (C) OntoOO Inc 2005 Jan
package fol;

import java.util.*;
import java.io.Serializable;

abstract public class Clause implements Serializable {

    static private Clause makeClause(Atom atom) { 
	Vector vec = new Vector();
	vec.addElement(atom);
	return new PositiveClause(vec);
    }
    static public Clause TRUEclause = makeClause(Symbol.TRUE);
    static public Clause FALSEclause = makeClause(Symbol.FALSE);

    protected HashSet variables = new HashSet();
    protected Vector negativeAtoms = null;
    protected Vector positiveAtoms = null;

    //  public Clause() {}

    abstract public String html();

    public int getNumberOfVariables() { return variables.size(); }
    public HashSet getVariables() { return variables; }

    public Vector getNegativeAtoms() { return negativeAtoms; }
    public Vector getPositiveAtoms() { return positiveAtoms; }

    public Clause subst(Variable var, Term term) {
	Vector newNegAtoms = subst(negativeAtoms, var, term);
	Vector newPosAtoms = subst(positiveAtoms, var, term);
	if ( null == newNegAtoms ) return  new PositiveClause(newPosAtoms);
	if ( null == newPosAtoms ) return  new NegativeClause(newNegAtoms);
	return new MixedClause(newNegAtoms, newPosAtoms);
    }
    static public Vector subst(Vector vecAtoms, Variable var, Term term) {
	if ( null == vecAtoms ) return null;
	Vector out = new Vector();
	int lng = vecAtoms.size();
	for ( int i = 0; i < lng; i++ ) {
	    Atom atomI = (Atom) vecAtoms.elementAt(i);
	    out.addElement(atomI.subst(var, term));
	}
	return out;
    }
    static public Vector sublis(Vector vecAtoms, Vector substitutions) {
	int lng = substitutions.size();
	for ( int i = 0; i < lng; i++ ) {
	    Substitution subs = (Substitution) substitutions.elementAt(i);
	    Variable var = subs.getVariable();
	    Term term = subs.getTerm();
	    vecAtoms = subst(vecAtoms, var, term);
	}
	return vecAtoms;
    }

    public boolean isTrue() { return ( this == TRUEclause ); }
    public boolean isFalse() { return ( this == FALSEclause ); }

}
