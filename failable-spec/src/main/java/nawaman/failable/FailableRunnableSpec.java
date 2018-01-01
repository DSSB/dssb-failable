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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import dssb.failable.Failable;
import dssb.failable.FailableException;

/**
 * This spec helps document the desired behaviours of any Failable runnable.
 * 
 * @author manusitn
 */
public class FailableRunnableSpec {
    
    //== gracefully() and toRunnable() =
    
    /**
     * When called with 'gracefully()' the runnable throw failable if the body throws a non-runtime exception.
     * 
     * @param runnable  the runnable.
     */
    public void testRunGracefully_nonRuntimeException(Failable.Runnable<Throwable> runnable) {
        test_nonRuntimeException(runnable, ()->runnable.gracefully().run());
        test_nonRuntimeException(runnable, ()->runnable.toRunnable().run());
    }
    
    /**
     * When called with 'gracefully()' the runnable throw the runtime if the body throws a exception.
     * 
     * @param runnable  the runnable.
     */
    public void testRunGracefully_runtimeException(Failable.Runnable<Throwable> runnable) {
        test_runtimeException(runnable, ()->runnable.gracefully().run());
        test_runtimeException(runnable, ()->runnable.toRunnable().run());
    }
    
    
    
    /**
     * When called with 'gracefully()' the runnable throw failable if the body throws a non-runtime exception.
     * 
     * @param runnable  the runnable.
     */
    private void test_nonRuntimeException(Failable.Runnable<Throwable> runnable, Runnable testBody) {
        precondition_theRunnableMustThrowANonRuntimeException(runnable);
        
        specification_whenRunGrafullyRunnableThrowFailException(runnable, testBody);
    }
    
    private void precondition_theRunnableMustThrowANonRuntimeException(Failable.Runnable<Throwable> runnable) {
        // Check to make sure the runnable throw some exception that is not a FailableException.
        try {
            runnable.run();
            fail("Precondision rejected: The runnable is expected to throw an exception for this test to make sense.");
        } catch (FailableException e) {
            fail("Precondision rejected: The runnable throw a fail exception so we cannot be sure if that was from the implementation of 'gracefully'");
        } catch (RuntimeException e) {
            fail("Precondision rejected: The runnable throw a runtime exception - this is not a focus for this test");
        } catch (Throwable e) {
            // This is expected.
        }
    }
    
    private void specification_whenRunGrafullyRunnableThrowFailException(Failable.Runnable<Throwable> runnable, Runnable testBody) {
        // == Implementation ==
        // The thrown exception should be converted to a failable exception.
        try {
            testBody.run();
            fail("The runnable is expected to throw an exception for this test to make sense.");
        } catch (FailableException e) {
            // This is expected.
        } catch (Throwable e) {
            fail("Expect a failable exception to be thrown not anything else.");
        }
    }
    
    

    /**
     * When called with 'gracefully()' the runnable throw the runtime if the body throws a exception.
     * 
     * @param runnable  the runnable.
     */
    private void test_runtimeException(Failable.Runnable<Throwable> runnable, Runnable testBody) {
        RuntimeException exception = precondition_theRunnableMustThrowARuntimeException(runnable);
        
        specification_whenRunGrafullyRunnableThrowTheException(runnable, testBody, exception);
    }
    
    private RuntimeException precondition_theRunnableMustThrowARuntimeException(Failable.Runnable<Throwable> runnable) {
        // Check to make sure the runnable throw some exception that is not a FailableException.
        try {
            runnable.run();
            fail("Precondision rejected: The runnable is expected to throw an exception for this test to make sense.");
        } catch (FailableException e) {
            fail("Precondision rejected: The runnable throw a fail exception so we cannot be sure if that was from the implementation of 'gracefully'");
        } catch (RuntimeException e) {
            // This is expected.
            return e;
        } catch (Throwable e) {
            fail("Precondision rejected: The runnable throw a runtime exception - this is not a focus for this test");
        }
        return null;
    }
    
    private void specification_whenRunGrafullyRunnableThrowTheException(Failable.Runnable<Throwable> runnable, Runnable testBody, RuntimeException exception) {
        // == Implementation ==
        // The thrown exception should be converted to a failable exception.
        try {
            testBody.run();
            fail("The runnable is expected to throw an exception for this test to make sense.");
        } catch (RuntimeException e) {
            // This is expected.
            assertEquals(exception.getMessage(),       e.getMessage());
            assertEquals(exception.getClass(),         e.getClass());
            assertEquals(exception.getStackTrace()[0], e.getStackTrace()[0]);
        } catch (Throwable e) {
            fail("Expect a runtime exception to be thrown not anything else.");
        }
    }
    
    //== toRunnable ==
    
}
