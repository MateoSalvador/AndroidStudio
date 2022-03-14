package ec.epn.detri.awm.ppq;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


//import androidx.test.espresso.action.ViewActions.click;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;

@RunWith(AndroidJUnit4.class)
public class MainAcitivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMensajeErrorNoMostradoInicialmente(){
        onView(withId(R.id.lblMessage)).check(ViewAssertions.matches((not(ViewMatchers.isDisplayed()))));
    }

    @Test
    public void testPlacaPrivadaInvalidaDisparaMensajeError(){
        String p = "PCY-123\n";
        onView(withId(R.id.txtPlaca)).perform(typeText(p));
        onView(withId(R.id.btnConsultar)).perform(click());

        onView(withId(R.id.lblMessage))
                //.check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("Placa privada incorrecta")));
    }

    @Test
    public void testPlacaPrivadavalida(){
        String p = "PCY-1234\n";
        onView(withId(R.id.txtPlaca)).perform(typeText(p));
        onView(withId(R.id.btnConsultar)).perform(click());

        onView(withId(R.id.lblMessage))
                //.check(ViewAssertions.matches(isDisplayed()))
                .check(ViewAssertions.matches(withText("Placa privada correcta")));
    }
}
