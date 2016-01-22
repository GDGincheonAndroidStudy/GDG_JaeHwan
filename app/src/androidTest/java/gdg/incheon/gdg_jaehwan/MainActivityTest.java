package gdg.incheon.gdg_jaehwan;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gdg.incheon.gdg_jaehwan.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    public static final String STRING_TO_BE_TYPED = "chicken";

    private static final String LAST_ITEM_ID = "item: 15";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void test() {
        //텍스트 타이핑
        onView(withId(R.id.edit_keyword))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());

        //텍스트 변경 확인
        onView(withId(R.id.edit_keyword)).check(matches(withText(STRING_TO_BE_TYPED)));

        //검색 클릭
        onView(withId(R.id.btn_search)).perform(ViewActions.click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
