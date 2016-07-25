import java.util.ArrayList;
/**
 * 
 * @author Shubham
 *
 */
public class Data6 {
	
	ArrayList<Double> attributes;
	int id;

	Data6() {
		attributes = new ArrayList<Double>();
		id = -1;
	}
	
	//for initializing
	Data6(double a[]) {
		attributes = new ArrayList<Double>();
		id = (int) a[0];
		// a[0] is the guest ID
		for (int i = 1; i < a.length; i++) {
			attributes.add(a[i]);
		}
	}
	//to print
	public String toString() {
		return "" + this.id +attributes+"\n";
	}
}
