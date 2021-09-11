/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;
import edu.ncsu.csc216.service_wolf.model.io.ServiceGroupWriter;
import edu.ncsu.csc216.service_wolf.model.io.ServiceGroupsReader;
import edu.ncsu.csc216.service_wolf.model.service_group.ServiceGroup;

/**
 * Manages the entire ServiceWolf system. Delegates file IO, Incident behaviors, and ServiceGroup behaviors
 * @author magolden
 */
public class ServiceWolfManager {

	/** The current service group */
	private ServiceGroup currentServiceGroup;
	
	/** List of all service groups in the ServiceWolf system */
	private ArrayList<ServiceGroup> serviceGroups;
	
	/** Singleton instance of ServiceWolfManager */
	private static ServiceWolfManager singleton;
	
	/**
	 * Constructs the ServiceWolfManger
	 */
	private ServiceWolfManager() {
		// private constructor
		this.serviceGroups = new ArrayList<ServiceGroup>();
		this.currentServiceGroup = null;
	}
	
	/**
	 * Gets the Instance of the Manager
	 * @return ServiceWolfManager instance
	 */
	public static ServiceWolfManager getInstance() {
		if (ServiceWolfManager.singleton == null) {
			ServiceWolfManager.singleton = new ServiceWolfManager();
		}
		return ServiceWolfManager.singleton;
	}
	
	/**
	 * Use the ServiceGroupWriter to save the Manager to a file
	 * @param fileName name of file to be created/overwritten
	 * @throws IllegalArgumentException with the message "Unable to save file." if the current service group is null or has no incidents
	 */
	public void saveToFile(String fileName) {
		if (this.currentServiceGroup == null || this.currentServiceGroup.getIncidents().size() == 0) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		ServiceGroupWriter.writeServiceGroupsToFile(fileName, this.serviceGroups);
	}
	
	/**
	 * Use the ServiceGroupReader to load a Manager from a file
	 * @param fileName name of file to be processed
	 */
	public void loadFromFile(String fileName) {
		ArrayList<ServiceGroup> unsortedSG = ServiceGroupsReader.readServiceGroupsFile(fileName);
		
		for (int i = 0; i < unsortedSG.size(); i++) {
			this.addServiceGroupToListByName(unsortedSG.get(i));
		}
		
		ServiceGroup firstSG = unsortedSG.get(0);
		this.loadServiceGroup(firstSG.getServiceGroupName());
		this.currentServiceGroup = firstSG;
	}
	
	/**
	 * Returns a 2D array of Incidents with an id number, state, title, and status details
	 * @return IncidentArray array of Incidents
	 */
	public String[][] getIncidentsAsArray() {
		if (this.currentServiceGroup == null) {
			return null;
		}
		
		int totalIncidents = 0;
		
		for (int i = 0; i < this.currentServiceGroup.getIncidents().size(); i++) {
			totalIncidents++;
		}
		
		String[][] incidentArray = new String[totalIncidents][4];
		int j = 0;
		for (Incident i : this.currentServiceGroup.getIncidents()) {
			incidentArray[j][0] = Integer.toString(i.getId());
			incidentArray[j][1] = i.getState();
			incidentArray[j][2] = i.getTitle();
			incidentArray[j][3] = i.getStatusDetails();
			j++;
		}
		
		return incidentArray;
	}
	
	/**
	 * Gets an Incident using its unique id number
	 * @param id id number of target incident
	 * @return Incident found
	 */
	public Incident getIncidentById(int id) {
		if (this.currentServiceGroup == null) {
			return null;
		}
		
		return this.currentServiceGroup.getIncidentById(id);
	}
	
	/**
	 * Executes a command on a specified incident
	 * @param id id of incident to execute command on
	 * @param command command to execute
	 */
	public void executeCommand(int id, Command command) {
		try {
			this.currentServiceGroup.executeCommand(id, command);
		} catch (NullPointerException e) {
			// do nothing
		}
	}	
	
	/**
	 * Deleted a specified incident
	 * @param id id of incident to be deleted
	 */
	public void deleteIncidentById(int id) {
		try {
			this.currentServiceGroup.deleteIncidentById(id);
		} catch (NullPointerException e) {
			// do nothing
		}
	}
	
	/**
	 * Adds a new Incident to a ServiceGroup
	 * @param title title of Incident
	 * @param caller caller of Incident
	 * @param message Incident message
	 * @throws IllegalArgumentException with the message "No service group selected." is the current service group is null
	 */
	public void addIncidentToServiceGroup(String title, String caller, String message) {
		if (this.currentServiceGroup == null) {
			throw new IllegalArgumentException("No service group selected.");
		}
		
		Incident incident = new Incident(title, caller, message);
		this.currentServiceGroup.addIncident(incident);
	}
	
	/**
	 * Sets a specified ServiceGroup to the currentServiceGroup
	 * @param name name of ServieGroup to make current
	 * @throws IllegalArgumentException with the message "No service group selected." is the current service group is null
	 */
	public void loadServiceGroup(String name) {
		if (this.currentServiceGroup == null) {
			throw new IllegalArgumentException("No service group selected.");
		}
		
		for (int i = 0; i < this.serviceGroups.size(); i++) {
			if (this.serviceGroups.get(i).getServiceGroupName().equals(name)) {
				this.currentServiceGroup = this.serviceGroups.get(i);
				this.currentServiceGroup.setIncidentCounter();
			}
		}
		
		if (!this.currentServiceGroup.getServiceGroupName().equals(name)) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		
	}
	
	/**
	 * Returns the ServiceGroupName
	 * @return serviceGroupName name of currentServiceGroup
	 */
	public String getServiceGroupName() {
		if (this.currentServiceGroup == null) {
			return null;
		}
		
		return this.currentServiceGroup.getServiceGroupName();
	}
	
	/**
	 * Returns a list of all ServiceGroup Names
	 * @return serviceGroupArray list of names
	 */
	public String[] getServiceGroupList() {
		String[] sgList = new String[this.serviceGroups.size()];
		for (int i = 0; i < sgList.length; i++) {
			sgList[i] = this.serviceGroups.get(i).getServiceGroupName();
		}
		return sgList;
	}
	
	/**
	 * Clears all service groups and sets the currentServiceGroup to null
	 */
	public void clearServiceGroups() {
		this.serviceGroups = new ArrayList<ServiceGroup>();
		this.currentServiceGroup = null;
	}
	
	/**
	 * Updates the name of a ServiceGroup
	 * @param updateName new ServiceGroup name
	 * @throws IllegalArgumentException with the message "No service group selected." is the current service group is null
	 * @throws IllegalArgumentException with the message "Invalid service group name." if updateName is null or empty
	 */
	public void editServiceGroup(String updateName) {
		if (updateName == null || "".equals(updateName)) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		
		checkDuplicateServiceName(updateName);
		
		if (this.currentServiceGroup == null) {
			throw new IllegalArgumentException("No service group selected.");
		}
		
		ServiceGroup sg = this.currentServiceGroup;
		this.serviceGroups.remove(sg);
		sg.setServiceGroupName(updateName);
		addServiceGroup(updateName);
		loadServiceGroup(updateName);
	}
	
	/**
	 * Adds a specified ServiceGroup to the ServiceGroupList
	 * @param sg ServiceGroup to add
	 */
	private void addServiceGroupToListByName(ServiceGroup sg) {
		checkDuplicateServiceName(sg.getServiceGroupName());
		
		int oldSize = this.serviceGroups.size();
		
		ServiceGroup tempSG = null;
		int i = 0;
		while(i < oldSize && oldSize == this.serviceGroups.size()) {
			if (this.serviceGroups.get(i).getServiceGroupName().compareTo(sg.getServiceGroupName()) > 0) {
				tempSG = this.serviceGroups.get(i);
				this.serviceGroups.set(i, sg);
				this.serviceGroups.add(i + 1, tempSG);
			}
			i++;
		}
		
		if (this.serviceGroups.size() == oldSize) {
			this.serviceGroups.add(sg);
		}
		
		this.currentServiceGroup = sg;
		this.loadServiceGroup(sg.getServiceGroupName());
	}
	
	/**
	 * Adds a new ServiceGroup to the ServiceGroup list
	 * @param name name of ServiceGroup
	 * @throws IllegalArgumentException with the message "Invalid service group name." if the service group name
	 * 		   is null, empty, or a duplicate
	 */
	public void addServiceGroup(String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		
		checkDuplicateServiceName(name);
		
		ServiceGroup sg = new ServiceGroup(name);
		int oldSize = this.serviceGroups.size();
		
		ServiceGroup tempSG = null;
		int i = 0;
		while(i < oldSize && oldSize == this.serviceGroups.size()) {
			if (this.serviceGroups.get(i).getServiceGroupName().compareTo(sg.getServiceGroupName()) > 0) {
				tempSG = this.serviceGroups.get(i);
				this.serviceGroups.set(i, sg);
				this.serviceGroups.add(i + 1, tempSG);
			}
			i++;
		}
		
		if (this.serviceGroups.size() == oldSize) {
			this.serviceGroups.add(sg);
		}
		
		this.currentServiceGroup = sg;
		loadServiceGroup(name);
	}
	
	/**
	 * Determines of two ServiceGroups have the same name
	 * @param name name of ServiceGroup to compare
	 * @throws IllegalArgumentException with the message "Invalid service group name." is the new name is a duplicate
	 */
	private void checkDuplicateServiceName(String name) {
		for (int i = 0; i < this.serviceGroups.size(); i++) {
			if (this.serviceGroups.get(i).getServiceGroupName().equals(name)) {
				throw new IllegalArgumentException("Invalid service group name.");
			}
		}
	}
	
	/**
	 * Deletes the currentServiceGroup and sets the currentServiceGroup to the first serviceGroup in the list
	 * @throws IllegalArgumentException with the message "No service group selected." is the current service group is null
	 */
	public void deleteServiceGroup() {
		if (this.currentServiceGroup == null) {
			throw new IllegalArgumentException("No service group selected.");
		}
		
		try {
			this.serviceGroups.remove(this.serviceGroups.indexOf(this.currentServiceGroup));
		} catch (IndexOutOfBoundsException e) {
			// do nothing
		}
			
		if (this.serviceGroups.size() == 0) {
			this.currentServiceGroup = null;
		} else {
			this.currentServiceGroup = this.serviceGroups.get(0);
		}
	}
	
	/**
	 * Resets the single ServiceWolfManger instance
	 */
	protected void resetManager() {
		ServiceWolfManager.singleton = null;
	}
}
