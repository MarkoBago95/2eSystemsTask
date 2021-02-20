package metar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import app.Subscriptions;
import metar.Metar;

@Entity
@Table(name = "metar")
public class Metar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airport_id")
	private Integer airport_id;
	

	@Column(name = "metar_value")
	private String metar_value;
	
	@Column(name ="icaoM")
	private String icaoM;
	
	@Column(name ="name")
	private String name;

	@Column(name ="temperature")
	private Integer temperature;
	
	@Column(name ="wind")
	private Integer wind;
	
	@Column(name ="visibility")
	private String visibility;
	
	@Column(name ="elevation")
	private Integer elevation;
	public Metar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getAirport_id() {
		return airport_id;
	}

	public void setAirport_id(Integer airport_id) {
		this.airport_id = airport_id;
	}

	public String getMetar_value() {
		return metar_value;
	}

	public void setMetar_value(String metar_value) {
		this.metar_value = metar_value;
	}

}
