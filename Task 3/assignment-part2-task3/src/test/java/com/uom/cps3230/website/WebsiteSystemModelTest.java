package com.uom.cps3230.website;

import com.uom.cps3230.website.enums.WebsiteStateEnum;
import junit.framework.Assert;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.util.Random;

public class WebsiteSystemModelTest implements FsmModel {
    WebsiteSystem sut = new WebsiteSystem();

    WebsiteStateEnum stateEnum = WebsiteStateEnum.LOGGED_OUT;
    boolean loggedIn = false;
    boolean viewingAlerts = false;

    @Override
    public WebsiteStateEnum getState() {
        return stateEnum;
    }

    @Override
    public void reset(boolean b) {
        if (b) {
            sut = new WebsiteSystem();
        }
        loggedIn = false;
        viewingAlerts = false;
        stateEnum = WebsiteStateEnum.LOGGED_OUT;
    }

    public boolean loginGuard() {return getState().equals(WebsiteStateEnum.LOGGED_OUT);}
    public @Action void login() {
        sut.login();

        stateEnum = WebsiteStateEnum.LOGGED_IN;
        loggedIn = true;

        Assert.assertEquals(loggedIn, sut.isLoggedIn());
        Assert.assertTrue(sut.isLoggedIn());
    }

    public boolean viewAlertsGuard() {return getState().equals(WebsiteStateEnum.LOGGED_IN);}
    public @Action void viewAlerts() {
        sut.viewAlerts();

        stateEnum = WebsiteStateEnum.LOGGED_IN;
        loggedIn = true;
        viewingAlerts = true;

        Assert.assertEquals(loggedIn, sut.isLoggedIn());
        Assert.assertEquals(viewingAlerts, sut.isViewingAlerts());
    }

    public boolean logOutGuard() {return getState().equals(WebsiteStateEnum.LOGGED_IN);}
    public @Action void logOut() {
        sut.logOut();

        stateEnum = WebsiteStateEnum.LOGGED_OUT;
        loggedIn = false;
        viewingAlerts = false;

        Assert.assertEquals(loggedIn, sut.isLoggedIn());
        Assert.assertEquals(viewingAlerts, sut.isViewingAlerts());
    }

    //Test runner
    @Test
    public void WebsiteSystemModelRunner() {
        final GreedyTester tester = new GreedyTester(new WebsiteSystemModelTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.
        tester.generate(500); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }
}
