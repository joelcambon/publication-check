package test;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocalMemcacheTest {

    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalMemcacheServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    // run this test twice to prove we're not leaking any state across tests
    private void doTest() {
        MemcacheService ms = MemcacheServiceFactory.getMemcacheService();
        assertFalse(ms.contains("yar"));
        ms.put("yar", "foo");
        assertTrue(ms.contains("yar"));
    }

    @Test
    public void testInsert1() {
        doTest();
    }

    @Test
    public void testInsert2() {
        doTest();
    }
}