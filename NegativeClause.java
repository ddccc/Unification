// (C) OntoOO Inc 2005 Jan
package fol;

import java.util.*;

public class NegativeClause extends Clause {
    private Vector factors = new Vector();
    public Vector getFactors() { return factors; }

    public NegativeClause(Vector negativeAtomsIn) {
	negativeAtoms = negativeAtomsIn; 
	int lng = negativeAtoms.size();
	for (int i = 0; i < lng; i++ ) {
	    Atom ti = (Atom) negativeAtoms.elementAt(i);
	    HashSet vars = ti.getVariables();
	    for ( Iterator it = vars.iterator(); it.hasNext(); ) variables.add(it.next());
	}
	if ( 0 < variables.size() ) { // rename the variables
	    HashSet newVars = new HashSet();
	    for ( Iterator it = variables.iterator(); it.hasNext(); ) {
		Variable var = (Variable) it.next();
		Variable varNew = Variable.gensym(var);
		newVars.add(varNew);
		negativeAtoms = subst(negativeAtoms, var, varNew);
	    }
	    variables = newVars;
	}
	if ( 1 < lng ) { // create factors here
	    Atom neg0 = (Atom) negativeAtoms.elementAt(0);
	    for (int i = 1; i < lng; i++ ) {
		Atom atomI = (Atom) negativeAtoms.elementAt(i);
		Vector subs = neg0.unify(atomI);
		if ( null == subs ) continue;
		Atom omit = (Atom) neg0.sublis(subs);
		Vector newNegAtoms = new Vector();
		newNegAtoms.addElement(omit);
		for (int j = 1; j < lng; j++ ) {
		    Atom atomJ = (Atom) negativeAtoms.elementAt(i);
		    atomJ = (Atom) atomJ.sublis(subs);
		    if ( omit.equals(atomJ) ) continue;
		    newNegAtoms.addElement(atomJ);
		}
		factors.addElement(new NegativeClause(newNegAtoms));
	    }
	}
    }
    public String html() { 
	StringBuffer sb = new StringBuffer();
	sb.append("Negative Literals: ");
	int lng = negativeAtoms.size();
	for ( int i = 0; i < lng; i++ ) {
	    Atom atomI = (Atom) negativeAtoms.elementAt(i);
	    sb.append(atomI.html());
	}
	lng = factors.size();
	if ( 0 < lng ) {
	    sb.append("\nFactor(s):\n");
	    for ( int i = 0; i < lng; i++ ) {
		NegativeClause ng = (NegativeClause) factors.elementAt(i);
		sb.append(ng.html());
	    }
	}
	return sb.toString();
    }
    /*
      Resolution is limited to the first literal in this clause.
      The in-clause cannot be a NegativeClause.
      A resolvent that is the empty clause yields the FALSE clause.
      A FALSE can be produced also by a semantic operation like: ==(4 5).
      resolvents will return null if:
      -- a FALSE clause is generated and 
      -- true = exitWhenFalse
     */
    public Vector resolvents(Clause in, boolean exitWhenFalse) { 
	Vector out = new Vector();
	// assume that the variables are disjoint
	Atom neg0 = (Atom) negativeAtoms.elementAt(0);
	// for each positive literal of 'in' try unification 
	Vector negIn = in.getNegativeAtoms();
	Vector posIn = in.getPositiveAtoms();
	if ( null == posIn ) return out; // not supposed to happen
	int lngPos = posIn.size();
	for ( int i = 0; i < lngPos; i++ ) {
	    Atom atomI = (Atom) posIn.elementAt(i);
	    Vector subs = neg0.unify(atomI);
	    if ( null == subs ) continue;
	    Clause newClause = makeResolvent(negativeAtoms, negIn, posIn, i, subs);
	    // test here for TRUE, FALSE
	    System.out.println("newClause: \n" + newClause.html());
	    out.addElement(newClause);
	}
	return out; 
    }
    private Clause makeResolvent(Vector negativeAtoms, 
				 Vector negIn, 
				 Vector posIn, int ip, Vector subs) {
	/*
	  We have:  negativeAtoms(0)subs == posIn(i)subs
	  Thus the new negative literals are:
	  remove duplicates in
	  [negativeAtoms U  negIn ]subs -  negativeAtoms(0)subs
	  The positive literals are: 
	  [posIn]subs - negativeAtoms(0)subs with duplicates removed
	  return TRUE if a new postive literal is TRUE
	  return TRUE if a new negative literal is FALSE
	  return TRUE if a new negative literal is equal to a new positive literal
	  return FALSE if there are no literals left
	  create a negative clause if there are no positive literals, etc.
	 */
	Atom neg0 = (Atom) negativeAtoms.elementAt(0);
	Atom omit = (Atom) neg0.sublis(subs);
	Vector newNegAtoms = new Vector();
	int lng = negativeAtoms.size();
	for ( int i = 1; i < lng; i++ ) {
	    Atom atomI = (Atom) negativeAtoms.elementAt(i);
	    atomI = (Atom) atomI.sublis(subs);
	    if ( atomI instanceof BoolInt2 ) {
		BoolInt2 b2 = (BoolInt2) atomI;
		Atom truthValue = b2.eval();
		if ( truthValue == Symbol.FALSE ) return TRUEclause;
		if ( truthValue == Symbol.TRUE ) continue;
	    }
	    if ( atomI.equals(omit) ) continue;
	    int lng2 = newNegAtoms.size();
	    boolean skip = false;
	    for ( int j = 0;  j < lng2; j++ ) {
		Atom atomJ = (Atom) newNegAtoms.elementAt(j);
		if ( atomJ.equals(atomI) ) { skip = true; break; }
	    }
	    if ( skip ) continue;
	    newNegAtoms.addElement(atomI);
	}
	
	lng = ( negIn == null ? 0 : negIn.size() );
	for ( int i = 0; i < lng; i++ ) {
	    Atom atomI = (Atom) negIn.elementAt(i);
	    atomI = (Atom) atomI.sublis(subs);
	    // if ( atomI.equals(omit) ) continue; impossible
	    if ( atomI instanceof BoolInt2 ) {
		BoolInt2 b2 = (BoolInt2) atomI;
		Atom truthValue = b2.eval();
		if ( truthValue == Symbol.FALSE ) return TRUEclause;
		if ( truthValue == Symbol.TRUE ) continue;
	    }
	    int lng2 = newNegAtoms.size();
	    boolean skip = false;
	    for ( int j = 0;  j < lng2; j++ ) {
		Atom atomJ = (Atom) newNegAtoms.elementAt(j);
		if ( atomJ.equals(atomI) ) { skip = true; break; }
	    }
	    if ( skip ) continue;
	    newNegAtoms.addElement(atomI);
	}

	Vector newPosAtoms = new Vector();
	lng = posIn.size();
	for ( int i = 0; i < lng; i++ ) {
	    if ( i == ip ) continue; // ignore this one
	    Atom atomI = (Atom) posIn.elementAt(i);
	    atomI = (Atom) atomI.sublis(subs);
	    if ( atomI.equals(omit) ) continue;
	    if ( atomI instanceof BoolInt2 ) {
		BoolInt2 b2 = (BoolInt2) atomI;
		Atom truthValue = b2.eval();
		if ( truthValue == Symbol.FALSE ) continue;
		if ( truthValue == Symbol.TRUE ) return TRUEclause;
	    }
	    int lng2 = newNegAtoms.size();
	    for ( int j = 0;  j < lng2; j++ ) {
		Atom atomJ = (Atom) newNegAtoms.elementAt(j);
		if ( atomJ.equals(atomI) ) return TRUEclause;
	    }
	    lng2 = newPosAtoms.size();
	    boolean skip = false;
	    for ( int j = 0;  j < lng2; j++ ) {
		Atom atomJ = (Atom) newPosAtoms.elementAt(j);
		if ( atomJ.equals(atomI) ) { skip = true; break; }
	    }
	    if ( skip ) continue;
	    newPosAtoms.addElement(atomI);
	}
	if ( 0 == newNegAtoms.size() ) {
	    if ( 0 == newPosAtoms.size() ) return FALSEclause;
	    return new PositiveClause(newPosAtoms);
	}
	if ( 0 == newPosAtoms.size() ) return new NegativeClause(newNegAtoms);
	return new MixedClause(newNegAtoms, newPosAtoms);
    }
}
