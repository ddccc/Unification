// (C) OntoOO Inc 2017 Mar
package fol;

// import java.util.*;

public class ProofStepSubstitution extends ProofStep {
    protected ProofStep proofStep;
    ProofStepSubstitution(Atom input, Atom result, ProofStep proofStep) {
	super(input, result, "Definition expansion");
	this.proofStep = proofStep;
    }
    public ProofStep getProofStep() { return  proofStep; }
    public String html() {
	StringBuffer sb = new StringBuffer(super.html("ProofStepAnd::"));
	sb.append("Expansion \n");
	sb.append(proofStep.html());
	return sb.toString();
    }
} // end ProofStepSubstitution
