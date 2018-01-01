//  ========================================================================
//  Copyright (c) 2017 Direct Solution Software Builders (DSSB).
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
package nawaman.failable;

import java.io.IOException;

import org.junit.Test;

import dssb.failable.FailableException;

/**
 * These test ensure that the default implementation is as expceted.
 **/
@SuppressWarnings("javadoc")
public class DefaultFailableRunnableTest {

    private final FailableRunnableSpec spec = new FailableRunnableSpec();
    
    @Test
    public void testRunGracefully_nonRuntimeException() {
        spec.testRunGracefully_nonFailableException(()->{
            throw new IOException();
        });
    }
    
    @Test
    
    public void testRunGracefully_runtimeException() {
        spec.testRunGracefully_failableException(()->{
            throw new FailableException(new NullPointerException());
        });
    }
    
}
