package nawaman.failable;

import java.io.IOException;

import org.junit.Test;

/**
 * These test ensure that the default implementation is as expceted.
 **/
@SuppressWarnings("javadoc")
public class DefaultFailableRunnableTest {

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
