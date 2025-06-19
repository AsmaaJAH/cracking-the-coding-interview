/*
7.2 Call Center: Imagine you have a call center with three levels of employees: respondent, manager,
and director. An incoming telephone call must be first allocated to a respondent who is free. If the
respondent can't handle the call, he or she must escalate the call to a manager. If the manager is not
free or not able to handle it, then the call should be escalated to a director. Design the classes and
data structures for this problem. Implement a method dispatchCall () which assigns a call to
the first available employee.
 */
package ch7_oop_design;
import java.util.*;

public class CallCenter2 {

    public enum Rank {
        RESPONDENT, MANAGER, DIRECTOR     // employee levels
    }

    public static class Call {
        private Rank rank;
        private String callerName;

        public Call(String callerName) {
            this.rank = Rank.RESPONDENT; // start at lowest level
            this.callerName = callerName;
        }

        public Rank getRank() {
            return rank;
        }

        public void escalate() {
            if (rank == Rank.RESPONDENT) {
                rank = Rank.MANAGER;
            } else if (rank == Rank.MANAGER) {
                rank = Rank.DIRECTOR;
            }
        }

        public String getCallerName() {
            return callerName;
        }
    }

    public static abstract class Employee {
        private boolean free = true;
        protected Rank rank;

        public boolean isFree() {
            return free;
        }

        public void setFree(boolean free) {
            this.free = free;
        }

        public Rank getRank() {
            return rank;
        }

        public abstract boolean canHandle(Call call);

        public void receiveCall(Call call) {
            setFree(false);
            System.out.println("Handling call from " + call.getCallerName());
        }

        public void finishCall() {
            setFree(true);
        }
    }

    public static class Respondent extends Employee {
        public Respondent() {
            this.rank = Rank.RESPONDENT;
        }

        @Override
        public boolean canHandle(Call call) {
            return call.getRank() == Rank.RESPONDENT;
        }
    }

    public static class Manager extends Employee {
        public Manager() {
            this.rank = Rank.MANAGER;
        }

        @Override
        public boolean canHandle(Call call) {
            return call.getRank() == Rank.MANAGER;
        }
    }

    public static class Director extends Employee {
        public Director() {
            this.rank = Rank.DIRECTOR;
        }

        @Override
        public boolean canHandle(Call call) {
            return call.getRank() == Rank.DIRECTOR;
        }
    }

    public static class CallHandler {
        private List<Respondent> respondents;
        private List<Manager> managers;
        private List<Director> directors;

        public CallHandler(int numRespondents, int numManagers, int numDirectors) {
            respondents = new ArrayList<>();
            managers = new ArrayList<>();
            directors = new ArrayList<>();

            for (int i = 0; i < numRespondents; i++) respondents.add(new Respondent());
            for (int i = 0; i < numManagers; i++) managers.add(new Manager());
            for (int i = 0; i < numDirectors; i++) directors.add(new Director());
        }

        public void dispatchCall(Call call) {
            Employee emp = getAvailableEmployee(call.getRank());
            if (emp != null) {
                emp.receiveCall(call);
                return;
            }

            // escalate and try again
            call.escalate();
            emp = getAvailableEmployee(call.getRank());
            if (emp != null) {
                emp.receiveCall(call);
                return;
            }

            call.escalate();
            emp = getAvailableEmployee(call.getRank());
            if (emp != null) {
                emp.receiveCall(call);
                return;
            }

            System.out.println("All employees are busy. Call from " + call.getCallerName() + " is waiting.");
        }

        private Employee getAvailableEmployee(Rank rank) {
            switch (rank) {
                case RESPONDENT:
                    for (Respondent r : respondents) {
                        if (r.isFree()) return r;
                    }
                    break;
                case MANAGER:
                    for (Manager m : managers) {
                        if (m.isFree()) return m;
                    }
                    break;
                case DIRECTOR:
                    for (Director d : directors) {
                        if (d.isFree()) return d;
                    }
                    break;
            }
            return null;
        }
    }
}
