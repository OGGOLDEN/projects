/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Uses a list of ServiceGroups to format and write to a text file
 * @author magolden
 */
public class ServiceGroupWriter {

	/**
	 * Constructs a ServiceGroupWriter
	 */
	public ServiceGroupWriter() {
		// TODO
	}
	
	/**
	 * Formats a list of ServiceGroups in the correct form and writes the results to a file
	 * @param fileName name of file to create/overwrite
	 * @param list list of ServiceGroups
	 * @throws IllegalArgumentException with the message "Unable to save file." if the file is not found
	 */
	public static void writeServiceGroupsToFile(String fileName, List<ServiceGroup> list) {
		FileOutputStream fos = null;
		PrintWriter out = null;
		
		try {
			fos = new FileOutputStream(fileName);
            out = new PrintWriter(fos);
            
            for (ServiceGroup sg : list) {
    			out.write("# " + sg.getServiceGroupName() + "\n");
    			for (Incident i : sg.getIncidents()) {
    				out.write(i.toString());
    			}
    		}
            
            out.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
