// (C) OntoOO Inc 2005 Jan
package fol;

// import java.util.*;
import java.io.Serializable;

public class ProofStep implements Serializable {
    protected Formula input;
    protected Atom result; // Atom.UNKNOWN or TRUE or FALSE
    protected String description;
    ProofStep(Formula input, Atom result, String description) {
	this.input = input; 
	this.result = result; 
	this.description = description;
    }
    public Formula getInput() { return input; }
    public Atom getResult() { return result; }
    public String getDescription() { return description; }

    public String html() { return html("ProofStep::"); }
    public String html(String prefix) { 
	StringBuffer sb = new StringBuffer(prefix + " \n");
	sb.append("Input: " + input.html() + "\n");
	sb.append("Result: " + result.html() + "\n");
	sb.append("Description: " + description + "\n");
	return sb.toString();
    } 
}
