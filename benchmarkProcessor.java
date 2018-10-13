import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class benchmarkProcessor {
	public static final  String[] titles = {"TOTAL ASSETS", "TOTAL LIABILITIES"};
	public static String path = "C:/Users/UserA/workspace/PayPal Hack/src/Industries";
	
    public static void main(String[] args) throws FileNotFoundException {
    	File folder = new File(path);
    	File[] listOfFiles = folder.listFiles();
    	System.out.println("Available Data by Industry:");
    	for(int i = 0; i< listOfFiles.length; i++)
    		System.out.println((i+1) + ". " + listOfFiles[i].getName());
    	System.out.println("Select an Industry: ");
    	Scanner sc = new Scanner(System.in);
    	int ind = sc.nextInt();
    	sc.close();
    	if(ind < 0 || ind > listOfFiles.length){
    		System.out.println("Not an Option. Try Again");
    		main(args);
    	}else{ //Now take anticipations for titleValues, normalize those values, and compare them to the bell curve
    		String industry = "/" + listOfFiles[ind-1].getName(); 
    		path += industry;
    		folder = new File(path);
        	listOfFiles = folder.listFiles();
    	}
    	
    	double [][] titleValues = new double[listOfFiles.length][titles.length];
    	double [] titleNorms = new double[listOfFiles.length];
    	//double [] titleAverages = new double[titles.length];
    	
    	for (int i = 0; i < listOfFiles.length; i++)
    	   titleValues[i] = scanCSV(path + "/" + listOfFiles[i].getName());
    	
    	/* Blind Averaging
		for(int j = 0; j < titles.length; j++ ){
    		double sum = 0.0;
    		int i = 0;
    		
    		while (i < titleValues.length){
    			sum += titleValues[i][j];
    			i++;
    		}
    		titleAverages[j] = sum/i;
    	}
		*/
		
    	for(int j=0; j < titles.length; j++){
			
			for(int i=0; i< titleValues.length; i++){
				titleNorms[i] = titleValues[i][j];
			}
			
			titleNorms = insertionSort(titleNorms);
			double min = titleNorms[0];
			double max = titleNorms[titleNorms.length-1]; 
			for(int k=0;k<titleNorms.length;k++){ //normalize all data
				double numerator = titleNorms[k] - min;
				double denominator = max - min;
				titleNorms[k] = numerator / denominator;
			}
			
			for(int i=0; i< titleValues.length; i++){
				System.out.println(titleNorms[i]);
				titleValues[i][j] = titleNorms[i];
			}//data is now normalized
			
		}
    	/* Blind Average Print
    	for(int i=0; i<titles.length; i++){
    		System.out.printf(titles[i] + " Average: " + "$%.2f\n", titleAverages[i]);
    	}
    	*/
    }

    public static double[] scanCSV(String path) throws FileNotFoundException{
    	String myCSV = "";
    	double[] values = new double[titles.length];
    	Scanner scanner = new Scanner(new File(path));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            myCSV += scanner.next();
        }
        myCSV = myCSV.toUpperCase();
        int i = 0;
        for (String title : titles){
	        int index1 = myCSV.indexOf(title) + title.length();
	        index1 += myCSV.indexOf(",", index1) + 3;
	        int index2 = myCSV.indexOf("\"", index1+5);
	        String t_ARS = myCSV.substring(index1, index2).trim();
	        System.out.println(t_ARS);
	        BigDecimal t_AR = new BigDecimal(t_ARS);
	        values[i] = t_AR.doubleValue();
	        i++;
        }
        scanner.close();
        return values;
    }
    
    public static double[] insertionSort(double[] array){
    	double temp;
    	for (int i = 1; i < array.length; i++) {
    		for (int j = i; j > 0; j--) {
    			if (array[j] < array [j - 1]) {
		    		temp = array[j];
		    	    array[j] = array[j - 1];
		    	    array[j - 1] = temp;
		    	}
    		}
    	}
    	return array;
    }
}