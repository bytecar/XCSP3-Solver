/**
 * AbsCon - Copyright (c) 2017, CRIL-CNRS - lecoutre@cril.fr
 * 
 * All rights reserved.
 * 
 * This program and the accompanying materials are made available under the terms of the CONTRAT DE LICENCE DE LOGICIEL LIBRE CeCILL which accompanies this
 * distribution, and is available at http://www.cecill.info
 */
package org.xcsp.modeler.problems;

import org.xcsp.common.IVar.Var;
import org.xcsp.modeler.ProblemAPI;

class Riddle5 implements ProblemAPI {

	@Override
	public void model() {
		Var x[] = array("x", size(4), i -> dom(range(15)));

		forall(range(3), i -> equal(add(x[i], 1), x[i + 1]));
		sum(x, EQ, 14);
	}
}
