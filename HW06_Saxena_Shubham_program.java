import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Shubham Saxena
 *
 */
public class HW06_Saxena_Shubham_program {

	public static void main(String[] args) {
		try {

			ArrayList<Data6> dataList = new ArrayList<Data6>();

			//taking input
			Scanner sc = new Scanner(new File(args[0]));
			sc.nextLine();

			while (sc.hasNextLine()) {
				Data6 dataRow = new Data6();
				String a[] = sc.nextLine().split(",");
				double row[] = new double[13];

				for (int i = 0; i < a.length; i++) {
					row[i] = Double.parseDouble(a[i].trim());
					dataRow = new Data6(row);
				}
				dataList.add(dataRow);
			}
			HW06_Saxena_Shubham_program hw6 = new HW06_Saxena_Shubham_program();

			// number of iterations to be performed. 
			//size of total elements - number of clusters needed.
			int iterations = dataList.size() - Integer.parseInt(args[1]);
			hw6.cluster(dataList, iterations);

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		}
	}

	
	/**
	 * This function performs clustering
	 * @param dataList : input data
	 * @param iterations : number of times the loops should run to give desired number of clusters. 
	 */
	void cluster(ArrayList<Data6> dataList, int iterations) {
		ArrayList<Cluster> clusterList = new ArrayList<Cluster>();

		// initializing the clusters.
		for (Data6 d : dataList) {
			ArrayList<Data6> cl = new ArrayList<Data6>();
			cl.add(d);
			clusterList.add(new Cluster(d, cl, d.id));
		}

		for (int k = 0; k < iterations; k++) {
			double minEdist = Double.MAX_VALUE;

			Cluster c1 = new Cluster();
			Cluster c2 = new Cluster();
			// finding closest two clusters.
			for (Cluster clust1 : clusterList) {
				for (Cluster clust2 : clusterList) {
					if (clust1 != clust2) {
						double temp = clust1.eucDist(clust2);
						if (temp <= minEdist) {
							minEdist = temp;
							c1 = clust1;
							c2 = clust2;
						}
					}
				}
			}

			Cluster merge = new Cluster(new Data6(new double[12]), new ArrayList<Data6>());
			// add from list 1
			for (Data6 d : c1.members) {
				merge.members.add(d);
			}
			// add from list 2
			for (Data6 d : c2.members) {
				merge.members.add(d);
			}
			// recalculate the new mean of cluster
			merge.recalculateClusterCenter();
			// inherit new cluster ID
			if (c1.id < c2.id) {
				merge.id = c1.id;
			} else {
				merge.id = c2.id;
			}
			// modifying clusters
			clusterList.remove(c2);
			clusterList.remove(c1);
			clusterList.add(merge);
		}
		System.out.println(clusterList);
	}
}
