package com.uom.cps3230.alerts;

import com.uom.cps3230.alerts.enums.AlertsStateEnum;
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

public class AlertsSystemModelTest implements FsmModel {
    AlertsSystem sut = new AlertsSystem();

    AlertsStateEnum stateEnum = AlertsStateEnum.EMPTY;
    boolean alertsEmpty = true;


    @Override
    public AlertsStateEnum getState() {
        return stateEnum;
    }

    @Override
    public void reset(boolean b) {
        if (b) {
            sut = new AlertsSystem();
        }
        alertsEmpty = true;
        stateEnum = AlertsStateEnum.EMPTY;
    }

    public @Action void createAlert() {
        sut.createAlert();

        stateEnum = AlertsStateEnum.NOT_EMPTY;
        alertsEmpty = false;

        Assert.assertEquals(alertsEmpty, sut.isAlertsEmpty());
    }

    public @Action void deleteAlerts() {
        sut.deleteAlerts();

        stateEnum = AlertsStateEnum.EMPTY;
        alertsEmpty = true;

        Assert.assertEquals(alertsEmpty, sut.isAlertsEmpty());
    }

    //Test runner
    @Test
    public void AlertsSystemModelRunner() {
        final GreedyTester tester = new GreedyTester(new AlertsSystemModelTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
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
