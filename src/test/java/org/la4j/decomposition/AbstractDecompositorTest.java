/*
 * Copyright 2011-2013, by Vladimir Kostyukov and Contributors.
 * 
 * This file is part of la4j project (http://la4j.org)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributor(s): -
 * 
 */

package org.la4j.decomposition;

import junit.framework.TestCase;

import org.la4j.factory.Basic1DFactory;
import org.la4j.factory.Basic2DFactory;
import org.la4j.factory.CCSFactory;
import org.la4j.factory.CRSFactory;
import org.la4j.factory.Factory;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.MockMatrix;

public abstract class AbstractDecompositorTest extends TestCase {

    public Factory[] factories() {
        return new Factory[] { new Basic1DFactory(), new Basic2DFactory(),
                new CRSFactory(), new CCSFactory() };
    }

    protected void performTest(MatrixDecompositor decompositor, double[][] input, 
        double[][][] output) {

        for (Factory factory : factories()) {

            Matrix a = factory.createMatrix(input);
            Matrix[] decomposition = a.decompose(decompositor);

            assertEquals(output.length, decomposition.length);

            for (int i = 0; i < decomposition.length; i++) {
                assertEquals(new MockMatrix(factory.createMatrix(output[i])),
                        new MockMatrix(decomposition[i]));
            }
        }
    }
}
