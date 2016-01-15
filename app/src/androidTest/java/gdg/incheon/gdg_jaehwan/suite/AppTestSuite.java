package gdg.incheon.gdg_jaehwan.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import gdg.incheon.gdg_jaehwan.ApplicationTest;
import gdg.incheon.gdg_jaehwan.MainActivityTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationTest.class, MainActivityTest.class})
public class AppTestSuite {}