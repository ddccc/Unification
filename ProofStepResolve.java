// (C) OntoOO Inc 2005 Jan
package fol;

// import java.util.*;

public class ProofStepResolve extends ProofStep {
    protected ProofStep proofStep;
    ProofStepResolve(Formula input, Atom result, ProofStep proofStep) {
	super(input, result, "Resolution result");
	this.proofStep = proofStep;
    }
    public ProofStep getProofStep() { return  proofStep; }
    public String html() {
	StringBuffer sb = new StringBuffer(super.html("ProofStepResolve::"));
	sb.append("Resolution \n");
	sb.append(proofStep.html());
	return sb.toString();
    }
} // end ProofStepResolve
