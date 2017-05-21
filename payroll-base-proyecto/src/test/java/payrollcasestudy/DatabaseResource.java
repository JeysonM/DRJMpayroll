package payrollcasestudy;

import org.junit.rules.ExternalResource;

import payrollcasestudy.boundaries.MemoryDatabase;


public class DatabaseResource extends ExternalResource {
    protected MemoryDatabase instance;

    public void before(){
        instance = MemoryDatabase.globalPayrollDatabase;
    }

    public void after(){
        instance.clear();
    }

    public MemoryDatabase getInstance() {
        return instance;
    }
}
