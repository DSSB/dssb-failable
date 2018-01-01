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
package dssb.failable;

/**
 * Failable actions.
 * 
 * @author NawaMan <nawaman@dssb.io>
 */
public class Failables {
    
    private Failables() {
    }
    
    /**
     * Failable runnable.
     *   
     * @param <T> the throwable type.
     **/
    @FunctionalInterface
    public static interface Runnable<T extends Throwable> extends Failable.Runnable<T> {
        
        /**
         * Convenient factory method to allow lambda 
         * 
         * @param runnable 
         * @return the failable runnable.
         **/
        public static <T extends Throwable> Runnable<T> of(Runnable<T> runnable) {
            return runnable;
        }
        
    }
    
    /**
     * Failable consumer.
     * 
     * @param <V>  the type of the return value.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface Supplier<V, T extends Throwable> extends Failable.Supplier<V, T> {
        
        /**
         * Convenient factory method to allow lambda
         *  
         * @param supplier the failable supplier
         * @return a failable supplier.
         **/
        public static <V, T extends Throwable> Supplier<V, T> of(Supplier<V, T> supplier) {
            return supplier;
        }
        
    }
    
    /**
     * Failable consumer. 
     * 
     * @param <V>  the value data type.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface Consumer<V, T extends Throwable> extends Failable.Consumer<V, T> {
        
        /**
         * Convenient factory method to allow lambda.
         * 
         * @param consumer  the failable consumer.
         * @return  the failable consumer.
         **/
        public static <V, T extends Throwable> Consumer<V, T> of(Consumer<V, T> consumer) {
            return consumer;
        }
        
    }
    
    /**
     * Failable function.
     * 
     * @param <V>  the input data type.
     * @param <R>  the returned data type.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface Function<V, R, T extends Throwable> extends Failable.Function<V, R, T>  {
        
        /**
         * Convenient factory method to allow lambda.
         * 
         * @param function  the failable function.
         * @return  the failable function.
         **/
        public static <V, R, T extends Throwable> Function<V, R, T> of(Function<V, R, T> function) {
            return function;
        }
    }
    
}
