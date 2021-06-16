// (C) OntoOO Inc 2005 Jan
package fol;

// import java.util.*;

public class ProofStepAtom extends ProofStep {
    ProofStepAtom(Atom input, Atom result, String description) {
	super(input, result, description);
    }
    public String html() {
	return super.html("ProofStepAtom::");
    }
} // end ProofStepAtom
