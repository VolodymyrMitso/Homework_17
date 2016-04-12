package mitso.v.homework_17.api.models.user;

import java.io.Serializable;

public class Company implements Serializable {

    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public String toString() {
        return  "\n---------- name = " + name +
                "\n---------- catchPhrase = " + catchPhrase +
                "\n---------- bs = " + bs;
    }
}
