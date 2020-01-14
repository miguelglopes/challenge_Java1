package Model;

/*
 * This class didn't need to exist. PhoneNumber could simply be a string.
 * However, for future proof, I decided to create it.
 * For instance, in the future we may want to create a method getCountryCode();
 */
public class PhoneNumber {
    String id;

    public PhoneNumber(String id) {
        this.id = id;
    }

    //this method needs to be overridden in order to be able to use it as a key in a HashMap
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    //this method needs to be overridden in order to be able to use it as a key in a HashMap
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        else if (!PhoneNumber.class.isAssignableFrom(o.getClass()))
            return false;
        else {
            PhoneNumber fn = (PhoneNumber) o;
            if (fn.id == null)
                return false;
            return this.id.equals(fn.id);
        }
    }
}
