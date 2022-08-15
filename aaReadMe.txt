File: c:/ddc/Java/fol/aaReadMe.txt
Date: Fri Sep 14 11:41:10 2018/ Tue Jun 15 17:43:02 2021

This directory contains a Java package for un-typed first order logic.
It contains a parser and theorem prover; see below for its specs.

The package has been extended with files that contain alternatives for
the (exponential) Robinson type unification algorithm, which is available
in the file Atom.java.  The alternatives are (semi) linear.

The file ZTestRUnify contains drivers for testing the Robinson version
and uses the code in Atom.java.

The file ZTestLUnify contains the Paterson-Wegman version and the
drivers for testing it.

The file ZTestBaader contains the Baader-Snyder version and the
drivers for testing it.

The file ZTest0 contains the DC version and the drivers for testing it.

The drivers have different versions to execute individual argument
pairs, groups of pairs, individual generators for pairs, groups of
generators and other combinations.  The drivers contain 'trace'
parameters that can be turned on/off.

The eight generators for argument pairs are in the file ZGen.

The file LinUnify2.pdf has a description of these unification
versions.
The published version is available at:
   https://rdcu.be/cTzTa

Installation
------------
Create a directory 'fol' and include the content of the repository.
To compile: execute in the directory fol simply: javac *.java.
To execute a driver go in a directory ABOVE fol and, for example:
    java fol.ZTestLUnify


The theorem prover
------------------
This directory contains a first order logic, un-typed, theorem prover.
Its core has a Kowalski type connection graph on which the unification
algorithm operates. The connection graph contains clauses in cnf format.
These clauses represent (optionally) axioms, theorems, lemmas and the 
negated conjecture to be proven. Repeated application of the unification
algorithm (called resolution) on complimentary literals aims to 
create a contradiction by generating an empty new clause; thereby
proving the conjecture.

A wrapper postpones using the connection graph machinery by applying
preprocessing on the conjecture, axioms, theorems and
definitions. See:
Dennis de Champeaux, "Subproblem Finder and Instance Checker, Two
Cooperating Modules for Theorem Provers", JACM,  vol 33, no 4, pp
633-657, 1986 October.
https://dl.acm.org/citation.cfm?doid=6490.6491

Files starting with "Z" show how to use the parser (text->formulas)
for generating input and how to operate the theorem prover.

