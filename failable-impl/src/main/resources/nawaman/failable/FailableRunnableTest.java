package nawaman.failable;

import java.io.IOException;

import org.junit.Test;

/**
 * In case, there are future diversion between the default implementation an this implementation
 * these tests ensure that they behave as expected.
 **/
@SuppressWarnings("javadoc")
public class FailableRunnableTest {
    
    private final FailableRunnableSpec spec = new FailableRunnableSpec();
    
    @Test
    public void testRunGracefully_nonRuntimeException() {
        spec.testRunGracefully_nonRuntimeException(()->{
            throw new IOException();
        });
    }
    
    @Test
    
    public void testRunGracefully_runtimeException() {
        spec.testRunGracefully_runtimeException(()->{
            throw new NullPointerException();
        });
    }
    
}
