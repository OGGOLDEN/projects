/**
 * 
 */
package edu.ncsu.csc216.service_wolf.model.service_group;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.service_wolf.model.command.Command;
import edu.ncsu.csc216.service_wolf.model.incident.Incident;

/**
 * Represents a ServiceGroup in the system, Each ServiceGroup has a name and a list of Incidents
 * @author magolden
 */
public class ServiceGroup {

	/** Name of service group */
	private String serviceGroupName;
	
	/** List of Incidents */
	private ArrayList<Incident> incidentList;
	
	/**
	 * Constructs a ServiceGroup with a specified name
	 * @param name name of service group
	 * @throws IllegalArgumentException with the message "Invalid service group name." if name is null or empty
	 */
	public ServiceGroup(String name) {
		setServiceGroupName(name);
		this.incidentList = new ArrayList<Incident>();
	}
	
	/**
	 * Sets the incident counter to 1 more than the highest id in the ServiceGroup
	 */
	public void setIncidentCounter() {
		int size = this.incidentList.size();
		Incident maxIncident = null;
		if (size == 0) {
			Incident.setCounter(1);
		} else {
			maxIncident = this.incidentList.get(size - 1);
			int maxId = maxIncident.getId();
			Incident.setCounter(maxId + 1);
		}
	}
	
	/**
	 * Sets the ServiceGroupName
	 * @param name name to call the ServiceGroup
	 * @throws IllegalArgumentException with the message "Invalid service group name." if name is null or empty
	 */
	public void setServiceGroupName(String name) {
		if (name == null || "".equals(name.trim())) {
			throw new IllegalArgumentException("Invalid service group name.");
		}
		this.serviceGroupName = name;
	}
	
	/**
	 * Returns the ServiceGroup's name
	 * @return serviceGroupName name of ServiceGroup
	 */
	public String getServiceGroupName() {
		return this.serviceGroupName;
	}
	
	/**
	 * Adds an Incident to the ServiceGroup
	 * @param incident incident to be added
	 * @throws IllegalArgumentException with the message "Incident cannot be created." if an Incident with the given id
	 * 		   already exists in the list or the incident is null
	 */
	public void addIncident(Incident incident) {
		if (incident == null) {
			throw new IllegalArgumentException("Incident cannot be created.");
		}
		
		if (this.incidentList.size() == 0) {
			this.incidentList.add(incident);
		} else if (incident.getId() > this.incidentList.get(this.incidentList.size() - 1).getId()) {
			this.incidentList.add(incident);
		} else if(incident.getId() < this.incidentList.get(0).getId()) {
			this.incidentList.add(0, incident);
		} else {
			for (int i = 0; i < this.incidentList.size(); i++) {
				if (incident.getId() == this.incidentList.get(i).getId()) {
					throw new IllegalArgumentException("Incident cannot be created.");
				}
			}
			
			for (int j = 0; j < this.incidentList.size() - 1; j++) {
				int currentId = this.incidentList.get(j).getId();
				int nextId = this.incidentList.get(j + 1).getId();
				
				if (currentId < incident.getId() && incident.getId() < nextId) {
					this.incidentList.add(j + 1, incident);
				}
			}
		}
	}
	
	/**
	 * Returns a list of all Incidents in the ServiceGroup
	 * @return list list of Incidents
	 */
	public List<Incident> getIncidents() {
		return this.incidentList;
	}
	
	/**
	 * Uses unique id numbers to get an Incident from the ServiceGroup
	 * @param id id of target incident
	 * @return incident incident in ServiceGroup
	 */
	public Incident getIncidentById(int id) {
		for (int i = 0; i < this.incidentList.size(); i++) {
			if (id == this.incidentList.get(i).getId()) {
				return this.incidentList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Executes a command on the specified incident
	 * @param id id of Incident being transitioned
	 * @param command command to be executed
	 * @throws IllegalArgumentException with the message "Command cannot be null." if the command is null
	 */
	public void executeCommand(int id, Command command) {
		if (command == null) {
			throw new IllegalArgumentException("Command cannot be null.");
		}
		getIncidentById(id).update(command);
	}
	
	/**
	 * Deletes a given Incident
	 * @param id id of Incident to be deleted
	 */
	public void deleteIncidentById(int id) {
		for (int i = 0; i < this.incidentList.size(); i++) {
			if (id == this.incidentList.get(i).getId()) {
				this.incidentList.remove(i);
			}
		}
	}
}
