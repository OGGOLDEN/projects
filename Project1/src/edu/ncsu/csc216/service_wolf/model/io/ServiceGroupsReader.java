/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.io;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Processes a file to get the ServiceGroups and associated Incidents from a text file
 * @author magolden
 */
public class ServiceGroupsReader {

	/**
	 * Constructor of ServiceGroupReader
	 */
	public ServiceGroupsReader() {
		// TODO
	}
	
	/**
	 * Returns an ArrayList of ServiceGroups from a given text file
	 * @param fileName filename to be processed
	 * @return list of service groups
	 * @throws IllegalArgumentException with the message "Unable to load file." if the file is not found
	 */
	public static ArrayList<ServiceGroup> readServiceGroupsFile(String fileName) {
		Scanner scan = null;
		ArrayList<ServiceGroup> serviceGroupList = new ArrayList<ServiceGroup>();
		String file = "";
		try {
			scan = new Scanner(new FileInputStream(fileName));
			while (scan.hasNextLine()) {
				file += scan.nextLine() + "\n";
			}
			
			if (file.charAt(0) != '#') {
				return serviceGroupList;
			}
			
			Scanner line = new Scanner(file);
			Scanner serviceGroupToken = null;
			serviceGroupToken = line.useDelimiter("\\r?\\n?[#]");
			
			while (serviceGroupToken.hasNext()) {
				String serviceGroup = serviceGroupToken.next().trim();
				ServiceGroup sg = processServiceGroup(serviceGroup);
				serviceGroupList.add(sg);
			}
			line.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		scan.close();
		
		for (int i = 0; i < serviceGroupList.size(); i++) {
			if (serviceGroupList.get(i) == null) {
				serviceGroupList.remove(i);
			}
		}
		
		return serviceGroupList;
	}
	
	/**
	 * Processes a string of ServiceGroups and converts it to a ServiceGroup object
	 * @param serviceGroup String of ServiceGroups
	 * @return ServiceGroup read from file
	 */
	private static ServiceGroup processServiceGroup(String serviceGroup) {
		Scanner scan = new Scanner(serviceGroup);
		String sgName = scan.nextLine();
		sgName = sgName.substring(0);
		ServiceGroup sg = new ServiceGroup(sgName);
		
		scan.useDelimiter("\\r?\\n?[*]");
		while (scan.hasNextLine()) {
			String incident = scan.next().trim();
			Incident i = processIncident(incident);
			try {
				sg.addIncident(i);
			} catch (IllegalArgumentException e) {
				// ignore
				scan.close();
				
				return null;
			}
		}
		
		scan.close();
		return sg;
	}
	
	/**
	 * Processes a string of Incidents and converts it to an Incident object
	 * @param incident String of Incidents
	 * @return Incident read from file
	 */
	private static Incident processIncident(String incident) {
		Scanner scan = new Scanner(incident);
		String incidentString = scan.nextLine();
		incidentString = incidentString.substring(0);
		ArrayList<String> incidentLog = new ArrayList<String>();
		while (scan.hasNextLine()) {
			String logMessages = scan.nextLine();
			logMessages = logMessages.substring(2);
			incidentLog.add(logMessages);
		}
		scan.close();
		
		Scanner i = new Scanner(incidentString);
		i.useDelimiter(",");
		int id = -1;
		try {
			String idString = i.next();
			id = Integer.valueOf(idString);
		} catch (NumberFormatException | IllegalStateException e) {
			// ignore
			i.close();
		}
		
		String state = null;
		String title = null;
		String caller = null;
		try {
			state = i.next();
			title = i.next();
			caller = i.next();
		} catch (NoSuchElementException | IllegalStateException e) {
			// ignore
			i.close();
		}
		
		int reopenCount = -1;
		try {
			String reopenString = i.next();
			reopenCount = Integer.valueOf(reopenString);
		} catch (NumberFormatException | IllegalStateException | NoSuchElementException e) {
			// ignore
			i.close();
		}
		
		String owner = null;
		String statusDetails = null;
		try {
			owner = i.next();
			statusDetails = i.next();
		} catch (NoSuchElementException | IllegalStateException e) {
			// ignore
			i.close();
		}
		i.close();
		
		Incident newIncident = null;
		try {
			newIncident = new Incident(id, state, title, caller, reopenCount, owner, statusDetails, incidentLog);
		} catch (IllegalArgumentException e) {
			return newIncident;
		}
		return newIncident;
	}
}
