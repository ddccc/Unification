// (C) OntoOO Inc 2005 Jan
package fol;

import java.util.*;

public class ProofStepAnd extends ProofStep {
    protected Vector proofSteps;
    ProofStepAnd(Conjunction input, Atom result, 
		 Vector proofSteps) {
	super(input, result, "Independent conjuncts");
	this.proofSteps = proofSteps;
    }
    public Vector getProofSteps() { return  proofSteps; }
    public String html() {
	StringBuffer sb = new StringBuffer(super.html("ProofStepAnd::"));
	int lng = proofSteps.size();
	for ( int i = 0; i < lng; i++ ) {
	    ProofStep ps = (ProofStep) proofSteps.elementAt(i);
	    sb.append("Argument #: " + i + "\n");
	    sb.append(ps.html());
	}
	return sb.toString();
    }


}
