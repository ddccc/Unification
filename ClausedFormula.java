// (C) OntoOO Inc 2005 Jan
package fol;

import java.util.*;
import java.io.Serializable;

public class ClausedFormula implements Serializable {
    static public boolean traceb = CG.traceb;

    protected Formula formula;
    protected Vector negativeClauses = new Vector();
    protected Vector positiveClauses = new Vector();
    protected Vector mixedClauses = new Vector();
    public ClausedFormula(Formula formula) {
	this.formula = formula;
	if ( traceb)
	    System.out.println("formula: " + formula.html());
	Formula miniscope = Formula.insurer(formula);
	if ( traceb)
	    System.out.println("miniscope: " + miniscope.html());

	Formula skolemized = Formula.skolemize(miniscope);
	if ( traceb)
	    System.out.println("skolemized: " + skolemized.html());

	// remove uq's ....
	Formula stripped = removeUQs(skolemized);
	if ( traceb)
	    System.out.println("stripped: " + stripped.html());
	addClauses(stripped,
		   negativeClauses, positiveClauses, mixedClauses);
	if ( traceb)
	    System.out.println(html());

    }
    public Formula getFormula() { return formula; }
    public Vector getNegativeClauses() { return negativeClauses; }
    public Vector getPositiveClauses() { return positiveClauses; }
    public Vector getMixedClauses() { return mixedClauses; }

    private Formula removeUQs(Formula skolemized) { 
	if ( skolemized instanceof Atom ) return skolemized;
	if ( skolemized instanceof Negation ) return skolemized;
	if ( skolemized instanceof Conjunction ) {
	    Conjunction con = (Conjunction) skolemized;
	    Vector elems = con.getConjuncts();
	    return new Conjunction(removeUQsElems(elems));
	}
	if ( skolemized instanceof Disjunction ) {
	    Disjunction con = (Disjunction) skolemized;
	    Vector elems = con.getDisjuncts();
	    return new Disjunction(removeUQsElems(elems));
	}
	if ( skolemized instanceof Universal ) {
	    Universal uni = (Universal) skolemized;
	    return removeUQs(uni.getBody());
	}
	if ( traceb)
	    System.out.println("## removeUQs unknown type: " + skolemized.html());
	return null;
    }
    private Vector removeUQsElems(Vector elems) {
	Vector out = new Vector();
	int lng = elems.size();
	for (int i = 0; i < lng; i++ ) {
	    Formula ti = (Formula) elems.elementAt(i);
	    out.addElement(removeUQs(ti));
	}
	return out;
    }
    private void addClauses(Formula skolemized,
			    Vector negativeClauses,
			    Vector positiveClauses,
			    Vector mixedClauses) {
	if ( traceb)
	    System.out.println("addClauses: " + skolemized.html());
	if ( skolemized instanceof Atom ) {
	    Vector vec = new Vector();
	    vec.addElement(skolemized);
	    positiveClauses.addElement(new PositiveClause(vec));
	    return;
	}
	if ( skolemized instanceof Negation ) {
	    Negation neg = (Negation) skolemized;
	    Vector vec = new Vector();
	    vec.addElement(neg.getFormula());  // atom
	    negativeClauses.addElement(new NegativeClause(vec));
	    return;
	}
	if ( skolemized instanceof Conjunction ) {
	    Conjunction con = (Conjunction) skolemized;
	    Vector conjuncts = con.getConjuncts();
	    int lng = conjuncts.size();
	    for ( int i = 0; i < lng; i++ ) {
		Formula conI = (Formula) conjuncts.elementAt(i);
		addClauses(conI, 
			   negativeClauses, positiveClauses, mixedClauses);
	    }
	    return;
	}
	if ( skolemized instanceof Disjunction ) {
	    Disjunction dis = (Disjunction) skolemized;
	    Vector disjuncts = dis.getDisjuncts();
	    int lng = disjuncts.size();
	    boolean change = false;
	    Vector disjuncts2 = new Vector();
	    for ( int i = 0; i < lng; i++ ) {
		Formula disI = (Formula) disjuncts.elementAt(i);
		if ( disI instanceof Disjunction ) {
		    change = true;
		    Disjunction disId = (Disjunction) disI; 
		    Vector disIdVec = disId.getDisjuncts();
		    int lng2 = disIdVec.size();
		    for ( int j = 0; j < lng2; j++ ) {
			disjuncts2.addElement(disIdVec.elementAt(j));
		    }
		} else
		    disjuncts2.addElement(disI);
	    }
	    if ( change ) {
		addClauses(new Disjunction(disjuncts2),
			   negativeClauses, positiveClauses, mixedClauses);
		return;
	    }
	    // check for conjuncts
	    for ( int i = 0; i < lng; i++ ) {
		Formula disI = (Formula) disjuncts.elementAt(i);
		if ( disI instanceof Conjunction ) {
		    // distribute
		    Conjunction con = (Conjunction) disI; 
		    Vector conjuncts = con.getConjuncts();
		    int lng2 = conjuncts.size();
		    // make the new disjuncts and launch them
		    for ( int j = 0; j < lng2; j++ ) {
			Formula cJ = (Formula) conjuncts.elementAt(j);
			Vector dis2 = new Vector();
			dis2.addElement(cJ);
			for ( int k = 0; k < lng; k++ ) {
			    if ( k == i ) continue; // skip this one
			    Formula disK = (Formula) disjuncts.elementAt(k);
			    dis2.addElement(disK);
			}
			addClauses(new Disjunction(dis2),
				   negativeClauses, positiveClauses, mixedClauses);
		    }
		    return;
		}
	    }
	    // find the negs and the pos atoms in disjuncts
	    Vector posAtoms = new Vector();
	    Vector negAtoms = new Vector();
	    for ( int i = 0; i < lng; i++ ) {
		Formula disI = (Formula) disjuncts.elementAt(i);
		if ( disI instanceof Atom ) { posAtoms.addElement(disI); continue; }
		if ( disI instanceof Negation ) { 
		    Negation neg = (Negation) disI;
		    negAtoms.addElement(neg.getFormula());
		    continue; 
		}
		// can not happen/ ignore anything else
		System.out.println("** Error in addClauses: " + skolemized.html());
	    }
	    int lngPos = posAtoms.size();
	    int lngNeg = negAtoms.size();
	    if ( 0 == lngPos && 0 == lngNeg ) {
		// can not happen
		System.out.println("** Error in addClauses: " + skolemized.html());
		return;
	    }
	    if ( 0 == lngNeg ) {
		positiveClauses.addElement(new PositiveClause(posAtoms));
		return;
	    }
	    if ( 0 == lngPos ) {
		negativeClauses.addElement(new NegativeClause(negAtoms));
		return;
	    }
	    mixedClauses.addElement(new MixedClause(negAtoms, posAtoms));
	}
	if ( traceb)
	    System.out.println("%%%: " + skolemized.html());
    }

    private String html(String prefix, Vector clauses) {
	StringBuffer sb = new StringBuffer(prefix + ":\n");
	int lng = clauses.size();
	for ( int i = 0; i < lng; i++ ) {
	    Clause clause = (Clause) clauses.elementAt(i);
	    sb.append("Clause: " + clause.html() + "\n");
	}
	return sb.toString();
    }
    public String html() {
	StringBuffer sb = new StringBuffer("ClausedFormula:\n");
	sb.append("Formula: " + formula.html() + "\n");
	if ( 0 < positiveClauses.size() )
	    sb.append(html("PositiveClauses", positiveClauses));
	if ( 0 < negativeClauses.size() )
	    sb.append(html("NegativeClauses", negativeClauses));
	if ( 0 < mixedClauses.size() )
	    sb.append(html("MixedClauses", mixedClauses));
	return sb.toString();
    }
}
