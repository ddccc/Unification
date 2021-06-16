// (C) OntoOO Inc 2005 Jan
package fol;

// import java.util.*;

public class ProofStepDefinition extends ProofStep {
    protected ProofStep proofStep;
    ProofStepDefinition(Atom input, Atom result, ProofStep proofStep) {
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
} // end ProofStepDefinition
