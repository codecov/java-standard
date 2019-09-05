package codecov;

public class Index {
    public boolean uncovered_if(boolean a) {
        if (a) 
            return false;
        else
            return true;
    }   

    public boolean fully_covered() {
        return true;
    }

    public boolean uncovered() {
        return true;
    }
}
