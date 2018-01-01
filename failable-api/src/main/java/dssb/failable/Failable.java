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
public class Failable {
    
    private Failable() {
    }
    
    /**
     * Failable runnable.
     *   
     * @param <T> the throwable type.
     **/
    @FunctionalInterface
    public static interface Runnable<T extends Throwable> {
        
        /**
         * Run this runnable.
         * 
         * @throws T the thrown exception.
         **/
        public void run() throws T;
        
        
        /**
         * Change to regular runnable.
         * 
         * @return Java's Runnable.
         **/
        public default java.lang.Runnable toRunnable() {
            return gracefully();
        }
        
        /**
         * Convert to a regular runnable and throw FailableException if there is an exception.
         * 
         * @return Java's Runnable.
         **/
        public default java.lang.Runnable gracefully() {
            return () -> {
                try {
                    run();
                } catch (FailableException t) {
                    throw t;
                } catch (Throwable t) {
                    throw new FailableException(t);
                }
            };
        }
        
        /**
         * Convert to a regular runnable that completely ignore the exception throw from it.
         * 
         * @return Java's Runnable.
         **/
        public default java.lang.Runnable carelessly() {
            return () -> {
                try {
                    run();
                } catch (Throwable t) {
                }
            };
        }
        
    }
    
    /**
     * Failable consumer.
     * 
     * @param <V>  the type of the return value.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface Supplier<V, T extends Throwable> {
        
        /**
         * Run this supplier. 
         * 
         * @return  the result value.
         * @throws T the thrown exception.
         **/
        public V get() throws T;
        
        /**
         * Convert to a regular supplier and throw FailableException if there is an exception.
         *  
         * @return  Java Supplier.
         **/
        public default java.util.function.Supplier<V> toSupplier() {
            return gracefully();
        }
        
        /**
         * Convert to a regular supplier and throw FailableException if there is an exception. 
         * 
         * @return   Java Supplier.
         **/
        public default java.util.function.Supplier<V> gracefully() {
            return () -> {
                try {
                    return get();
                } catch (FailableException t) {
                    throw t;
                } catch (Throwable t) {
                    throw new FailableException(t);
                }
            };
        }
        
        /**
         * Convert to a regular supplier that completely ignore the exception throw from it. 
         * 
         * @return   Java Supplier.
         **/
        public default java.util.function.Supplier<V> carelessly() {
            return () -> {
                try {
                    return get();
                } catch (Throwable t) {
                    return null;
                }
            };
        }
    }
    
    /**
     * Failable consumer. 
     * 
     * @param <V>  the value data type.
     * @param <T>  the type of the thrown exception.
     **/
    @FunctionalInterface
    public static interface Consumer<V, T extends Throwable> {
        
        /**
         * Run this consumer. 
         * 
         * @param value the accepted value.
         * @throws T  the thrown exception.
         **/
        public void accept(V value) throws T;
        
        /**
         * Convert to a regular consumer and throw FailableException if there is an exception.
         * 
         * @return  Java Consumer.
         **/
        public default java.util.function.Consumer<V> toConsumer() {
            return gracefully();
        }
        
        /**
         * Convert to a regular consumer and throw FailableException if there is an exception. 
         * 
         * @return  Java Consumer.
         **/        
        public default java.util.function.Consumer<V> gracefully() {
            return v -> {
                try {
                    accept(v);
                } catch (FailableException t) {
                    throw t;
                } catch (Throwable t) {
                    throw new FailableException(t);
                }
            };
        }
        
        /**
         * Convert to a regular consumer that completely ignore the exception throw from it. 
         * 
         * @return  Java Consumer.
         **/
        public default java.util.function.Consumer<V> carelessly() {
            return v -> {
                try {
                    accept(v);
                } catch (Throwable t) {
                }
            };
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
    public static interface Function<V, R, T extends Throwable> {
        
        /**
         * Run this function. 
         * 
         * @param value  the input value.
         * @return  the returned value.
         * @throws T  the thrown exception.
         **/
        public R apply(V value) throws T;
        
        /**
         * Convert to a regular function and throw FailableException if there is an exception. 
         * 
         * @return  Java Function.
         **/
        public default java.util.function.Function<V, R> toFunction() {
            return gracefully();
        }
        
        /**
         * Convert to a regular function and throw FailableException if there is an exception. 
         * 
         * @return  Java Function.
         **/        
        public default java.util.function.Function<V, R> gracefully() {
            return v -> {
                try {
                    return apply(v);
                } catch (FailableException t) {
                    throw t;
                } catch (Throwable t) {
                    throw new FailableException(t);
                }
            };
        }
        
        /**
         * Convert to a regular function that completely ignore the exception throw from it. 
         * 
         * @return  Java Function.
         **/
        public default java.util.function.Function<V, R> carelessly() {
            return v -> {
                try {
                    return apply(v);
                } catch (Throwable t) {
                    return null;
                }
            };
        }
    }
    
}
