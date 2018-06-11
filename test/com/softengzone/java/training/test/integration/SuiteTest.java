package com.softengzone.java.training.test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AccountServiceTest.class,
	BookmarkServiceTest.class
})
public class SuiteTest {

}
