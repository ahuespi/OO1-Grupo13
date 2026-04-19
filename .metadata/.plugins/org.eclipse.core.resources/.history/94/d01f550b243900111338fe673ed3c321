package model;

import java.util.Objects;

public class Doctor {

	private String firstName;
	private String lastName;
	private String speciality;
	
	public Doctor(String firstName, String lastName, String speciality) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.speciality = speciality;
	}
	
	// -------- Methods --------
	
	public String getFullName() {
		return firstName + ", " + lastName + ". | Especialidad: " + speciality;
	}
	
	public float calculateIMC(Patient Patient) {
		float height = Patient.getHeight();
		float weight = Patient.getWeight();
		return weight / (height * height);
	}

	

	// --------- Polimorfismo --------------
	@Override
	public String toString() {
		return "Doctor [firstName=" + firstName + ", lastName=" + lastName + ", speciality=" + speciality
				+ ", getFullName()=" + getFullName() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getSpeciality()=" + getSpeciality() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	

	// -------- Getters and Setters ---------
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	
}
