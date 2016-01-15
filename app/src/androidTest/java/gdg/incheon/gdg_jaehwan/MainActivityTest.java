package gdg.incheon.gdg_jaehwan;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{

    private MainActivity activity;

    @Mock
    private Resources mockResources;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
        MockitoAnnotations.initMocks(mockResources);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void loadStringResource() throws Exception {
        String appName = activity.getResources().getString(R.string.app_name);
        assertThat(appName, is("GDG_JaeHwan"));
    }

    @Test
    public void loadMockStringResource() throws Exception {
        when(mockResources.getString(R.string.app_name)).thenReturn("GDG_JaeHwan");
        String appName = mockResources.getString(R.string.app_name);
        assertThat(appName, is("GDG_JaeHwan"));
    }

    @Test
    public void testOnCreate() throws Exception {

    }

    @Test
    public void testOnDestroy() throws Exception {

    }
}