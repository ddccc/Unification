// (C) OntoOO Inc 2005 Jan
package fol;

import java.util.*;

public class MixedClause extends Clause {
    public MixedClause(Vector negativeAtomsIn, Vector positiveAtomsIn) {
	negativeAtoms = negativeAtomsIn; 
	int lng = negativeAtoms.size();
	for (int i = 0; i < lng; i++ ) {
	    Atom ti = (Atom) negativeAtoms.elementAt(i);
	    HashSet vars = ti.getVariables();
	    for ( Iterator it = vars.iterator(); it.hasNext(); ) variables.add(it.next());
	}
	positiveAtoms = positiveAtomsIn; 
	lng = positiveAtoms.size();
	for (int i = 0; i < lng; i++ ) {
	    Atom ti = (Atom) positiveAtoms.elementAt(i);
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
		positiveAtoms = subst(positiveAtoms, var, varNew);
	    }
	    variables = newVars;
	}

    }
    public String html() { 
	StringBuffer sb = new StringBuffer();
	sb.append("\nNegative Literals: ");
	int lng = negativeAtoms.size();
	for ( int i = 0; i < lng; i++ ) {
	    Atom atomI = (Atom) negativeAtoms.elementAt(i);
	    sb.append(atomI.html());
	}
	sb.append("\nPositive Literals: ");
	lng = positiveAtoms.size();
	for ( int i = 0; i < lng; i++ ) {
	    Atom atomI = (Atom) positiveAtoms.elementAt(i);
	    sb.append(atomI.html());
	}
	return sb.toString();
    }
}
