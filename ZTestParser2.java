// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.*;

public class ZTestParser2 {

    public static void main (String [] arguments) {

	Parser parser = new Parser(true);

	Formula out = parser.parse2(
		 "(eq ?t &&(LaunchedPageAdvice(?t AngelRoot) <(?t 309) ))"
		 );
	if ( null == out ) {
	    System.out.println("parse2 failed: out == null");
	    return;
	}
	System.out.println("out: " + out.html());

    } // end of main
    
} // end class ZTestParser2


