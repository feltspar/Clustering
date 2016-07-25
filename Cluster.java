import java.util.ArrayList;

/**
 * 
 * @author Shubham
 *
 */
public class Cluster {
	int id;
	//mean
	Data6 clusterCenter;
	//data rows of guests
	ArrayList<Data6> members;

	Cluster() {
		clusterCenter = new Data6();
		members = new ArrayList<Data6>();
	}

	Cluster(Data6 clusterCenter, ArrayList<Data6> members) {	
		this.clusterCenter = clusterCenter;
		this.members = members;
	}

	Cluster(Data6 clusterCenter, ArrayList<Data6> members, int id) {	
		this.clusterCenter = clusterCenter;
		this.members = members;
		this.id = id;
	}
	
	/**
	 * //for new mean for newly formed cluster 
	 * @return
	 */
	public Data6 recalculateClusterCenter() {
		double[] centerArr = new double[12];
		// adding
		for (Data6 d : members) {
			for (int i = 0; i < d.attributes.size(); i++) {
				centerArr[i] += d.attributes.get(i);
			}
		}
		
		// dividing
		for (int i = 0; i < centerArr.length; i++) {
			centerArr[i] /= members.size();
		}
		
		Data6 center = new Data6(centerArr);
		this.clusterCenter = center;
		return center;
		
	}
	
	/**
	 * //calculates the Euclidean distance.
	 * @param c cluster to be compared with
	 * @return
	 */
	public double eucDist(Cluster c){
		Data6 myCenter = recalculateClusterCenter();
		Data6 otherCenter = c.recalculateClusterCenter();
		double eDist = 0.0;
		for (int i = 0; i < myCenter.attributes.size(); i++) {
			eDist = eDist + Math.pow((double) myCenter.attributes.get(i) - (double) otherCenter.attributes.get(i), 2);
		}
		eDist = Math.sqrt(eDist);
		return eDist;
	}

	
	/**
	 * printing 
	 */
	public String toString() {
		return "Cluster size:" +members.size()+"\n"+ members + "\n"+ "cl cent:"+ this.clusterCenter;// "center:" + clusterCenter + "; members: "
									// + members + "\n";
	}
}
